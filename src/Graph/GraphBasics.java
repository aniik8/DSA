package Graph;

import java.util.ArrayList;
import java.util.Scanner;

public class GraphBasics {
    public static void main(String[] args) {
//        representMatrix();
        addGraph();

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
    // list representation of Graph
    static void addEdge(ArrayList<ArrayList<Integer>> adj, int n, int m){
        adj.get(n).add(m);
        adj.get(m).add(n);
    }
    static void printAdjList(ArrayList<ArrayList<Integer>> adj){
        for (int i = 0; i < adj.size(); i++) {
            System.out.print("Head is " + i + " ->");
            for (int j = 0; j < adj.get(i).size(); j++) {
                System.out.print(adj.get(i).get(j) + " -> " );
            }
            System.out.println();
        }
    }
    static void addGraph(){
        int vertices = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
                adj.add(new ArrayList<>());
        }
        addEdge(adj, 0, 1);
        addEdge(adj, 0, 4);
        addEdge(adj, 1, 2);
        addEdge(adj, 1, 3);
        addEdge(adj, 1, 4);
        addEdge(adj, 2, 3);
        addEdge(adj, 3, 4);

        printAdjList(adj);
    }
    // printing graph by using arrayList
        public ArrayList<ArrayList<Integer>> printGraph(int V, ArrayList<ArrayList<Integer>> adj) {
            ArrayList<ArrayList<Integer>> a1 = new ArrayList<ArrayList<Integer>> ();
            for(int i=0;i<adj.size();i++)
            {
                ArrayList<Integer> a= new ArrayList<Integer>();
                a.add(i);
                for(int j=0;j<adj.get(i).size();j++)
                {
                    a.add(adj.get(i).get(j));
                }
                a1.add(a);
            }
            return a1;
        }

}
