package VirtualMachine.MustPassTests.PushPeek;

import interpreter.virtualmachine.Program;
import interpreter.virtualmachine.VirtualMachine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("VirtualMachine_PushPeek_Double")
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
    void pushSixPushNine_popPeekRunTimeStack()
    {
        virtualMachine.push(9);
        assertEquals(9, virtualMachine.pop(VirtualMachine.PEEK_RUNTIMESTACK));
    }

    @Test
    void pushSixPushFourtyNine_popPeekRunTimeStack()
    {
        virtualMachine.push(49);
        assertEquals(49, virtualMachine.pop(VirtualMachine.PEEK_RUNTIMESTACK));
    }

    @Test
    void pushSixPushThreeThousand_popPeekRunTimeStack()
    {
        virtualMachine.push(3000);
        assertEquals(3000, virtualMachine.pop(VirtualMachine.PEEK_RUNTIMESTACK));
    }

    @Test
    void pushSixPushZero_popPeekRunTimeStack()
    {
        virtualMachine.push(0);
        assertEquals(0, virtualMachine.pop(VirtualMachine.PEEK_RUNTIMESTACK));
    }

    @Test
    void pushSixPushNegative_popPeekRuntimeStack()
    {
        virtualMachine.push(-1);
        assertEquals(-1, virtualMachine.pop(VirtualMachine.PEEK_RUNTIMESTACK));
    }

    @Test
    void pushSixPushNegativeTwoThousand_popPeekRuntimeStack()
    {
        virtualMachine.push(-2000);
        assertEquals(-2000, virtualMachine.pop(VirtualMachine.PEEK_RUNTIMESTACK));
    }

    @Test
    void pushSixPushThreePeek_popPeekRuntimeStackTwice()
    {
        virtualMachine.push(3);
        assertEquals(3, virtualMachine.pop(VirtualMachine.PEEK_RUNTIMESTACK));
        assertEquals(3, virtualMachine.pop(VirtualMachine.PEEK_RUNTIMESTACK));
    }

    @Test
    void pushSixPushFour_popPeekRuntimeStackTwice()
    {
        virtualMachine.push(4);
        assertEquals(4, virtualMachine.pop(VirtualMachine.PEEK_RUNTIMESTACK));
        assertEquals(4, virtualMachine.pop(VirtualMachine.PEEK_RUNTIMESTACK));
    }

    @Test
    void popPeekRuntimeStackTwice()
    {
        assertEquals(6, virtualMachine.pop(VirtualMachine.PEEK_RUNTIMESTACK));
        assertEquals(6, virtualMachine.pop(VirtualMachine.PEEK_RUNTIMESTACK));
    }
}
