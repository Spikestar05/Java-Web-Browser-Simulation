public class BrowserQueue<T>{
    private BrowserArrayList<T> queue;

    public BrowserQueue(int capacity){
        queue = new BrowserArrayList<>(capacity);
    }

    public void enqueue(T data){
        queue.enqueue(data);
    }

    public T elementAt(int index){
        return queue.getElementAt(index);
    }

    public T dequeue(){
        return queue.dequeue();
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }

    public int size(){
        return queue.size();
    }
}
