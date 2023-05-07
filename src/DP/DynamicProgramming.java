package DP;

import java.util.Arrays;

public class DynamicProgramming {
    public static void main(String[] args) {
        int values[] = {1, 2, 3};
        int[] arr = {1, 3, 5};
        int weight[] = {4, 5, 1};
//        System.out.println(knapSack(4, values, weight, 3));
//        System.out.println(isSubsetSum(6, arr, 30));
        System.out.println(equalPartition(3, arr));
    }

    static int knapSack(int W, int wt[], int val[], int n) {
        if (n == 0 || W == 0)
            return 0;
        if (wt[n - 1] <= W) {
            return Math.max(val[n - 1] + knapSack(W - wt[n - 1], wt, val, n - 1), knapSack(W, wt, val, n - 1));

        } else {
            return knapSack(W, wt, val, n - 1);
        }
    }

    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Returns the value of maximum profit
    static int knapSackRec(int W, int wt[], int val[],
                           int n, int[][] dp) {
        // Base condition
        if (n == 0 || W == 0)
            return 0;

        if (dp[n][W] != -1)
            return dp[n][W];

        if (wt[n - 1] > W)

            // Store the value of function call
            // stack in table before return
            return dp[n][W]
                    = knapSackRec(W, wt, val, n - 1, dp);

        else

            // Return value of table after storing
            return dp[n][W]
                    = max((val[n - 1]
                            + knapSackRec(W - wt[n - 1], wt, val,
                            n - 1, dp)),
                    knapSackRec(W, wt, val, n - 1, dp));
    }

    static int knapSacks(int W, int wt[], int val[], int N) {

        // Declare the table dynamically
        int dp[][] = new int[N + 1][W + 1];

        // Loop to initially filled the
        // table with -1
        for (int i = 0; i < N + 1; i++)
            for (int j = 0; j < W + 1; j++)
                dp[i][j] = -1;

        return knapSackRec(W, wt, val, N, dp);
    }

    static int knaapSack(int W, int wt[], int val[], int n) {
        int t[][] = new int[n + 1][W + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < W + 1; j++) {
                if (i == 0 || j == 0) {
                    t[i][j] = 0;
                }
            }
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < W + 1; j++) {
                if (wt[i - 1] <= j) {
                    t[i][j] = Math.max(val[i - 1] + t[i - 1][j - wt[i - 1]], t[i - 1][j]);
                } else {
                    t[i][j] = t[i - 1][j];
                }
            }
        }
        return t[n][W];
    }
    static Boolean isSubsetSum(int N, int arr[], int sum){
        // initialization
        boolean[][] t = new boolean[N+1][sum+1];
        for(int i =0; i < N+1; i++){
            for (int j = 0; j < sum + 1; j++) {
                if(i == 0)
                    t[i][j] = false;
                if(j == 0)
                    t[i][j] = true;
            }
            }
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                if(arr[i-1]<= j){
                    t[i][j] = t[i-1][j -arr[i-1]] || t[i-1][j];
                }
                else
                    t[i][j] = t[i-1][j];
            }
        }
        return t[N][sum];
    }

    // subset sum problem GFG
    static int equalPartition(int N, int arr[])
    {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
        }
        if(sum %2 != 0)
            return 0;
        else{
           boolean res = isSubsetSum(N, arr, sum/2);
           return res ? 1: 0;
        }
    }
    // Can partition sum DP
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if(sum %2 != 0)
            return false;
        else{
            return isSubsetSum(nums.length, nums, sum/2);

        }
    }
    public int minDifference(int arr[], int N) {
        // Your code goes here
        int total = 0;
        for(int i=0; i<N; i++) total += arr[i];

        int sum = total;
        // Subset Sum Logic !!
        boolean[] prev = new boolean[N+1];
        Arrays.fill(prev, true);

        for(int i=0; i<N+1; i++){
            boolean[] curr = new boolean[sum+1];

            for(int j=0; j<sum+1; j++){
                if((i==0 || i!=0) && j==0) curr[j] = true;
                else if(i==0 && j!=0) curr[j] = false;
                else
                if(arr[i-1] <= j) curr[j] = prev[j] || prev[j-arr[i-1]];
                else curr[j] = prev[j];
            }
            prev = curr;
        }

        int minI = (int)Math.pow(10,9);
        for(int s=0; s<=total/2; s++)
            if(prev[s] == true) minI = Math.min(minI, Math.abs((total - s) - s));


        return minI;
    }
}
