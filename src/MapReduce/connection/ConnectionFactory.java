package MapReduce.connection;

import MapReduce.connection.socket.MasterRunnable;
import MapReduce.main.ClusterType;
import MapReduce.main.JobContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by kashishtayal on 2/1/17.
 */
public final class ConnectionFactory {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionFactory.class);
    private static final String MASTER_SERVER = "MASTER_SERVER";
    public static void createMasterServer(JobContext inJobContext,
                                          ClusterType inClusterType,
                                          String inHostName,
                                          int inPortName) throws Exception {
        switch (inClusterType){
            case SOCKET:
                createThreadMasterServer(inJobContext,inHostName,inPortName);
            case VERTX:
                createVertxMasterServer(inJobContext,inHostName,inPortName);
        }
    }
    private static void createThreadMasterServer(JobContext inJobContext,
                                                 String inHostName,
                                                 int inPortName){
        LOGGER.debug("Creating master server of connection type " + ClusterType.SOCKET + " at host[" +
                inHostName+"] & port["+inPortName+"]");
        Runnable socketConnectionBasedRunnable = new MasterRunnable(inHostName,inPortName, inJobContext);
        try(Node node = NodeFactory.getThreadNode(MASTER_SERVER);){
            node.startThreadNode(socketConnectionBasedRunnable);
        } catch (Exception e) {
            /**
             * if the exception is thrown from try block add a method to recover from the failure
             * or inform the ClusterManager
             */
        }

    }

    private static void createVertxMasterServer(JobContext inJobContext,
                                                String inHostName,
                                                int inPortName){

    }

}
