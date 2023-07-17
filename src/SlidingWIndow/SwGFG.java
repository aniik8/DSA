package SlidingWIndow;
import java.util.*;

public class SwGFG {
    public static void main(String[] args) {
        int[] arr= {100, 200, 300, 400};
        ArrayList<Integer> list= new ArrayList<>();
        list = ;
        System.out.println(maximumSumSubarray(2, );
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
    static public long[] printFirstNegativeInteger(long A[], int N, int K)
    {

    }
}
