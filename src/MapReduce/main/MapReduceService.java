package MapReduce.main;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;

/**
 * Created by kashishtayal on 1/27/17.
 */
public class MapReduceService {
    private static final Logger LOGGER = LogManager.getLogger(MapReduceService.class);
    private final ClusterManager _clusterManager;
    private final IMapReduceSocketClient _mapReduceClient;
    public MapReduceService(ITask mapTask, ITask reduceTask, URL dataPath , ClusterType inType) {
        JobContext jobContext = new JobContext(mapTask,reduceTask,dataPath);
        _clusterManager = new ClusterManager(jobContext, inType);
        if(inType == ClusterType.SOCKET){
            _mapReduceClient = new MapReduceSocketClient();
        }else{
            _mapReduceClient = new MapReduceVertxClient();
        }
    }

    public void startMapReduce() throws IOException {
        try {
            _clusterManager.startMasterNode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String hostName = _clusterManager.getMasterHostName();
        int portNumber = _clusterManager.getMasterPortNumber();
        _mapReduceClient.startClient(hostName,portNumber);
    }
}
