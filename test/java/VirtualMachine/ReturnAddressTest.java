package VirtualMachine;

import interpreter.virtualmachine.Program;
import interpreter.virtualmachine.VirtualMachine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("VirtualMachine_ReturnAddressTest")
public class ReturnAddressTest
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
    void emptyStack_popOnce()
    {
        assertEquals(Integer.MIN_VALUE, virtualMachine.popReturnAddress());
    }

    @Test
    void emptyStack_popTwice()
    {
        virtualMachine.popReturnAddress();
        assertEquals(Integer.MIN_VALUE, virtualMachine.popReturnAddress());
    }

    @Test
    void pushOneItem_popOnce()
    {
        virtualMachine.pushReturnAddress(1234);
        assertEquals(1234, virtualMachine.popReturnAddress());
    }

    @Test
    void pushTwoItems_popOnce()
    {
        virtualMachine.pushReturnAddress(1234);
        virtualMachine.pushReturnAddress(123);
        assertEquals(123, virtualMachine.popReturnAddress());
    }

    @Test
    void pushTwoItems_popTwice()
    {
        virtualMachine.pushReturnAddress(1234);
        virtualMachine.pushReturnAddress(123);
        virtualMachine.popReturnAddress();
        assertEquals(124, virtualMachine.popReturnAddress());
    }
}
