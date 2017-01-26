package MapReduce.main.master;

import MapReduce.main.ITask;
import MapReduce.main.Signal;
import MapReduce.main.workers.WorkerManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by kashishtayal on 1/25/17.
 */
public class MasterServerManager{
    private final Logger logger = Logger.getLogger("MasterServerManager");
    Thread _masterThread;
    private WorkerManager _workerManager;
    private ITask _mapTask;
    private ITask _reduceTask;
    public MasterServerManager(ITask inMapTask, ITask inReduceTask){
        _mapTask = inMapTask;
        _reduceTask = inReduceTask;
        _masterThread = new Thread("MASTER");
        _workerManager = new WorkerManager();
    }

    public void startUpMaster() throws IOException {
        logger.log(Level.INFO, "Stating up master node...");
        Properties prop = new Properties();
        try(
                InputStream in = getClass().getResourceAsStream("master.properties");
        ) {
            prop.load(in);
            String[] values = prop.getProperty("master").split(":");
            int port = Integer.parseInt(values[1]);
            MasterRunnable master = new MasterRunnable(port);
        }
    }

    private class MasterRunnable implements Runnable{
        private final int _port;
        MasterRunnable(int inPort){
            _port = inPort;
        }
        @Override
        public void run() {
            try (
                    ServerSocket serverSocket = new ServerSocket(_port);
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ){
                String inSignal = null;
                while((inSignal = in.readLine())!=null) {
                    if(inSignal.equals(Signal.START_MAP_REDUCE)){

                    }
                }

            } catch (IOException e) {
                logger.log(Level.SEVERE,"Exception caught when trying to listen on port "
                        + _port + " or listening for a connection");
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
        private void startMap(PrintWriter out){
            Map<Integer,String> workerInfo = null;
            Map<Integer, Socket> workerSocketMap = new HashMap<>();
            try {
                workerInfo =  _workerManager.startWorkerServers(_mapTask);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Cannot start worker servers for map task "+ e.getMessage());
                out.println(Signal.MAP_WORKER_SETUP_FAILED);
            }
            for(Map.Entry<Integer,String> entry : workerInfo.entrySet()){
                Socket socket = null;
                try {
                    socket = new Socket(entry.getValue(),entry.getKey());
                } catch (UnknownHostException e) {
                    logger.log(Level.SEVERE,"Don't know about host " + entry.getValue()+":"+entry.getKey());
                } catch (IOException e) {
                    logger.log(Level.SEVERE,"Couldn't get I/O for the connection to " + entry.getValue()+":"+entry.getKey());
                }
                workerSocketMap.put(entry.getKey(), socket);
            }
            distributeWork(workerSocketMap);
        }
        private void distributeWork(Map<Integer, Socket> workerSocketMap){

        }
    }
}
