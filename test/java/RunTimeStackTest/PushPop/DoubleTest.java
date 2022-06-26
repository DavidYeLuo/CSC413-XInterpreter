package PushPop;

import interpreter.virtualmachine.RunTimeStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DoubleTest
{
    private RunTimeStack runTimeStack;

    // Init is called in every test
    @BeforeEach
    void init()
    {
        runTimeStack = new RunTimeStack();
        runTimeStack.push(6);
    }

    @AfterEach
    void tearDown() {runTimeStack = null;}

    @Test
    void pushSixPushNinePop()
    {
        runTimeStack.push(9);
        assertEquals(9, runTimeStack.pop());
    }

    @Test
    void pushSixPushFourtyNinePop()
    {
        runTimeStack.push(49);
        assertEquals(49, runTimeStack.pop());
    }

    @Test
    void pushSixPushThreeThousandPop()
    {
        runTimeStack.push(3000);
        assertEquals(3000, runTimeStack.pop());
    }

    @Test
    void pushSixPushZeroPop()
    {
        runTimeStack.push(0);
        assertEquals(0, runTimeStack.pop());
    }

    @Test
    void pushSixPushNegativeOnePop()
    {
        runTimeStack.push(-1);
        assertEquals(-1, runTimeStack.pop());
    }

    @Test
    void pushSixPushNegativeTwoThousandPop()
    {
        runTimeStack.push(-2000);
        assertEquals(-2000, runTimeStack.pop());
    }

    @Test void pushSixPushThreePopPop()
    {
        runTimeStack.push(3);
        runTimeStack.pop();
        assertEquals(6, runTimeStack.pop());
    }

    @Test void pushSixPushFourPopPop()
    {

        runTimeStack.push(4);
        runTimeStack.pop();
        assertEquals(6, runTimeStack.pop());
    }

    @Test
    void popPop()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            runTimeStack.pop();
            runTimeStack.pop();
        });
    }
}
