package interpreter.virtualmachine;

import com.sun.jdi.connect.ListeningConnector;

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

    public int popFrame() {return framePointer.pop();}

    public int getCurrentFrameSize()
    {
        int currentSize = runTimeStack.size() - framePointer.peek();
        return currentSize;
    }

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
        ArrayList<Integer> framePtr = new ArrayList<>(framePointer);
        StringBuilder      result   = new StringBuilder();

        if (framePtr.size() == 1)
        {
            result.append(runTimeStack);
            System.out.println(runTimeStack);
            return;
        }
        // This deals with anything greater than 1
        for (int i = 0; i < framePtr.size() - 1; i++)
        {
            try
            {
                result.append(runTimeStack.subList(framePtr.get(i), framePtr.get(i + 1)))
                        .append(" ");

            } catch (Exception e)
            {
            }
        }
        if(framePointer.get(framePointer.size()-1) == runTimeStack.size() + 1)
        {
            result.append("[]");
            System.out.println(result);
            return;
        }
        result.append(runTimeStack.subList(framePtr.get(framePtr.size()-1), runTimeStack.size()));
        System.out.println(result);
    }

    public int getSize()
    {
        return runTimeStack.size();
    }

    public int getFrameListSize()
    {
        return framePointer.size();
    }

    // Helpers:
    private int getLastIndex() {return runTimeStack.size() - 1;}

    private int getLastFrame() {return framePointer.peek();}
}
