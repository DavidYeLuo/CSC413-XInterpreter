import interpreter.virtualmachine.RunTimeStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.TestHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadTest
{
    private RunTimeStack runTimeStack;

    // Init is called in every test
    @BeforeEach
    void init() { runTimeStack = new RunTimeStack(); }

    @AfterEach
    void tearDown() { runTimeStack = null; }

    @Test
    void emptyFramePop()
    {
        assertEquals(0, runTimeStack.popFrame());
    }

    @Test void zeroOffsetZeroFrame()
    {
        // Param
        int offset = 0;
        int stackSize = 30;
        int framePointer = 0; // Made up to simulate (There isn't a peek frame)

        // Edit here

        TestHelper.pushNStack(runTimeStack, framePointer + offset - 1);
        runTimeStack.push(4321);
        TestHelper.pushNStack(runTimeStack, (stackSize - 1) - framePointer - offset);

        // Call
        runTimeStack.load(offset);

        assertEquals(4321, runTimeStack.pop());
    }
    @Test void singleOffsetZeroFrame()
    {
        // Param
        int offset = 12;
        int stackSize = 30;
        int framePointer = 0; // Made up to simulate (There isn't a peek frame)

        // Edit here

        TestHelper.pushNStack(runTimeStack, offset);
        runTimeStack.push(4321);
        TestHelper.pushNStack(runTimeStack, (stackSize - 1) - framePointer - offset);

        // Call
        runTimeStack.load(offset);

        assertEquals(4321, runTimeStack.pop());
    }

    @Test void zeroOffsetSingleFrame()
    {
        // Param
        int offset = 0;
        int stackSize = 33;
        int framePointer = 12; // Made up to simulate (There isn't a peek frame)

        // Edit here
        TestHelper.pushNStack(runTimeStack, framePointer);
        runTimeStack.push(432);
        runTimeStack.newFrameAt(0);
        TestHelper.pushNStack(runTimeStack, (stackSize - 1) - framePointer - offset);

        // Call
        runTimeStack.load(offset);

        assertEquals(432, runTimeStack.pop());
    }

    @Test void singleOffsetSingleFrame()
    {
        // Param
        int offset = 3;
        int stackSize = 30;
        int framePointer = 12; // Made up to simulate (There isn't a peek frame)

        // Edit here

        TestHelper.pushNStack(runTimeStack, framePointer + 1);
        runTimeStack.newFrameAt(0);
        TestHelper.pushNStack(runTimeStack, offset - 1);
        runTimeStack.push(321);

        TestHelper.pushNStack(runTimeStack, (stackSize - 1) - framePointer - offset);

        // Call
        runTimeStack.load(offset);

        assertEquals(321, runTimeStack.pop());
    }

    @Test void singleOffsetTwoFrame()
    {
        // Param
        int offset = 2;
        int stackSize = 30;
        int framePointer = 10; // Made up to simulate (There isn't a peek frame)

        // Edit here

        TestHelper.pushNStack(runTimeStack, 5+1);
        runTimeStack.newFrameAt(0);
        TestHelper.pushNStack(runTimeStack, 5);
        runTimeStack.newFrameAt(0);

        TestHelper.pushNStack(runTimeStack,  offset - 1);
        runTimeStack.push(4321);
        TestHelper.pushNStack(runTimeStack, (stackSize - 1) - framePointer - offset);

        // Call
        runTimeStack.load(offset);

        assertEquals(4321, runTimeStack.pop());
    }
}
