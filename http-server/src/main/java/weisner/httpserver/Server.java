package weisner.httpserver;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

// this class waits for client connections
public class Server {
    private ServerSocket serverSocket;
    private Map<String, Method> routeMap;

    // starts the server and waits for a client to join
    public void start(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        Socket clientConnection = this.serverSocket.accept();
        System.out.println("connected");
    }

    public void stop() throws IOException {
        this.serverSocket.close();
    }

    // method to register objects that have the Route annotation
    // this maps methods to URIs so requests can be processed
    public void registerRoutes(Object[] objects) {
        final Class<Route> routeAnnotation = weisner.httpserver.Route.class;
        for (Object obj : objects) {
            for (Method m : obj.getClass().getMethods()) {
                if (m.isAnnotationPresent(routeAnnotation)) {
                    Route annotation = m.getAnnotation(routeAnnotation);
                    this.routeMap.put(annotation.uri(), m);
                }
            }
        }
    }
}
