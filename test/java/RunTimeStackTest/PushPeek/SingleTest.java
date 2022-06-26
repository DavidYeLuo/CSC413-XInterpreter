package PushPeek;


import interpreter.virtualmachine.RunTimeStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("RunTimeStack_PushPeek_Single")
class SingleTest
{
    private RunTimeStack runTimeStack;

    // Init is called in every test
    @BeforeEach
    void init() {runTimeStack = new RunTimeStack();}

    @AfterEach
    void tearDown() {runTimeStack = null;}

    @Test
    void pushSixPeek()
    {
        runTimeStack.push(6);
        assertEquals(6, runTimeStack.peek());
    }

    @Test
    void pushFourtyNinePeek()
    {
        runTimeStack.push(49);
        assertEquals(49, runTimeStack.peek());
    }

    @Test
    void pushThreeThousandPeek()
    {
        runTimeStack.push(3000);
        assertEquals(3000, runTimeStack.peek());
    }

    @Test
    void pushZeroPeek()
    {
        runTimeStack.push(0);
        assertEquals(0, runTimeStack.peek());
    }

    @Test
    void pushNegativeOnePeek()
    {
        runTimeStack.push(-1);
        assertEquals(-1, runTimeStack.peek());
    }

    @Test
    void pushNegativeTwoThousandPeek()
    {
        runTimeStack.push(-2000);
        assertEquals(-2000, runTimeStack.peek());
    }


    @Test
    void emptyPeek()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            runTimeStack.peek();
        });
    }
}
