public class BrowserStack<T>{
    private BrowserLinkedList<T> stack;

    public BrowserStack(){
        stack = new BrowserLinkedList<>();
    }

    public void push(T data){
        stack.addLast(data);
    }

    public T pop(){
       return stack.removeLast();
    }

    public T peek(){
       return stack.peekLast();
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public int size(){
        return stack.size();
    }

    public StackIterator<T> getStackIterator(){
        return stack.getStackIterator();
    }
}