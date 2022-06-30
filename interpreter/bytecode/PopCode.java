package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class PopCode extends ByteCode implements Dumpable
{
    private int popNumberOfTimes;

    @Override
    public void init(ArrayList<String> args)
    {
        popNumberOfTimes = ByteCode.parseInt(args.get(0));
    }

    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        virtualMachine.pop(popNumberOfTimes);
    }

    @Override
    public String dump()
    {
        return "POP " + popNumberOfTimes;
    }
}
