package MapReduce.main;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by kashishtayal on 2/4/17.
 */
public class MapReduceSocketClient implements IMapReduceSocketClient {
    private static final Logger LOGGER = LogManager.getLogger(MapReduceSocketClient.class);
    @Override
    public void startClient(String inHostName, int inPortNumber) {
        try (
                Socket echoSocket = new Socket(inHostName, inPortNumber);
                PrintWriter out =
                        new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(echoSocket.getInputStream()));
        ) {
            LOGGER.debug("Connected to Master Server");
            /**
             * Talk to MasterServer
             */
            out.println("START");
            String inMessage = in.readLine();
            if(inMessage.contains("COMPLETE")){
                LOGGER.debug("MAP REDUCE COMPLETED");
            }else if(inMessage.contains("ERROR")){
                LOGGER.error("MAP REDUCE FAILED : "+ inMessage);
            }
        } catch (UnknownHostException e) {
            LOGGER.error("Don't know about host " + inHostName);
        } catch (IOException e) {
            LOGGER.error("Couldn't get I/O for the connection to " +
                    inHostName);
        }
    }
}
