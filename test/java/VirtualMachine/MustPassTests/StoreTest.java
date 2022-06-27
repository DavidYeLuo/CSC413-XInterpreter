package VirtualMachine.MustPassTests;

import interpreter.virtualmachine.Program;
import interpreter.virtualmachine.RunTimeStack;
import interpreter.virtualmachine.VirtualMachine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test.TestHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("VirtualMachine_StoreTest")
public class StoreTest
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
    void pushTenItems_CallNewFrameAtZero_CallStoreZero()
    {
        TestHelper.pushNStack(virtualMachine, 9);
        virtualMachine.push(13456);

        virtualMachine.store(0);
        TestHelper.popNStack(virtualMachine, 9);

        assertEquals(13456, virtualMachine.pop(1));
    }

    @Test
    void pushTenItems_CallNewFrameAtZero_CallStoreSix()
    {
        TestHelper.pushNStack(virtualMachine, 9);
        virtualMachine.push(13456);

        virtualMachine.store(6);
        TestHelper.popNStack(virtualMachine, 3);

        assertEquals(13456, virtualMachine.pop(1));
    }
    @Test
    void pushTenItems_CallNewFrameAtZero_CallStoreEight()
    {
        TestHelper.pushNStack(virtualMachine, 9);
        virtualMachine.push(13456);

        virtualMachine.store(8);
        TestHelper.popNStack(virtualMachine, 1);

        assertEquals(13456, virtualMachine.pop(1));
    }

    @Test
    void pushTenItems_CallNewFrameAtZero_CallStoreTen()
    {
        TestHelper.pushNStack(virtualMachine, 9);
        virtualMachine.push(13456);

        virtualMachine.store(8);

        assertEquals(13456, virtualMachine.pop(1));
    }

    @Test
    void pushTenItems_CallNewFrameAtZero_CallStoreEleven()
    {
        TestHelper.pushNStack(virtualMachine, 9);
        virtualMachine.push(13456);

        virtualMachine.store(8);

        assertEquals(13456, virtualMachine.pop(1));
    }

    @Test
    void pushTenItems_CallNewFrameAtZero_CallStoreNegativeTen()
    {
        virtualMachine.push(4312);
        TestHelper.pushNStack(virtualMachine, 9);

        assertEquals(4312, virtualMachine.pop(1));
    }
    @Test
    void pushTenItems_CallNewFrameAtFive_CallStoreZero()
    {
        TestHelper.pushNStack(virtualMachine, 9);
        virtualMachine.push(13456);
        virtualMachine.newFrameAt(5);

        virtualMachine.store(0);
        TestHelper.popNStack(virtualMachine, 4);

        assertEquals(13456, virtualMachine.pop(1));
    }
    @Test
    void pushTenItems_CallNewFrameAtFour_CallStoreThree()
    {
        TestHelper.pushNStack(virtualMachine, 9);
        virtualMachine.push(13456);
        virtualMachine.newFrameAt(4);

        virtualMachine.store(3);

        assertEquals(13456, virtualMachine.pop(1));
    }
    @Test
    void pushZeroItem_CallNewFrameAtZero_CallStoreZero()
    {
        assertEquals(Integer.MIN_VALUE, virtualMachine.pop(1));
    }
    @Test
    void pushZeroItems_CallNewFrameAtZero_CallLoadNegativeTen()
    {
        assertEquals(Integer.MIN_VALUE, virtualMachine.pop(1));
    }
}
