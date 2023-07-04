package Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        int[] num = {100,80,60,70,60,75,85} ;
//        System.out.println(Arrays.toString(nextLargerElementLeft(num, num.length)));
        System.out.println(Arrays.toString(calculateSpan(num, num.length)));
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

    // Stock span problem
    public static int[] calculateSpan(int price[], int n){
        Stack<Integer> stack = new Stack<>();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            while(!stack.isEmpty() && price[stack.peek()] <= price[i]) stack.pop();
            if(stack.isEmpty())
                arr[i] = i+1;
            else arr[i] = i - stack.peek();
            stack.push(i);
        }
        return arr;
    }
    static List<Integer> leftSmaller(int n, int a[])
    {   Stack<Integer> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if(stack.size() == 0) list.add(-1);
            else if(stack.size() > 0 && a[i] > stack.peek())
                list.add(stack.peek());
            else if(stack.size() > 0 && a[i] <= stack.peek()){
                while(stack.size() > 0 && a[i] <= stack.peek())
                    stack.pop();
                if(stack.size() == 0) list.add(-1);
                else list.add(stack.peek());

            }
            stack.push(a[i]);
        }
        return list;
    }
    static int[] help_classmate(int[] arr, int n)
    {   Stack<Integer> stack = new Stack<>();
        int[] array = new int[arr.length];
        int a = arr.length-1;
        for (int i = n-1; i >=0 ; i--) {
            if(stack.size() == 0)
                array[a--] = -1;
            else if(stack.size() > 0 && arr[i] > stack.peek()){
                array[a--] = stack.peek();
            }else if(stack.size() > 0 && arr[i] <= stack.peek()){
                while(stack.size() > 0 && arr[i] <= stack.peek())
                {
                    stack.pop();
                }
                if(stack.size() == 0) array[a--] = -1;
                else array[a--] = stack.peek();

            }
            stack.push(arr[i]);
        }
        return array;
        // Your code goes here
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

// MIn stack leetcode
class MinStack {
    int minEle = Integer.MAX_VALUE;
    private static int top;
    private static int minTop;
    private static ArrayList<Integer> stack;
    private static ArrayList<Integer> minElement;
    public MinStack() {
        stack = new ArrayList<>();
        minElement = new ArrayList<>();
        top=-1;
        minTop = -1;
    }

    public void push(int val) {
        stack.add(val);
        if(val <= minEle)
        {
            minElement.add(val);
            minEle = val;
            minTop++;
        }
        top++;
    }

    public void pop() {
        if(stack.get(top) == minElement.get(minTop))
        {
            minElement.remove(minTop);
            minTop--;
        }
        stack.remove(top);
        top--;
    }

    public int top() {
        return stack.get(top);
    }

    public int getMin() {
        return minElement.get(minTop);
    }
}
