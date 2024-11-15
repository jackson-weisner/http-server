package weisner.httpserver;

public class Routes {
    @Route(uri = "/test", method = "POST")
    public static Response testMethod(Request request) {return new JsonResponse("response");}

    @Route(uri = "/test.html", method = "GET")
    public static Response getTestHtml(Request request) {return new HtmlResponse("client/content/test.html");}

    @Route(uri = "/test.css", method = "GET")
    public static Response getCss(Request request) {return new CssResponse("client/content/test.css");}

    @Route(uri = "/favicon.ico", method = "GET")
    public static Response favicon(Request request) {return new JsonResponse("response");}

    @Route(uri = "/test.png", method = "GET")
    public static Response png(Request request) {return new ImageResponse("client/content/test.png");}
}
