package DP;

public class DynamicProgramming {
    public static void main(String[] args) {
        int values[] = {1,2,3} ; int weight[] = {4,5,1};
        System.out.println(knapSack(4, values, weight, 3));
    }
    static int knapSack(int W, int wt[], int val[], int n)
    {
        if(n == 0 || W == 0)
            return 0;
        if(wt[n-1] <= W){
            return knapSack(W-wt[n-1], wt, val, n-1);
        }
        else{
            return knapSack(W, wt, val, n-1);
        }
    }
}
