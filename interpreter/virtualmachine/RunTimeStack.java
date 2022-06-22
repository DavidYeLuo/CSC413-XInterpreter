package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RunTimeStack {

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
    public int peek() { return runTimeStack.get(getLastIndex()); }
    public void push(int num) { runTimeStack.add(num); }
    public int pop() { return runTimeStack.remove(getLastIndex()); }

    // FrameStack Methods
    public void newFrameAt(int offset) { framePointer.push( getLastIndex() - offset); }
    public int popFrame() { return framePointer.pop(); }

    /**
     * Takes the value located offset amount above the current frame pointer
     * and push the value to the top of the stack.
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
     * @param offset
     * @return value that is stored
     */
    public int store(int offset)
    {
        int storeValue = runTimeStack.get(getLastIndex()); // Top of the stack
        runTimeStack.set(framePointer.peek() + offset, storeValue);
        return storeValue;
    }

    // Helpers:
    private int getLastIndex() { return runTimeStack.size() - 1; }
    private int getLastFrame() { return framePointer.peek(); }
}
