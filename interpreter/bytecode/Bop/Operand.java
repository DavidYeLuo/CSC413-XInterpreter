package interpreter.bytecode.Bop;

/**
 * Operand class used to represent an operand
 * in a valid mathematical expression.
 */
public class Operand
{
    private int value;

    /**
     * construct operand from string token.
     * We are given a string value token and we are going to store it as an integer.
     * We will convert the token into integer first and then store it.
     */
    public Operand(String token)
    {
        // Preparing to call parse.
        if(token == null)
        {
            System.out.println("ERROR: token is null in the Operand constructor.");

            // Exit
            return; // No point on parsing a null value
        }

        // Token is a string but we need integer values to later do operations.
        // We will use the Integer wrapper class to parse the token into an int.
        try
        {
            value = Integer.parseInt(token);
        }
        catch(Exception ex)
        {
            System.out.println("ERROR: Token isn't valid in the constructor argument."
                    + "Failed to parse token to Integer");
        }
    }

    /**
     * construct operand from integer
     */
    public Operand(int value)
    {
        this.value = value;
    }

    /**
     * return value of operand
     */
    public int getValue()
    {
        return this.value;
    }

    /**
     * Check to see if given token is a valid
     * operand.
     * This is true if we can convert the token into an integer and fail otherwise.
     */
    public static boolean check(String token)
    {
        // Preparing to call parseInt
        if (token == null)
        {
            System.out.println("ERROR: Token is null in check(token). Returning False");

            // Exit
            return false; // No point on parsing a Null
        }

        // Call parseInt
        try
        {
            Integer.parseInt(token);
        } catch (Exception ex)
        {
            // Token is not a valid operand.
            return false;
        }

        // Successfully parsed therefore token is a valid operand.
        return true;
    }
}
