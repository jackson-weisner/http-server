package weisner.httpserver;

public class TestRoute {
    @Route(uri="/test")
    public static Response testMethod() {
        System.out.println("running testMethod");
        return new JsonResponse(200, "json return statement");
    }
}
