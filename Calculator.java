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
      }
      if(operationIndex != -1){
        double right = calculate.removeLast();
        double left = calculate.removeLast();
        if(operationIndex == 0){
          calculate.addLast(left + right);
        }
        if(operationIndex == 1){
          calculate.addLast(left - right);
        }
      }
    }
    return 0.0;
  }

  public static void main(String[] args){
    String s = "1 2 +";
    eval(s);
  }
}
