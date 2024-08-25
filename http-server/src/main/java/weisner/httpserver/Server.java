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
        this.rh.registerRoutes(classes);
    }

    // starts the server and waits for a client to join
    public void start(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        Socket clientSocket = this.serverSocket.accept();

        try (OutputStream out = clientSocket.getOutputStream();
             InputStream in = clientSocket.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out))) {

            System.out.println(br.readLine());
            this.rh.executeRoute("/test");

//            bw.write("HTTP/1.1 200 OK\r\nAccess-Control-Allow-Origin: *\r\nContent-Type: text/plain; charset=UTF-8\r\nConnection: Keep-Alive\r\nServer: MyServer\r\n\r\nHello World!\r\n");
            Response response = new JsonResponse(200, "json response");
            bw.write(response.toString());
        }
        clientSocket.close();
    }

    public void stop() throws IOException {
        this.serverSocket.close();
    }
}
