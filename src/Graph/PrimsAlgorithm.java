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
            ans += current.wt;
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
