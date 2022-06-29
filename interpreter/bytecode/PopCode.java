package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class PopCode extends ByteCode implements Dumpable
{
    private int popNumberOfTimes;

    @Override
    public void init(ArrayList<String> args)
    {
        if(ByteCode.isArgsNullOrEmpty(args)) return;
        popNumberOfTimes = ByteCode.parseInt(args.get(0));
    }

    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        if(popNumberOfTimes == VirtualMachine.ERROR_RETURN_CODE)
        {
            System.out.println("ERROR: pop has an error code in the param");
            return;
        }
        virtualMachine.pop(popNumberOfTimes);
    }

    @Override
    public String dump()
    {
        return "POP " + popNumberOfTimes;
    }
}
