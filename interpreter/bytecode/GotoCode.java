package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

public class GotoCode extends JumpCode
{
    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        virtualMachine.setProgramCounter(getAddress());
    }
}
