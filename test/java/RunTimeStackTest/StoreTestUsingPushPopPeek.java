import interpreter.virtualmachine.RunTimeStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.TestHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StoreTestUsingPushPopPeek
{
    private RunTimeStack runTimeStack;

    // Init is called in every test
    @BeforeEach
    void init()
    {
        runTimeStack = new RunTimeStack();
    }

    @AfterEach
    void tearDown()
    {
        runTimeStack = null;
    }

    @Test
    void zeroOffsetZeroFrame()
    {
        // Param
        int offset = 0;
        int stackSize = 11;
        int framePointer = 0; // Made up to simulate (There isn't a peek frame)

        // Edit here
        runTimeStack.push(935); // Just to make sure it isn't by chance
        TestHelper.pushNStack(runTimeStack, (stackSize - 1) - 1 - framePointer - offset);
        runTimeStack.push(123);

        // Call
        runTimeStack.store(offset);

        // retrieve and check for the value at the offset above the current stackframe
        int location = (stackSize - 1); // start at the top
        location -= framePointer;
        location -= offset;
        TestHelper.popNStack(runTimeStack, location);

        assertEquals(123, runTimeStack.pop());
    }

    @Test
    void singleOffsetZeroFrame()
    {
        // Param
        int offset = 3;
        int stackSize = 11;
        int framePointer = 0; // Made up to simulate (There isn't a peek frame)

        // Edit here
        TestHelper.pushNStack(runTimeStack, framePointer + offset - 1);
        runTimeStack.push(935); // Just to make sure it isn't by chance
        TestHelper.pushNStack(runTimeStack, (stackSize - 1) - framePointer - offset);
        runTimeStack.push(123);

        // Call
        runTimeStack.store(offset);

        // retrieve and check for the value at the offset above the current stackframe
        int location = (stackSize - 1); // start at the top
        location -= framePointer;
        location -= offset;
        TestHelper.popNStack(runTimeStack, location);

        assertEquals(123, runTimeStack.pop());
    }

    @Test void zeroOffsetSingleFrame()
    {
        // Param
        int offset = 0;
        int stackSize = 11;
        int framePointer = 6; // Made up to simulate (There isn't a peek frame)

        // Edit here

        // build up to the frame pointer
        TestHelper.pushNStack(runTimeStack, framePointer);
        runTimeStack.newFrameAt(0);

        // build up to the offset
        runTimeStack.push(935); // Just to make sure it isn't by chance

        // build the rest
        TestHelper.pushNStack(runTimeStack, (stackSize - 1) -1 - framePointer - offset);
        runTimeStack.push(123);

        // Call
        runTimeStack.store(offset);

        // retrieve and check for the value at the offset above the current stackframe
        int location = (stackSize - 1); // start at the top
        location -= framePointer;
        location -= offset;
        TestHelper.popNStack(runTimeStack, location);

        assertEquals(123, runTimeStack.pop());
    }

    @Test void singleOffsetSingleFrame()
    {
        // Param
        int offset = 3;
        int stackSize = 11;
        int framePointer = 6; // Made up to simulate (There isn't a peek frame)

        // Edit here

        // build up to the frame pointer
        TestHelper.pushNStack(runTimeStack, framePointer);
        runTimeStack.newFrameAt(0);

        // build up to the offset
        TestHelper.pushNStack(runTimeStack, offset-1);
        runTimeStack.push(935); // Just to make sure it isn't by chance

        // build the rest
        TestHelper.pushNStack(runTimeStack, (stackSize - 1) - framePointer - offset);
        runTimeStack.push(123);

        // Call
        runTimeStack.store(offset);

        // retrieve and check for the value at the offset above the current stackframe
        int location = (stackSize - 1); // start at the top
        location -= framePointer;
        location -= offset;
        TestHelper.popNStack(runTimeStack, location);

        assertEquals(123, runTimeStack.pop());
    }

    @Test void singleOffsetTwoFrame()
    {
        // Param
        int offset = 3;
        int stackSize = 30;
        int framePointer = 10; // Made up to simulate (There isn't a peek frame)

        // Edit here

        // build up to the frame pointer
        TestHelper.pushNStack(runTimeStack, 5);
        runTimeStack.newFrameAt(0);
        TestHelper.pushNStack(runTimeStack, 5);
        runTimeStack.newFrameAt(0);
        TestHelper.pushNStack(runTimeStack, offset);
        runTimeStack.newFrameAt(0);

        // build up to the offset
        TestHelper.pushNStack(runTimeStack, offset - 1);
        runTimeStack.push(935); // Just to make sure it isn't by chance

        // build the rest
        TestHelper.pushNStack(runTimeStack, (stackSize - 1) - framePointer - offset);
        runTimeStack.push(123);

        // Call
        runTimeStack.store(offset);

        // retrieve and check for the value at the offset above the current stackframe
        int location = (stackSize - 1); // start at the top
        location -= framePointer;
        location -= offset;
        TestHelper.popNStack(runTimeStack, location);

        assertEquals(123, runTimeStack.pop());
    }
}
