package interpreter.virtualmachine;

import interpreter.bytecode.ByteCode;

import java.util.Stack;

public class VirtualMachine
{
    private RunTimeStack   runTimeStack;
    private Stack<Integer> returnAddress;
    private Program        program;
    private int            programCounter;
    private boolean        isRunning;
    private boolean        isDumpModeOn;

    /**
     * This is a flag for the pop methods to pop the current frame.
     */
    public static final int POP_CURRENT_FRAME = Integer.MIN_VALUE + 31455;

    /**
     * This is a flag for the pop methods to peek the current frame. <p>
     * Saves us an instruction and it is better performant than using
     * two commands: pop and then push
     */
    public static final int PEEK_RUNTIMESTACK = Integer.MIN_VALUE + 41399;

    public VirtualMachine(Program program)
    {
        runTimeStack   = new RunTimeStack();
        returnAddress  = new Stack<>();
        this.program   = program;
        programCounter = 0;
        isRunning      = false;
        isDumpModeOn   = false;
    }

    public void executeProgram()
    {
        isRunning = true;

        ByteCode currentCode;
        while(isRunning)
        {
            currentCode = program.getCode(programCounter);
            currentCode.execute(this);

            programCounter++;
        }
    }

    // Code below are ByteCodeRequests
    public void halt() {isRunning = false;}

    /**
     * To pop the stack:
     * Set the desiredAmount for the amount of pops.
     * <p>
     * To pop the current frame:
     * set the desiredAmount as VirualMachine.POP_CURRENT_STACK
     * <p>
     * NOTE: This will also clear frame boundary!
     *
     * To peek the top of the run time stack:
     * set the desiredAmount as VirtualMachine.POP_PEEK_STACKFRAME
     *
     * @param desiredAmount
     * @return returns the latests pop value
     */
    public int pop(int desiredAmount)
    {
        int maximumPop = runTimeStack.getCurrentFrameSize();
        int popNumDecided; // the amount of pop decided

        boolean isCommand = VirtualMachine.isPopCommand(desiredAmount);
        if(isCommand)
        {
            // Check for special commands
            if (desiredAmount == VirtualMachine.POP_CURRENT_FRAME)
            {
                popNumDecided = maximumPop;
                runTimeStack.popFrame();
            }
            else if(desiredAmount == VirtualMachine.PEEK_RUNTIMESTACK)
            {
                return runTimeStack.peek();
            }
            else
            {
                // Shouldn't happen
                System.out.println("Error: Popcode shouldn't get here.");
                popNumDecided = maximumPop;
            }
        }
        else
        {
            if (maximumPop < 1) return Integer.MIN_VALUE; // This shouldn't be allowed to happen.

            popNumDecided = Math.min(desiredAmount, maximumPop);
            if (popNumDecided < 1) return Integer.MIN_VALUE; // This shouldn't be allowed to happen
        }

        for (int i = 0; i < popNumDecided - 1; i++)
        {
            runTimeStack.pop();
        }
        return runTimeStack.pop();
    }

    public void setProgramCounter(int index) {programCounter = index;}

    public void store(int desiredOffset)
    {
        if (desiredOffset < 0) return;

        int maxOffset = runTimeStack.getCurrentFrameSize();
        if (maxOffset < 1) return;

        int offset = Math.min(desiredOffset, maxOffset);
        if (offset < 1) return;

        runTimeStack.store(offset);
    }

    public void load(int desiredOffset)
    {
        if (desiredOffset < 0) return;

        int maxOffset = runTimeStack.getCurrentFrameSize();
        if (maxOffset < 1) return;

        int offset = Math.min(desiredOffset, maxOffset);
        if (offset < 1) return;

        runTimeStack.load(offset);
    }

    public void push(int value) {runTimeStack.push(value);}

    public void newFrameAt(int desiredOffset)
    {
        if (desiredOffset < 0) return;

        int maxOffset = runTimeStack.getCurrentFrameSize();
        if (maxOffset < 1) return;

        int offset = Math.min(desiredOffset, maxOffset);
        if (offset < 1) return;

        runTimeStack.newFrameAt(offset);
    }

    public int getProgramCounter()                   {return programCounter;}

    public void pushReturnAddress(int returnAddress) {this.returnAddress.push(returnAddress);}

    public int popReturnAddress()                    {return this.returnAddress.pop();}
    public void setDumpMode(boolean mode) { isDumpModeOn = mode; }

    private static boolean isPopCommand(int number)
    {
        return number == VirtualMachine.PEEK_RUNTIMESTACK
                || number == VirtualMachine.POP_CURRENT_FRAME;
    }
}
