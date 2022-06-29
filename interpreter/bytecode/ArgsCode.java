package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class ArgsCode extends ByteCode implements Dumpable
{
    private int numOfArgs;
    @Override
    public void init(ArrayList<String> args)
    {
        if(ByteCode.isArgsNullOrEmpty(args)) return;
        numOfArgs = ByteCode.parseInt(args.get(0));
    }

    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        virtualMachine.newFrameBelow(numOfArgs);
    }

    @Override
    public String dump()
    {
        return "ARGS " + numOfArgs;
    }
}
