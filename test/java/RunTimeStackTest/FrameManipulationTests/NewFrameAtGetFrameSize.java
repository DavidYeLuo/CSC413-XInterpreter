package FrameManipulationTests;

import interpreter.virtualmachine.RunTimeStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RunTimeStack_NewFrameAt_PopFrame")
public class NewFrameAtGetFrameSize
{
    private RunTimeStack runTimeStack;

    // Init is called in every test
    @BeforeEach
    void init()
    {
        runTimeStack = new RunTimeStack();
        runTimeStack.push(9);
        runTimeStack.push(3);
        runTimeStack.push(6);
    }

    @AfterEach
    void tearDown() {runTimeStack = null;}

    @Test
    void pushThreeItems_callNewFrameAt_never_getFrameSize()
    {
        assertEquals(1, runTimeStack.getFrameSize());
    }

    @Test
    void pushThreeItems_callNewFrameAt_once_getFrameSize()
    {
        runTimeStack.newFrameAt(0);
        assertEquals(2, runTimeStack.getFrameSize());
    }

    @Test
    void pushThreeItems_callNewFrameAt_twice_getFrameSize()
    {
        runTimeStack.newFrameAt(0);
        runTimeStack.newFrameAt(1);
        assertEquals(3, runTimeStack.getFrameSize());
    }
}
