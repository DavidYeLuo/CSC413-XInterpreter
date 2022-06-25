package interpreter.bytecode.Bop.operators.logicalOperators;

import interpreter.bytecode.Bop.Operand;
import interpreter.bytecode.Bop.operators.Operator;

public class LessThanOperator extends Operator
{
    @Override
    public Operand execute(int leftNum, int rightNum)
    {
        int returnValue;
        returnValue = (leftNum < rightNum)? 1:0;

        return new Operand(returnValue);
    }
}
