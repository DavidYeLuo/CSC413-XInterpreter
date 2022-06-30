package interpreter.bytecode;

/**
 * Anything that derives from JumpCode will have their address resolved.
 */
public abstract class JumpCode extends LabelCode
{
    private int resolvedAddress;

    /**
     * Mainly used for the Program to give the resolvedAddress
     * @param resolvedAddress
     */
    public final void setAddress(int resolvedAddress)
    {
        this.resolvedAddress = resolvedAddress;
    }

    /**
     * Getter for derived classes
     * @return resolved address
     */
    protected final int getAddress()
    {
        return resolvedAddress;
    }
}
