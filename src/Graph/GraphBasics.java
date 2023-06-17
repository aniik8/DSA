package Graph;

import java.util.Scanner;

public class GraphBasics {
    public static void main(String[] args) {

    }

    // Graph using adjacency list
    static void representMatrix(){
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();   // number of vertices
        int m = sc.nextInt();   // number of edges.
        int[][] adjMatrix = new int[n+1][n+1];
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adjMatrix[u][v] = 1;
            adjMatrix[v][u] = 1;
        }
        printAdjMatrix(adjMatrix, n);
    }
    static void printAdjMatrix(int[][] arr, int n){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
