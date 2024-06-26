package interpreter.bytecode;

import interpreter.bytecode.ByteCode;
import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;
import java.util.Scanner;

public class ReadCode extends ByteCode implements Dumpable
{
    private int userValue;

    private static Scanner scanner;

    static
    {
        scanner = new Scanner(System.in);
    }

    @Override
    public void init(ArrayList<String> args) {}

    @Override
    public void execute(VirtualMachine virtualMachine)
    {
        // Ask user for input. Repeat if value isn't an integer.
        boolean isValidInput = false;
        while (!isValidInput)
        {
            // Prompt user
            System.out.print("Please enter an integer : ");

            try
            {
                userValue    = scanner.nextInt();
                isValidInput = true;
            } catch (Exception e)
            {
                scanner.nextLine();
            }
        }
        virtualMachine.push(userValue);
    }

    @Override
    public String dump()
    {
        return "READ";
    }
}
