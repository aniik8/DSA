package Queue;

import java.util.LinkedList;
import java.util.Queue;

public class QueueClass {
    public static void main(String[] args) {
        QueueA queueobj = new QueueA(5);
        queueobj.enqueue(2);
        queueobj.enqueue(10);
        queueobj.enqueue(23);
        queueobj.enqueue(32);
        queueobj.enqueue(0);
        System.out.println(queueobj.isFull());
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
