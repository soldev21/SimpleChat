package main;

import main.model.client.Client;
import main.registry.ClientHandlerRegistry;
import main.registry.HashMapClientHandlerRegistry;
import main.registry.RegistryHolder;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static main.util.ChatLogger.info;

public class ChatServer implements Runnable {

    private static final int DEFAULT_PORT = 8080;

    protected int serverPort = DEFAULT_PORT;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;
    protected ExecutorService threadPool ;
    private ClientHandlerRegistry registry;
    private int id = 0;

    public ChatServer(){
        init();
    }

    public ChatServer(int port) {
        this.serverPort = port;
        init();
    }

    private void init(){
        registry = new HashMapClientHandlerRegistry<Integer,ClientHandler>();
        threadPool = Executors.newFixedThreadPool(100);
        RegistryHolder.setRegistry(registry);
    }

    public void run() {
        // start socket server
        openServerSocket();

        while (!isStopped()) {
            ClientHandler clientHandler;
            Socket clientSocket;
            // waiting for new connections
            clientSocket = acceptSocket();
            // creates new cient handler
            clientHandler = getClientHandler(clientSocket);
            // adds client handler to clientHandlerRegistry
            registry.add(id,clientHandler);
            // increment user id
            id++;
            // executes client handler in new thread
            threadPool.execute(clientHandler);
        }
        info("EchoServer Stopped.");
    }

    private Socket acceptSocket(){
        try {
            return this.serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeException(
                    "Error accepting client connection", e);
        }
    }

    private ClientHandler getClientHandler(Socket clientSocket){
        ClientHandler clientHandler = new ClientHandler(clientSocket,
                new Client(id));
        return clientHandler;
    }


    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() {
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
            info("Server started.");
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port 8080", e);
        }
    }
}
