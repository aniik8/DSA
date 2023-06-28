package Graph;
import java.util.*;
public class TopQues {
    public static void main(String[] args) {
        int[][] prerequisites = {{1,0}};
        System.out.println(Arrays.toString(findOrder(2, prerequisites)));
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
    //by using cycle detection in dfs, this problem can be solved easily..
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
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        boolean[] visited  =new boolean[numCourses];
        int[] arr = new int[numCourses];
        boolean[] onStack = new boolean[numCourses];
        Stack<Integer> stack = new Stack<>();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] num : prerequisites) {
            int u=num[0];
            int v=num[1];
            adj.get(u).add(v);
        }
        for (int i = 0; i < numCourses; i++) {
            if(!visited[i]){
                if(dfsTopo(adj, stack, i, visited, onStack)){
                    return new int[0];
                }
            }
        }
        if(stack.isEmpty())
            return new int[0];
        else{
            int i = 0;
            while(!stack.isEmpty()){
                arr[i++] = stack.pop();
            }
        }
        return arr;
    }
    static boolean dfsTopo(ArrayList<ArrayList<Integer>> adj,  Stack<Integer> stack, int v, boolean[] visited,
                           boolean[] onStack){
        visited[v] = true;
        onStack[v] = true;
        for (int neighbor :
                adj.get(v)) {
            if (!visited[neighbor]) {
                if (dfsTopo(adj, stack, neighbor, visited, onStack)) {
                    return true;
                } else if (onStack[neighbor])
                    return false;
            }

        }
        onStack[v] = false;
        stack.push(v);
        return false;
    }
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        boolean[] visited =new boolean[numCourses];
        boolean[] dfsVisited =new boolean[numCourses];
        Stack<Integer> stack=new Stack<>();
        List<List<Integer>> adjList=new ArrayList<>();
        for(int i=0;i< numCourses;i++){
            adjList.add(new ArrayList<>());
        }
        for(int[] edge:prerequisites){
            int u=edge[0];
            int v=edge[1];
            adjList.get(u).add(v);
        }
        int[] ans=new int[numCourses];
        for(int i=0;i<numCourses;i++){
            if(!visited[i]){
                if(topSort(i,adjList,visited,dfsVisited,stack)){
                    return new int[0];
                }
            }
        }


        for(int i=ans.length-1;i>=0;i--){
            ans[i]=stack.peek();
            stack.pop();
        }
        return ans;
    }

    public boolean topSort(int node,List<List<Integer>> adjList,boolean []visited,boolean [] dfsVisited,Stack<Integer> stack){
        if(dfsVisited[node])
            return true;
        if(visited[node])
            return false;
        visited[node]=true;
        dfsVisited[node]=true;
        for(int nbr:adjList.get(node)){
            if(topSort(nbr,adjList,visited,dfsVisited,stack))
                return true;
        }
        dfsVisited[node]=false;
        stack.push(node);
        return false;
    }
}

