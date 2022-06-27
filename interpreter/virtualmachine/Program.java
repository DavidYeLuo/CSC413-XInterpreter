package interpreter.virtualmachine;

import interpreter.bytecode.ByteCode;
import interpreter.bytecode.JumpCode;
import interpreter.bytecode.LabelCode;

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
        Stack<JumpCode> jumpableStack = new Stack<>();

        for (int i = 0; i < program.size(); i++)
        {
            ByteCode byteCode = program.get(i);

            // 1st pass through the arrayList keeping track of label codes and their labels.
            if (byteCode.getClass() == LabelCode.class) // Only allow labels
            {
                // Need to grab label from the ByteCode
                LabelCode labelCode = (LabelCode) byteCode;
                labelToAddressMap.add(labelCode.getLabel());
            }

            // Keep track of gotoByteCode to resolve it later.
            if (byteCode instanceof JumpCode)
            {
                jumpableStack.add((JumpCode) byteCode);
            }
            labelToAddressMap.incrementCounter();
        }
        // 2nd pass through the arrayList look for call, goto and falsebranch codes
        // and do the following:
        //  Look at stored label codes and find the 1 that has the match label value.

        // Set the address of from the bytecode to the address retrieved from our map
        JumpCode jumpCode;
        int      address;
        while (!jumpableStack.isEmpty())
        {
            jumpCode = jumpableStack.pop();
            address  = labelToAddressMap.get(jumpCode.getLabel());
            jumpCode.setAddress(address);
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
        }

        public void incrementCounter()
        {
            latestProgramCounter++;
        }

        public int get(String label)
        {
            return map.get(label);
        }
    }
}

