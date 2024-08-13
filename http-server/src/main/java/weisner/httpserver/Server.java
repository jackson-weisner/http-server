package weisner.httpserver;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

// this class waits for client connections
public class Server {
    private ServerSocket serverSocket;
    private final Map<String, Method> routeMap;

    {
        this.routeMap = new HashMap<>();
    }

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
    // this maps methods to URIs so requests can be processed and executed
    public void registerRoutes(Class<?>[] classes) {
        final Class<Route> routeAnnotation = weisner.httpserver.Route.class;
        for (Class<?> c : classes) {
            for (Method m : c.getMethods()) {
                if (m.isAnnotationPresent(routeAnnotation)) {
                    String uriString = m.getAnnotation(routeAnnotation).uri();
                    this.routeMap.put(uriString, m);
                    DebugOutput.info("registered route " + uriString);
                }
            }
        }
    }

    // checks if the route map has a method associated with the URI passed in
    // if it does then invoke the method
    private void executeRoute(String uri) {
        if (this.routeMap.containsKey(uri)) {
            try {
                this.routeMap.get(uri).invoke(null);
            } catch (Exception e) {
                DebugOutput.error("can't invoke route method for uri \"" + uri + "\"");
            }
        } else {
            // TODO generate 404
        }
        // TODO send response to client
    }
}
