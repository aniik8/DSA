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
}

