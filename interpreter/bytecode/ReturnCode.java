package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class ReturnCode extends ByteCode
{
    private String optionalIdentifier;
    @Override
    public void init(ArrayList<String> args)
    {
        if(ByteCode.isArgsNullOrEmpty(args)) return;
        optionalIdentifier = args.get(0);
    }

    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        // Stores the top of the stack
        int returnValue = virtualMachine.pop(1);

        // Clean up the frame AND removes the boundary
        virtualMachine.pop(virtualMachine.POP_CLEAN_FRAME);
        virtualMachine.pop(VirtualMachine.POP_FRAME);

        // Push return value to the caller's frame
        if(returnValue != VirtualMachine.ERROR_RETURN_CODE)
            virtualMachine.push(returnValue);

        // Transfer control back to the caller
        virtualMachine.setProgramCounter(virtualMachine.popReturnAddress());
    }
}
