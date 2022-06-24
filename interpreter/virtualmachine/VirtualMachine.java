package interpreter.virtualmachine;

import java.util.Stack;

public class VirtualMachine
{

    private RunTimeStack   runTimeStack;
    private Stack<Integer> returnAddress;
    private Program        program;
    private int            programCounter;
    private boolean        isRunning;
    private boolean        isDumpModeOn;

    public VirtualMachine(Program program)
    {
        runTimeStack   = new RunTimeStack();
        returnAddress  = new Stack<>();
        this.program   = program;
        programCounter = 0;
        isRunning      = true;
        isDumpModeOn   = false;
    }

    public void executeProgram()
    {
        System.out.println("executeProgram() isn't inplemented in VirtualMachine.");
    }

    // Code below are ByteCodeRequests
    public void halt() { isRunning = false; }
    public int pop(int desiredAmount)
    {
       int maximumPop = runTimeStack.getFrameSize();
       if(maximumPop < 1) return Integer.MIN_VALUE; // This shouldn't be allowed to happen.

       int popNum = Math.min(desiredAmount, maximumPop);
       if(popNum < 1) return Integer.MIN_VALUE; // This shouldn't be allowed to happen.

       for(int i = 0; i < popNum - 1; i++)
       {
           runTimeStack.pop();
       }
       return runTimeStack.pop();
    }

    public void goTo(int index) { programCounter = index; }
    public void store(int desiredOffset)
    {
        if(desiredOffset < 0) return;

        int maxOffset = runTimeStack.getFrameSize();
        if(maxOffset < 1) return;

        int offset = Math.min(desiredOffset, maxOffset);
        if(offset < 1) return;

        runTimeStack.store(offset);
    }
    public void load(int desiredOffset)
    {
        if(desiredOffset < 0) return;

        int maxOffset = runTimeStack.getFrameSize();
        if(maxOffset < 1) return;

        int offset = Math.min(desiredOffset, maxOffset);
        if(offset < 1) return;

        runTimeStack.load(offset);
    }

    public void push(int value) { runTimeStack.push(value); }
    public void newFrameAt(int desiredOffset)
    {
        if(desiredOffset < 0) return;

        int maxOffset = runTimeStack.getFrameSize();
        if(maxOffset < 1) return;

        int offset = Math.min(desiredOffset, maxOffset);
        if(offset < 1) return;

        runTimeStack.newFrameAt(offset);
    }
}
