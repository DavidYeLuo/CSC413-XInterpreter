package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class LitCode extends ByteCode implements Dumpable
{
    private int literal;
    private String identifier;

    @Override
    public void init(ArrayList<String> args)
    {
        if(ByteCode.isArgsNullOrEmpty(args)) return;
        literal = ByteCode.parseInt(args.get(0));
        if(args.size() > 1) identifier = args.get(1);
    }

    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        virtualMachine.push(literal);
    }

    @Override
    public String dump()
    {
        String result;

        // Basic Syntax : LIT <value> <id> int <id>
        if(identifier == null)
        {
            result = String.format("LIT %d", literal);
            return result;
        }
        result = String.format("LIT %d %s int %s",
                               literal,
                               identifier,
                               identifier);
        return result;
    }
}
