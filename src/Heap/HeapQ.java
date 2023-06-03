package Heap;
import java.util.HashMap;
import java.util.*;
//        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
public class HeapQ {
    public static void main(String[] args) {
      int[] arr = {3,1,4,2,5};
        System.out.println(findKthLargest(arr, 4));
        System.out.println(nearlySorted(arr, 5, 2));
    }
    // 3,2,1,5,6,4
    // 4, 5, 6
    static public int findKthLargest(int[] nums, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int n = nums.length;
        int a = 0;
        for (int i = 0; i < n; i++) {
            pq.add(nums[i]);
            a++;
            if(a > k){
                pq.poll();
                a--;
            }
        }
        return pq.peek();
    }
    // Geeks for geeks  Question
    public static int kthSmallest(int[] arr, int l, int r, int k)
    {
        PriorityQueue<Integer> pq= new PriorityQueue<>(Collections.reverseOrder());
        int n = r-l+1, a = 0;
        for (int i = 0; i < n; i++) {
            pq.add(arr[i]);
            a++;
            if(a > k)
            {
                pq.poll();
                a--;
            }

        }
        return pq.peek();
    }
    // Nearly sorted array
    // {6,5,3,2,8,10,9} k = 3
    // 2,3,5,6
    static ArrayList <Integer> nearlySorted(int arr[], int num, int k)
    {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        ArrayList<Integer> list = new ArrayList<>();
        int n = arr.length;
        int a = 0;
        for (int i = 0; i < n; i++) {
            pq.add(arr[i]);
            a++;
            if(a > k){
                list.add(pq.poll());
                a--;
            }
        }
        while(!pq.isEmpty()){
            list.add(pq.poll());
        }
        return list;
    }
}
