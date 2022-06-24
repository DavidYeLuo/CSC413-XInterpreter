package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class StoreCode extends ByteCode
{
    private int offset;
    private String identifier; // Used for Debug

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
    }
}
