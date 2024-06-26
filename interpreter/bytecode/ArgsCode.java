package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class ArgsCode extends ByteCode implements Dumpable
{
    private int numOfArgs;

    @Override
    public void init(ArrayList<String> args)
    {
        numOfArgs = ByteCode.parseInt(args.get(0));
    }

    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        virtualMachine.setNumOfArgs(numOfArgs);
        virtualMachine.newFrameAt(numOfArgs);
    }

    @Override
    public String dump()
    {
        return "ARGS " + numOfArgs;
    }
}
