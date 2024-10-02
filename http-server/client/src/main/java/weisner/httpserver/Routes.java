package weisner.httpserver;

public class Routes {
    @Route(uri = "/test")
    public static Response testMethod() {return new JsonResponse("response");}

    @Route(uri = "/test.html")
    public static Response getTestHtml() {return new HtmlResponse("test.html");}

    @Route(uri = "/test.css")
    public static Response getCss() {return new CssResponse("test.css");}
}
