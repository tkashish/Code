package Algorithms.Stanford;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

/**
 * Created by kashishtayal on 12/20/16.
 */

/*
answer -3612829
 */
public class UnionFind {
    private static int treeCount = 1;
    public static int getCount(){
        int result = treeCount;
        treeCount++;
        return result;
    }
    Map<Integer, Vertex> vertexValueMap = new HashMap<>();
    Map<Integer, List<Edge>> adjList = new HashMap<>();
    List<Edge> edgesList = new ArrayList<>();
    int numOfVertices = 0;
    public UnionFind(String inFileName){
        init(inFileName);
    }
    private void init(String inFileName){
        URL path = UnionFind.class.getClassLoader().getResource(inFileName);
        try {
            List<String> lines = FIleReaderUtils.fileReader(path);
            for(int i = 1; i < lines.size(); i++){
                String line  =  lines.get(i);
                String[] data = line.split("\\s");
                int v1 = Integer.parseInt(data[0]);
                int v2 = Integer.parseInt(data[1]);
                int weight = Integer.parseInt(data[2]);
                Vertex ver1 = vertexValueMap.get(v1);
                Vertex ver2 = vertexValueMap.get(v2);
                if(ver1 == null){
                    ver1 = new Vertex(v1);
                    vertexValueMap.put(v1,ver1);
                }
                if(ver2 == null){
                    ver2 = new Vertex(v2);
                    vertexValueMap.put(v2,ver2);
                }
                Edge edge = new Edge(ver1, ver2,weight);
                edgesList.add(edge);
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
            Collections.sort(edgesList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    class Edge implements Comparable<Edge>{
        Vertex vertex1;
        Vertex vertex2;
        int weight;
        Edge(Vertex s, Vertex e, int w){
            vertex1 = s;
            vertex2 = e;
            weight = w;
        }
        public Vertex toVertex(Vertex fromVertex){
            return vertex1.value==fromVertex.value?vertex2:vertex1;
        }
        @Override
        public int compareTo(Edge o) {
            if(weight > o.weight) return 1;
            else if(weight < o.weight) return -1;
            else return 0;
        }
    }
    public int getMSTCost(){
        int result = 0;
        for(Edge e : edgesList){
            Vertex v1 = e.vertex1;
            Vertex v2 = e.vertex2;
            int v1Code = v1.tree.code;
            int v2Code = v2.tree.code;
            if(v1Code < 0 && v2Code < 0){
                Tree tree = new Tree(getCount());
                v1.tree = tree;
                v2.tree = tree;
                result+=e.weight;
            }else if(v1Code > 0 && v2Code < 0){
                v2.tree = v1.tree;
                result+=e.weight;
            }else if(v2Code > 0 && v1Code < 0){
                v1.tree = v2.tree;
                result+=e.weight;
            }else{
                if(v1Code != v2Code){
                    Tree v2Tree = v2.tree;
                    v2Tree.code = v1Code;
                    result+=e.weight;
                }else{
                    // do nothing as they are in the same tree
                    // and joining them will form a cycle
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        UnionFind uf = new UnionFind("edges.txt");
        System.out.println(uf.getMSTCost());
    }
}
class Tree{
    int code = -1;
    Tree(int c){
        code = c;
    }
}
class Vertex{
    int value;
    Tree tree;
    Vertex(int val){
        value = val;
        tree = new Tree(-1);
    }
}
