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
//            System.out.println("ERROR: top of the stack is empty");
            return;
        }
        if(topStack == 0) virtualMachine.setProgramCounter(getAddress());
        if(topStack != 1) System.out.println("Error: value not expected");
    }


    @Override
    public String dump()
    {
        return "FALSEBRANCH " + getLabel();
    }
}
