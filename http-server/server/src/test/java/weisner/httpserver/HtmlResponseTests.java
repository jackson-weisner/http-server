package weisner.httpserver;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HtmlRoutes {
    @Route(uri = "/test.html", method = "GET")
    public static Response getFile(Request request) {
        return new HtmlResponse(RouteCommon.pathPrefix + "test.html");
    }

    @Route(uri = "/content-type", method = "GET")
    public static Response getContentType(Request request) {
        Response fileResponse = new HtmlResponse(RouteCommon.pathPrefix + "test.html");
        return new JsonResponse(fileResponse.getResponseHeaders().get("Content-Type"));
    }
}

public class HtmlResponseTests {
    private static ServerThread serverThread;
    // Starts the server on a new thread
    @BeforeAll
    public static void setup() {
        HtmlResponseTests.serverThread = new ServerThread(new Server(new Class<?>[]{HtmlRoutes.class}));
        HtmlResponseTests.serverThread.start();
    }

    // Closes server after tests complete
    @AfterAll
    public static void tearDown() {
        HtmlResponseTests.serverThread.stopServer();
    }

    @Test
    public void correctResponse() {
        assertEquals("<!DOCTYPE html><html lang=\"en\"><head>    <meta charset=\"UTF-8\">    <title>Title</title></head><body></body></html>", RequestRunner.send("http://localhost:8888/test.html", "GET"));
    }

    @Test
    public void containsHeader() {
        assertEquals("text/html", RequestRunner.send("http://localhost:8888/content-type", "GET"));
    }
}
