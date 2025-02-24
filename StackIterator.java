import java.util.NoSuchElementException;

//custome iterator for traversing stack implemented using doubly linked list
public class StackIterator<T>{
    private BrowserLinkedList<T>.Node current;

    //Constructor to initializes the iterator starting from the top of stack
    public StackIterator(BrowserLinkedList<T> stack){
        this.current = stack.getTail();
    }

    //Checks if there are more elements to iterate
    public boolean hasNext(){
        if(current != null){
            return true;
        }else{
            return false;
        }
    }

    //Returns the next element in the iteration and move pointer backwards
    public T next(){
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        T data = current.data;
        current = current.prev;
        return data;
    }
}