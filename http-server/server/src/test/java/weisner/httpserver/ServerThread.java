package weisner.httpserver;

import java.io.IOException;

public class ServerThread extends Thread {

    private final Server server;
    public ServerThread(Server server) { this.server = server; }
    public void stopServer() {
        this.server.stop();
    }

    @Override
    public void run() {
        try {
            server.start(8888);
        } catch (IOException e) {
            System.out.println("Server closed");
        }
    }
}
