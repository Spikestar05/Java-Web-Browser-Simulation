import java.util.NoSuchElementException;

//doubly linked list implementation for stack
public class BrowserLinkedList<T>{

    //inner class for node in a linked list
    class Node{
        T data;
        Node next, prev;

        //constructor Initializes a new node with data
        Node(T data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node head, tail;
    private int size;

    //constructor to make empty list.
    public BrowserLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    //Adds a new element to the end of the linked list
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

    //Removes and returns the last element of the linked list
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

    //returns last element of list
    public T peekLast(){
        if(tail == null){
            throw new NoSuchElementException("Stack is empty");
        }
        return tail.data;
    }

    //check if linked list is empty
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }else{
            return false;
        }
    }

    //returns size of list
    public int size(){
        return size;
    }

    //returns head of list
    public Node getHead(){
        return head;
    }

    //returns tail of list
    public Node getTail(){
        return tail;
    }
    
    //Returns an iterator for traversing stack
    public StackIterator<T> getStackIterator(){
        return new StackIterator<>(this);
    }
}