package Graph;
import java.util.*;
public class TopQues {
    public static void main(String[] args) {
        int[][] prerequisites = {{1,0}, {0,1}};
        System.out.println(cannFinish(2, prerequisites));
    }

    /*
     * Use topological sort and check if the sorted length and the numcourse are the same or not.
     *  if not that means there are cycles in the grahp*/
    static boolean cannFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        //CREATING ADJANCY LIST
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] e : prerequisites) {
            adj.get(e[1]).add(e[0]);
        }
        System.out.println(adj);
        boolean[] visited = new boolean[numCourses];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                dfss(stack, i, adj, visited);
            }
        }
        return stack.size() == numCourses;
    }

    static void dfss(Stack<Integer> stack, int V, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        visited[V] = true;
        for (int neighbor :
                adj.get(V)) {
            if (!visited[V]) {
                dfss(stack, neighbor, adj, visited);
            }
        }
        stack.push(V);
    }
    static boolean canFinish(int numCourses, int[][] prerequisites){
        boolean[] visited = new boolean[numCourses];
        boolean[] onStack = new boolean[numCourses];
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] e : prerequisites) {
            adj.get(e[1]).add(e[0]);
        }
        for (int i = 0; i < numCourses; i++) {
            if(!visited[i])
                if(dfs(i, visited, onStack, adj))
                    return false;
        }
        return true;
    }
    static boolean dfs(int V, boolean[] visited, boolean[] onStack, ArrayList<ArrayList<Integer>> adj){
        visited[V] = true;
        onStack[V] = true;
        for (int neighbor :adj.get(V))
        {
            if (!visited[neighbor]) {
                if (dfs(neighbor, visited, onStack, adj))
                    return true;

            }
            else if (onStack[neighbor])
                return true;
        }
        onStack[V] = false;
        return false;
    }
    // Can finish 2 -- course schedule 2
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        boolean[] visited  =new boolean[numCourses];
        Stack<Integer> stack = new Stack<>();
        List<List<Integer>> adj = new ArrayList<>(numCourses);
        for (int[] num :
                prerequisites) {
            adj.get(num[1]).add(num[0]);
        }
        for (int i = 0; i < numCourses; i++) {
            if(!visited[i]){
                if(dfsTopo(adj, stack, i, visited)){
                    return new int[0];
                }
            }
        }
        if(stack.isEmpty())
            return new int[0];
    }
    static boolean dfsTopo(List<List<Integer>> adj,  Stack<Integer> stack, int v, boolean[] visited){
        visited[v] = true;
        for (int neighbor :
                adj.get(v)) {
            if (!visited[neighbor])
                if(dfsTopo(adj, stack, neighbor, visited)){
                    return true;
                }
        }
        stack.push(v);
        return false;
    }
}

