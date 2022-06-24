package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class GotoCode extends JumpCode
{
    @Override
    public void init(ArrayList<String> args)
    {
        super.init(args);
    }

    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        virtualMachine.goTo(getAddress());
    }

}
