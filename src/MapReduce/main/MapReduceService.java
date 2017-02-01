package MapReduce.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by kashishtayal on 1/27/17.
 */
public class MapReduceService {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final ClusterManager _clusterManager;
    public MapReduceService(ITask mapTask, ITask reduceTask, URL dataPath) {
        JobContext jobContext = new JobContext(mapTask,reduceTask,dataPath);
        _clusterManager = new ClusterManager(jobContext);
    }
    public void startMapReduce() throws IOException {
        String masterInfo = _clusterManager.startMasterNode();
        String[] values = masterInfo.split(":");
        String hostName = values[0];
        int portNumber = Integer.parseInt(values[1]);
        logger.log(Level.INFO, "Starting Map Reduce Service ...");
        try (
                Socket echoSocket = new Socket(hostName, portNumber);
                PrintWriter out =
                        new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(echoSocket.getInputStream()));
        ) {
            logger.log(Level.INFO, "Service started ....");
            in.readLine();
            out.println("START");
            String message = in.readLine();
            logger.log(Level.INFO, message);
        } catch (UnknownHostException e) {
            logger.log(Level.SEVERE,"Don't know about host " + hostName);
        } catch (IOException e) {
            logger.log(Level.SEVERE,"Couldn't get I/O for the connection to " +
                    hostName);
        }
    }
}
