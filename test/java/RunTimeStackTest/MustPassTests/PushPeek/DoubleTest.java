package MustPassTests.PushPeek;

import interpreter.virtualmachine.RunTimeStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("RunTimeStack_PushPeek_Double")
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
    void pushSixPushNinePeek()
    {
        runTimeStack.push(9);
        assertEquals(9, runTimeStack.peek());
    }

    @Test
    void pushSixPushFourtyNinePeek()
    {
        runTimeStack.push(49);
        assertEquals(49, runTimeStack.peek());
    }

    @Test
    void pushSixPushThreeThousandPeek()
    {
        runTimeStack.push(3000);
        assertEquals(3000, runTimeStack.peek());
    }

    @Test
    void pushSixPushZeroPeek()
    {
        runTimeStack.push(0);
        assertEquals(0, runTimeStack.peek());
    }

    @Test
    void pushSixPushNegativeOnePeek()
    {
        runTimeStack.push(-1);
        assertEquals(-1, runTimeStack.peek());
    }

    @Test
    void pushSixPushNegativeTwoThousandPeek()
    {
        runTimeStack.push(-2000);
        assertEquals(-2000, runTimeStack.peek());
    }

    @Test
    void pushSixPushThreePeekPeek()
    {
        runTimeStack.push(3);
        assertEquals(3, runTimeStack.peek());
        assertEquals(3, runTimeStack.peek());
    }

    @Test
    void pushSixPushFourPeekPeek()
    {

        runTimeStack.push(4);
        assertEquals(4, runTimeStack.peek());
        assertEquals(4, runTimeStack.peek());
    }

    @Test
    void peekPeek()
    {
        assertEquals(6, runTimeStack.peek());
        assertEquals(6, runTimeStack.peek());
    }
}
