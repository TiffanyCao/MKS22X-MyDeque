import java.util.*;

public class Calculator{
  public static double eval(String s){
    String[] operations = {"+", "-", "*", "/"};
    MyDeque<Double> calculate = new MyDeque<Double>();
    Scanner read = new Scanner(s);
    while(read.hasNext()){
      String temp = read.next();
      //System.out.println(temp);
      int operationIndex = -1;
      for(int i = 0; i < 4; i++){
        if(temp.equals(operations[i])) operationIndex = i;
      }
      //System.out.println(operationIndex);
      if(operationIndex == -1){
        calculate.addLast(Double.parseDouble(temp));
      }else if(operationIndex != -1){
        double right = calculate.removeLast();
        double left = calculate.removeLast();
        if(operationIndex == 0){
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
      }
    }
    return calculate.getFirst();
  }

  public static void main(String[] args){
    String s = "1 2 +";
    System.out.println(eval(s)); //is 3.0
    System.out.println(eval("10 2.0 +")); //is 12.0
    System.out.println(eval("11 3 - 4 + 2.5 *")); //is 30.0
    System.out.println(eval("8 2 + 99 9 - * 2 + 9 -")); //is 893.0
    System.out.println(eval("1 2 3 4 5 + * - -")); //is 26.0
  }
}
