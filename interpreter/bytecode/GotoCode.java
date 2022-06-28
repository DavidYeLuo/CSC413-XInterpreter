package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

public class GotoCode extends JumpCode implements Dumpable
{
    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        virtualMachine.setProgramCounter(getAddress());
    }

    @Override
    public String dump()
    {
        return "GOTO " + getLabel();
    }
}
