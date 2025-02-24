import java.util.NoSuchElementException;
import java.util.Iterator;

//circular array-based list implementation
public class BrowserArrayList<T> implements Iterable<T>{
    private T[] array;//array to store elements
    private int front, rear, size, capacity;

    //Constructor to initializes the circular array with a given capacity
    @SuppressWarnings("unchecked") 
    public BrowserArrayList(int capacity){
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    //gets an element at a specific index without removing it
    public T getElementAt(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return array[(front + index) % capacity];
    }
    
    //adding element to end of list
    public void enqueue(T data){
        if(size == capacity){
            resize();
        }
        array[rear] = data;//add data to end
        rear = (rear + 1) % capacity;
        size++;
    }

    //remove and return from end of list
    public T dequeue(){
        if(isEmpty()){
            throw new NoSuchElementException("Queue is empty");
        }
        T data = array[front];//gets front element
        array[front] = null;
        front = (front + 1) % capacity;//Move front pointer in a circular manner
        size--;
        return data;
    }

    //checks if array is empty
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }else{
            return false;
        }
    }

    //returns current size of array
    public int size(){
        return size;
    }

    //resize array when max capacity is reached
    @SuppressWarnings("unchecked")
    public void resize(){
        int newCapacity = capacity*2;
        T[] newArray = (T[]) new Object[newCapacity];
        
        //copy element from old array to new array
        for(int i = 0; i < size; i++){
            newArray[i] =  array[(front + i) % capacity];
        }
        array = newArray;
        front = 0;
        rear = size;
        capacity = newCapacity;
    }

    //return iterator for traversing array
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    //Inner class implementing an iterator
    private class ArrayListIterator implements Iterator<T> {
        private int index = front;
        private int count = 0;

        //return true if there are more arrays to iterate
        @Override
        public boolean hasNext(){
            if(count < size){
                return true;
            } else{
                return false;
            }
        }

        //returns next element in array
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
