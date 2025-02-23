import java.util.NoSuchElementException;

public class BrowserLinkedList<T>{
    class Node{
        T data;
        Node next, prev;
        Node(T data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node head, tail;
    private int size;

    public BrowserLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    public void addLast(T data){
        Node newNode = new Node(data);
        if(tail == null){
            head = newNode;
            tail = newNode;
        }else{
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public T removeLast(){
        if (tail == null){
            throw new NoSuchElementException("Stack is empty");
        }
        T data = tail.data;
        tail = tail.prev;
        if(tail != null){
            tail.next = null;
        }else{
            head = null;
        }
        size--;
        return data;
    }

    public T peekLast(){
        if(tail == null){
            throw new NoSuchElementException("Stack is empty");
        }
        return tail.data;
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

    public Node getHead(){
        return head;
    }

    public Node getTail(){
        return tail;
    }
    
    public StackIterator<T> getStackIterator(){
        return new StackIterator<>(this);
    }
}