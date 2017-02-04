package MapReduce.connection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by kashishtayal on 2/4/17.
 */
public class Node implements AutoCloseable{

    private ExecutorService _executor;

    public Node(String inNodeName) {
        ThreadFactory threadFactory = new MyThreadFactory(inNodeName);
        _executor = Executors.newSingleThreadExecutor(threadFactory);
    }

    public void startThreadNode(Runnable inRunner){
        _executor.submit(inRunner);
    }

    @Override
    public void close() throws Exception {
        _executor.shutdown();
    }
}
