package MapReduce.main;

import MapReduce.main.workers.WorkerManager;

import java.io.IOException;

/**
 * Created by kashishtayal on 1/25/17.
 */
public class Main {
    public static void main(String[] args) throws IOException {
//        IMapTask mapTask = null;
//        IReduceTask reduceTask = null;
//        MapReduceClient runner = new MapReduceClient(mapTask, reduceTask);
            WorkerManager manager = new WorkerManager();
        manager.startWorkerServers();
    }
}
