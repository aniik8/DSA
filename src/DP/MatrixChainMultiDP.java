package DP;

public class MatrixChainMultiDP {
    public static void main(String[] args) {
        int[] arr = {10, 30, 5, 60};
        int N = 4;
        System.out.println(matrixMultiplication(N, arr));
    }

    static int matrixMultiplication(int N, int arr[])
    {
        return matrixChain(1, N-1, N, arr);
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
}
