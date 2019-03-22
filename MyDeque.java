public class MyDeque<E>{

  private E[] data;
  private int size, start, end;

  public MyDeque(){
    @SuppressWarnings("unchecked")
    E[] d = (E[]) new Object[10];
    data = d;
  }

  public MyDeque(int initialCapacity){
    @SuppressWarnings("unchecked")
    E[] d = (E[]) new Object[initialCapacity];
    data = d;
  }

  public int size(){
    return size;
  }

  public String print(){
    String result = "[";
    for(int i = 0; i < data.length; i++){
      if(i == data.length - 1){
        result += data[i] + "]";
      }else result += data[i] + ", ";
    }
    return result;
  }

  public String toString(){
    String result = "[";
    if(start != 0 && end < start){
      for(int i = start; i < (size - start) + start; i++){
        result += data[i] + ", ";
      }
      for(int y = 0; y <= end; y++){
        if(y != end){
          result += data[y] + ", ";
        }else result += data[y];
      }
    }else if(end > start){
      for(int i = start; i <= end; i++){
        if(i != end){
          result += data[i] + ", ";
        }else result += data[i];
      }
    }
    result += "]";
    return result;
  }

  public void addFirst(E element){
    if(size == 0){ //if the array is empty
      data[0] = element;
      start = 0;
      end = 0;
      size++;
      //System.out.println(1);
    }else if(size != data.length && start == 0){ //if more elements can be added at the end and be labeled as start
      data[end + 1] = element;
      start = end + 1;
      size++;
      //System.out.println(2);
    }else if(size != data.length && start != 0 && end != (start - 1)){ //if start is in the middle of the array and more elements can be added before it
      data[start - 1] = element;
      start--;
      size++;
      //System.out.println(3);
    }else if((start != 0 && end == (start - 1)) || size == data.length){ //if not, resize and add
      resize(element);
    }
  }

  public void addLast(E element){
    if(size != data.length && end >= start && end < (data.length - 1)){ //if more elements can be added at the end of the array
      data[end + 1] = element;
      end++;
      size++;
    }else if(size != data.length && end == (data.length - 1)){ //if more elements can be added to the start of the array
      data[0] = element;
      end = 0;
      size++;
    }else if(size != data.length && end < start){ //if more elements can be added before the start index;
      data[end + 1] = element;
      end++;
      size++;
    }else if(size == data.length || end == (start - 1)){ //if no more elements can be added, resize then add
      resize();
      data[end + 1] = element;
      end++;
      size++;
    }
  }

  public void resize(){
    @SuppressWarnings("unchecked")
    E[] d = (E[]) new Object[data.length * 2 + 1];
    if(start != 0 && end < start){
      int index = 0;
      for(int i = start; i < data.length; i++){
        d[index] = data[i];
        index++;
      }
      for(int y = 0; y <= end; y++){
        d[index] = data[y];
        index++;
      }
    }else if(end > start){
      int index = 0;
      for(int i = start; i <= end; i++){
        d[index] = data[i];
        index++;
      }
    }
    data = d;
    start = 0;
    end = this.size() - 1;
  }

  public void resize(E element){
    @SuppressWarnings("unchecked")
    E[] d = (E[]) new Object[data.length * 2 + 1];
    int index = 1;
    d[0] = element;
    for(int i = start; i < (size - start) + start; i++){
      d[index] = data[i];
    }
    for(int y = 0; y <= end; y++){
      d[index] = data[y];
    }
    size++;
    data = d;
    start = 0;
    end = this.size() - 1;
  }

  public static void main(String[] args){
    MyDeque<Integer> test = new MyDeque<Integer>(2);
    test.addFirst(9);
    test.addFirst(8);

    System.out.println(test.print());
    System.out.println(test);
    System.out.println(test.start);
    System.out.println(test.end);

    test.resize();
    System.out.println();
    System.out.println(test.print());
    System.out.println(test);
    System.out.println(test.start);
    System.out.println(test.end);


    test.addLast(7);
    System.out.println();
    System.out.println(test.print());
    System.out.println(test);
    System.out.println(test.start);
    System.out.println(test.end);

    test.addLast(6);
    System.out.println();
    System.out.println(test.print());
    System.out.println(test);
    System.out.println(test.start);
    System.out.println(test.end);

    test.addLast(5);
    System.out.println();
    System.out.println(test.print());
    System.out.println(test);
    System.out.println(test.start);
    System.out.println(test.end);

    test.addLast(4);
    System.out.println();
    System.out.println(test.print());
    System.out.println(test);
    System.out.println(test.start);
    System.out.println(test.end);

    test.addFirst(0);
    System.out.println();
    System.out.println(test.print());
    System.out.println(test);
    System.out.println(test.start);
    System.out.println(test.end);
    /*test.addLast(6);
    test.addLast(5);
    test.addLast(4);
    test.addLast(3);
    test.addLast(2);
    test.addLast(1);
    test.addLast(0);
    */

    /*System.out.println(test.print());
    System.out.println(test);
    System.out.println(test.start);
    System.out.println(test.end);
    */
  }
}
