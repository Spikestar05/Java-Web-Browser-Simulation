import java.util.Iterator;

//queue implementation using a custom circular array list
public class BrowserQueue<T> implements Iterable<T>{
    private BrowserArrayList<T> queue;//array-based queue

    //Constructor to initializes queue with a specified capacity
    public BrowserQueue(int capacity){
        queue = new BrowserArrayList<>(capacity);
    }

    //Adds an element to the end of the queue
    public void enqueue(T data){
        queue.enqueue(data);
    }

    //gets element at a specific idex of queue
    public T elementAt(int index){
        return queue.getElementAt(index);
    }

    //removes and returns the front of queue
    public T dequeue(){
        return queue.dequeue();
    }

    //checks if queu is empty
    public boolean isEmpty(){
        return queue.isEmpty();
    }

    //returns size of queue
    public int size(){
        return queue.size();
    }

    //return iterator to travers queue
    public Iterator<T> iterator(){
        return queue.iterator();
    }
}