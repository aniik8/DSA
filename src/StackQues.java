import java.util.Arrays;
import java.util.Stack;

public class StackQues {
    public static void main(String[] args) {
        StackOperation stack = new StackOperation(10);
        stack.push(10);
        stack.push(16);
        stack.push(1);
        System.out.println(stack.peek());
        stack.displayStack();
        stack.pop();
        System.out.println(stack.peek());
        stack.push(9);
        System.out.println(stack.peek());
        stack.isEmpty();
    }
    // stack questions. From GFG and leetcode
    // 1. Identification of stack
    // inside loop value of j depends on ith loop;
    // 1. Stack implementation using array.
    //
}
class StackOperation{
    private static int top=-1;
    private static int[] stack;
    StackOperation(int size){
        stack = new int[size];
    }
    // push operation on stack
    void push(int element){
        if(!isFull()) {
            stack[++top] = element;
        }
        else{
            displayFull();
        }
    }
    //pop element from stack
    void pop(){
        if(!isEmpty()) {
            top--;
        }else displayEmpty();
    }
    // peek element
    int peek(){
        if(!isEmpty())
            return stack[top];
        else return -1;
    }
    boolean isEmpty(){
        if(top == 0)
            return true;
        return false;
    }
    boolean isFull(){
        return top == stack.length;
    }
    void displayFull(){
        if(isFull())
            System.out.println("Stack overflow");
    }
    void displayEmpty(){
        if(isEmpty())
            System.out.println("Stack underflow");
    }
    void displayStack(){
        System.out.println(Arrays.toString(stack));
    }
}