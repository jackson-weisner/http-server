package weisner.httpserver;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Routes {
    @Route(uri = "/test", method = "GET")
    public static Response testMethod(Request request) { return new JsonResponse("test message"); }

    @Route(uri = "/post", method = "POST")
    public static Response postTest(Request request) { return new JsonResponse("test message"); }
}

public class JsonResponseTest {
    private static ServerThread serverThread;
    // Starts the server on a new thread
    @BeforeAll
    public static void setup() {
        JsonResponseTest.serverThread = new ServerThread();
        JsonResponseTest.serverThread.start();
    }

    // Closes server after tests complete
    @AfterAll
    public static void tearDown() {
        JsonResponseTest.serverThread.stopServer();
    }

    @Test
    public void correctOutput() {
        assertEquals(RequestRunner.send("http://localhost:8888/test", "GET"), "test message\r");
    }

    @Test
    public void incorrectMethod() {
        assertNotEquals(RequestRunner.send("http://localhost:8888/test", "POST"), "test message\r");
    }

    @Test
    public void postTest() {
        System.out.println(RequestRunner.send("http://localhost:8888/post", "POST", "t=asdf"));
    }
}
