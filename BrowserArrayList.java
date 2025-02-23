import java.util.NoSuchElementException;
import java.util.Iterator;

public class BrowserArrayList<T> implements Iterable<T>{
    private T[] array;
    private int front, rear, size, capacity;

    @SuppressWarnings("unchecked") 
    public BrowserArrayList(int capacity){
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    public T getElementAt(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return array[(front + index) % capacity];
    }
    
    public void enqueue(T data){
        if(size == capacity){
            resize();
        }
        array[rear] = data;
        rear = (rear + 1) % capacity;
        size++;
    }

    public T dequeue(){
        if(isEmpty()){
            throw new NoSuchElementException("Queue is empty");
        }
        T data = array[front];
        array[front] = null;
        front = (front + 1) % capacity;
        size--;
        return data;
    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        }else{
            return false;
        }
    }

    public int size(){
        return size;
    }

    @SuppressWarnings("unchecked") 
    public void resize(){
        int newCapacity = capacity*2;
        T[] newArray = (T[]) new Object[newCapacity];
        for(int i = 0; i < size; i++){
            newArray[i] =  array[(front + i) % capacity];
        }
        array = newArray;
        front = 0;
        rear = size;
        capacity = newCapacity;
    }

    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {
        private int index = front;
        private int count = 0;

        @Override
        public boolean hasNext(){
            if(count < size){
                return true;
            } else{
                return false;
            }
        }
        @Override
        public T next(){
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            T data = array[index];
            index = (index + 1) % capacity;
            count++;
            return data;
        }
    }
}
