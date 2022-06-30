package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class StoreCode extends ByteCode implements Dumpable
{
    private int offset;

    // Debug variables below
    private String identifier;

    private int topOfTheStack;

    @Override
    public void init(ArrayList<String> args)
    {
        if (ByteCode.isArgsNullOrEmpty(args)) return;
        offset = ByteCode.parseInt(args.get(0));
        if (args.size() > 1) identifier = args.get(1);
    }

    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        virtualMachine.store(offset);
        topOfTheStack = virtualMachine.pop(1);
    }

    @Override
    public String dump()
    {
        StringBuilder result = new StringBuilder();
        // Basic Syntax : STORE <offset> <id> <id>=<top-of-stack>
        result.append(String.format("STORE %d", offset));
        if (identifier == null)
        {
            return result.toString();
        }

        result.append(String.format(" %s", identifier));

        if (topOfTheStack == VirtualMachine.ERROR_RETURN_CODE)
        {
            return result.toString();
        }

        result.append(String.format(" %s=%d", identifier, topOfTheStack));
        return result.toString();
    }
}
