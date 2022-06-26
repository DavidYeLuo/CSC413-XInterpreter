package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RunTimeStack
{
    private static boolean DEBUG = false;

    private List<Integer>  runTimeStack;
    private Stack<Integer> framePointer;

    public RunTimeStack()
    {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // Add initial Frame Pointer, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }

    // RunTimeStack methods
    public int peek() {return runTimeStack.get(getLastIndex());}

    public void push(int num) {runTimeStack.add(num);}

    public int pop()          {return runTimeStack.remove(getLastIndex());}

    // FrameStack Methods
    public void newFrameAt(int offset) {framePointer.push(getLastIndex() + 1 - offset);}

    public int popFrame()            {return framePointer.pop();}

    public int getCurrentFrameSize() {return runTimeStack.size() - framePointer.peek();}

    /**
     * Takes the value located offset amount above the current frame pointer
     * and push the value to the top of the stack.
     *
     * @param offset
     * @return value that is loaded
     */
    public int load(int offset)
    {
        int loadValue = runTimeStack.get(framePointer.peek() + offset);
        runTimeStack.add(loadValue); // Push the value to the top of the stack
        return loadValue;
    }

    /**
     * Takes the top of the stack and place it at an offset above the current frame pointer
     *
     * @param offset
     * @return value that is stored
     */
    public int store(int offset)
    {
        int storeValue = runTimeStack.get(getLastIndex()); // Top of the stack
        runTimeStack.set(framePointer.peek() + offset, storeValue);
        return storeValue;
    }

    public void dump()
    {
        // Needed to not change the state of the RunTimeStack
        Stack<Integer> pointerListClone = (Stack<Integer>) framePointer.clone();

        if (DEBUG)
        {
            System.out.printf("FrameStack: %s\n", runTimeStack);
            System.out.printf("Pointerstack: %s\n", pointerListClone);
        }

        // Build a list to help print
        ArrayList<ArrayList<Integer>> frameList = new ArrayList<>();
        ArrayList<Integer>            frame     = new ArrayList<>();

        if (runTimeStack.isEmpty())
        {
            System.out.println("[]");
            return;
        }

        int pointer = pointerListClone.pop();

        // Makes sense to process backward because of stack data structure
        for (int i = runTimeStack.size() - 1; i >= 0; i--)
        {
            frame.add(0, runTimeStack.get(i));

            if (i == pointer)
            {
                frameList.add(0, frame);
                frame = new ArrayList<>();
                if (pointerListClone.isEmpty()) continue;
                pointer = pointerListClone.pop();
            }
        }

        // Build a String to print
        StringBuilder runTimeStackStringBuilder = new StringBuilder();
        int           lastFrame                 = frameList.size() - 1;
        int           lastIndex;
        for (int _frame = 0; _frame <= lastFrame; _frame++)
        {
            // Update lastIndex
            lastIndex = frameList.get(_frame).size() - 1;

            runTimeStackStringBuilder.append("[");
            for (int frameIndex = 0; frameIndex <= lastIndex; frameIndex++)
            {
                runTimeStackStringBuilder.append(frameList.get(_frame).get(frameIndex));

                if (frameIndex != lastIndex)
                {
                    runTimeStackStringBuilder.append(",");
                }
            }
            runTimeStackStringBuilder.append("]");

            if (_frame != lastFrame)
            {
                runTimeStackStringBuilder.append(" ");
            }
        }
        if (DEBUG)
        {
            System.out.printf("Raw Data: %s\n", frameList);
        }

        // Print
        System.out.println(runTimeStackStringBuilder);
    }

    // Helpers:
    private int getLastIndex() {return runTimeStack.size() - 1;}

    private int getLastFrame() {return framePointer.peek();}
}
