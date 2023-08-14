package DP;

import java.util.Arrays;

public class DP2 {
    public static void main(String[] args) {
        int[] nums = {10, 8};
//        System.out.println(minDifference(nums));
    }
    static int boundedKnapsack(int[] weight, int[] value, int W, int n){
        if(n == 0 || W == 0)
            return 0;
        // if the current weight is less than the weight of knapsack
        if(weight[n-1] <= W){
            // two choices take or leave
            return Math.max(value[n-1]+ boundedKnapsack(weight, value, W - weight[n-1], n - 1),
                    boundedKnapsack(weight, value, W, n-1));
        }
        else
            return boundedKnapsack(weight, value, W, n-1);
    }
// memoization -- in recursion there are various repeating calls which increases the complexity of the program
// so to reduce the function calls and reduce the complexity of the program, use memoization
    static int[][] t = new int[1002][1002];
    static int knapSack(int W, int[] wt, int[] val, int n){
        for (int i = 0; i < n; i++) {
            Arrays.fill(t[i], -1);
        }
        return knapSackhelper(W, wt, val, n, t);
    }
    static int knapSackhelper(int W, int[] wt, int[] val, int n, int[][]t)
    {
        if(n == 0 || W == 0)
            return 0;
        if(t[n] [W] != -1)
            return t[n][W];
        if(wt[n-1] <= W)
            return t[n][W] = Math.max(val[n-1]+knapSackhelper(W-wt[n-1], wt, val, n-1, t),
                    knapSackhelper(W, wt, val, n-1, t));
        else{
            return t[n][W] = knapSackhelper(W, wt, val, n-1, t);
        }
    }
// Minimum subset difference problem
    static public int minDifference(int[] arr, int n)
    {
        // minimum difference would be the one which will be the closest to the half one 
    int range = 0, num=Integer.MAX_VALUE;
    // sum of the given array
    for (int i = 0; i < arr.length; i++) {
        range += arr[i];
    }

    //just initialization of array
    boolean[][] t = new boolean[n+1][range+1];
    for (int i = 0; i < n+1; i++) {
        for (int j = 0; j < range+1; j++) {
            if(i == 0) {
                t[i][j] = false;
            }
            if(j == 0) {
                t[i][j] = true;
            }
        }
    }

    // subset sum problem code....
    for (int i = 1; i < arr.length+1; i++) {
        for (int j = 1; j < range+1; j++) {
            if(arr[i - 1] <= j){
                t[i][j] = t[i-1][j - arr[i-1]] || t[i-1][j];
            }
            else
                t[i][j] = t[i-1][j];
        }
    }

    for (int i = range/2; i  >= 0; i--) {
        if(t[n][i]) {
            num = i;
            break;
        }
    }
    return (range - 2 * num);
    }
   //
   public int longestCommonSubsequence(String text1, String text2) {

   }
}
