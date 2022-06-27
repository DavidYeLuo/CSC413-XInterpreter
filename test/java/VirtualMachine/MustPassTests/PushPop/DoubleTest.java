package VirtualMachine.MustPassTests.PushPop;

import interpreter.virtualmachine.Program;
import interpreter.virtualmachine.RunTimeStack;
import interpreter.virtualmachine.VirtualMachine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("VirtualMachine_PushPop_Double")
public class DoubleTest
{
    private VirtualMachine virtualMachine;

    // Init is called in every test
    @BeforeEach
    void init()
    {
        virtualMachine = new VirtualMachine(new Program());
        virtualMachine.push(6);
    }

    @AfterEach
    void tearDown() {virtualMachine = null;}

    @Test
    void pushSixPushNine_popOne()
    {
        virtualMachine.push(9);
        assertEquals(9, virtualMachine.pop(1));
    }

    @Test
    void pushSixPushFourtyNine_popOne()
    {
        virtualMachine.push(49);
        assertEquals(49, virtualMachine.pop(1));
    }

    @Test
    void pushSixPushThreeThousand_popOne()
    {
        virtualMachine.push(3000);
        assertEquals(3000, virtualMachine.pop(1));
    }

    @Test
    void pushSixPushZero_popOne()
    {
        virtualMachine.push(0);
        assertEquals(0, virtualMachine.pop(1));
    }

    @Test
    void pushSixPushNegative_popOne()
    {
        virtualMachine.push(-1);
        assertEquals(-1, virtualMachine.pop(1));
    }

    @Test
    void pushSixPushNegativeTwoThousand_popOne()
    {
        virtualMachine.push(-2000);
        assertEquals(-2000, virtualMachine.pop(1));
    }

    @Test
    void pushSixPushThree_popTwice()
    {
        virtualMachine.push(3);
        virtualMachine.pop(1);
        assertEquals(6, virtualMachine.pop(1));
    }

    @Test
    void pushSixPushThree_popTwo()
    {
        virtualMachine.push(3);
        assertEquals(6, virtualMachine.pop(2));
    }

    @Test
    void pushSixPushFour_popTwice()
    {

        virtualMachine.push(4);
        virtualMachine.pop(1);
        assertEquals(6, virtualMachine.pop(1));
    }

    @Test
    void pushSixPushFour_popTwo()
    {

        virtualMachine.push(4);
        assertEquals(6, virtualMachine.pop(2));
    }

    @Test
    void emptyStack_popTwice()
    {
        assertEquals(6, virtualMachine.pop(1));
        assertEquals(Integer.MIN_VALUE, virtualMachine.pop(1));
    }
}
