package weisner.httpserver;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Server s = new Server(new Class<?>[]{Routes.class});
            s.start(8888);
            s.stop();
        } catch (IOException e) {
            DebugOutput.error("can't start the server");
        }
    }
}