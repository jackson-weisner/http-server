package weisner.httpserver;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// this class should be defined in the client
class Routes {}

public class RouteHandler {
    private final Map<String, Method> routeMap;
    private List<Class<?>> classList;

    {
        this.routeMap = new HashMap<>();
        this.classList = new ArrayList<>();
        this.classList.add(Routes.class);
    }

    public void addRouteClass(Class<?> c) {
        this.classList.add(c);
    }

    // method to register objects that have the Route annotation
    // this maps methods to URIs so requests can be processed and executed
    void registerRoutes() {
        final Class<Route> routeAnnotation = Route.class;
        for (Class<?> c : this.classList) {
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
