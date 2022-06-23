import interpreter.virtualmachine.RunTimeStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        runTimeStack.dump();
    }
}
