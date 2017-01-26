package MapReduce.main.workers;

import MapReduce.main.ITask;
import MapReduce.main.Signal;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by kashishtayal on 1/25/17.
 */
class TaskRunnable implements Runnable{
    private final Logger logger = Logger.getLogger("TaskRunnable");
    int _port;
    ITask _task;
    TaskRunnable(int inPort, ITask inTask){
        _port = inPort;
        _task = inTask;
    }
    @Override
    public void run() {
        try (
                ServerSocket serverSocket = new ServerSocket(_port);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ){
            String inFilePath;
            while((inFilePath = in.readLine()) != null) {
                InputStream is = findAndReadFile(inFilePath);
                if(is == null){
                    out.write("File Not Found");
                }
                executeHelper(is);
                out.println(Signal.FILE_PROCESSED);
            }

            out.println(Signal.JOB_COMPLETE);

        } catch (IOException e) {
            logger.log(Level.SEVERE,"Exception caught when trying to listen on port "
                    + _port + " or listening for a connection");
            logger.log(Level.SEVERE, e.getMessage());
        }

    }

    private InputStream findAndReadFile(String inFilePath){
        return getClass().getResourceAsStream(inFilePath);
    }

    private void executeHelper(InputStream inputStream){
        _task.execute(inputStream);
    }
}