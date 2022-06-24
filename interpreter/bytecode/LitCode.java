package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class LitCode extends ByteCode
{
    private int literal;
    private String identifier;

    @Override
    public void init(ArrayList<String> args)
    {
        if(args == null || !args.isEmpty()) return;
        try
        {
            literal = Integer.parseInt(args.get(0));
        } catch (Exception e)
        {
            System.out.println("Error: Failed to parse with LitCode.");
        }
        if(args.size() > 1) identifier = args.get(1);
    }

    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        virtualMachine.push(literal);
    }
}
