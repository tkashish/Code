package Algorithms.Stanford;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

/**
 * Created by kashishtayal on 12/20/16.
 */
public class MinimiumSpanningTree {
    Map<Integer, List<Edge>> adjList = new HashMap<>();
    int numOfVertices = 0;
    public MinimiumSpanningTree(String inFileName){
        init(inFileName);
    }
    private void init(String inFileName){
        URL path = MinimiumSpanningTree.class.getClassLoader().getResource(inFileName);
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
        MinimiumSpanningTree mst = new MinimiumSpanningTree("edges.txt");
        System.out.println(mst.calculateMSTCost());
    }
}
