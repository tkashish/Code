package LeetCode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kashishtayal on 2/15/17.
 */
public class CloneGraph {
    Map<Integer, UndirectedGraphNode> labelToNodeMap;
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) return null;
        labelToNodeMap = new HashMap<>();
        return getNodeClone(node);
    }

    private UndirectedGraphNode getNodeClone(UndirectedGraphNode inNode){
        int label = inNode.label;
        if(labelToNodeMap.containsKey(label)){
            return labelToNodeMap.get(label);
        }
        UndirectedGraphNode clone = new UndirectedGraphNode(label);
        labelToNodeMap.put(label,clone);
        List<UndirectedGraphNode> neighborsOfClonedNode = clone.neighbors;

        List<UndirectedGraphNode> neighbors = inNode.neighbors;
        for(UndirectedGraphNode neighbor : neighbors){
            UndirectedGraphNode neighborClone = getNodeClone(neighbor);
            neighborsOfClonedNode.add(neighborClone);
        }
        return clone;
    }
    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }

    public static void main(String[] args) {

    }
}
