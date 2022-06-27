import interpreter.virtualmachine.RunTimeStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("RunTimeStack_DumpTest")
public class DumpTest
{
    private RunTimeStack runTimeStack;

    // Init is called in every test
    @BeforeEach
    void init() { runTimeStack = new RunTimeStack(); }

    @AfterEach
    void tearDown() { runTimeStack = null; }

    @Test
    void simpleTest()
    {
        for(int i = 1; i <= 8; i++)
        {
            runTimeStack.push(i);
            if(i == 3 || i == 6)
            {
                runTimeStack.newFrameAt(0);
            }
        }
        System.out.println("*** RunTimeStack");
        System.out.println("Dump Test");
        System.out.println("Expected: [1,2,3] [4,5,6] [7,8]");
        System.out.print("Result: ");

        // Not sure why dump() doesn't return a string value so this is how we are testing
        runTimeStack.dump();

        System.out.println("***");
    }
}
