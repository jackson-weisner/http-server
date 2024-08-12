package weisner.httpserver;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Server s = new Server();
//            s.registerRoutes(new Class<?>[]{TestRoute.class});
            s.registerRoutes(new Object[]{new TestRoute()});
            s.start(8888);
            s.stop();
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }
}