import java.util.EmptyStackException;

//Stack implementation using a doubly linked list
public class BrowserStack<T>{
    private BrowserLinkedList<T> stack;

    //Constructor to Initialize an empty stack
    public BrowserStack(){
        stack = new BrowserLinkedList<>();
    }

    //push new element to top of stack
    public void push(T data){
        stack.addLast(data);
    }

    //remove and return element from top of stack
    public T pop(){
        if(stack.isEmpty()){
            throw new EmptyStackException();
        }
        return stack.removeLast();
    }

    //return top element without removing it
    public T peek(){
        if(stack.isEmpty()){
            throw new EmptyStackException();
        }
        return stack.peekLast();
    }

    //check if stack is empty
    public boolean isEmpty(){
        return stack.isEmpty();
    }

    //return size of stack
    public int size(){
        return stack.size();
    }

    //Returns iterator for traversing the stack
    public StackIterator<T> getStackIterator(){
        return stack.getStackIterator();
    }
}