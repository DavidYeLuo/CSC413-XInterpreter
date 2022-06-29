package interpreter.virtualmachine;

import interpreter.bytecode.*;

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
    public static final int POP_CLEAN_FRAME   = Integer.MAX_VALUE;
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

            // Quick and dirty implementation
            // Could use a hashmap to improve performance here.
            if (isDumpModeOn)
            {
                if (currentCode instanceof Dumpable)
                {
                    String dump = ((Dumpable) currentCode).dump();
                    System.out.println(dump);
                }

                // Shouldn't print these.
                if (currentCode.getClass() == HaltCode.class ||
                        currentCode.getClass() == DumpCode.class)
                {
                    // Move on to the next ByteCode
                    programCounter++;
                    continue;
                }
                runTimeStack.dump();
            }

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
                if (runTimeStack.getFrameListSize() <= 0) return ERROR_RETURN_CODE;
                return runTimeStack.popFrame();
            } else if (desiredAmount == VirtualMachine.PEEK_RUNTIMESTACK)
            {
                return runTimeStack.peek();
            } else
            {
                // Shouldn't happen
                System.out.println("Error: Popcode shouldn't get here.");
                maximumPop = this.getCurrentFrameSize();
                popNumDecided = maximumPop;
            }
        } else
        {
            if(runTimeStack.getFrameListSize() <= 0) return ERROR_RETURN_CODE;
            maximumPop = this.getCurrentFrameSize();
            popNumDecided = VirtualMachine.getValueBetweenZeroAndMax(desiredAmount, maximumPop);

            if (popNumDecided <= 0) return ERROR_RETURN_CODE;
        }

        for (int i = 0; i < popNumDecided - 1; i++)
        {
            runTimeStack.pop();
        }
        return runTimeStack.pop();
    }

    public void setProgramCounter(int index)
    {
        if (index < 0) return;
        programCounter = index;
    }

    public void store(int desiredOffset)
    {
        // -1 because offset starts at 0
        int maxOffset = runTimeStack.getCurrentFrameSize() - 1;
        int offset    = VirtualMachine.getValueBetweenZeroAndMax(desiredOffset, maxOffset);

        runTimeStack.store(offset);
    }

    public void load(int desiredOffset)
    {
        if (runTimeStack.getSize() == 0) return;
        // -1 because offset starts at 0
        int maxOffset = this.getCurrentFrameSize() - 1;
        int offset = VirtualMachine.getValueBetweenZeroAndMax(desiredOffset, maxOffset);

        runTimeStack.load(offset);
    }

    public void push(int value) {runTimeStack.push(value);}

    public void newFrameAt(int desiredOffset)
    {
        // -1 because offset starts at 0
        int maxOffset = runTimeStack.getCurrentFrameSize() - 1;
        int offset    = VirtualMachine.getValueBetweenZeroAndMax(desiredOffset, maxOffset);

        runTimeStack.newFrameAt(offset);
    }

    public int getProgramCounter()                   {return programCounter;}

    public void pushReturnAddress(int returnAddress) {this.returnAddress.push(returnAddress);}

    public int popReturnAddress()
    {
        if (returnAddress.isEmpty()) return ERROR_RETURN_CODE;
        return this.returnAddress.pop();
    }

    public void setDumpMode(boolean mode) {isDumpModeOn = mode;}

    // Helpers
    private int getCurrentFrameSize()
    {
        int size = runTimeStack.getCurrentFrameSize();
        return size < 0? 0 : size;
    }

    /**
     * Checks if the number is a pop command and not a number.
     * @param number
     * @return
     */
    private static boolean isPopCommand(int number)
    {
        return number == VirtualMachine.PEEK_RUNTIMESTACK || number == VirtualMachine.POP_FRAME;
    }

    /**
     * Gives a value from 0 to maxValue
     * If the value is less than 0 then return 0
     * If the value is greater than maxValue then return maxValue
     * @param value Will be treated as a natural number
     * @param maxValue Will be treated as natural number
     * @return
     */
    private static int getValueBetweenZeroAndMax(int value, int maxValue)
    {
        if (value < 0) value = 0;
        if (maxValue < 0) maxValue = 0;
        if (value > maxValue) return maxValue;

        return value;
    }
}
