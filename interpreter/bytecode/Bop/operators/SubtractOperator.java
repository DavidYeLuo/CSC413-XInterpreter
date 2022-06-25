package interpreter.bytecode.Bop.operators;


import interpreter.bytecode.Bop.Operand;

public class SubtractOperator extends Operator
{
    /**
     * Perform math subtraction operation.
     * @param leftNum first operand of operator in integer
     * @param rightNum second operand of operator in integer
     * @return Takes the difference of the two number (left - right).
     */
    @Override
    public Operand execute(int leftNum, int rightNum)
    {
        int result = leftNum - rightNum;

        return new Operand(result);
    }
}
