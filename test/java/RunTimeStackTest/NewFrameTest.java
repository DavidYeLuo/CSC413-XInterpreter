package test.java.RunTimeStackTest;

import interpreter.virtualmachine.RunTimeStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.html.HTMLBRElement;
import test.TestHelper;

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
        TestHelper.pushNStack(runTimeStack, 4);
        runTimeStack.newFrameAt(3);
        assertEquals(1, runTimeStack.popFrame());
    }

    @Test void doubleFramePop()
    {
        runTimeStack.push(0);
        runTimeStack.newFrameAt(0);
        assertEquals(1, runTimeStack.popFrame());

        TestHelper.pushNStack(runTimeStack, 3);
        runTimeStack.newFrameAt(2);
        assertEquals(2, runTimeStack.popFrame());
    }

    @Test void multipleFramePop()
    {
        TestHelper.pushNStack(runTimeStack, 10);

        runTimeStack.newFrameAt(3);
        assertEquals(7, runTimeStack.popFrame());

        TestHelper.pushNStack(runTimeStack, 15);

        runTimeStack.newFrameAt(5);
        assertEquals(20, runTimeStack.popFrame());

        TestHelper.pushNStack(runTimeStack, 3);

        runTimeStack.newFrameAt(1);
        assertEquals(27, runTimeStack.popFrame());

        TestHelper.pushNStack(runTimeStack, 20);

        runTimeStack.newFrameAt(4);
        assertEquals(44, runTimeStack.popFrame());
    }
}
