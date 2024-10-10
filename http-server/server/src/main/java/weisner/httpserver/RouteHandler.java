package weisner.httpserver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// this class should be defined in the client
class Routes {}

public class RouteHandler {
    // this class contains the method associated with the Route annotation
    // and includes the http method
    // this is used for checking when executing the routes
    private class RouteInfo {
        private Method routeMethod;
        private String httpMethod;
        public RouteInfo(Method routeMethod, String httpMethod) {
            this.routeMethod = routeMethod;
            this.httpMethod = httpMethod;
        }
        public Method getRouteMethod() { return this.routeMethod; }
        public String getHttpMethod() { return this.httpMethod; }
    }
    private final Map<String, RouteInfo> routeMap;
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
                    var annotation = m.getAnnotation(routeAnnotation);
                    this.routeMap.put(annotation.uri(), new RouteInfo(m, annotation.method()));
                    DebugOutput.info("registered route " + annotation.uri());
                }
            }
        }
    }

    // checks if the route map has a method associated with the URI passed in
    // if it does then invoke the method
    public String executeRoute(Request request) {
        String uri = request.getUri();
        if (this.routeMap.containsKey(uri)) {
            try {
                DebugOutput.info(uri);
                RouteInfo routeInfo = this.routeMap.get(uri);
                if (!request.getMethod().equals(routeInfo.getHttpMethod())) {
                    throw new Exception();
                }
                return this.routeMap.get(uri).getRouteMethod().invoke(null, request).toString();
            } catch (Exception e) {
                DebugOutput.error("can't invoke route method for uri \"" + uri + "\"");
            }
        } else {
            DebugOutput.error("client attempted to access " + uri);
            Response response = new JsonResponse("");
            return response.toString();
            // TODO generate 404
        }
        // TODO send response to client
        return "ERROR";
    }
}
