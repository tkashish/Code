package MapReduce.main;

import java.util.List;

/**
 * Created by kashishtayal on 1/25/17.
 */
public class MapReduceClient {
    private final ITask _mapTask;
    private final ITask _reduceTask;
    private List<Thread> _workers;
    public MapReduceClient(ITask inMapTask, ITask inReduceTask){
        _mapTask = inMapTask;
        _reduceTask = inReduceTask;
        Thread.currentThread().setName("MASTER");
    }
}
