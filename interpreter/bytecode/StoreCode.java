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
        if(ByteCode.isArgsNullOrEmpty(args)) return;
        offset = ByteCode.parseInt(args.get(0));
        if(args.size() > 1) identifier = args.get(1);
    }

    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        virtualMachine.store(offset);
        topOfTheStack = virtualMachine.pop(VirtualMachine.PEEK_RUNTIMESTACK);
    }

    @Override
    public String dump()
    {
        String result;
        // Basic Syntax : STORE <offset> <id> <id>=<top-of-stack>
        if (identifier == null)
        {
            result = String.format("STORE %d", offset);
            return result;
        }
        // NOTE: if there isn't anything in the top of the stack.
        // Then it will print the lowest value.
        result = String.format("STORE %d %s %s=%d",
                               offset,
                               identifier,
                               identifier,
                               topOfTheStack);
        return result;
    }
}
