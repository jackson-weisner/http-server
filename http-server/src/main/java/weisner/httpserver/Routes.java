package weisner.httpserver;

public class Routes {
    @Route(uri = "/test")
    public static Response testMethod() {
        return new JsonResponse(200, "json return statement");
    }


    @Route(uri = "/another/test/route")
    public static Response anotherTestMethod() {
        return new HtmlResponse(200, "test.html");
//        return new JsonResponse(200, "another json return");
    }
}
