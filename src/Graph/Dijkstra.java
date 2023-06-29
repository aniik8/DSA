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
            if(visited[node])
                continue;
            visited[node] = true;
            for (ArrayList<Integer> currNode :
                    adj.get(node)) {
                int adjNode = currNode.get(0);
                int edgeWeight = currNode.get(1);
                if(edgeWeight + dis < distance[adjNode]){
                    distance[adjNode] = edgeWeight + dis;
                    priorityQueue.add(new pair(distance[adjNode],adjNode));
                }
            }
        }
        return distance;
    }
}
class pair {
    int a, distance;

    pair(int a, int distance) {
        this.a = a;
        this.distance = distance;
    }
}