import java.util.*;

public class Calculator{

  /**A method that reads in a string of an postfix expression and evaluates it
  *@param String postfix expression
  *@return double answer
  */
  public static double eval(String s){
    String[] operations = {"+", "-", "*", "/", "%"}; //list of operations
    MyDeque<Double> calculate = new MyDeque<Double>(); //create a deque
    Scanner read = new Scanner(s);
    while(read.hasNext()){ //read in the whole expression one-by-one
      String temp = read.next();
      //System.out.println(temp);
      int operationIndex = -1;
      for(int i = 0; i < 5; i++){ //check if it's an operation
        if(temp.equals(operations[i])) operationIndex = i;
      }
      //System.out.println(operationIndex);
      if(operationIndex == -1){ //if it's not an operation, add the number to the deque
        calculate.addLast(Double.parseDouble(temp));
        /*System.out.println(calculate);
        System.out.println(calculate.size());
        System.out.println();
        */
      }else if(operationIndex != -1){ //if it is an operation
        double right = calculate.removeLast(); //remove the last two values
        double left = calculate.removeLast();
        if(operationIndex == 0){ //check for which operation it is and perform it on the values
          calculate.addLast(left + right);
        }
        if(operationIndex == 1){
          calculate.addLast(left - right);
        }
        if(operationIndex == 2){
          calculate.addLast(left * right);
        }
        if(operationIndex == 3){
          calculate.addLast(left / right);
        }
        if(operationIndex == 4){
          calculate.addLast(left % right);
        }
        /*System.out.println(calculate);
        System.out.println(calculate.size());
        System.out.println();
        */
      }
    }
    return calculate.getFirst(); //return the only value left in the deque
  }

  public static void main(String[] args){
    String s = "1 2 +";
    System.out.println(eval(s)); //is 3.0
    System.out.println(eval("10 2.0 +")); //is 12.0
    System.out.println(eval("11 3 - 4 + 2.5 *")); //is 30.0
    System.out.println(eval("8 2 + 99 9 - * 2 + 9 -")); //is 893.0
    System.out.println(eval("1 2 3 4 5 + * - -")); //is 26.0
    System.out.println(eval("10 5 % 5 + 20 + 5 %")); //is 0
    System.out.println(eval("4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 4 + + + + + + + + + + + + + + + + + + + + + + + + + + +")); //is 108.0
  }
}
