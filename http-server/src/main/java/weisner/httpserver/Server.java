package weisner.httpserver;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

// this class waits for client connections
public class Server {
    private ServerSocket serverSocket;
    private final RouteHandler rh;

    {
        this.rh = new RouteHandler();
    }

    public Server(Class<?>[] classes) {
        this.rh.registerRoutes(classes);
    }

    // starts the server and waits for a client to join
    public void start(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        Socket clientConnection = this.serverSocket.accept();
    }

    public void stop() throws IOException {
        this.serverSocket.close();
    }
}
