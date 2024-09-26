package weisner.httpserver;


import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.start(8888);
            server.stop();
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }
}