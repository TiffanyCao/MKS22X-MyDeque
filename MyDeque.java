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

  public String toString(){
    String result = "[";
    for(int i = 0; i < data.length; i++){
      if(i == data.length - 1){
        result += data[i] + "]";
      }else result += data[i] + ", ";
    }
    return result;
  }

  public void addFirst(E element){
    if(size != data.length && start == 0){ //if more elements can be added at the end and be labeled as start
      data[end + 1] = element;
      start = end + 1;
    }else if(size != data.length && start != 0 && end != (start - 1)){ //if start is in the middle of the array and more elements can be added before it
      data[start - 1] = element;
      start--;
    }else if((start != 0 && end == (start - 1)) || size == data.length){ //if not, resize and add
      //resize(element);
    }
  }

  public void addLast(E element){
    if(size != data.length && end > start && end < (data.length - 1)){ //if more elements can be added at the end of the array
      data[end + 1] = element;
      end++;
    }else if(size != data.length && end > start && end == (data.length - 1)){ //if more elements can be added to the start of the array
      data[0] = element;
      end = 0;
    }else if(size != data.length && end < start){ //if more elements can be added before the start index;
      data[end + 1] = element;
      end++;
    }else if(size == data.length || end == (start - 1)){ //if no more elements can be added, resize then add
      //resize();
      data[end + 1] = element;
      end++;
    }
  }

  public void resize(){
    @SuppressWarnings("unchecked")
    E[] d = (E[]) new Object[data.length * 2 + 1];
    if(start != 0 && end < start){
      for(int i = start; i < data.length; i++){
        d[i - start] = data[i];
      }
      for(int y = 0; y <= end; y++){

      }
    }
  }
}
