package VirtualMachine.MustPassTests.PushPop;

import interpreter.virtualmachine.Program;
import interpreter.virtualmachine.VirtualMachine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("VirtualMachine_PushPop_Single")
public class SingleTest
{
    private VirtualMachine virtualMachine;

    // Init is called in every test
    @BeforeEach
    void init() {virtualMachine = new VirtualMachine(new Program());}

    @AfterEach
    void tearDown() {virtualMachine = null;}

    @Test
    void pushSix_popOne()
    {
        virtualMachine.push(6);
        assertEquals(6, virtualMachine.pop(1));
    }

    @Test
    void pushFourtyNine_popOne()
    {
        virtualMachine.push(49);
        assertEquals(49, virtualMachine.pop(1));
    }

    @Test
    void pushThreeThousand_popOne()
    {
        virtualMachine.push(3000);
        assertEquals(3000, virtualMachine.pop(1));
    }

    @Test
    void pushZero_popOne()
    {
        virtualMachine.push(0);
        assertEquals(0, virtualMachine.pop(1));
    }

    @Test
    void pushNegativeOne_popOne()
    {
        virtualMachine.push(-1);
        assertEquals(-1, virtualMachine.pop(1));
    }

    @Test
    void pushNegativeTwoThousand_popOne()
    {
        virtualMachine.push(-2000);
        assertEquals(-2000, virtualMachine.pop(1));
    }

    @Test
    void emptyStack_popTwo()
    {
        assertEquals(Integer.MIN_VALUE, virtualMachine.pop(2));
    }

    @Test
    void pushOne_popOneHundred()
    {
        virtualMachine.push(1);
        assertEquals(1, virtualMachine.pop(100));
    }

    @Test
    void pushOne_popNegativeOneHundred()
    {
        virtualMachine.push(1);
        assertEquals(Integer.MIN_VALUE, virtualMachine.pop(-100));
    }

    @Test
    void emptyStack_popOne()
    {
        assertEquals(Integer.MIN_VALUE, virtualMachine.pop(1));
    }
}
