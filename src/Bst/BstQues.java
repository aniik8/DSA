package Bst;

public class BstQues {
    public static void main(String[] args) {

    }
    // Leetcode 96 - recursive
    public int numTrees(int n) {
        if(n <= 1)
            return 1;
        int sum = 0;
        for(int i = 1; i <=n;i++){
            int left = numTrees(i-1);
            int right = numTrees(n - i);
            sum += left *right;
        }
        return sum;
    }
    // Memoized
    static int[] t = new int[20];
    public int numTreesMemoized(int n) {
        for(int i = 0; i < 20; i++){
            t[i] = 0;

        }
        return returnNumTrees(n, t);
    }
    public int returnNumTrees(int n, int[] t){
        if(n <= 1)
            return 1;
        if(t[n] != 0)
            return t[n];

        for(int i = 1; i <=n;i++){

            t[n] += returnNumTrees(i-1, t) * returnNumTrees(n - i,t);
        }
        return t[n];
    }

}

