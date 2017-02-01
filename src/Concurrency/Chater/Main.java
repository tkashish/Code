package Concurrency.Chater;

import java.io.IOException;

/**
 * Created by kashishtayal on 1/27/17.
 */
public class Main {
    public static void main(String[] args) {
        Thread server = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DateServer.main(null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        server.start();
        Thread client = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DateClient.main(null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        client.start();

    }
}
