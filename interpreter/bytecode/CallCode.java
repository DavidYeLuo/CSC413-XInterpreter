package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class CallCode extends JumpCode implements Dumpable
{
    private String identifier; // debug purpose
    private ArrayList<Integer> args;

    @Override
    public void init(ArrayList<String> args)
    {
        super.init(args);
        this.args = new ArrayList<>();
        if(ByteCode.isArgsNullOrEmpty(args)) return;
        identifier = args.get(0);
    }

    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        // Stores the return address for later.
        virtualMachine.pushReturnAddress( virtualMachine.getProgramCounter() );

        // Not a clever way to find arguments...
        // Trying to pop the frame to copy their value
        // and then push them back to preserve the state.
        int element = virtualMachine.pop(1);
        while(element != VirtualMachine.ERROR_RETURN_CODE)
        {
            args.add(element);
            element = virtualMachine.pop(1);
        }
        // push the arguments back
        for(int i = args.size() - 1; i >= 0; i++)
        {
            virtualMachine.push(args.get(i));
        }

        // Jumps to the label
        virtualMachine.setProgramCounter(getAddress());
    }

    @Override
    public String dump()
    {
        // Basic Syntax : CALL <id> <base-id>(<args>)
        StringBuilder result = new StringBuilder();

        result.append("CALL");
        if(identifier == null || args.isEmpty()) return result.toString();
        String argsFormat = args.toString().replace( "[", "(" )
                .replace("]", ")");

        result.append(String.format(" %s %s(%s)", identifier, getBaseId(identifier), argsFormat));

        return args.toString();
    }
}
