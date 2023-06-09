package DP;

public class UnBoundedKnapSack {
    public static void main(String[] args) {
        int val[] = {1,2,5}, price[] = {1, 5, 8, 9, 10, 17, 17, 20};
//        System.out.println(knapSack(4, 8, val, wt));
//        System.out.println(cutRod(price, 8));
//        System.out.println(coinChange(val, 10));
        System.out.println(coinChangei(val, 11));
    }

    // Unbounded Knapsack - in which repetition is allowed
    static int knapSack(int N, int W, int val[], int wt[])
    {
        int[][] t = new int[N+1][W+1];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < W + 1; j++) {
                if(i == 0 || j == 0)
                    t[i][j] = 0;
            }
        }
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < W+1; j++) {
                if(wt[i-1] <= j)
                t[i][j] = Math.max(val[i-1] + t[i][j-wt[i -1]], t[i-1][j]);
                else
                    t[i][j] = t[i-1][j];
            }
        }
        return t[N][W];
    }
    // Rod Cutting Problem (Exactly same as knapsack)
    static int cutRod(int price[], int n) {
        int[][] t= new int[n+1][n+1];
        int[] len = new int[n];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if(i == 0 || j == 0){
                    t[i][j] = 0;
                }
            }
        }
//        https://leetcode.com/discuss/general-discussion/475924/my-experience-and-notes-for-learning-dp
        for (int i = 0; i < n; i++) {
            len[i] = i+1;
        }
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1; j++) {
                if(len[i-1] <= j){
                    t[i][j] = Math.max(price[i-1] + t[i][j-len[i-1]], t[i-1][j]);
                }
                else t[i][j] = t[i-1][j];
            }

        }
        return t[n][n];
    }
    // coin change problem - calculate the total number of ways by which the coin can be selected to given sum
    static int coinChange(int[] coins, int amount){
        int n = coins.length;
        int[][] t = new int[n+1][amount+1];
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < amount+1; j++) {
                if(i == 0)
                    t[i][j] = 0;
                else if(j == 0)
                    t[i][j] = 1;
            }

        }
        t[0][0] = 1;
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < amount+1; j++) {
                if(coins[i-1] <=j){
                    t[i][j] = t[i][j - coins[i-1]] + t[i-1][j];
                }
                else t[i][j] = t[i-1][j];
            }
        }
        return t[n][amount];
    }

    // https://leetcode.com/problems/coin-change/description/
    public static int coinChangei(int[] coins, int amount){
        int n = coins.length;
        int[][] t = new int[n+1][amount+1];
        for(int i = 0; i < n+1; i++){
            for (int j = 0; j < amount + 1; j++) {
                if(i == 0)
                    t[i][j] = Integer.MAX_VALUE-1;
                else if(j == 0)
                    t[i][j] = 0;
            }
        }
        t[0][0] = Integer.MAX_VALUE-1;
        for (int i = 1; i < amount+1;i++) {
            if(i % coins[0] == 0){
                t[1][i] = 1;
            }else t[1][i] = Integer.MAX_VALUE-1;
        }
        // unbounded knapsack code
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < amount+1; j++) {
                if(coins[i - 1] <= j){
                    t[i][j] = Math.min(1+ t[i][j - coins[i-1]], t[i-1][j]);
                }
                else
                    t[i][j] = t[i-1][j];
            }
        }
        return t[n][amount];
    }
}
