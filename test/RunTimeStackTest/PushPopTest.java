package test.RunTimeStackTest;

import interpreter.virtualmachine.RunTimeStack;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PushPopTest
{
    private RunTimeStack runTimeStack;

    // Init is called in every test
    @BeforeEach void init() { runTimeStack = new RunTimeStack(); }

    @AfterEach void tearDown() { runTimeStack = null; }

    @Test void singlePushPop()
    {
        runTimeStack.push(6);
        assertEquals(6, runTimeStack.pop());
    }

    @Test void doublePushPop()
    {
        runTimeStack.push(5);
        runTimeStack.push(-4);
        assertEquals(-4, runTimeStack.pop());
        assertEquals(5, runTimeStack.pop());
    }

    @Test void multiplePushPop()
    {
        runTimeStack.push(0);
        runTimeStack.push(5);
        runTimeStack.push(69);
        runTimeStack.push(369);
        runTimeStack.push(999999);
        assertEquals(999999, runTimeStack.pop());
        assertEquals(369, runTimeStack.pop());
        assertEquals(69, runTimeStack.pop());
        assertEquals(5, runTimeStack.pop());
        assertEquals(0, runTimeStack.pop());
    }

    @Test void emptyPop()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> { runTimeStack.pop(); });
    }
}