package Heap;
import java.util.HashMap;
import java.util.*;
//        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
public class HeapQ {
    public static void main(String[] args) {
      int[] arr = {1,1,2,2,2,3};
//        System.out.println(findKthLargest(arr, 4));
//        System.out.println(nearlySorted(arr, 5, 2));
//        System.out.println(Arrays.toString(printKClosest(arr, arr.length, 4, 35)));
        System.out.println(Arrays.toString(frequencySort(arr)));
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
        List<Integer> list = new ArrayList<>();
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
    // LeetCode 658 -- using binary search.
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - k;

        // Perform binary search to find the starting index of the subarray
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }
        return result;
        }
        // Top K frequent elements 347
        public int[] topKFrequent(int[] nums, int k) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                map.getOrDefault(nums[i], map.get(nums[i]+1));
            }
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> (map.get(b) - (map.get(a))));
            priorityQueue.addAll(map.keySet());

            int[] arr = new int[k];
            for (int i = 0; i < k; i++) {
                arr[i] = priorityQueue.poll();
            }
            return  arr;
        }
        // leetcode 1636
     static public int[] frequencySort(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

         for (int i = 0; i < nums.length; i++) {
             if(!map.containsKey(nums[i])){
                 map.put(nums[i], 1);
             }else map.put(nums[i], map.get(nums[i])+1);
         }
         int[] arr = new int[nums.length];
         int i = 0;
         PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> (
                  map.get(a) != map.get(b)
                         ? map.get(b) - map.get(a)
                         : a - b));
         priorityQueue.addAll(map.keySet());
         while(priorityQueue.size() > 0){
             int num = priorityQueue.poll();
             int limit = map.get(num);
             for (int j = 0; j < limit; j++) {
                 arr[i++] = num;
             }
         }
         return arr;
    }
}









