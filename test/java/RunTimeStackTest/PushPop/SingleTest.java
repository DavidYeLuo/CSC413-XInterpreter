package PushPop;

import interpreter.virtualmachine.RunTimeStack;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RunTimeStack_Single_Test")
class SingleTest
{
    private RunTimeStack runTimeStack;

    // Init is called in every test
    @BeforeEach
    void init() {runTimeStack = new RunTimeStack();}

    @AfterEach
    void tearDown() {runTimeStack = null;}

    @Test
    void pushSixPop()
    {
        runTimeStack.push(6);
        assertEquals(6, runTimeStack.pop());
    }

    @Test
    void pushFourtyNinePop()
    {
        runTimeStack.push(49);
        assertEquals(49, runTimeStack.pop());
    }

    @Test
    void pushThreeThousandPop()
    {
        runTimeStack.push(3000);
        assertEquals(3000, runTimeStack.pop());
    }

    @Test
    void pushZeroPop()
    {
        runTimeStack.push(0);
        assertEquals(0, runTimeStack.pop());
    }

    @Test
    void pushNegativeOnePop()
    {
        runTimeStack.push(-1);
        assertEquals(-1, runTimeStack.pop());
    }

    @Test
    void pushNegativeTwoThousandPop()
    {
        runTimeStack.push(-2000);
        assertEquals(-2000, runTimeStack.pop());
    }


    @Test
    void emptyPop()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            runTimeStack.pop();
        });
    }
}