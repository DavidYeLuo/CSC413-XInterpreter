import interpreter.virtualmachine.RunTimeStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test.TestHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("RunTimeStack_LoadTest")
public class LoadTest
{
    private RunTimeStack runTimeStack;

    // Init is called in every test
    @BeforeEach
    void init() {runTimeStack = new RunTimeStack();}

    @AfterEach
    void tearDown() {runTimeStack = null;}

    @Test
    void emptyFramePop()
    {
        assertEquals(0, runTimeStack.popFrame());
    }

    @Test
    void pushTenItems_CallNewFrameAtZero_CallLoadZero()
    {
        runTimeStack.push(13456);
        TestHelper.pushNStack(runTimeStack, 9);

        runTimeStack.load(0);
        assertEquals(13456, runTimeStack.pop());
    }

    @Test
    void pushTenItems_CallNewFrameAtZero_CallLoadSix()
    {
       TestHelper.pushNStack(runTimeStack, 6);
       runTimeStack.push(43124);
       TestHelper.pushNStack(runTimeStack, 3);

       runTimeStack.load(6);
       assertEquals(43124, runTimeStack.pop());
    }
    @Test
    void pushTenItems_CallNewFrameAtZero_CallLoadEight()
    {
        TestHelper.pushNStack(runTimeStack, 8);
        runTimeStack.push(4312);
        TestHelper.pushNStack(runTimeStack, 1);

        runTimeStack.load(8);
        assertEquals(4312, runTimeStack.pop());
    }

    @Test
    void pushTenItems_CallNewFrameAtZero_CallLoadTen()
    {
        TestHelper.pushNStack(runTimeStack, 9);
        runTimeStack.push(4312);

        assertThrows(Exception.class, () ->
        {
            runTimeStack.load(10);
        });
    }

    @Test
    void pushTenItems_CallNewFrameAtZero_CallLoadEleven()
    {
        TestHelper.pushNStack(runTimeStack, 9);
        runTimeStack.push(4312);

        assertThrows(Exception.class, () ->
        {
            runTimeStack.load(11);
        });
    }

    @Test
    void pushTenItems_CallNewFrameAtZero_CallLoadNegativeTen()
    {

        TestHelper.pushNStack(runTimeStack, 9);
        runTimeStack.push(4312);

        assertThrows(Exception.class, () ->
        {
            runTimeStack.load(-10);
        });
    }
    @Test
    void pushTenItems_CallNewFrameAtFive_CallLoadZero()
    {

        TestHelper.pushNStack(runTimeStack, 5);
        runTimeStack.push(4312);
        TestHelper.pushNStack(runTimeStack, 4);
        runTimeStack.newFrameAt(5);

        runTimeStack.load(0);
        assertEquals(4312, runTimeStack.pop());
    }
    @Test
    void pushTenItems_CallNewFrameAtFour_CallLoadThree()
    {
        TestHelper.pushNStack(runTimeStack, 9);
        runTimeStack.push(4312);
        runTimeStack.newFrameAt(4);

        runTimeStack.load(3);
        assertEquals(4312, runTimeStack.pop());
    }
    @Test
    void pushZeroItem_CallNewFrameAtZero_CallLoadZero()
    {
        assertThrows(Exception.class, () ->
        {
            runTimeStack.load(0);
        });
    }
    @Test
    void pushZeroItems_CallNewFrameAtZero_CallLoadNegativeTen()
    {
        assertThrows(Exception.class, () ->
        {
            runTimeStack.load(-10);
        });
    }

    @Test
    void pushZeroItems_CallNewFrameAtZero_CallLoadTen()
    {
        assertThrows(Exception.class, () ->
        {
            runTimeStack.load(10);
        });
    }
}