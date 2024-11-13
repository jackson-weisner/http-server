package weisner.httpserver;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

class Routes {
    @Route(uri = "/test", method = "GET")
    public static Response testMethod(Request request) { return new JsonResponse("test message"); }
}
public class CssResponseTest {
    private static ServerThread serverThread;
    @BeforeAll
    public static void setup() {
        CssResponseTest.serverThread = new ServerThread();
        CssResponseTest.serverThread.start();
    }
    @AfterAll
    public static void tearDown() {
        CssResponseTest.serverThread.stopServer();
    }

    @Test
    public void test() {
        assertTrue(RequestRunner.send("http://localhost:8888/test", "GET").equals("test message\r"));
    }
}
