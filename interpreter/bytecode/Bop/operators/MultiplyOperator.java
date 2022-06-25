package interpreter.bytecode.Bop.operators;


import interpreter.bytecode.Bop.Operand;

public class MultiplyOperator extends Operator
{
    /**
     * Perform math multiplication operation.
     * @param leftNum first operand of operator in integer
     * @param rightNum second operand of operator in integer
     * @return left times right (left * right).
     */
    @Override
    public Operand execute(int leftNum, int rightNum)
    {
        int result = leftNum * rightNum;

        return new Operand(result);
    }
}
