package SlidingWIndow;
import java.util.*;

public class SwGFG {
    public static void main(String[] args) {
        int[] arr= {1, 2, 3, 1, 4, 5 ,2,3,6};
        int[] nums = {-1};
//        System.out.println(Arrays.toString(printFirstNegativeInteger(arr, 8, 3)));
//        System.out.println(max_of_subarrays(arr, 9,3));
//        System.out.println((int)findMaxAverage(nums, 1));
//        System.out.println(countGoodSubstrings("xyzzaz"));
        System.out.println(divisorSubstrings(430043, 2));
    }
    // maximum sum subarray of size k
    static long maximumSumSubarray(int K, List<Integer> Arr,int N){
        int i = 0;
        int j = 0;
        long sum = 0;
        long maxSum = Long.MIN_VALUE;
        while(j < N){
            sum += Arr.get(j);
            if(j - i +1 < K){
                j++;
            }
            else if(j - i + 1 == K)
            {
                maxSum = Math.max(maxSum, sum);
                sum -= Arr.get(i);
                i++;
                j++;
            }
        }
        return maxSum;
    }
    // print First negative integer
    // {-8, 2, 3, -6, 10};
    static public long[] printFirstNegativeInteger(long A[], int N, int K)
    {   long[] negative = new long[N - K + 1];
        int i = 0, j = 0, a = 0;
        Queue<Long> queue = new LinkedList<>();
        while(j < N){
            if(A[j] < 0)
            {
                queue.add(A[j]);
            }
            if(j - i + 1 < K){
                j++;
            }
            else if(j - i + 1 == K){
                if(!queue.isEmpty()){
                    negative[a++] = queue.peek();
                    if(A[i] == queue.peek())
                        queue.poll();
                }else{
                    negative[a++] = 0;
                }
                j++;
                i++;
            }
        }
        return negative;
    }
// Count occurences of anagrams
//    static int search(String pat, String txt) {
//        int i = 0, j = 0, count = 0;
//        int K = pat.length();
//        StringBuilder str = new StringBuilder();
//        while(j  < txt.length()){
//            str.append(txt.charAt(j));
//            if(j - i + 1 < K){
//                j++;
//            }else if(j - i + 1 == K){
//
//            }
//        }
//    }
    // {1, 2, 3, 1, 4, 5 ,2,3,6}
    static ArrayList <Integer> max_of_subarrays(int arr[], int n, int k)
    {
        ArrayList<Integer> list = new ArrayList<>();
        int i = 0, j = 0;
        Deque<Integer> deque = new LinkedList<>();
        while(j < n){
            if(deque.isEmpty()){
                deque.add(arr[j]);
            }
            else{
                while(deque.size() > 0 && deque.peekLast() < arr[j]){
                    deque.removeLast();
                }
                deque.add(arr[j]);
            }
            if(j - i + 1 < k)
                j++;
            else if(j - i + 1 == k){
                list.add(deque.peek());
                if(arr[i] == deque.peek())
                    deque.removeFirst();
                i++;
                j++;

            }
        }
        return list;
    }
// Leetcode 643
    static  public double findMaxAverage(int[] nums, int k) {
        int i = 0, j = 0;
        double sum = 0, maximum_average = -10001.00000, average = -100001;
        while(j < nums.length){
            sum += nums[j];

            if(j - i + 1 < k)
                j++;
            else if(j - i + 1 == k){
//                System.out.println(sum / k);
                maximum_average = Math.max(maximum_average, sum / k);
                sum -= nums[i];
//                System.out.println(maximum_average);
                i++;
                j++;
            }
        }
        return maximum_average;
    }
// leetcode 1876
    static public int countGoodSubstrings(String s) {
        int k = 3, i = 0, j = 0, n = s.length(), count= 0;
        int[] arr = new int[26];
        int index;
        while(j < n){
            index = s.charAt(j) - 'a';
           arr[index]++;
           if(arr[index] > 1){
               while(arr[index] > 1){
                   int ind = s.charAt(i) - 'a';
                   arr[ind]--;
                   i++;
               }
           }
               if(j - i +1 < k){
                   j++;
               }
               else if(j - i + 1 == k){
                   count++;
                   index = s.charAt(i) - 'a';
                   arr[index]--;
                   i++;
                   j++;
               }

        }
        return count;
    }
    //2269. Find the K-Beauty of a Number
    static int divisorSubstrings(int num, int k) {
        int i = 0, j = 0,  len = num, count = 0;
        StringBuilder str = new StringBuilder();

        while(len != 0){
            str.append(len % 10);
            len /= 10;
        }

        String stri = str.reverse().toString();
//        System.out.println(stri);
        while(j < stri.length()){
            if(j - i + 1 < k)
                j++;
            else if(j - i + 1 == k){
                int n = Integer.parseInt(stri.substring(i, (j+1)));
                if(n != 0){
                    if(num % n == 0)
                        count++;
                }
                i++;
                j++;
            }
    }
        return count;
}
}