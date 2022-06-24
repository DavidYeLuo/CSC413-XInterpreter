package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class ArgsCode extends ByteCode
{
    private int numOfArgs;
    @Override
    public void init(ArrayList<String> args)
    {
        if(args == null || !args.isEmpty()) return;
        try
        {
            numOfArgs = Integer.parseInt(args.get(0));
        } catch (Exception e)
        {
            System.out.println("Error: Failed to parse with StoreCode.");
        }
    }

    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        virtualMachine.newFrameAt(numOfArgs);
    }
}
