import java.util.NoSuchElementException;

public class StackIterator<T>{
    private BrowserLinkedList<T>.Node current;

    public StackIterator(BrowserLinkedList<T> stack){
        this.current = stack.getTail();
    }

    public boolean hasNext(){
        if(current != null){
            return true;
        }else{
            return false;
        }
    }

    public T next(){
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        T data = current.data;
        current = current.prev;
        return data;
    }
}