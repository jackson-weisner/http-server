package weisner.httpserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// this class waits for client connections
public class Server {
    public final static String httpVersion = "HTTP/1.1";
    private ServerSocket serverSocket;
    private final RouteHandler rh;

    {
        this.rh = new RouteHandler();
    }

    // optional constructor to pass in a different class than Routes
    public Server(Class<?>[] classes) {
        for (Class<?> c : classes) {
            this.rh.addRouteClass(c);
        }
        this.rh.registerRoutes();
    }

    public Server() {
        this.rh.registerRoutes();
    }

    // starts the server and waits for a client to join
    public void start(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        while (true) {
            Socket clientSocket = this.serverSocket.accept();
            ClientConnection clientConnection = new ClientConnection(clientSocket, this.rh);
            Thread thread = new Thread(clientConnection);
            thread.start();
        }
    }

    public void stop() {
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            DebugOutput.info("Server closed");
        }
    }
}
