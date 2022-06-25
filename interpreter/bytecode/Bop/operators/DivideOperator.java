package interpreter.bytecode.Bop.operators;


import interpreter.bytecode.Bop.Operand;

public class DivideOperator extends Operator
{
    /**
     * Perform math division operation.
     * @param leftNum first operand of operator in integer
     * @param rightNum second operand of operator in integer
     * @return left divided by right (left / right).
     */
    @Override
    public Operand execute(int leftNum, int rightNum)
    {
        int result = leftNum / rightNum;

        return new Operand(result);
    }
}
