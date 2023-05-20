package DP;

import java.util.Arrays;

public class MatrixChainMultiDP {
    static int[][] t = new int[500][500];
    static String s = "T|F^F&T|F^F^F^T|T&T^T|F^T^F&F^T|T^F";
    static int N = s.length();
    static int[][][] tp = new int[N+1][N+1][2];
    public static void main(String[] args) {
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 500; j++) {
                t[i][j] = -1;
            }
        }
        int[] arr = {10, 30, 5, 60};


//        System.out.println(matrixMultiplication(N, arr));
//        System.out.println(minCutMemoize("aaabba"));
         // 2 because of isTrue can only have 1 or 0
        for (int row[][] : tp) {
            for (int col[] : row)
                Arrays.fill(col, -1);
        }
//        System.out.println(countWays(s.length(), s));
        System.out.println(superEggDrop(1, 2));
    }

    static int matrixMultiplication(int N, int arr[])
    {
        return matrixChainMemoize(1, N-1, N, arr);
    }
    static int matrixChain(int i, int j, int N, int[] arr){
        int min = Integer.MAX_VALUE;
        if(i >= j)
            return 0;
        for (int k = i; k <= j-1; k++) {
            int temp = (matrixChain(i, k, N, arr) + matrixChain(k+1, j, N, arr))
                    + arr[i-1] * arr[j] * arr[k];
            if(temp < min){
                min = temp;
            }
        }
        return min;

    }
    static int matrixChainMemoize(int i, int j, int N, int[] arr){
        int min = Integer.MAX_VALUE;
        if(i >= j)
            return 0;
        if(t[i][j] != -1)
            return t[i][j];
        for (int k = i; k <= j-1; k++) {
            int temp = (matrixChainMemoize(i, k, N, arr) + matrixChainMemoize(k+1, j, N, arr))
                    + arr[i-1] * arr[j] * arr[k];
            if(temp < min){
                min = temp;
            }
        }
        t[i][j] = min;
        return min;

    }
    static int minCut(String s) {
        return checkPalin(s, 0, s.length());
    }
    static int checkPalin(String s, int i, int j){
        if(i > j) return 0;
        if(isPalindrome(s, i, j))
            return 0;
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j-1; k++) {
            int temp = 1 + checkPalin(s, i, k) + checkPalin(s,k+1, j) ;
            if(temp < min){
                min = temp;
            }
        }
        return min;
    }
    static boolean isPalindrome(String s, int i, int j ){
        if(i == j)return true;
        while(i < j){
            if(s.charAt(i) != s.charAt(j-1)){
                return  false;
            }
            i++; j--;
        }
        return true;
    }
    // Minimum cut memoize
    static int minCutMemoize(String s){
        return checkPalinMemoize(s, 0, s.length());
    }
// problem with first input of gfg ques...
    static int checkPalinMemoize(String s, int i, int j){
        if(i >= j) return 0;
        if(t[i][j] != -1) return t[i][j];
        if(isPalindrome(s, i, j)) return 0;
        int min  = Integer.MAX_VALUE;
        for (int k = i; k < j-1; k++) {
            int temp =  checkPalinMemoize(s, i, k) + checkPalinMemoize(s, k+1, j)+1;
            if(temp < min){
                min = temp;
            }
        }
        t[i][j] = min;
        return min;
    }
    // optimized function for check Palindrome memoize for interview bit problem
// BASICALLy here we can see that we instead of checking the input in table from (i,k) we re checking it only one time
// that can be a slow process.
    static int checkPalinMemoize_Updated(String s, int i, int j){
        if(i >= j) return 0;
        if(t[i][j] != -1) return t[i][j];
        if(isPalindrome(s, i, j)) return 0;
        int min = Integer.MAX_VALUE, left, right;
        for (int k = i; k < j-1; k++) {
            if(t[i][k] != -1)
                left = t[i][k];
            else {
                left = checkPalinMemoize_Updated(s, i, k);
                t[i][k] = left;

            }

            if(t[k+1][j] != -1)
                right = t[k+1][j];
            else {
                right = checkPalinMemoize_Updated(s, k + 1, j);
                t[i][k] = right;
            }
            int temp = left + right + 1;
            if(min > temp){
                min = temp;
            }
        }
        t[i][j] = min;
        return min;
    }
    // Boolean Parenthesization
    static int countWays(int N, String S){
        return countTrueMemoize(0,  N-1, S, 1, tp);
    }
    static int countTrueMemoize(int i, int j, String S, int isTrue, int[][][] dp){
        if (i > j)
            return 0;
        // base condition
        if (i == j)
        {
            if (isTrue == 1)
            {
                return (S.charAt(i) == 'T') ? 1 : 0;
            }
            else
            {
                return (S.charAt(i) == 'F') ? 1 : 0;
            }
        }
        if(tp[i][j][isTrue] != -1) return tp[i][j][isTrue];
        int leftTrue, rightTrue, leftFalse, rightFalse;
        int temp_ans = 0;

        for (int k = i + 1; k <= j - 1; k = k + 2) {

            // Count number of True in left Partition
            if (tp[i][k - 1][1] != -1) {
                leftTrue = tp[i][k - 1][1];
            } else leftTrue = countTrueMemoize(i, k - 1, S, 1, dp);

            // Count number of False in left Partition
            if (tp[i][k - 1][0] != -1)
                leftFalse = tp[i][k - 1][0];
            else
                leftFalse = countTrueMemoize(i, k - 1, S, 0, dp);

            // Count number of True in right Partition
            if (tp[k + 1][j][1] != -1)
                rightTrue = tp[k + 1][j][1];
            else
                rightTrue = countTrueMemoize(k + 1, j, S, 1, dp);

            // Count number of False in right Partition
            if (tp[k + 1][j][0] != -1)
                rightFalse = tp[k + 1][j][0];
            else
                rightFalse = countTrueMemoize(k + 1, j, S, 0, dp);
            // Evaluate AND operation
            if (S.charAt(k) == '&') {
                if (isTrue == 1) {
                    temp_ans += leftTrue * rightTrue;
                } else {
                    temp_ans += leftTrue * rightFalse + leftFalse * rightTrue + leftFalse * rightFalse;
                }
            }
            // Evaluate OR operation
            else if (S.charAt(k) == '|') {
                if (isTrue == 1) {
                    temp_ans += leftTrue * rightTrue + leftTrue * rightFalse + leftFalse * rightTrue;
                } else {
                    temp_ans += leftFalse * rightFalse;
                }
            }

            // Evaluate XOR operation
            else if (S.charAt(k) == '^') {
                if (isTrue == 1) {
                    temp_ans += leftTrue * rightFalse + leftFalse * rightTrue;
                } else {
                    temp_ans += leftTrue * rightTrue + leftFalse * rightFalse;
                }
            }

            tp[i][j][isTrue] = temp_ans;
        }
        return temp_ans;

    }
// memoize version of the problem above
static int countTrue(int i, int j, String S, int isTrue){
    if (i > j)
        return 0;
    // base condition
    if (i == j)
    {
        if (isTrue == 1)
        {
            return (S.charAt(i) == 'T') ? 1 : 0;
        }
        else
        {
            return (S.charAt(i) == 'F') ? 1 : 0;
        }
    }
    int leftTrue, rightTrue, leftFalse, rightFalse;
    int temp_ans = 0;

    for (int k = i + 1; k <= j - 1; k = k + 2)
    {

        // Count number of True in left Partition
        leftTrue = countTrue(i, k - 1, S,1);

        // Count number of False in left Partition
        leftFalse = countTrue( i, k - 1, S,0);


        // Count number of True in right Partition
        rightTrue = countTrue(k + 1, j, S,1);

        // Count number of False in right Partition
        rightFalse = countTrue(k + 1, j,S,0 );

        // Evaluate AND operation
        if (S.charAt(k) == '&')
        {
            if (isTrue == 1)
            {
                temp_ans += leftTrue * rightTrue;
            }
            else
            {
                temp_ans += leftTrue * rightFalse + leftFalse * rightTrue + leftFalse * rightFalse;
            }
        }
        // Evaluate OR operation
        else if (S.charAt(k) == '|')
        {
            if (isTrue == 1)
            {
                temp_ans += leftTrue * rightTrue + leftTrue * rightFalse + leftFalse * rightTrue;
            }
            else
            {
                temp_ans += leftFalse * rightFalse;
            }
        }

        // Evaluate XOR operation
        else if (S.charAt(k) == '^')
        {
            if (isTrue == 1)
            {
                temp_ans += leftTrue * rightFalse + leftFalse * rightTrue;
            }
            else
            {
                temp_ans += leftTrue * rightTrue + leftFalse * rightFalse;
            }
        }
    }
    return temp_ans;

}
    public static  int superEggDrop(int k, int n) {
            if(n == 1) return k;
            if(k == 0 || k == 1) return k;
            int min = Integer.MAX_VALUE, temp;
        for (int i = 1; i <= k; i++) {
            temp = 1 + Math.max(superEggDrop(i-1, n-1), superEggDrop(k-i, n));
            min = Math.min(temp, min);
        }
        return min;
    }
}

