package MapReduce.main;

import MapReduce.main.threadnode.MasterRunnable;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by kashishtayal on 1/27/17.
 */
public class ClusterManager {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private Node _masterNode;
    private static final String MASTER_NODE = "MASTER_NODE";
    private JobContext _jobContext;
    private Properties _masterServerProperties;
    public ClusterManager(JobContext inJobContext){
        _jobContext = inJobContext;
        try {
            readMasterServerInfo();
            readWorkerServerInfo();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "unable to read serverinfo" + e);
        }
    }

    public String startMasterNode(){
        logger.log(Level.INFO, "startMasterNode");
        String hostName = null;
        int portNumber = Integer.MIN_VALUE;
        String masterServerInfoString = null;
        for(Object keys : _masterServerProperties.keySet()){
            masterServerInfoString = (String)_masterServerProperties.get(keys);
            String[] values = masterServerInfoString.split(":");
            portNumber = Integer.parseInt(values[1]);
        }
        Node node = Node.getThreadNode(MASTER_NODE);
        Runnable runnable = new MasterRunnable(portNumber,_jobContext);
        node.startThreadNode(runnable);
        return masterServerInfoString;
    }

    private void readMasterServerInfo() throws IOException {
        _masterServerProperties = new Properties();
        try (InputStream in = getClass().getResourceAsStream("master.properties"); ) {
            _masterServerProperties.load(in);
        }
    }
    private void readWorkerServerInfo() throws IOException {
        Properties prop = new Properties();
        try (InputStream in = getClass().getResourceAsStream("master.properties"); ) {
            prop.load(in);
            for(Object keys : prop.keySet()){
                String[] values = ((String)prop.get(keys)).split(":");
                String hostName = values[0];
                int portNumber = Integer.parseInt(values[1]);
            }
        }
    }

}
