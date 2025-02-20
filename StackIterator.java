import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackIterator<T> implements Iterator<T> {
    private BrowserLinkedList<T>.Node current;

    public StackIterator(BrowserLinkedList<T> stack){
        this.current = stack.getHead();
    }

    @Override
    public boolean hasNext(){
        if(current != null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public T next(){
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        T data = current.data;
        current = current.next;
        return data;
    }
}