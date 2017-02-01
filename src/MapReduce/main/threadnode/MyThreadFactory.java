package MapReduce.main.threadnode;

import java.util.concurrent.ThreadFactory;

/**
 * Created by kashishtayal on 1/27/17.
 */
public class MyThreadFactory implements ThreadFactory {
    private final String _threadName;
    public MyThreadFactory(String inThreadName){
        _threadName = inThreadName;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r,_threadName);
        return thread;
    }
}
