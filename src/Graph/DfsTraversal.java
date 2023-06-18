package Graph;
import java.util.*;
public class DfsTraversal {
    public static void main(String[] args) {
        int V = 5; // Number of vertices

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        // Create adjacency list for the graph
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Add edges between vertices
        adj.get(0).add(1);
        adj.get(0).add(2);
        adj.get(1).add(3);
        adj.get(2).add(4);

        DfsTraversal dfsExample = new DfsTraversal();
        ArrayList<Integer> dfsTraversal = dfsExample.dfsOfGraph(V, adj);

        System.out.println("DFS Traversal:");
        for (int vertex : dfsTraversal) {
            System.out.print(vertex + " ");
        }

    }

        public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
            boolean[] visited = new boolean[V];
            ArrayList<Integer> dfsTraversal = new ArrayList<>();

            Stack<Integer> stack = new Stack<>();
            stack.push(0); // Start from vertex 0

            while (!stack.isEmpty()) {
                int vertex = stack.pop();

                if (!visited[vertex]) {
                    visited[vertex] = true;
                    dfsTraversal.add(vertex);

                    for (int neighbor : adj.get(vertex)) {
                        if (!visited[neighbor]) {
                            stack.push(neighbor);
                        }
                    }
                }
            }

            return dfsTraversal;
        }

}
