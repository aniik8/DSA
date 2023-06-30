package Graph;
import java.util.*;
public class Dijkstra {
    public static void main(String[] args) {

    }
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S)
    {
     PriorityQueue<pair> priorityQueue = new PriorityQueue<>((node1, node2) -> node1.distance- node2.distance);
     boolean[] visited = new boolean[V];
     int[] distance = new int[V];
        for (int i = 0; i < V; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[S] = 0;
        priorityQueue.add(new pair(S, 0));
        while(!priorityQueue.isEmpty()){
            int dis = priorityQueue.peek().distance;
            int node = priorityQueue.peek().a;
            priorityQueue.remove();
            if(visited[node])
                continue;
            visited[node] = true;
            for (int i = 0; i < adj.get(node).size(); i++) {
                int edgeWeight = adj.get(node).get(i).get(1);
                int adjNode = adj.get(node).get(i).get(0);
                if(edgeWeight + dis < distance[adjNode]){
                    distance[adjNode] = edgeWeight + dis;
                    priorityQueue.add(new pair(distance[adjNode],adjNode));
                }
            }
        }
        return distance;
    }
    // shortest path from one node to another - return the path
    public static List<Integer> shortestPath(int n, int m, int edges[][]) {
        // converting the adjacency matrix to list
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            adj.get(edges[i][0]).add(new Pair(edges[i][1], edges[i][2]));
            adj.get(edges[i][1]).add(new Pair(edges[i][0], edges[i][2]));
        }
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>((x, y) -> x.distance - y.distance);
        int[] distance = new int[n+1];
        int[] parent = new int[n+1];
        // filling the distance array with infinite value and parent array with 1
        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
            parent[i] = i;
        }
        distance[1] = 0;
        priorityQueue.add(new Pair(0, 1));
        // normal dijkstra implementation just added a functionality of parent[adjEdge]
        while(!priorityQueue.isEmpty()){
            int dis=priorityQueue.peek().distance;
            int node=priorityQueue.peek().node;
            priorityQueue.remove();
            for(Pair it:adj.get(node)){
                int edgeweight=it.distance;
                int adjedge=it.node;
                if(dis+edgeweight<distance[adjedge]){
                    distance[adjedge]=dis+edgeweight;
                    priorityQueue.add(new Pair(dis+edgeweight,adjedge));
                    parent[adjedge] = node;
                }
            }
        }
        // if a node is unreachable then, put -1 in that place
        List<Integer> list = new ArrayList<>();
        if(distance[n] == Integer.MAX_VALUE)
        {
            list.add(-1);
            return list;
        }
        // this will add the node from parent array as from dry run we see that parent array will have path to our
        // solution
        int node = n;
        while(parent[node] != node){
            list.add(node);
            node = parent[node];
        }
        list.add(1);
        // reversing the list
        Collections.reverse(list);
        return list;
    }
}
class pair {
    int a, distance;

    pair(int a, int distance) {
        this.a = a;
        this.distance = distance;
    }
}

class Pair{
    int distance;
    int node;
    Pair(int distance,int node){
        this.distance=distance;
        this.node=node;
    }

}
class Solution
{
    //Function to find the shortest distance of all the vertices
    //from the source vertex S.
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S)
    {
        // Write your code here
        PriorityQueue<Pair> pq=new PriorityQueue<Pair>((x,y)->x.distance-y.distance);

        int dist[]=new int[V];
        for(int i=0;i<V;i++){
            dist[i]=(int)(1e9);
        }
        dist[S]=0;

        pq.add(new Pair(0,S));

        while(!pq.isEmpty()){
            int dis=pq.peek().distance;
            int node=pq.peek().node;
            pq.remove();
            for(ArrayList<Integer> it:adj.get(node)){
                int edgeweight=it.get(1);
                int adjedge=it.get(0);
                if(dis+edgeweight<dist[adjedge]){
                    dist[adjedge]=dis+edgeweight;
                    pq.add(new Pair(dis+edgeweight,adjedge));
                }
            }
        }

        return dist;

    }
}