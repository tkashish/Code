package MapReduce.main.threadnode;

import MapReduce.main.JobContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by kashishtayal on 1/27/17.
 */
public class MasterRunnable implements Runnable{
    private final ConsoleHandler handler = new ConsoleHandler();
    private static final Logger logger = Logger.getLogger(MasterRunnable.class.getName());
    private String _hostName;
    private static int _portNumber;
    private static JobContext _jobContext;
    public MasterRunnable(int inPortNumber, JobContext inJobContext){
        _portNumber = inPortNumber;
        _jobContext = inJobContext;
    }
    @Override
    public void run() {
        try {
            MasterRunnable.main(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static boolean startMapReduceJob(){
        logger.log(Level.INFO, "starting map reduce job .... ");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.log(Level.INFO, "map reduce job completed.... ");
        return true;
    }

    public static void main(String[] args) throws IOException {
        logger.log(Level.INFO, "Starting master node ....");
        try (
                ServerSocket serverSocket = new ServerSocket(_portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ){
            logger.log(Level.INFO,"master node started....");
            out.println("Master Started");
            String message = in.readLine();
            boolean jobStatus = false;
            if(message.equals("START")) {
                jobStatus = startMapReduceJob();
                if(jobStatus == true){
                    out.println("COMPLETE");
                }else{
                    out.println("JOB FAILED");
                }
            }else{
                logger.log(Level.SEVERE, "incorrect message from client : "+message);
                out.println("JOB FAILED");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE,"Exception caught when trying to listen on port "
                    + _portNumber + " or listening for a connection");
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
