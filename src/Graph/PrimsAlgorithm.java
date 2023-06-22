package Graph;
import java.util.*;
public class PrimsAlgorithm {
    public static void main(String[] args) {

    }
    static class Pair implements Comparable<Pair> {
        int v;
        int wt;
        public Pair(int v, int wt) {
            this.v = v;
            this.wt = wt;
        }
        @Override
        public int compareTo(Pair p2) {
            return this.wt - p2.wt;
        }
    }
    static class Edge{
        int src, dst, wt;
        public Edge(int src, int dst, int wt){
            this.src = src;
            this.dst = dst;
            this.wt = wt;
        }
    }
    static int spanningTree(int V, int E, int edges[][]){
        // Code Here.

        ArrayList<ArrayList<Edge>> adj = new ArrayList<>();

        for(int i=0; i<V; i++){
            adj.add(new ArrayList<Edge>());
        }

        for(int[] i : edges){
            int u = i[0];
            int v = i[1];
            int d = i[2];
            adj.get(u).add(new Edge(u, v, d));
            adj.get(v).add(new Edge(v, u, d));
        }

        int cost = 0;
        boolean[] vis = new boolean[V];
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        pq.add(new Pair(0, 0));

        while(!pq.isEmpty()) {
            Pair curr = pq.remove();
            int node = curr.v; // Store the current node

            if(!vis[node]) {
                vis[node] = true;
                cost += curr.wt;
                for(Edge e : adj.get(node)) { // Iterate over the edges of the current node
                    int adjNode = e.dst; // Get the adjacent node
                    if(!vis[adjNode]) {
                        pq.add(new Pair(adjNode, e.wt));
                    }
                }
            }
        }

        return cost;

    }
    static int spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj){
        boolean[] vis = new boolean[V];
        PriorityQueue<Pair> queue = new PriorityQueue<>();
        queue.add(new Pair(0,0));
        int ans = 0;
        while(queue.size() != 0){
            Pair current = queue.remove();
            int u = current.v;
            if(vis[u]){
                continue;
            }
            ans = current.wt;
            vis[u] = true;
            ArrayList<ArrayList<Integer>> neighbor = adj.get(u);
            for (ArrayList<Integer> list :
                    neighbor) {
                int vertex = list.get(0);
                int weight = list.get(1);
                if(vis[vertex] == false){
                    queue.add(new Pair(vertex, weight));
                }
            }
        }
        return ans;

    }
}
