package Algorithms.Stanford;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

/**
 * Created by kashishtayal on 12/20/16.
 */
public class UndirectedGraph {
    Map<Integer, List<Edge>> adjList = new HashMap<>();
    int numOfVertices = 0;
    public UndirectedGraph(String inFileName){
        init(inFileName);
    }
    private void init(String inFileName){
        URL path = UndirectedGraph.class.getClassLoader().getResource(inFileName);
        try {
            List<String> lines = FIleReaderUtils.fileReader(path);
            for(int i = 1; i < lines.size(); i++){
                String line  =  lines.get(i);
                String[] data = line.split("\\s");
                int v1 = Integer.parseInt(data[0]);
                int v2 = Integer.parseInt(data[1]);
                int weight = Integer.parseInt(data[2]);
                Edge edge = new Edge(v1,v2,weight);

                List<Edge> list1 = adjList.get(v1);
                if(list1 == null) list1 = new ArrayList<>();
                list1.add(edge);
                adjList.put(v1,list1);

                List<Edge> list2 = adjList.get(v2);
                if(list2 == null) list2 = new ArrayList<>();
                list2.add(edge);
                adjList.put(v2,list2);

            }
            numOfVertices = adjList.size();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public int calculateMSTCost(){
        PriorityQueue<Edge> heap = new PriorityQueue<>();
        Set<Integer> visitedVertices = new HashSet<>();
        int startVertex = adjList.keySet().iterator().next();
        heap.addAll(adjList.get(startVertex));
        visitedVertices.add(startVertex);
        int result = 0;
        while(visitedVertices.size() < numOfVertices){
            Edge edge = heap.remove();
            int to = 0;
            while(true){
                int v1 = edge.vertex1;
                int v2 = edge.vertex2;
                if(visitedVertices.contains(v1) && visitedVertices.contains(v2)){
                    edge = heap.remove();
                }else if(!visitedVertices.contains(v1)){
                    to = v1;
                    break;
                }else{
                    to = v2;
                    break;
                }
            }
            visitedVertices.add(to);
            result += edge.weight;
            List<Edge> edges = adjList.get(to);
            for(Edge e : edges){
                if(!heap.contains(e) && e!=edge) heap.add(e);
            }
        }
        return result;
    }
    public int shortestPathLengthBetween(int inV1, int inV2){
        Map<Integer,Integer> followMap = new HashMap<>();
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] < o2[1]) return -1;
                else if(o1[1] > o2[1]) return 1;
                else return 0;
            }
        });

        Set<Integer> visited = new HashSet<>();
        heap.add(new int[]{inV1,0,inV1});
        int result = -1;
        while(visited.size() < adjList.size()){
            int[] vert = heap.poll();
            int id = vert[0];
            int dist = vert[1];
            int prev = vert[2];
            while(visited.contains(id)){
                vert = heap.poll();
                id = vert[0];
                dist = vert[1];
                prev = vert[2];
            }
            followMap.put(id,prev);
            visited.add(id);
            if(id == inV2){
                result = dist;
            }
            List<Edge> neighbors = adjList.get(id);
            for(Edge edge : neighbors){
                int neighborId = edge.toVertex(id);
                if(!visited.contains(neighborId)){
                    heap.add(new int[]{neighborId,dist+edge.weight,id});
                }
            }
        }
        int curr = inV2;
        while(curr != inV1){
            System.out.print(curr+" <- ");
            curr = followMap.get(curr);
        }
        System.out.println(inV1);
        return result;
    }
    class Edge implements Comparable<Edge>{
        int vertex1;
        int vertex2;
        int weight;
        Edge(int s, int e, int w){
            vertex1 = s;
            vertex2 = e;
            weight = w;
        }
        public int toVertex(int fromVertex){
            return vertex1==fromVertex?vertex2:vertex1;
        }
        @Override
        public int compareTo(Edge o) {
            if(weight > o.weight) return 1;
            else if(weight < o.weight) return -1;
            else return 0;
        }
    }

    public static void main(String[] args) {
        UndirectedGraph graph = new UndirectedGraph("dijkstraTest.txt");
        System.out.println(graph.shortestPathLengthBetween(1,5));
    }
}
