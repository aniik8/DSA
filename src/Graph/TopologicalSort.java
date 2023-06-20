package Graph;
import java.util.*;
public class TopologicalSort {
    public static void main(String[] args) {

    }
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj)
    {   boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < V; i++) {
            if(!visited[i]){
                dfs(i, adj, visited, stack);
            }
        }
        int[] result = new int[V];
        int x = 0;
        for (int i = 0; i < V; i++) {
            result[x++] = stack.pop();
        }
        return result;
    }
    static void dfs(int v, ArrayList<ArrayList<Integer>> adj,boolean[] visited, Stack<Integer>stack){
        visited[v] = true;
        for (int neighbor : adj.get(v)) {
            if(!visited[neighbor])
                dfs(neighbor, adj, visited, stack);
        }
        stack.push(v);
    }
}
