package DP;

public class MatrixChainMultiDP {
    static int[][] t = new int[500][500];
    public static void main(String[] args) {
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 500; j++) {
                t[i][j] = -1;
            }
        }
        int[] arr = {10, 30, 5, 60};
        int N = 4;
        String s = "T|T&F^T";
//        System.out.println(matrixMultiplication(N, arr));
//        System.out.println(minCutMemoize("aaabba"));
        System.out.println(countWays(s.length(), s));
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
        return countTrue(0,  N, S, 1);
    }
    static int countTrue(int i, int j, String S, int isTrue){
        if (i > j)
            return 0;

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
                leftTrue = countTrue(i, k - 1, S,
                        1);

                // Count number of False in left Partition
                leftFalse = countTrue( i, k - 1,
                         S,0);


                // Count number of True in right Partition
                rightTrue = countTrue(k + 1, j,
                        S,1);

                // Count number of False in right Partition
                rightFalse = countTrue(k + 1,
                        j,S,0 );

            // Evaluate AND operation
            if (S.charAt(k) == '&')
            {
                if (isTrue == 1)
                {
                    temp_ans  = temp_ans + leftTrue * rightTrue;
                }
                else
                {
                    temp_ans = temp_ans
                            + leftTrue * rightFalse
                            + leftFalse * rightTrue
                            + leftFalse * rightFalse;
                }
            }
            // Evaluate OR operation
            else if (S.charAt(k) == '|')
            {
                if (isTrue == 1)
                {
                    temp_ans = temp_ans
                            + leftTrue * rightTrue
                            + leftTrue * rightFalse
                            + leftFalse * rightTrue;
                }
                else
                {
                    temp_ans
                            = temp_ans + leftFalse * rightFalse;
                }
            }

            // Evaluate XOR operation
            else if (S.charAt(k) == '^')
            {
                if (isTrue == 1)
                {
                    temp_ans = temp_ans
                            + leftTrue * rightFalse
                            + leftFalse * rightTrue;
                }
                else
                {
                    temp_ans = temp_ans
                            + leftTrue * rightTrue
                            + leftFalse * rightFalse;
                }
            }
        }
        return temp_ans;

    }

}

