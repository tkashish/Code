package MapReduce.main;

import java.io.IOException;
import java.net.URL;

/**
 * Created by kashishtayal on 1/27/17.
 */
public class Runner {
    public static void main(String[] args) throws IOException {
        ITask mapTask = null;
        ITask reduceTask = null;
        URL dataPath = null;
        ClusterType type = ClusterType.SOCKET;
        MapReduceService service = new MapReduceService(mapTask, reduceTask, dataPath, type);
        service.startMapReduce();
    }
}