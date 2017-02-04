package MapReduce.connection.socket;

import MapReduce.main.JobContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kashishtayal on 2/4/17.
 */
public class MasterRunnable implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(MasterRunnable.class);
    private final String _hostName;
    private final int _portNumber;
    private final JobContext _jobContext;
    public MasterRunnable(String inHostName, int inPortNumber, JobContext inJobContext){
        _hostName = inHostName;
        _portNumber = inPortNumber;
        _jobContext = inJobContext;
    }

    @Override
    public void run() {
        main(new String[]{_hostName,Integer.toString(_portNumber)});
    }

    public static void main(String[] args) {
        if(args.length < 2){
            throw new IllegalArgumentException("Server hostname and port number not available");
        }
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        doRun(hostName,portNumber);
    }

    private static void startMapReduceJob() throws InterruptedException {
        LOGGER.debug("Map Reduce Job Started....");
        Thread.sleep(3000);
        LOGGER.debug("Map Reduce Job Completed...");
    }

    private static void doRun(String inHostName, int inPortNumber){
        try (
                ServerSocket serverSocket = new ServerSocket(inPortNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ){
            String inMessage = in.readLine();
            if(inMessage.equals("START")){
                try{
                    startMapReduceJob();
                    out.println("COMPLETED");
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage());
                    out.println("ERROR : e.getMessage");
                }
            }
        } catch (IOException e) {
            LOGGER.error("Exception caught when trying to listen on port "
                    + inPortNumber + " or listening for a connection");
            LOGGER.error(e.getMessage());
        }
    }
}
