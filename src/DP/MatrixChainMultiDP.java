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
        System.out.println(matrixMultiplication(N, arr));
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
}
