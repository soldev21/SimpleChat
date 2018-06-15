package main;

import static main.util.ChatLogger.info;

/**
 * Created by Sherif on 4/13/2018.
 */
public class Main {
    public static void main(String[] args) {
        ChatServer server = new ChatServer(9191);
        server.run();
        try {
            Thread.sleep(60000 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        info("Stopping EchoServer");
        server.stop();
    }
}
