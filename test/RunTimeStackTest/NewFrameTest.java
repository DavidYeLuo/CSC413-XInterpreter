package test.RunTimeStackTest;

import interpreter.virtualmachine.RunTimeStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NewFrameTest
{
    private RunTimeStack runTimeStack;

    // Init is called in every test
    @BeforeEach
    void init() { runTimeStack = new RunTimeStack(); }

    @AfterEach
    void tearDown() { runTimeStack = null; }

    @Test void emptyFramePop()
    {
        assertEquals(0, runTimeStack.popFrame());
    }

    @Test void singleFramePop()
    {
        runTimeStack.push(0);
        runTimeStack.push(0);
        runTimeStack.push(0);
        runTimeStack.push(0);
        runTimeStack.newFrameAt(3);
        assertEquals(0, runTimeStack.popFrame());
    }

    @Test void doubleFramePop()
    {
        runTimeStack.push(0);
        runTimeStack.newFrameAt(0);
        assertEquals(0, runTimeStack.popFrame());

        runTimeStack.push(0);
        runTimeStack.push(0);
        runTimeStack.push(0);
        runTimeStack.newFrameAt(2);
        assertEquals(1, runTimeStack.popFrame());
    }

    @Test void multipleFramePop()
    {
        for(int i = 0; i < 10; i++)
        {
            runTimeStack.push(i);
        }
        runTimeStack.newFrameAt(3);
        assertEquals(6, runTimeStack.popFrame());

        for(int i = 0; i < 15; i++)
        {
            runTimeStack.push(i);
        }
        runTimeStack.newFrameAt(5);
        assertEquals(19, runTimeStack.popFrame());

        for(int i = 0; i < 3; i++)
        {
            runTimeStack.push(i);
        }
        runTimeStack.newFrameAt(1);
        assertEquals(26, runTimeStack.popFrame());

        for(int i = 0; i < 20; i++)
        {
            runTimeStack.push(i);
        }
        runTimeStack.newFrameAt(4);
        assertEquals(43, runTimeStack.popFrame());
    }
}
