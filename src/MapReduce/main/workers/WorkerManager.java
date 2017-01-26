package MapReduce.main.workers;

import MapReduce.main.ITask;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by kashishtayal on 1/25/17.
 */
public class WorkerManager {
    private final Logger logger = Logger.getLogger("WorkerManager");
    Map<Integer, Thread> portServerMap;
    Map<Integer, String> portHostNameMap;
    public WorkerManager(){
        portServerMap = new HashMap<>();
        portHostNameMap = new HashMap<>();
    }

    public Map<Integer,String> startWorkerServers(ITask inTask) throws IOException {
        logger.log(Level.INFO, "Stating up worker nodes...");
        Properties prop = new Properties();
        try ( InputStream in = getClass().getResourceAsStream("worker.properties"); ) {
            prop.load(in);
            for(Object keys : prop.keySet()){
                String[] values = ((String)prop.get(keys)).split(":");
                String hostName = values[0];
                int portNumber = Integer.parseInt(values[1]);
                startWorkerAt(portNumber,hostName,inTask);
            }
        }
        return portHostNameMap;
    }

    private void startWorkerAt(int portNumber, String hostName, ITask inTask){
        Runnable runnable = new TaskRunnable(portNumber, inTask);
        Thread thread = new Thread(runnable,(hostName+" : "+ portNumber));
        thread.start();
        portServerMap.put(portNumber,thread);
        portHostNameMap.put(portNumber,hostName);
    }

}
