package Stack;

import java.util.Arrays;
import java.util.Stack;

public class StackQues {
    public static void main(String[] args) {
//        StackOperation stack = new StackOperation(10);
//        stack.push(10);
//        stack.push(16);
//        stack.push(1);
//        System.out.println(stack.peek());
//        stack.displayStack();
//        stack.pop();
//        System.out.println(stack.peek());
//        stack.push(9);
//        System.out.println(stack.peek());
//        stack.isEmpty();
        long[] num = {10, 5, 11, 6, 20, 12} ;
        System.out.println(Arrays.toString(nextLargerElementLeft(num, num.length)));
    }
    // stack questions. From GFG and leetcode
    // 1. Identification of stack
    // inside loop value of j depends on ith loop;

    //2. Nearest greatest right
    public static long[] nextLargerElement(long[] arr, int n){
        long[] array = new long[n];
        Stack<Long> stack = new Stack<>();
        int a = n-1;
        for (int i = n-1; i >=0 ; i--) {
            if(stack.size() == 0)
                array[a--] = -1;
            else if(stack.size() > 0 && arr[i] < stack.peek())
                array[a--] = stack.peek();
            else if(stack.size() > 0 && arr[i] >= stack.peek())
            {
                while(!stack.isEmpty() && arr[i] >= stack.peek()){
                    stack.pop();
                }
                if(stack.isEmpty()) array[a--] = -1;
                else array[a--] = stack.peek();
            }
            stack.push(arr[i]);
        }
        return array;
    }
    // nearest greatest to left.
    public static long[] nextLargerElementLeft(long[] arr, int n){
        long[] array = new long[n];
        Stack<Long> stack = new Stack<>();
        int a = 0;
        for (int i = 0; i <n  ; i++) {
            if(stack.size() == 0)
                array[a++] = -1;
            else if(stack.size() > 0 && arr[i] < stack.peek())
                array[a++] = stack.peek();
            else if(stack.size() > 0 && arr[i] >= stack.peek())
            {
                while(!stack.isEmpty() && arr[i] >= stack.peek()){
                    stack.pop();
                }
                if(stack.isEmpty()) array[a++] = -1;
                else array[a++] = stack.peek();
            }
            stack.push(arr[i]);
        }
        return array;
    }
}
// 1. Stack implementation using array.
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