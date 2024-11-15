package weisner.httpserver;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ImageRoutes {
    @Route(uri = "/png", method = "GET")
    public static Response getPng(Request request) {
        Response fileResponse = new ImageResponse(RouteCommon.pathPrefix + "test.png");
        return new JsonResponse(fileResponse.getResponseHeaders().get("Content-Type"));
    }

    @Route(uri = "/jpeg", method = "GET")
    public static Response getJpeg(Request request) {
        Response fileResponse = new ImageResponse(RouteCommon.pathPrefix + "test.jpeg");
        return new JsonResponse(fileResponse.getResponseHeaders().get("Content-Type"));
    }

    @Route(uri = "/gif", method = "GET")
    public static Response getGif(Request request) {
        Response fileResponse = new ImageResponse(RouteCommon.pathPrefix + "test.gif");
        return new JsonResponse(fileResponse.getResponseHeaders().get("Content-Type"));
    }
}

public class ImageResponseTests {
    private static ServerThread serverThread;
    // Starts the server on a new thread
    @BeforeAll
    public static void setup() {
        ImageResponseTests.serverThread = new ServerThread(new Server(new Class<?>[]{ImageRoutes.class}));
        ImageResponseTests.serverThread.start();
    }

    // Closes server after tests complete
    @AfterAll
    public static void tearDown() {
        ImageResponseTests.serverThread.stopServer();
    }

    @Test
    public void testPng() {
        assertEquals("image/png", RequestRunner.send("http://localhost:8888/png", "GET"));
    }

    @Test
    public void testJpeg() {
        assertEquals("image/jpeg", RequestRunner.send("http://localhost:8888/jpeg", "GET"));
    }

    @Test
    public void testGif() {
        assertEquals("image/gif", RequestRunner.send("http://localhost:8888/gif", "GET"));
    }
}
