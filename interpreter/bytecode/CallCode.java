package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CallCode extends JumpCode implements Dumpable
{
    private String        identifier; // debug purpose
    private List<Integer> args;

    @Override
    public void init(ArrayList<String> args)
    {
        super.init(args);
        if (ByteCode.isArgsNullOrEmpty(args)) return;
        identifier = args.get(0);
    }

    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        // Retrieves args from the vm
        args = virtualMachine.getArgs();

        // Stores the return address for later.
        virtualMachine.pushReturnAddress(virtualMachine.getProgramCounter());

        // Jumps to the label
        virtualMachine.setProgramCounter(getAddress());
    }

    @Override
    public String dump()
    {
        // Basic Syntax : CALL <id> <base-id>(<args>)
        StringBuilder result = new StringBuilder();

        result.append("CALL");

        if (identifier == null) return result.toString();
        result.append(" " + identifier);
        String argsFormat = args.toString().replace("[", "").replace("]", "");

        result.append(String.format(" %s(%s)", getBaseId(identifier), argsFormat));

        return result.toString();
    }
}
