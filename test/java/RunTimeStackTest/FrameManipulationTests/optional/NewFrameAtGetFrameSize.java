package optional;

import interpreter.virtualmachine.RunTimeStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Optional test
 */
@DisplayName("RunTimeStack_NewFrameAt_GetFrameSize")
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
    void pushThreeItems_callNewFrameAt_zero_getCurrentFrameSize()
    {
        runTimeStack.newFrameAt(0);
        assertEquals(0, runTimeStack.getCurrentFrameSize());
    }

    @Test
    void pushThreeItems_callNewFrameAt_one_getCurrentFrameSize()
    {
        runTimeStack.newFrameAt(1);
        assertEquals(1, runTimeStack.getCurrentFrameSize());
    }

    @Test
    void pushThreeItems_callNewFrameAt_two_getCurrentFrameSize()
    {
        runTimeStack.newFrameAt(2);
        assertEquals(2, runTimeStack.getCurrentFrameSize());
    }

    @Test
    void pushThreeItems_callNewFrameAt_three_getCurrentFrameSize()
    {
        runTimeStack.newFrameAt(3);
        assertEquals(3, runTimeStack.getCurrentFrameSize());
    }

    @Test
    void pushThreeItems_callNewFrameAt_four_getCurrentFrameSize()
    {
        runTimeStack.newFrameAt(4);
        assertEquals(4, runTimeStack.getCurrentFrameSize());
    }
    @Test
    void pushThreeItems_callNewFrameAt_five_getCurrentFrameSize()
    {
        runTimeStack.newFrameAt(5);
        assertEquals(5, runTimeStack.getCurrentFrameSize());
    }
    @Test
    void pushThreeItems_callNewFrameAt_negativeOne_getCurrentFrameSize()
    {
        runTimeStack.newFrameAt(-1);
        assertEquals(-1, runTimeStack.getCurrentFrameSize());
    }

    @Test
    void pushThreeItems_callNewFrameAt_negativeTwo_getCurrentFrameSize()
    {
        runTimeStack.newFrameAt(-2);
        assertEquals(-2, runTimeStack.getCurrentFrameSize());
    }

    @Test
    void pushThreeItems_callNewFrameAt_negativeTen_getCurrentFrameSize()
    {
        runTimeStack.newFrameAt(-10);
        assertEquals(-10, runTimeStack.getCurrentFrameSize());
    }
}
