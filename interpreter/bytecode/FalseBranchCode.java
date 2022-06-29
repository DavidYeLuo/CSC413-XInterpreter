package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

public class FalseBranchCode extends JumpCode implements Dumpable
{
    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        int topStack = virtualMachine.pop(1);
        if(topStack == VirtualMachine.ERROR_RETURN_CODE)
        {
            return;
        }
        if(topStack == 0) virtualMachine.setProgramCounter(getAddress());
    }


    @Override
    public String dump()
    {
        return "FALSEBRANCH " + getLabel();
    }
}
