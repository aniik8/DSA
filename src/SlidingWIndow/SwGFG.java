package SlidingWIndow;
import java.util.*;

public class SwGFG {
    public static void main(String[] args) {
        long[] arr= {12, -1, -7, 8, -15, 30, 16, 28};

        System.out.println(Arrays.toString(printFirstNegativeInteger(arr, 8, 3)));
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
}
