package main;

import main.model.client.Client;
import main.model.request.Body;
import main.model.request.RequestObject;
import main.model.response.ResponseHeader;
import main.model.response.ResponseObject;
import main.util.Utils;
import main.registry.RegistryHolder;

import java.io.*;
import java.net.Socket;

import static main.util.ChatLogger.info;


public class ClientHandler implements Runnable {

    protected Socket clientSocket = null;
    private DataOutputStream out;
    private Client client;


    public ClientHandler(Socket clientSocket,Client client) {
        this.clientSocket = clientSocket;
        this.client = client;
    }

    public void run() {
        try {
            // gets input stream from client socket
            DataInputStream input = new DataInputStream(
                    (clientSocket.getInputStream()));
            // get output stream from client socket
            out = new DataOutputStream(clientSocket.getOutputStream());
            String line ;
            // while not end handle request
            while (!(line = input.readUTF()).equals("end")) {
                handleRequest(line);
                info(line);
            }
            input.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleRequest(String s){
        RequestObject requestObject = Utils.convertValue(s,RequestObject.class);
        int receiverId = requestObject.getHeader().getReceiverId();
        ResponseObject responseObject = new ResponseObject()
                .setHeader(new ResponseHeader()
                .setSenderId(client.getId()))
                .setBody(new Body()
                .setMessage(requestObject.getBody().getMessage()));
        info(Utils.convertToJson(responseObject));
        ((ClientHandler) RegistryHolder.getRegistry().get(receiverId)).send(responseObject);
    }

    public void send(ResponseObject response){
        try {
            out.writeUTF(Utils.convertToJson(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
