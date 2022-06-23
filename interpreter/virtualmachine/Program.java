package interpreter.virtualmachine;

import interpreter.bytecode.ByteCode;

import java.util.*;

public class Program
{

    private List<ByteCode>    program;
    private LabelToAddressMap labelToAddressMap;

    public Program()
    {
        program           = new ArrayList<>();
        labelToAddressMap = new LabelToAddressMap();
    }

    protected ByteCode getCode(int programCounter)
    {
        return this.program.get(programCounter);
    }

    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter
     * HINT: make note what type of data-structure ByteCodes are stored in.
     * **** METHOD SIGNATURE CANNOT BE CAHNGED *****
     */
    public void resolveAddress()
    {
        // We're using a stack to save us from needing to loop through the program again.
        Stack<ByteCode> jumpableStack = new Stack<>();

        for (ByteCode byteCode : program)
        {
            // 1st pass through the arrayList keeping track of label codes and their labels.
            if (byteCode.getClass() == LabelCode.class) // Only allow labels
            {
                // Need to grab label from the ByteCode
                labelToAddressMap.add(byteCode.getLabel());
            }

            // Keep track of gotoByteCode to resolve it later.
            if (byteCode instanceof JumpCode)
            {
                jumpableStack.add(byteCode);
            }
        }
        // 2nd pass through the arrayList look for call, goto and falsebranch codes
        // and do the following:
        //  Look at stored label codes and find the 1 that has the match label value.

        // Set the address of from the bytecode to the address retrieved from our map
        ByteCode byteCode;
        int      address;
        while (!jumpableStack.isEmpty())
        {
            byteCode = jumpableStack.pop();
            address  = labelToAddressMap.get(byteCode.getLabel());
            byteCode.setAddress(address);
        }
    }


    public void add(ByteCode bc)
    {
        program.add(bc);
    }

    private class LabelToAddressMap
    {
        private Map<String, Integer> map;
        private int                  latestProgramCounter;

        LabelToAddressMap()
        {
            map                  = new HashMap<>();
            latestProgramCounter = 0;
        }

        public void add(String label)
        {
            map.put(label, latestProgramCounter);
            latestProgramCounter++;
        }

        public int get(String label)
        {
            return map.get(label);
        }
    }
}

