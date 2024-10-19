package weisner.httpserver;

import java.awt.datatransfer.StringSelection;
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

            // try block for receiving requests from clients and sending responses
            try (OutputStream out = clientSocket.getOutputStream();
                 InputStream in = clientSocket.getInputStream();
                 BufferedReader br = new BufferedReader(new InputStreamReader(in));
                 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out))) {
                StringBuilder stringBuilder = new StringBuilder();
                char[] buffer = new char[1024];
                while (br.ready()) {
                    if (br.read(buffer) == -1) break;
                    stringBuilder.append(buffer).append("\n");
                }
                Request request = new Request(stringBuilder.toString());
                bw.write(this.rh.executeRoute(request));
            }
            clientSocket.close();
        }
    }

    public void stop() throws IOException {
        this.serverSocket.close();
    }
}
