package weisner.httpserver;

public class Routes {
    @Route(uri = "/test")
    public static Response testMethod() {return new JsonResponse(200, "response");}

    @Route(uri = "/test.html")
    public static Response getTestHtml() {return new HtmlResponse(200, "test.html");}
}
