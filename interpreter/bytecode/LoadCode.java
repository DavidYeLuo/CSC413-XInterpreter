package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class LoadCode extends ByteCode implements Dumpable
{
    private int    offset;
    private String identifier; // Used for Debug

    @Override
    public void init(ArrayList<String> args)
    {
        offset = ByteCode.parseInt(args.get(0));
        if (args.size() > 1) identifier = args.get(1);
    }

    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        virtualMachine.load(offset);
    }

    @Override
    public String dump()
    {
        String result;

        // Basic Syntax : LOAD <offset> <id> <load id>
        if (identifier == null)
        {
            result = String.format("LOAD %d", offset);
            return result;
        }
        result = String.format("LOAD %d %s <load %s>", offset, identifier, identifier);
        return result;
    }
}
