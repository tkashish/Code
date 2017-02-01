package MapReduce.main;

import MapReduce.main.threadnode.MyThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by kashishtayal on 1/27/17.
 */
public class Node{
    private ExecutorService _executor;
    private Node(){
    }
    public static Node getThreadNode(String inNodeName){
        Node node = new Node();
        ThreadFactory threadFactory = new MyThreadFactory(inNodeName);
        node._executor = Executors.newSingleThreadExecutor(threadFactory);
        return node;
    }
    public void startThreadNode(Runnable inRunner){
        _executor.submit(inRunner);
        _executor.shutdown();
    }
}
