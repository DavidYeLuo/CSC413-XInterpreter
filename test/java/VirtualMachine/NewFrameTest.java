package VirtualMachine;

import interpreter.virtualmachine.Program;
import interpreter.virtualmachine.VirtualMachine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test.TestHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("VirtualMachine_NewFrameTest")
public class NewFrameTest
{
    private VirtualMachine virtualMachine;

    // Init is called in every test
    @BeforeEach
    void init()
    {
        virtualMachine = new VirtualMachine(new Program());
    }

    @AfterEach
    void tearDown() {virtualMachine = null;}

    @Test
    void pushTenItems_newFrameAtZero_popFrame()
    {
        TestHelper.pushNStack(virtualMachine, 10);

        virtualMachine.newFrameBelow(0);

        assertEquals(10, virtualMachine.pop(VirtualMachine.POP_FRAME));
    }

    @Test
    void newState_popFrame()
    {
        assertEquals(0, virtualMachine.pop(VirtualMachine.POP_FRAME));
    }

    @Test
    void newState_PopTwice()
    {
        assertEquals(0, virtualMachine.pop(VirtualMachine.POP_FRAME));
        assertEquals(Integer.MIN_VALUE, virtualMachine.pop(VirtualMachine.POP_FRAME));
    }

    @Test
    void pushTenItems_newFrameAtThree_popFrame()
    {
        TestHelper.pushNStack(virtualMachine, 10);

        virtualMachine.newFrameBelow(3);

        assertEquals(7, virtualMachine.pop(VirtualMachine.POP_FRAME));
    }

    @Test
    void pushTenItems_newFrameAtNine_popFrame()
    {
        TestHelper.pushNStack(virtualMachine, 10);

        virtualMachine.newFrameBelow(9);

        assertEquals(1, virtualMachine.pop(VirtualMachine.POP_FRAME));
    }

    @Test
    void pushTenItems_newFrameAtTen_popFrame()
    {
        TestHelper.pushNStack(virtualMachine, 10);

        virtualMachine.newFrameBelow(10);

        assertEquals(1, virtualMachine.pop(VirtualMachine.POP_FRAME));
    }

    @Test
    void pushTenItems_newFrameAtOneHundred_popFrame()
    {
        TestHelper.pushNStack(virtualMachine, 10);

        virtualMachine.newFrameBelow(100);

        assertEquals(1, virtualMachine.pop(VirtualMachine.POP_FRAME));
    }
    @Test
    void pushTenItems_newFrameAtNegativeTen_popFrame()
    {
        TestHelper.pushNStack(virtualMachine, 10);

        virtualMachine.newFrameBelow(-10);

        assertEquals(10, virtualMachine.pop(VirtualMachine.POP_FRAME));
    }
}
