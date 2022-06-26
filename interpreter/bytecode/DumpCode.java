package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class DumpCode extends ByteCode
{
    private String mode;
    @Override
    public void init(ArrayList<String> args)
    {
        if(ByteCode.isArgsNullOrEmpty(args)) return;
        mode = args.get(0);
    }

    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        if(mode == null) return;

        boolean modeInBool;
        if(mode.equals("ON"))
        {
            modeInBool = true;
        }
        else if(mode.equals("OFF"))
        {
            modeInBool = false;
        }
        else
        {
            System.out.println("Not the right argument in the DumpCode.");
            modeInBool = false;
        }
        virtualMachine.setDumpMode(modeInBool);
    }
}
