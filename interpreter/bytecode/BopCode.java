package interpreter.bytecode;

import interpreter.bytecode.Bop.Operand;
import interpreter.bytecode.Bop.operators.Operator;
import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class BopCode extends ByteCode implements Dumpable
{
    private String strOperator;

    @Override
    public void init(ArrayList<String> args)
    {
        strOperator = args.get(0);
    }

    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        // Retrieve operands
        int rightValue = virtualMachine.pop(1);
        int leftValue  = virtualMachine.pop(1);

        // Retrieve operator
        Operator operator = Operator.getOperator(strOperator);

        // Perform calculation
        Operand result = operator.execute(leftValue, rightValue);

        // Push the result to the top of the stack
        virtualMachine.push(result.getValue());
    }

    @Override
    public String dump()
    {
        return "BOP " + strOperator;
    }
}
