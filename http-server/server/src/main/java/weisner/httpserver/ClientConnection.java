package weisner.httpserver;

import java.io.*;
import java.net.Socket;

// class for handling client connections from the server
// enables multithreading
class ClientConnection implements Runnable {
    private final Socket clientSocket;
    private final RouteHandler rhInstance;
    public ClientConnection(Socket clientSocket, RouteHandler rhInstance) {
        this.rhInstance = rhInstance;
        this.clientSocket = clientSocket;
    }

    // what the thread does when started
    @Override
    public void run() {
        // create all the streams ect
        try (OutputStream out = clientSocket.getOutputStream();
             InputStream in = clientSocket.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            StringBuilder stringBuilder = new StringBuilder();

            // read all content from the socket
            char[] buffer = new char[1024];
            while (br.ready()) {
                if (br.read(buffer) == -1) break;
                stringBuilder.append(buffer).append("\n");
            }
            Request request = new Request(stringBuilder.toString());
            out.write(this.rhInstance.executeRoute(request));
        } catch (Exception e) {
            DebugOutput.error("An error has occurred");
        } finally {
            try {
                this.clientSocket.close();
            } catch (IOException e) {
                DebugOutput.error("Can't close client socket");
            }
        }
    }
}
