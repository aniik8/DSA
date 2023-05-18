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
//        System.out.println(matrixMultiplication(N, arr));
        System.out.println(minCutMemoize("aaabba"));
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
}
