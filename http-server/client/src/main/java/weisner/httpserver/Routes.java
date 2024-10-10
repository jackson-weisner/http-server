package weisner.httpserver;

public class Routes {
    @Route(uri = "/test", method = "GET")
    public static Response testMethod(Request request) {return new JsonResponse("response");}

    @Route(uri = "/test.html", method = "GET")
    public static Response getTestHtml(Request request) {return new HtmlResponse("test.html");}

    @Route(uri = "/test.css", method = "GET")
    public static Response getCss(Request request) {return new CssResponse("test.css");}

    @Route(uri = "/favicon.ico", method = "GET")
    public static Response favicon(Request request) {return new JsonResponse("response");}
}
