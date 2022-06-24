package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class FalseBranchCode extends JumpCode
{
    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        int topStack = virtualMachine.pop(1);
        if(topStack == 0) virtualMachine.goTo(getAddress());
    }
}
