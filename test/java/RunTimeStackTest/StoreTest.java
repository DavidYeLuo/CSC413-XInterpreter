import interpreter.virtualmachine.RunTimeStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.TestHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StoreTest
{
    private RunTimeStack runTimeStack;

    // Init is called in every test
    @BeforeEach
    void init() {runTimeStack = new RunTimeStack();}

    @AfterEach
    void tearDown() {runTimeStack = null;}

    @Test
    void pushTenItems_CallNewFrameAtZero_CallStoreZero()
    {
        TestHelper.pushNStack(runTimeStack, 9);
        runTimeStack.push(13456);

        runTimeStack.store(0);
        TestHelper.popNStack(runTimeStack, 9);

        assertEquals(13456, runTimeStack.pop());
    }

    @Test
    void pushTenItems_CallNewFrameAtZero_CallStoreSix()
    {
        TestHelper.pushNStack(runTimeStack, 9);
        runTimeStack.push(13456);

        runTimeStack.store(6);
        TestHelper.popNStack(runTimeStack, 3);

        assertEquals(13456, runTimeStack.pop());
    }
    @Test
    void pushTenItems_CallNewFrameAtZero_CallStoreEight()
    {
        TestHelper.pushNStack(runTimeStack, 9);
        runTimeStack.push(13456);

        runTimeStack.store(8);
        TestHelper.popNStack(runTimeStack, 1);

        assertEquals(13456, runTimeStack.pop());
    }

    @Test
    void pushTenItems_CallNewFrameAtZero_CallStoreTen()
    {
        TestHelper.pushNStack(runTimeStack, 9);
        runTimeStack.push(13456);

        runTimeStack.store(8);

        assertThrows(Exception.class, () ->
        {
            runTimeStack.store(10);
        });
    }

    @Test
    void pushTenItems_CallNewFrameAtZero_CallStoreEleven()
    {
        TestHelper.pushNStack(runTimeStack, 9);
        runTimeStack.push(13456);

        runTimeStack.store(8);

        assertThrows(Exception.class, () ->
        {
            runTimeStack.store(11);
        });
    }

    @Test
    void pushTenItems_CallNewFrameAtZero_CallStoreNegativeTen()
    {
        TestHelper.pushNStack(runTimeStack, 9);
        runTimeStack.push(4312);

        assertThrows(Exception.class, () ->
        {
            runTimeStack.load(-10);
        });
    }
    @Test
    void pushTenItems_CallNewFrameAtFive_CallStoreZero()
    {
        TestHelper.pushNStack(runTimeStack, 9);
        runTimeStack.push(13456);
        runTimeStack.newFrameAt(5);

        runTimeStack.store(0);
        TestHelper.popNStack(runTimeStack, 4);

        assertEquals(13456, runTimeStack.pop());
    }
    @Test
    void pushTenItems_CallNewFrameAtFour_CallStoreThree()
    {
        TestHelper.pushNStack(runTimeStack, 9);
        runTimeStack.push(13456);
        runTimeStack.newFrameAt(4);

        runTimeStack.store(3);

        assertEquals(13456, runTimeStack.pop());
    }
    @Test
    void pushZeroItem_CallNewFrameAtZero_CallStoreZero()
    {
        assertThrows(Exception.class, () ->
        {
            runTimeStack.store(0);
        });
    }
    @Test
    void pushZeroItems_CallNewFrameAtZero_CallLoadNegativeTen()
    {
        assertThrows(Exception.class, () ->
        {
            runTimeStack.store(-10);
        });
    }

    @Test
    void pushZeroItems_CallNewFrameAtZero_CallLoadTen()
    {
        assertThrows(Exception.class, () ->
        {
            runTimeStack.store(10);
        });
    }
}
