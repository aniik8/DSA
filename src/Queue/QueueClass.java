package Queue;

//import java.util.LinkedList;
import java.util.*;

public class QueueClass {
    public static void main(String[] args) {
//        QueueA queueobj = new QueueA(5);
//        queueobj.enqueue(2);
//        queueobj.enqueue(10);
//        queueobj.enqueue(23);
//        queueobj.enqueue(32);
//        queueobj.enqueue(0);
//        System.out.println(queueobj.isFull());
        int[] arr = {1,1,1,0,0,1},
                sandw = {1,0,0,0,1,1};
        System.out.println(countStudents3(arr, sandw));
    }
    // gfg
    // yewaahkpuo
    // yyyyyyyyyy
    // yyyyyyyyyy
    static String FirstNonRepeating(String A)
    {
        HashMap<Character, Integer> map = new HashMap<>();
        Queue<Character> queue = new LinkedList<>();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < A.length(); i++) {
            map.put(A.charAt(i), map.getOrDefault(A.charAt(i), 0)+1);
            queue.add(A.charAt(i));
            while(!queue.isEmpty()){
                char ch = queue.peek();
                if(map.get(ch) > 1)
                    queue.poll();
                else
                    break;
            }
            if(queue.isEmpty())
                str.append('#');
            else
                str.append(queue.peek());
        }
        return str.toString();
    }
    // 1700 number of students unable to eat
    public static int countStudents(int[] students, int[] sandwiches) {
        Queue<Integer> queue = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        int[] visited = new int[sandwiches.length];
        for (int i = sandwiches.length-1; i >= 0 ; i--) {
            stack.push(sandwiches[i]);
            queue.add(sandwiches.length-1 -  i);
        }
        System.out.println(stack);
        System.out.println(queue);
        while(!stack.isEmpty()){
            if(students[queue.peek()]== stack.peek())
            {
                queue.remove();
                stack.pop();
            }else{
                visited[queue.peek()]++;
                queue.add(queue.peek());
                queue.remove();
                if(visited[queue.peek()] == queue.size()){
                    break;
                }
            }
        }
        return stack.size();
    }
    // count students approach 2
    public int countStudents2(int[] students, int[] sandwiches) {
        Queue<Integer> queue = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = sandwiches.length-1; i >= 0 ; i--) {
            stack.push(sandwiches[i]);
            queue.add(sandwiches.length-1 -  i);
        }
        int count = 0;
        while(stack.size() != 0){
            if(students[queue.peek()]== stack.peek())
            {   count = 0;
                queue.remove();
                stack.pop();
            }else{

                queue.add(queue.peek());
                queue.remove();
                count++;
            }
            if(count == queue.size())
                return count;
        }
        return 0;
    }
    public static int countStudents3(int[] students, int[] sandwiches){
        Stack<Integer> st=new Stack<>();
        Queue<Integer> q=new LinkedList<>();
        int x=0;
        for(int i=0;i<students.length;i++){
            q.add(students[i]);
        }
        for(int i=sandwiches.length-1;i>=0;i--){
            st.push(sandwiches[i]);
            System.out.println("i am working " + i);
        }
        while(st.size()>0)
        {
            if(q.peek()==st.peek()){
                st.pop();
                q.remove();
                x++;
            }
            else{
                q.add(q.remove());
            }
        }
        int ans=students.length-x;
        return ans;
    }
}
// insertion at the end and deletion at the front
class QueueA{
    int[] queue;
    int front;
    int rear;
    // constructor of Queue
    QueueA(int n){
        queue = new int[n];
        front = 0;
        rear = -1;
    }
    // enqueue - insertion at the end
    void enqueue(int x){
        if(!isFull())
            queue[++rear] = x;
    }
    // dequeue
    int dequeue(){
        int num = -1;
        if(!isEmpty()) {
            num = queue[front];
            front++;
        }else System.out.println("Queue is empty, nothing to pop, will return -1");
        return num;
    }
    boolean isEmpty(){
        return front == rear;
    }
    boolean isFull(){
        return rear == queue.length-1;
    }
}
class MyStack {
    Queue<Integer> queue;
    Queue<Integer> helper;
    public int latest;
    public int top;
    public MyStack() {
         queue = new LinkedList<>();
         helper = new LinkedList<>();
    }

    public void push(int x) {
        top = x;
        queue.add(top);
    }

    public int pop() {
        latest = -1;
        if(queue.isEmpty()){
            while(helper.size() >= 2){
                top = helper.poll();
                queue.add(top);

        }
            latest = helper.poll();
        }
        else{
            while(queue.size() >= 2){
                top = queue.poll();
                helper.add(top);
            }
            latest = queue.poll();
        }
        return latest;
    }
    public int top(){
        return top;
    }

    public boolean empty() {
        return queue.isEmpty() && helper.isEmpty();
    }
}
class MyStack2 {
    Queue<Integer> queue;
    public int latest;
    public int temp;
    public int top = -1;
    public MyStack2() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        temp = top;
        top = x;
        queue.add(top);
    }

    public int pop() {
        latest = -1;
        while(latest != temp){
            queue.add(queue.poll());
        }
        latest = queue.poll();
        return latest;
    }
    public int top(){
        return top;
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
class MyQueue {
    Stack<Integer> stack1;
    Stack<Integer> stack2;
    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        stack1.push(x);
    }

    public int pop(){
        while(!(stack1.isEmpty())){
            stack2.push(stack1.pop());
        }
        int removed = stack2.pop();

        while(!(stack2.isEmpty())){
            stack1.push(stack2.pop());
        }
        return removed;
    }

    public int peek(){
        while(!(stack1.isEmpty())){
            stack2.push(stack1.pop());
        }
        int peekEle = stack2.peek();

        while(!(stack2.isEmpty())){
            stack1.push(stack2.pop());
        }
        return peekEle;
    }

    public boolean empty() {
        return (stack1.empty() && stack2.empty());
    }
}