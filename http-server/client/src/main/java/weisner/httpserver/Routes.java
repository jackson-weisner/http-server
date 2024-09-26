package weisner.httpserver;

public class Routes {
    @Route(uri = "/test")
    public static Response testMethod() {return new JsonResponse(200, "response");}
}
