package Graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class BfsTraversal {
    public static void main(String[] args) {

    }
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];     // to check if a particular vertex is visited or not
        LinkedList<Integer> list = new LinkedList<>();  // to add the neighbours
        ArrayList<Integer> arr = new ArrayList<>();     // our final answer

        // adding vertex 0 to our answer as well as list because it is the starting vertex
        visited[0] = true;
        list.add(0);
        // will add until all are visited
        while(!list.isEmpty()){
            int x = list.poll();
            arr.add(x);
        // adding the vertex to the list so that
            for (int ver : adj.get(x)){
                if(!visited[ver]){
                    visited[ver] = true;
                    list.add(ver);
                }
            }
        }
        return arr;
    }

    public ArrayList<Integer> bfsOfGraphs(int V, ArrayList<ArrayList<Integer>> adj) {
        // array list
        // queue
        // boolean array
        boolean[] visited = new boolean[V];     // to check if a particular vertex is visited or not
        LinkedList<Integer> list = new LinkedList<>();  // to add the neighbours
        ArrayList<Integer> arr = new ArrayList<>();     // our final answer
        visited[0] = true;
        list.add(0);
        while (!list.isEmpty()) {
            int x = list.poll();
            arr.add(x);
            for (int neighbor :
                    adj.get(x)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    list.add(neighbor);
                }
            }
        }
        return arr;
    }
}
