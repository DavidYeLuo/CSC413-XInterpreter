package interpreter;

import interpreter.bytecode.ByteCode;
import interpreter.virtualmachine.Program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


public final class ByteCodeLoader
{

    private BufferedReader byteSource;

    /**
     * Constructor Simply creates a buffered reader.
     * YOU ARE NOT ALLOWED TO READ FILE CONTENTS HERE
     * THIS NEEDS TO HAPPEN IN loadCodes.
     */
    public ByteCodeLoader(String file) throws IOException
    {
        this.byteSource = new BufferedReader(new FileReader(file));
    }

    /**
     * This function should read one line of source code at a time.
     * For each read line:
     * Tokenize string to break it into parts. Can also use the split function in the String class.
     * Grab THE correct class name for the given ByteCode from CodeTable
     * Create an instance of the ByteCode class name returned from code table.
     * Parse any additional arguments for the given ByteCode and send them to
     * the newly created ByteCode instance via the init function.
     * Then add newly created and initialize ByteCode to the program
     */
    public Program loadCodes()
    {
        Program program = new Program();

        String[] items; // Will be used to store bytecode arguments
        String   line;

        String            byteCodeName;  // ByteCode name from .x.cod file.
        String
                          className;     // Classname after its mapped from name in source code to class name.
        Class             classBlueprint;
        ByteCode          bc;
        ArrayList<String> args;

        // Process every bytecode
        try
        {
            while (this.byteSource.ready())
            {
                // Tokenize read line.
                line  = this.byteSource.readLine();
                items = line.split("\\s+");

                // grab first token of line.
                byteCodeName = items[0];

                // grab class name from token.
                className = CodeTable.getClassName(byteCodeName);

                // load class blueprint from classname.
                classBlueprint = Class.forName("interpreter.bytecode." + className);

                // get declared constructor (should be no arg constructor).
                // create a new instance of bytecode using constructor.
                bc = (ByteCode) classBlueprint.getDeclaredConstructor().newInstance();

                args = new ArrayList<>();
                for (int i = 1; i < items.length; i++)
                {
                    args.add(items[i]);
                }
                // pass args to bytecode init function
                bc.init(args);

                // add bytecode to program
                program.add(bc);
                //                args.clear(); // Why do we need to do this? Doesn't breaks everything?
            }


        } catch (IOException | ClassNotFoundException | NoSuchMethodException |
                 InstantiationException | IllegalAccessException | InvocationTargetException e)
        {
            e.printStackTrace();
            System.err.println(e);
            System.exit(-2);
        }

        program.resolveAddress();
        return program;

        // Unknown step:
        // grab remaining bytecode.
    }
}
