package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

public class ReturnCode extends JumpCode
{
    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        // Stores the top of the stack
        int returnValue = virtualMachine.pop(1);

        // Clean up the frame AND removes the boundary
        virtualMachine.pop(VirtualMachine.POP_CURRENT_FRAME);

        // Push return value to the caller's frame
        virtualMachine.push(returnValue);

        // Transfer control back to the caller
        virtualMachine.goTo(virtualMachine.popReturnAddress());
    }
}
