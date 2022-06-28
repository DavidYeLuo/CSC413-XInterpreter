package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class ReturnCode extends ByteCode implements Dumpable
{
    // Debug variables below
    private String optionalIdentifier;
    private int returnValue;
    @Override
    public void init(ArrayList<String> args)
    {
        if(ByteCode.isArgsNullOrEmpty(args)) return;
        optionalIdentifier = args.get(0);
    }

    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        returnValue = virtualMachine.pop(1);

        // Clean up the frame AND removes the boundary
        virtualMachine.pop(virtualMachine.POP_CLEAN_FRAME);
        virtualMachine.pop(VirtualMachine.POP_FRAME);

        // Push return value to the caller's frame
        if(returnValue == VirtualMachine.ERROR_RETURN_CODE)
            virtualMachine.push(returnValue);

        // Transfer control back to the caller
        // Stores the top of the stack
        int returnAddress = virtualMachine.popReturnAddress();
        if(returnAddress == VirtualMachine.ERROR_RETURN_CODE)
        {
//            System.out.println("ERROR: empty return address stack.");
            return;
        }
        virtualMachine.setProgramCounter(returnValue);
    }

    @Override
    public String dump()
    {
        // Basic Syntax : RETURN <id> EXIT <base-id>:<value>
        StringBuilder result = new StringBuilder();
        result.append("RETURN");
        if(optionalIdentifier == null) return result.toString();

        // Find base id
        String baseId = optionalIdentifier.substring(0, optionalIdentifier.indexOf("<"));

        result.append(String.format(" %s %s:%d", optionalIdentifier, baseId, returnValue));
        return result.toString();
    }
}
