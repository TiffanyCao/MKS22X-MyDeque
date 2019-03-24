import java.util.*;

public class MyDeque<E>{

  private E[] data;
  private int size, start, end;

  /**A constructor for MyDeque
  *creates an array of size 10 by default
  */
  public MyDeque(){
    @SuppressWarnings("unchecked")
    E[] d = (E[]) new Object[10];
    data = d;
    size = 0;
  }

  /**A second constructor for specified array size
  *@param int initialCapacity is the given array size
  */
  public MyDeque(int initialCapacity){
    @SuppressWarnings("unchecked")
    E[] d = (E[]) new Object[initialCapacity];
    data = d;
    size = 0;
  }

  /**A method that returns the size or number of objects in the array
  *@return int size
  */
  public int size(){
    return size;
  }

  /**A method that returns the element at start
  *@return E
  *@throws NoSuchElementException if deque is empty
  */
  public E getFirst(){
    if(size == 0){
      throw new NoSuchElementException();
    }
    return data[start];
  }

  /**A method that returns the element at end
  *@return E
  *@throws NoSuchElementException if deque is empty
  */
  public E getLast(){
    if(size == 0){
      throw new NoSuchElementException();
    }
    return data[end];
  }

  /**A method that prints the array as is (not in order)
  @return String result
  */
  public String toString(){
    String result = "{";
    for(int i = 0; i < data.length; i++){ //loops through data
      if(i == data.length - 1){
        result += data[i] + "}";
      }else result += data[i] + " ";
    }
    return result;
  }

  /**A method that prints the array in order from smallest value to greatest value
  @return String result
  */
  public String print(){
    String result = "{";
    if(start == end){
      result += data[start];
    }else if(start != 0 && end < start){ //if end is less than start...
      for(int i = start; i < data.length; i++){ //add values from start first
        if(data[i] != null) result += data[i] + " ";
      }
      for(int y = 0; y <= end; y++){ //then add values up to the end
        if(y != end){
          result += data[y] + " ";
        }else result += data[y];
      }
    }else if(end > start){ //if end isn't less than start...
      for(int i = start; i <= end; i++){ //add from start to end
        if(i != end){
          result += data[i] + " ";
        }else result += data[i];
      }
    }
    result += "}";
    return result; //return the string
  }

  /**A method that adds an element as start
  *@param E element
  *@throws NullPointerException element should not be null
  */
  public void addFirst(E element){
    if(element == null){ //if the element is nu;;
      throw new NullPointerException();
    }
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
    }else if((start != 0 && end == (start - 1)) || size == data.length){ //if no more elements can be added, resize then add as start
      resize(element);
      /*data[end + 1] = element;
      start = end + 1;
      size++;
      */
    }
  }

  /**A method that adds an element as end
  *@param E element
  *@throws NullPointerException element should not be null
  *element should not be null
  */
  public void addLast(E element){
    if(element == null){ //if the element is null
      throw new NullPointerException();
    }
    if(size == 0){ //if this is the first element
      data[0] = element;
      start = 0;
      end = 0;
      size++;
    }else if(size != data.length && end >= start && end < (data.length - 1)){ //if more elements can be added toward the back of the array
      data[end + 1] = element;
      end++;
      size++;
      //System.out.println(1);
    }else if(size != data.length && end == (data.length - 1)){ //if the end index is at the last index of the array but more elements can be added to the front
      data[0] = element;
      end = 0;
      size++;
      //System.out.println(2);
    }else if(size != data.length && end < start && (end != start - 1)){ //if more elements can be added before the start index;
      data[end + 1] = element;
      end++;
      size++;
      //System.out.println(3);
    }else if(size == data.length || end == (start - 1)){ //if no more elements can be added, resize then add
      resize();
      data[end + 1] = element;
      end++;
      size++;
      //System.out.println(4);
    }
  }

  /**A method that removes the element at the start index
  *@return E element
  *@throws NoSuchElementException if deque is empty
  */
  public E removeFirst(){
    if(size == 0){ //if the deque is empty
      throw new NoSuchElementException();
    }
    E temp = data[start]; //store the element
    data[start] = null; //set element at index to null
    size--; //decrease the size
    if((((data.length - size) + (Math.abs(end - start))) != start) && (start != size) && start != ((Math.abs(end - start)) + end)){ //if start isn't the last index with a value
      //System.out.println(((data.length - size) + (Math.abs(end - start))));
      //System.out.println(size);
      //System.out.println(start);
      start++; //start index increases
      return temp;
    }else{ //if start is the last index
      start = 0; //start index becomes 0
      return temp;
    }
  }

  public E removeLast(){
    if(size == 0){
      throw new NoSuchElementException();
    }
    E temp = data[end];
    data[end] = null;
    size--;
    if(end != 0){
      end--;
      return temp;
    }else{
      end = (start + size) - 1;
      return temp;
    }
  }

  /**A method that creates a larger array than the current array and copies over the elements in order
  */
  public void resize(){
    @SuppressWarnings("unchecked")
    E[] d = (E[]) new Object[data.length * 2 + 1]; //new empty array
    if(start != 0 && end < start){ //if start is not the zero index and end is less than start...
      int index = 0;
      for(int i = start; i < data.length; i++){ //copy over from start
        if(data[i] != null){
          d[index] = data[i];
          index++;
        }
      }
      for(int y = 0; y <= end; y++){ //copy over from zero to end
        d[index] = data[y];
        index++;
      }
    }else if(end >= start){ //otherwise, copy over from end to start
      int index = 0;
      for(int i = start; i <= end; i++){
        d[index] = data[i];
        index++;
      }
    }
    data = d; //set data to new, larger array
    start = 0; //set start to zero
    end = this.size() - 1; //set end to size-1
  }

  public void resize(E element){
    @SuppressWarnings("unchecked")
    E[] d = (E[]) new Object[data.length * 2 + 1];
    d[0] = element;
    if(start != 0 && end < start){
      int index = 1;
      for(int i = start; i < data.length; i++){
        if(data[i] != null){
          d[index] = data[i];
          index++;
        }
      }
      for(int y = 0; y <= end; y++){
        d[index] = data[y];
        index++;
      }
    }else if(end >= start){
      int index = 1;
      for(int i = start; i <= end; i++){
        d[index] = data[i];
        index++;
      }
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

    System.out.println(test);
    System.out.println("order: " + test.print());
    System.out.println("start: " + test.start);
    System.out.println("end: " + test.end);
    System.out.println("size: " + test.size());

    test.resize();
    System.out.println();
    System.out.println(test);
    System.out.println("order: " + test.print());
    System.out.println("start: " + test.start);
    System.out.println("end: " + test.end);
    System.out.println("size: " + test.size());


    test.addLast(7);
    System.out.println();
    System.out.println(test);
    System.out.println("order: " + test.print());
    System.out.println("start: " + test.start);
    System.out.println("end: " + test.end);
    System.out.println("size: " + test.size());

    test.addLast(6);
    System.out.println();
    System.out.println(test);
    System.out.println("order: " + test.print());
    System.out.println("start: " + test.start);
    System.out.println("end: " + test.end);
    System.out.println("size: " + test.size());

    test.addLast(5);
    System.out.println();
    System.out.println(test);
    System.out.println("order: " + test.print());
    System.out.println("start: " + test.start);
    System.out.println("end: " + test.end);
    System.out.println("size: " + test.size());

    test.addLast(4);
    System.out.println();
    System.out.println(test);
    System.out.println("order: " + test.print());
    System.out.println("start: " + test.start);
    System.out.println("end: " + test.end);
    System.out.println("size: " + test.size());

    test.addFirst(0);
    System.out.println();
    System.out.println(test);
    System.out.println("order: " + test.print());
    System.out.println("start: " + test.start);
    System.out.println("end: " + test.end);
    System.out.println("size: " + test.size());

    test.addFirst(1);
    System.out.println();
    System.out.println(test);
    System.out.println("order: " + test.print());
    System.out.println("start: " + test.start);
    System.out.println("end: " + test.end);
    System.out.println("size: " + test.size());

    test.addFirst(2);
    System.out.println();
    System.out.println(test);
    System.out.println("order: " + test.print());
    System.out.println("start: " + test.start);
    System.out.println("end: " + test.end);
    System.out.println("size: " + test.size());

    System.out.println();
    System.out.println("removedFirst: " + test.removeFirst());
    System.out.println(test);
    System.out.println("order: " + test.print());
    System.out.println("start: " + test.start);
    System.out.println("end: " + test.end);
    System.out.println("size: " + test.size());


    System.out.println();
    System.out.println("removedFirst: " + test.removeFirst());
    System.out.println(test);
    System.out.println("order: " + test.print());
    System.out.println("start: " + test.start);
    System.out.println("end: " + test.end);
    System.out.println("size: " + test.size());

    test.addFirst(-1);
    System.out.println();
    System.out.println(test);
    System.out.println("order: " + test.print());
    System.out.println("start: " + test.start);
    System.out.println("end: " + test.end);
    System.out.println("size: " + test.size());

    test.addFirst(-2);
    System.out.println();
    System.out.println(test);
    System.out.println("order: " + test.print());
    System.out.println("start: " + test.start);
    System.out.println("end: " + test.end);
    System.out.println("size: " + test.size());

    test.addLast(10);
    System.out.println();
    System.out.println(test);
    System.out.println("order: " + test.print());
    System.out.println("start: " + test.start);
    System.out.println("end: " + test.end);
    System.out.println("size: " + test.size());

    System.out.println();
    System.out.println("removedLast: " + test.removeLast());
    System.out.println(test);
    System.out.println("order: " + test.print());
    System.out.println("start: " + test.start);
    System.out.println("end: " + test.end);
    System.out.println("size: " + test.size());

    System.out.println();
    System.out.println("removedLast: " + test.removeLast());
    System.out.println(test);
    System.out.println("order: " + test.print());
    System.out.println("start: " + test.start);
    System.out.println("end: " + test.end);
    System.out.println("size: " + test.size());

    System.out.println();
    MyDeque<Integer> test2 = new MyDeque<Integer>(5);
    test2.addFirst(10);
    test2.addLast(11);
    test2.addLast(12);
    test2.addLast(13);
    test2.addFirst(0);
    System.out.println(test2);
    System.out.println("order: " + test2.print());
    System.out.println("start: " + test2.start);
    System.out.println("end: " + test2.end);
    System.out.println("size: " + test2.size());

    System.out.println();
    System.out.println("removedFirst: " + test2.removeFirst());
    System.out.println(test2);
    System.out.println("order: " + test2.print());
    System.out.println("start: " + test2.start);
    System.out.println("end: " + test2.end);
    System.out.println("size: " + test2.size());

    System.out.println();
    test2.addFirst(20);
    System.out.println(test2);
    System.out.println("order: " + test2.print());
    System.out.println("start: " + test2.start);
    System.out.println("end: " + test2.end);
    System.out.println("size: " + test2.size());

    System.out.println();
    System.out.println("removedLast: " + test2.removeLast());
    System.out.println(test2);
    System.out.println("order: " + test2.print());
    System.out.println("start: " + test2.start);
    System.out.println("end: " + test2.end);
    System.out.println("size: " + test2.size());

    test2.resize();
    System.out.println();
    System.out.println(test2);
    System.out.println("order: " + test2.print());
    System.out.println("start: " + test2.start);
    System.out.println("end: " + test2.end);
    System.out.println("size: " + test2.size());


    System.out.println();
    System.out.println("removedFirst: " + test2.removeFirst());
    System.out.println(test2);
    System.out.println("order: " + test2.print());
    System.out.println("start: " + test2.start);
    System.out.println("end: " + test2.end);
    System.out.println("size: " + test2.size());

    System.out.println();
    test2.addLast(-11);
    System.out.println(test2);
    System.out.println("order: " + test2.print());
    System.out.println("start: " + test2.start);
    System.out.println("end: " + test2.end);
    System.out.println("size: " + test2.size());


    System.out.println();
    test2.addLast(-20);
    System.out.println(test2);
    System.out.println("order: " + test2.print());
    System.out.println("start: " + test2.start);
    System.out.println("end: " + test2.end);
    System.out.println("size: " + test2.size());

    System.out.println();
    System.out.println("removedLast: " + test2.removeLast());
    System.out.println(test2);
    System.out.println("order: " + test2.print());
    System.out.println("start: " + test2.start);
    System.out.println("end: " + test2.end);
    System.out.println("size: " + test2.size());

    System.out.println();
    MyDeque<Integer> test3 = new MyDeque<Integer>(3);
    test3.addFirst(1);
    test3.addLast(2);
    test3.addLast(3);
    System.out.println(test3);
    System.out.println("order: " + test3.print());
    System.out.println("start: " + test3.start);
    System.out.println("end: " + test3.end);
    System.out.println("size: " + test3.size());

    System.out.println();
    System.out.println("removedFirst: " + test3.removeFirst());
    System.out.println(test3);
    System.out.println("order: " + test3.print());
    System.out.println("start: " + test3.start);
    System.out.println("end: " + test3.end);
    System.out.println("size: " + test3.size());

    System.out.println();
    System.out.println("removedLast: " + test3.removeLast());
    System.out.println(test3);
    System.out.println("order: " + test3.print());
    System.out.println("start: " + test3.start);
    System.out.println("end: " + test3.end);
    System.out.println("size: " + test3.size());

    System.out.println();
    MyDeque<Integer> test4 = new MyDeque<Integer>(4);
    test4.addLast(0);
    test4.addLast(1);
    test4.addLast(2);
    test4.addFirst(-1);
    System.out.println(test4);
    System.out.println("order: " + test4.print());
    System.out.println("start: " + test4.start);
    System.out.println("end: " + test4.end);
    System.out.println("size: " + test4.size());

    System.out.println();
    System.out.println("removedLast: " + test4.removeLast());
    System.out.println("removedLast: " + test4.removeLast());
    System.out.println(test4);
    System.out.println("order: " + test4.print());
    System.out.println("start: " + test4.start);
    System.out.println("end: " + test4.end);
    System.out.println("size: " + test4.size());

    System.out.println();
    System.out.println("removedFirst: " + test4.removeFirst());
    System.out.println(test4);
    System.out.println("order: " + test4.print());
    System.out.println("start: " + test4.start);
    System.out.println("end: " + test4.end);
    System.out.println("size: " + test4.size());


    test4.resize();
    System.out.println();
    System.out.println(test4);
    System.out.println("order: " + test4.print());
    System.out.println("start: " + test4.start);
    System.out.println("end: " + test4.end);
    System.out.println("size: " + test4.size());

    test4.addLast(1);
    test4.addLast(2);
    test4.addLast(3);
    test4.addLast(4);
    test4.addLast(5);
    System.out.println();
    System.out.println(test4);
    System.out.println("order: " + test4.print());
    System.out.println("start: " + test4.start);
    System.out.println("end: " + test4.end);
    System.out.println("size: " + test4.size());

    test4.addFirst(10);
    System.out.println();
    System.out.println(test4);
    System.out.println("order: " + test4.print());
    System.out.println("start: " + test4.start);
    System.out.println("end: " + test4.end);
    System.out.println("size: " + test4.size());

    System.out.println();
    System.out.println("removedFirst: " + test4.removeFirst());
    System.out.println(test4);
    System.out.println("order: " + test4.print());
    System.out.println("start: " + test4.start);
    System.out.println("end: " + test4.end);
    System.out.println("size: " + test4.size());

    System.out.println();
    MyDeque<Integer> test5 = new MyDeque<Integer>(10);
    test5.addLast(1);
    test5.addLast(2);
    test5.addLast(3);
    test5.addLast(4);
    test5.addLast(5);
    test5.addFirst(0);

    System.out.println(test5);
    System.out.println("order: " + test5.print());
    System.out.println("start: " + test5.start);
    System.out.println("end: " + test5.end);
    System.out.println("size: " + test5.size());
    for(int i = 0; i < 5; i++){
      test5.removeLast();
    }

    System.out.println();
    System.out.println(test5);
    System.out.println("order: " + test5.print());
    System.out.println("start: " + test5.start);
    System.out.println("end: " + test5.end);
    System.out.println("size: " + test5.size());

    test5.addLast(1);
    test5.addLast(2);
    test5.addLast(3);
    test5.addLast(4);

    System.out.println();
    System.out.println(test5);
    System.out.println("order: " + test5.print());
    System.out.println("start: " + test5.start);
    System.out.println("end: " + test5.end);
    System.out.println("size: " + test5.size());

    System.out.println();
    System.out.println("removedFirst: " + test5.removeFirst());
    System.out.println(test5);
    System.out.println("order: " + test5.print());
    System.out.println("start: " + test5.start);
    System.out.println("end: " + test5.end);
    System.out.println("size: " + test5.size());
  }
}
