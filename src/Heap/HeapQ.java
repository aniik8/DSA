package Heap;
import java.util.HashMap;
import java.util.*;
//        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
public class HeapQ {
    public static void main(String[] args) {
      int[] arr = {12, 16, 22, 30, 35, 39, 42,
              45, 48, 50, 53, 55, 56};
        System.out.println(findKthLargest(arr, 4));
        System.out.println(nearlySorted(arr, 5, 2));
        System.out.println(Arrays.toString(printKClosest(arr, arr.length, 4, 35)));
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
    // Find the Kth closest elements -
    static int[] printKClosest(int[] arr, int n, int k, int x) {
        int[] array = new int[k];
        int a = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return Math.abs(b - x) - Math.abs(a - x);
            }
        });
        for(int i = 0; i< arr.length; i++){
            maxHeap.add(arr[i]);
            if(maxHeap.size() > k){
                maxHeap.poll();
            }
        }
        while(!maxHeap.isEmpty()){
            array[a++] = maxHeap.peek();
            maxHeap.poll();
        }
        return array;
    }
    // LeetCode 658
}









