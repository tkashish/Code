package MapReduce.main;

import MapReduce.connection.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by kashishtayal on 1/27/17.
 */
public class ClusterManager {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private JobContext _jobContext;
    private String _masterHostName;
    private int _masterPortNumber;
    private final ClusterType _clusterType;
    public ClusterManager(JobContext inJobContext, ClusterType inType){

        _jobContext = inJobContext;
        _clusterType = inType;
        try {
            readMasterServerInfo();
            readWorkerServerInfo();
        } catch (IOException e) {
            LOGGER.error("unable to read serverinfo" + e);
        }
    }

    public void startMasterNode() throws Exception {
        LOGGER.debug("startMasterNode");
        ConnectionFactory.createMasterServer(_jobContext,_clusterType,_masterHostName,_masterPortNumber);
    }

    public String getMasterHostName(){
        return _masterHostName;
    }

    public int getMasterPortNumber(){
        return _masterPortNumber;
    }

    private void readMasterServerInfo() throws IOException {
        LOGGER.debug("reading master properties....");
        Properties _masterServerProperties = new Properties();
        try (InputStream in = getClass().getResourceAsStream("master.properties"); ) {
            _masterServerProperties.load(in);
            String masterServerInfoString = null;
            for(Object keys : _masterServerProperties.keySet()){
                masterServerInfoString = (String)_masterServerProperties.get(keys);
                String[] values = masterServerInfoString.split(":");
                _masterHostName = values[0];
                _masterPortNumber = Integer.parseInt(values[1]);
            }
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
