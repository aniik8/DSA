package Heap;
import java.util.HashMap;
import java.util.*;
//        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
public class HeapQ {
    public static void main(String[] args) {
      int[] arr = {3,2,3,1,2,4,5,5,6};
        System.out.println(findKthLargest(arr, 4));
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
}
