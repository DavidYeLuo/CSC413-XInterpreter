package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class LabelCode extends ByteCode
{
    private String label;

    @Override
    public void init(ArrayList<String> args)
    {
        if(ByteCode.isArgsNullOrEmpty(args)) return;
        label = args.get(0);
    }

    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        // No functionality
    }

    /**
     * Main purpose is to register this label in the program
     * so that it can resolve address later.
     */
    public final String getLabel()
    {
        return label;
    }
}
