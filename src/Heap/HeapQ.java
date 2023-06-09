package Heap;
import java.util.HashMap;
import java.util.*;
//        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
public class HeapQ {
    public static void main(String[] args) {
      int[] arr = {1,6,1};
      int[][] point = {{1,3}, {-2,2}};
//        System.out.println(findKthLargest(arr, 4));
//        System.out.println(nearlySorted(arr, 5, 2));
//        System.out.println(Arrays.toString(printKClosest(arr, arr.length, 4, 35)));
        /*System.out.println(Arrays.toString(frequencySort(arr)));*/
//        System.out.println(frequencySort("tree"));
//        System.out.println(Arrays.toString(kClosest(point,1)));
//        System.out.println(minCost(arr, arr.length));
        System.out.println(smallestDistancePair(arr, 3));
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
                         ? map.get(a) - map.get(b)
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
    // Input: s = "tree"
    // Output: "eert"
    static public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            if(!map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), 1);
            }else map.put(s.charAt(i), map.get(s.charAt(i))+1);
        }
        String str = "";
        int i = 0;
        PriorityQueue<Character> priorityQueue = new PriorityQueue<>((a, b) -> (
                map.get(a) != map.get(b)
                        ? map.get(b) - map.get(a)
                        : a - b));
        priorityQueue.addAll(map.keySet());
        while(priorityQueue.size() > 0){
            char ch = priorityQueue.poll();
            int limit = map.get(ch);
            for (int j = 0; j < limit; j++) {
                str += ch;
            }
        }
        return str;
    }

    // K closest point to origin
    // [[1,3],[-2,2]]
    public static int[][] kClosest(int[][] points, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[][] pointFromOrigin = new int[k][points[0].length];
        System.out.println("points" + Arrays.toString(points[1]));
        int distance = 0;
        for (int i = 0; i < points.length; i++) {
            for(int j = 0; j < points[i].length; j++){

                distance += Math.pow(points[i][j], 2);
            }
            map.put(i, distance);
            distance = 0;
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a,b) -> map.get(a) - map.get(b));
        priorityQueue.addAll(map.keySet());
//        while(priorityQueue.size() > 0){
//            System.out.println(Arrays.toString(points[priorityQueue.poll()]));
//        }

        for (int i = 0; i < k; i++) {
            System.out.println(Arrays.toString(points[priorityQueue.peek()]));
            pointFromOrigin[i] = (points[priorityQueue.poll()]);
        }


        return pointFromOrigin;
    }
    // Minimum Cost of ropes
    static long minCost(long arr[], int n)
    {
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            priorityQueue.add(arr[i]);
        }
        long sum = 0;
        while(priorityQueue.size() >=2){
            long a = priorityQueue.poll();
            long b = priorityQueue.poll();
            sum +=  a+ b;
            priorityQueue.add(a+b);
        }
        return sum;
    }

    public static long minSum(int arr[], int n) {
        if (n == 1) {
            return arr[0];
        }
        if (n == 2) {
            return arr[0] + arr[1];
        }
        Arrays.sort(arr);

        long n1 = 0;
        long n2 = 0;

        for (int i = 0; i < n; ) {
            n1 = (n1) * 10 + (long) (arr[i]);
            i++;
            if (i == n) {
                break;
            }
            n2 = (n2) * 10 + (long) (arr[i]);
            i++;
        }
        return n1 + n2;
    }
    public static int smallestDistancePair(int[] nums, int k) {
        // 1,3,1
        // 1,3,1
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                priorityQueue.add(Math.abs(nums[i]- nums[j]));
            }
        }
        while(priorityQueue.size() > k){
            priorityQueue.poll();
        }
        return priorityQueue.peek();
    }
    // GFG
    public static long sumBetweenTwoKth(long A[], long N, long K1, long K2)
    {
        PriorityQueue<Long> maxH = new PriorityQueue<>(
                Collections.reverseOrder());

        // Using this for loop we eliminate the extra
        // elements which are greater than K2'th smallest
        // element as they are not required for us

        for (int i = 0; i < N; i++) {

            maxH.add(A[i]);

            if (maxH.size() > K2) {
                maxH.remove();
            }
        }
        // popping out the K2'th smallest element
        maxH.remove();

        long ans = 0;
        // adding the elements to ans until we reach the
        // K1'th smallest element
        while (maxH.size() > K1) {

            ans += maxH.peek();
            maxH.remove();
        }

        return ans;
    }
    // usingg sorting
    static int sumBetweenTwoKth_sort(int arr[],
                                int k1, int k2)
    {
        // Sort the given array
        Arrays.sort(arr);

        // Below code is equivalent to
        int result = 0;

        for (int i = k1; i < k2 - 1; i++)
            result += arr[i];

        return result;
    }
    static int kthLargestSum(int arr[], int N, int K)
    {
        int sum[] = new int[N + 1];
        sum[0] = 0;
        sum[1] = arr[0];
        for (int i = 2; i <= N; i++)
            sum[i] = sum[i - 1] + arr[i - 1];
        PriorityQueue<Integer> Q
                = new PriorityQueue<Integer>();

        for (int i = 1; i <= N; i++) {
            for (int j = i; j <= N; j++) {
                int x = sum[j] - sum[i - 1];

                if (Q.size() < K)
                    Q.add(x);

                else {
                    if (Q.peek() < x) {
                        Q.poll();
                        Q.add(x);
                    }
                }
            }
        }
        return Q.poll();
    }
}









