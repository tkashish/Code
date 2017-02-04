package MapReduce.connection;

/**
 * Created by kashishtayal on 1/27/17.
 */
public class NodeFactory {
    private NodeFactory(){
    }
    public static Node getThreadNode(String inNodeName){
        Node node = new Node(inNodeName);
        return node;
    }
}
