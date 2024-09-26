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

            try (OutputStream out = clientSocket.getOutputStream();
                 InputStream in = clientSocket.getInputStream();
                 BufferedReader br = new BufferedReader(new InputStreamReader(in));
                 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out))) {

                String firstLine = br.readLine();
                Request request = new Request(firstLine);
                bw.write(this.rh.executeRoute(request.getUri()));
            }
            clientSocket.close();
        }
    }

    public void stop() throws IOException {
        this.serverSocket.close();
    }
}
