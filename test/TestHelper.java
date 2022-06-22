package test;

import interpreter.virtualmachine.RunTimeStack;

public abstract class TestHelper
{
   /**
    * Pushes num amount of random values to the run time stack.
    * @param runTimeStack
    * @param num of times to add to the stack to the run time stack
    */
   public static void pushNStack(RunTimeStack runTimeStack, int num)
   {
      for(int i = 0; i < num; i++)
      {
         runTimeStack.push((int) (Math.random() * 1000));
      }
   }

   /**
    * Pops num amount of times in the run time stack
    * @param runTimeStack
    * @param num of times to be popped in the run time stack
    */
   public static void popNStack(RunTimeStack runTimeStack, int num)
   {
      for(int i = 0; i < num; i++)
      {
         runTimeStack.pop();
      }
   }
}
