package weisner.httpserver;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RouteHandler {
    private final Map<String, Method> routeMap;

    {
        this.routeMap = new HashMap<>();
    }

    // method to register objects that have the Route annotation
    // this maps methods to URIs so requests can be processed and executed
    public void registerRoutes(Class<?>[] classes) {
        final Class<Route> routeAnnotation = Route.class;
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
   public String executeRoute(String uri) {
        if (this.routeMap.containsKey(uri)) {
            try {
                DebugOutput.info(uri);
                return this.routeMap.get(uri).invoke(null).toString();
            } catch (Exception e) {
                DebugOutput.error("can't invoke route method for uri \"" + uri + "\"");
            }
        } else {
            DebugOutput.error("client attempted to access " + uri);
            Response response = new JsonResponse(400, "");
            return response.toString();
            // TODO generate 404
        }
        // TODO send response to client
        return "";
    }
}
