package test.RunTimeStackTest;

import interpreter.virtualmachine.RunTimeStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PushPeekTest
{
    private RunTimeStack runTimeStack;

    // Init is called in every test
    @BeforeEach
    void init() { runTimeStack = new RunTimeStack(); }

    @AfterEach
    void tearDown() { runTimeStack = null; }

    @Test void emptyPeek()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> { runTimeStack.peek(); });
    }

    @Test void singlePeek()
    {
        runTimeStack.push(254);
        assertEquals(254, runTimeStack.peek());
    }

    @Test void doublePeek()
    {
        runTimeStack.push(43);
        assertEquals(43, runTimeStack.peek());
        runTimeStack.push(214);
        assertEquals(214, runTimeStack.peek());
    }

    @Test void multiplePeek()
    {
        runTimeStack.push(53);
        assertEquals(53, runTimeStack.peek());
        runTimeStack.push(21);
        assertEquals(21, runTimeStack.peek());
        runTimeStack.push(4);
        assertEquals(4, runTimeStack.peek());
        runTimeStack.push(3);
        assertEquals(3, runTimeStack.peek());
        runTimeStack.push(245);
        assertEquals(245, runTimeStack.peek());
        runTimeStack.push(2111);
        assertEquals(2111, runTimeStack.peek());
    }
}
