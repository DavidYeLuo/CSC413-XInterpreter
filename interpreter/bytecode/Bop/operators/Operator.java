package interpreter.bytecode.Bop.operators;

import interpreter.bytecode.Bop.Operand;
import interpreter.bytecode.Bop.operators.logicalOperators.*;

import java.util.HashMap;
import java.util.Map;

public abstract class Operator {
    // Register hashmap in the static initialization
    private static Map<String, Operator> operatorMap;
    private static Map<Class<? extends Operator>, Integer> priorityMap;

    // Static initialization
    static
    {
        operatorMap = new HashMap<>();

        // Register new operators here
        operatorMap.put("+", new AddOperator());
        operatorMap.put("-", new SubtractOperator());
        operatorMap.put("*", new MultiplyOperator());
        operatorMap.put("/", new DivideOperator());

        // Logical Operators
        operatorMap.put("==", new EqualOperator());
        operatorMap.put("!=", new NotEqualOperator());
        operatorMap.put("<", new LessThanOperator());
        operatorMap.put("<=", new LessThanEqualOperator());
        operatorMap.put(">", new GreaterThanOperator());
        operatorMap.put(">=", new GreaterThanEqualOperator());
        operatorMap.put("|", new OrOperator());
        operatorMap.put("&", new AndOperator());

        priorityMap = new HashMap<>();

        // Set priorities for each operators here
        // Higher priority means that the operation is executed first.
        // **Important!** Registered operator should also assign a priority
        priorityMap.put(AddOperator.class, 1);
        priorityMap.put(SubtractOperator.class, 1);
        priorityMap.put(MultiplyOperator.class, 2);
        priorityMap.put(DivideOperator.class, 2);
    }

    /**
     * retrieve the priority of an Operator
     * @return priority of an Operator as an int
     */
    public int getPriority() // Not sure if this will break the grading
    {
        // This method is no longer abstract.
        // To prevent hardcoding priorities in each subclasses,
        // I'm using a hashmap that takes in a class type as key and a priority as value.
        return priorityMap.get(this.getClass());
    }

    /**
     * Method to execute an operator given two operands.
     * @param operandOne first operand of operator (Also known as left operand)
     * @param operandTwo second operand of operator (Also known as right operand)
     * @return an operand of the result of the operation.
     */
    public Operand execute(Operand operandOne, Operand operandTwo)
    {
        // Error checking
        if(operandOne == null && operandTwo == null)
        {
            System.out.println("ERROR: Operand(s) null");
            return null;
        }

        int leftValue = operandOne.getValue();
        int rightValue = operandTwo.getValue();

        return execute(leftValue, rightValue);
    }

    /**
     * Abstract method to execute an operator given two integers.
     * @param leftNum first operand of operator in integer
     * @param rightNum second operand of operator in integer
     * @return an operand of the result of the operation.
     */
    public abstract Operand execute(int leftNum, int rightNum);

    /**
     * used to retrieve an operator from our HashMap.
     * This will act as out publicly facing function,
     * granting access to the Operator HashMap.
     *
     * @param token key of the operator we want to retrieve
     * @return reference to a Operator instance.
     * Note: This can return a null if the operator isn't found in the hashmap.
     */
    public static Operator getOperator(String token)
    {
        return operatorMap.get(token);
    }

    
     /**
     * determines if a given token is a valid operator.
     * please do your best to avoid static checks
     * for example token.equals("+") and so on.
     * Think about what happens if we add more operators.
     */
    public static boolean check(String token)
    {
        // The operator map returns a null when an operator is not found.
        // So we return true when we can find it in the hashmap
        // and false when it isn't found.
        return ( operatorMap.get(token) != null );
    }
}
