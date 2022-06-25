package interpreter.bytecode.Bop.operators;


import interpreter.bytecode.Bop.Operand;

public class AddOperator extends Operator
{
    /**
     * Perform math addition operation.
     * @param leftNum first operand of operator in integer
     * @param rightNum second operand of operator in integer
     * @return the sum of the two numbers.
     */
    @Override
    public Operand execute(int leftNum, int rightNum)
    {
        int result = leftNum + rightNum;

        return new Operand(result);
    }
}
