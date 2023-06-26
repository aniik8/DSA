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
// iterative solution of dfs
// this is based on the dfs problem of gfg where we need to run loop reversely so that we can have our correct answer
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

                ArrayList<Integer> neighbors = adj.get(vertex);
                for (int i = neighbors.size() - 1; i >= 0; i--)
                {
                    int neighbor = neighbors.get(i);
                    if (!visited[neighbor]) {
                        stack.push(neighbor);
                    }
                }
            }
        }

        return dfsTraversal;
    }

//  Dfs traversal recursive
    public static ArrayList<Integer> dfsRecursive(int V, ArrayList<ArrayList<Integer>> adj){
        boolean[] visited = new boolean[V];
        ArrayList<Integer> list = new ArrayList<>();
        dfs(0, list, visited, adj);
        return list;
    }
    static void dfs(int v, ArrayList<Integer> list, boolean[] visited, ArrayList<ArrayList<Integer>> adj){
        visited[v] = true;
        list.add(v);
        for (int x :adj.get(v)) {
            dfs(x, list, visited, adj);
        }
    }
    // DFS traversal similar problem ---> Detect a cycle in a graph
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {

        boolean[] visited = new boolean[V];

        for (int v = 0; v < V; v++) {
            if (!visited[v]) {
                Stack<Integer> stack = new Stack<>();
                stack.push(v);
                int[] parent = new int[V];
                parent[v] = -1;

                while (!stack.isEmpty()) {
                    int vertex = stack.pop();
                    visited[vertex] = true;

                    ArrayList<Integer> neighbors = adj.get(vertex);
                    for (int neighbor : neighbors) {
                        if (!visited[neighbor]) {
                            stack.push(neighbor);
                            parent[neighbor] = vertex;
                        } else if (neighbor != parent[vertex]) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    // Detecting Cycle in Directed graph
    // Why the previous approach don't work in this case? because for some cases like 0 -> 1 <- 2 , it'll detect cycle
    // which is not present so, here we'll use a stack named as onstack which will keep record of the nodes in the stack
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        boolean[] onstack = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < V; i++) {
            if(!visited[i]){
               stack.push(i);
               while(!stack.isEmpty()){
                   int vertex = stack.pop();
                   visited[vertex] = true;
                   onstack[vertex] = true;
                   ArrayList<Integer> neighbours = adj.get(vertex);
                   for (int neighbour : neighbours){
                       if(!visited[neighbour]){
                           stack.push(neighbour);
                       }else if(onstack[neighbour]){
                           return true;
                       }
                   }
                   onstack[vertex] = false;

               }
            }
        }
        return false;
    }
// is cycle in an undirected graph recursive
    public boolean isCyclic_recursive(int V, ArrayList<ArrayList<Integer>> adj){
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {       // it'll have disconnected components as well
            if (dfs_cycle(visited, adj, -1, i))
                return true;
        }
        return false;
    }
    public boolean dfs_cycle(boolean[] visited, ArrayList<ArrayList<Integer>> adj, int parent, int v){
        visited[v] = true;
        for (int neighbor : adj.get(v)) {
            if (!visited[neighbor]){
                if(dfs_cycle(visited, adj, v, neighbor)) return true;
            }else if(parent != neighbor)
                return true;
        }
        return false;
    }
}
