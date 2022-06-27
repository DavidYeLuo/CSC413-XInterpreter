package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

public class CallCode extends JumpCode
{
    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        // Stores the return address for later.
        virtualMachine.pushReturnAddress( virtualMachine.getProgramCounter() );

        // Jumps to the label
        virtualMachine.setProgramCounter(getAddress());
    }
}
