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
    public static final int POP_FRAME = Integer.MIN_VALUE + 31455;

    /**
     * This is a flag for the pop methods to peek the current frame. <p>
     * Saves us an instruction and it is better performant than using
     * two commands: pop and then push
     */
    public static final int PEEK_RUNTIMESTACK = Integer.MIN_VALUE + 41399;
    public static final int POP_CLEAN_FRAME = Integer.MAX_VALUE;
    public static final int ERROR_RETURN_CODE = Integer.MIN_VALUE;

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
        while (isRunning)
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
     * set the desiredAmount as VirualMachine.POP_STACK
     * <p>
     * NOTE: This will also clear frame boundary!
     * <p>
     * To peek the top of the run time stack:
     * set the desiredAmount as VirtualMachine.POP_PEEK_STACKFRAME
     *
     * @param desiredAmount
     * @return returns the latests pop value
     */
    public int pop(int desiredAmount)
    {
        int maximumPop;
        int popNumDecided; // the amount of pop decided

        boolean isCommand = VirtualMachine.isPopCommand(desiredAmount);
        if (isCommand)
        {
            // Check for special commands
            if (desiredAmount == VirtualMachine.POP_FRAME)
            {
                // Doesn't handle cleaning!
                if(runTimeStack.getFrameListSize() <= 0) return ERROR_RETURN_CODE;
                return runTimeStack.popFrame();
            } else if (desiredAmount == VirtualMachine.PEEK_RUNTIMESTACK)
            {
                return runTimeStack.peek();
            } else
            {
                // Shouldn't happen
                System.out.println("Error: Popcode shouldn't get here.");
                maximumPop = runTimeStack.getCurrentFrameSize();
                popNumDecided = maximumPop;
            }
        } else
        {
            if(runTimeStack.getFrameListSize() <= 0) return ERROR_RETURN_CODE;
            maximumPop = runTimeStack.getCurrentFrameSize();
            popNumDecided = VirtualMachine.getValueBetweenZeroAndMax(desiredAmount, maximumPop);

            if(popNumDecided <= 0) return ERROR_RETURN_CODE;
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
        // -1 because offset starts at 0
        int maxOffset = runTimeStack.getCurrentFrameSize() - 1;
        int offset = VirtualMachine.getValueBetweenZeroAndMax(desiredOffset, maxOffset);

        runTimeStack.store(offset);
    }

    public void load(int desiredOffset)
    {
        // -1 because offset starts at 0
        int maxOffset = runTimeStack.getCurrentFrameSize() - 1;
        int offset = VirtualMachine.getValueBetweenZeroAndMax(desiredOffset, maxOffset);
        if(offset <= 0) offset = 0;

        runTimeStack.load(offset);
    }

    public void push(int value) {runTimeStack.push(value);}

    public void newFrameAt(int desiredOffset)
    {
        // -1 because offset starts at 0
        int maxOffset = runTimeStack.getSize() - 1;
        int offset = VirtualMachine.getValueBetweenZeroAndMax(desiredOffset, maxOffset);

        runTimeStack.newFrameAt(offset);
    }

    public int getProgramCounter()                   {return programCounter;}

    public void pushReturnAddress(int returnAddress) {this.returnAddress.push(returnAddress);}

    public int popReturnAddress()                    {
        if(returnAddress.isEmpty()) return ERROR_RETURN_CODE;
        return this.returnAddress.pop();
    }

    public void setDumpMode(boolean mode)            {isDumpModeOn = mode;}

    // Helpers
    private static boolean isPopCommand(int number)
    {
        return number == VirtualMachine.PEEK_RUNTIMESTACK || number == VirtualMachine.POP_FRAME;
    }
    private static int getValueBetweenZeroAndMax(int value, int maxValue)
    {
        if(value < 0) return 0;
        if(value > maxValue) return maxValue;

        return value;
    }
}
