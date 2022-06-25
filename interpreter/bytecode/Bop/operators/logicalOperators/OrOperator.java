package interpreter.bytecode.Bop.operators.logicalOperators;

import interpreter.bytecode.Bop.Operand;
import interpreter.bytecode.Bop.operators.Operator;

public class OrOperator extends Operator
{
    @Override
    public Operand execute(int leftNum, int rightNum)
    {
        int returnValue;
        returnValue = (leftNum + rightNum > 0)? 1:0;

        return new Operand(returnValue);
    }
}
