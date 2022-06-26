package FrameManipulationTests;

import interpreter.virtualmachine.RunTimeStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RunTimeStack_NewFrameAt_PopFrame")
public class NewFrameAtPopFrame
{
    private RunTimeStack runTimeStack;

    // Init is called in every test
    @BeforeEach
    void init()
    {
        runTimeStack = new RunTimeStack();
        runTimeStack.push(3);
        runTimeStack.push(6);
        runTimeStack.push(9);
    }

    @AfterEach
    void tearDown() {runTimeStack = null;}

    @Test
    void pushThreeItems_callNewFrameAt_zero_andPopFrame()
    {
        runTimeStack.newFrameAt(0);
        assertEquals(3, runTimeStack.popFrame());
    }

    @Test
    void pushThreeItems_callNewFrameAt_one_popFrame()
    {
        runTimeStack.newFrameAt(1);
        assertEquals(2, runTimeStack.popFrame());
    }

    @Test
    void pushThreeItems_callNewFrameAt_two_popFrame()
    {
        runTimeStack.newFrameAt(2);
        assertEquals(1, runTimeStack.popFrame());
    }

    @Test
    void pushThreeItems_callNewFrameAt_three_popFrame()
    {
        runTimeStack.newFrameAt(3);
        assertEquals(0, runTimeStack.popFrame());
    }

    @Test
    void pushThreeItems_callNewFrameAt_four_popFrame()
    {
        runTimeStack.newFrameAt(4);
        assertTrue(runTimeStack.popFrame() < 0);
    }

    @Test
    void pushThreeItems_callNewFrameAt_five_popFrame()
    {
        runTimeStack.newFrameAt(5);
        assertTrue(runTimeStack.popFrame() < 0);
    }

    @Test
    void pushThreeItems_callNewFrameAt_negativeTen_popFrame()
    {
        runTimeStack.newFrameAt(-10);
        assertEquals(13, runTimeStack.popFrame());
    }

    @Test
    void pushThreeItems_callNewFrameAt_negativeTwenty_popFrame()
    {
        runTimeStack.newFrameAt(-20);
        assertEquals(23, runTimeStack.popFrame());
    }

    @Test
    void pushThreeItems_callNewFrameAt_negativeThirtyFour_popFrame()
    {
        runTimeStack.newFrameAt(-34);
        assertEquals(37, runTimeStack.popFrame());
    }
}
