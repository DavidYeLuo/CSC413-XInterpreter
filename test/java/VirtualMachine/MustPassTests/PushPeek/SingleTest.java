package VirtualMachine.MustPassTests.PushPeek;

import interpreter.virtualmachine.Program;
import interpreter.virtualmachine.VirtualMachine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("VirtualMachine_PushPeek_Single")
public class SingleTest
{
    private VirtualMachine virtualMachine;

    // Init is called in every test
    @BeforeEach
    void init() {virtualMachine = new VirtualMachine(new Program());}

    @AfterEach
    void tearDown() {virtualMachine = null;}

    @Test
    void pushSix_popPeekRunTimeStack()
    {
        virtualMachine.push(6);
        assertEquals(6, virtualMachine.pop(VirtualMachine.PEEK_RUNTIMESTACK));
    }

    @Test
    void pushFourtyNine_popPeekRunTimeStack()
    {
        virtualMachine.push(49);
        assertEquals(49, virtualMachine.pop(VirtualMachine.PEEK_RUNTIMESTACK));
    }

    @Test
    void pushThreeThousandPop_popPeekRunTimeStack()
    {
        virtualMachine.push(3000);
        assertEquals(3000, virtualMachine.pop(VirtualMachine.PEEK_RUNTIMESTACK));
    }

    @Test
    void pushZero_popPeekRunTimeStack()
    {
        virtualMachine.push(0);
        assertEquals(0, virtualMachine.pop(VirtualMachine.PEEK_RUNTIMESTACK));
    }

    @Test
    void pushNegativeOne_popPeekRunTimeStack()
    {
        virtualMachine.push(-1);
        assertEquals(-1, virtualMachine.pop(VirtualMachine.PEEK_RUNTIMESTACK));
    }

    @Test
    void pushNegativeTwoThousand_popPeekRunTimeStack()
    {
        virtualMachine.push(-2000);
        assertEquals(-2000, virtualMachine.pop(VirtualMachine.PEEK_RUNTIMESTACK));
    }


    @Test
    void emptyStack_popPeekRunTimeStack()
    {
        assertEquals(Integer.MIN_VALUE, virtualMachine.pop(1));
    }
}
