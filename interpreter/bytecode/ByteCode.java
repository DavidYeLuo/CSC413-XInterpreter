package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public abstract class ByteCode
{
    public abstract void init(ArrayList<String> args);

    public abstract void execute(VirtualMachine virtualMachine);

    // Helper functions below

    /**
     * Parse string to an int
     * If fail just return lowest integer value.
     * @param stringNumber
     * @return integer value
     */
    protected static int parseInt(String stringNumber)
    {
        int returnValue;
        try
        {
            returnValue = Integer.parseInt(stringNumber);

            return returnValue;
        } catch (Exception e)
        {
            System.out.println("Error: Failed to parse in one of the ByteCodes." + "Returning lowest int value.");
            returnValue = Integer.MIN_VALUE;

            return returnValue;
        }
    }

    protected static boolean isArgsNullOrEmpty(ArrayList<String> args)
    {
        return args == null || args.isEmpty();
    }

    protected static String getBaseId(String id)
    {
        return id.substring(0, id.indexOf("<"));
    }
}