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

    // kahn's algorithm --- aks
    // this algorithm uses the topological sort using bfs where we first check the incoming degree(or indegree) of
    // each node element. 2. now we'll see that the element who have the indegree of 0, gets added to the queue
    // 3. until our queue is not empty we'll add the current queue front element and  we'll check it's neighbor
    // if the neighbor have a indegree of 0 (--indegree[neighbor] == 0) then add the neighbor to the queue)
    static int[] topoSortBFS(int V, ArrayList<ArrayList<Integer>> adj){
        Queue<Integer> queue = new LinkedList<>();
        int[] indegree = new int[V];
        for(ArrayList<Integer> list: adj){
            for (Integer e :
                    list) {
                indegree[e]++;
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        bfs(list, indegree, V, queue,adj);
        int[] arr = new int[V];
        for (int i = 0; i < V; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
    static void bfs(ArrayList<Integer> list, int[] indegree, int V, Queue<Integer> queue,ArrayList<ArrayList<Integer>> adj){
        for (int i = 0; i < V; i++) {
            if(indegree[i] == 0)
                queue.add(i);
        }
        while (!queue.isEmpty()){
            int x = queue.poll();
            list.add(x);
            for (int a : adj.get(x)) {
                if(--indegree[a] == 0)
                    queue.add(a);
            }
        }
    }
}
