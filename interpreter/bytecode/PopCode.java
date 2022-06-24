package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class PopCode extends ByteCode
{
    private int popNumberOfTimes;

    @Override
    public void init(ArrayList<String> args)
    {
        if (args == null || !args.isEmpty()) return;
        try
        {
            popNumberOfTimes = Integer.parseInt(args.get(0));
        } catch (Exception e)
        {
            System.out.println("Error: Failed to parse with PopCode.");
        }
    }

    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        virtualMachine.pop(popNumberOfTimes);
    }
}
