package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.model.request.Body;
import main.model.request.RequestHeader;
import main.model.request.RequestObject;
import main.util.ChatLogger;

import java.io.*;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

import static main.util.ChatLogger.info;

/**
 * Created by Sherif on 4/15/2018.
 */
public class TestClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost",9191);
        System.out.println("Socket connected.");

        DataInputStream input = new DataInputStream(
                (socket.getInputStream()));

        DataOutputStream writer = new DataOutputStream(socket.getOutputStream());

        // Scanner for reading input from user
        Scanner scanner = new Scanner(System.in);

        // creates runnable for reading input stream
        Runnable runnable = ()-> {
            try {
                while (true){
                    info(input.readUTF());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        // Creates new thread for reading input messages
        new Thread(runnable).start();

        // write messages to chat server from console input
        String line;
        while (!(line= scanner.nextLine()).equals("end")){
            writer.writeUTF(line);
        }

    }
}
