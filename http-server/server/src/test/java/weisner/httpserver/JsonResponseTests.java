package weisner.httpserver;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JsonRoutes {
    @Route(uri = "/test", method = "GET")
    public static Response testMethod(Request request) { return new JsonResponse("test message"); }

    @Route(uri = "/post", method = "POST")
    public static Response postTest(Request request) {
        Map<String, String> parameters = request.getUriParameters();
        String result = "first" + parameters.get("first") + "second" + parameters.get("second");
        return new JsonResponse(result);
    }
}

public class JsonResponseTests {
    private static ServerThread serverThread;
    // Starts the server on a new thread
    @BeforeAll
    public static void setup() {
        JsonResponseTests.serverThread = new ServerThread(new Server(new Class<?>[]{JsonRoutes.class}));
        JsonResponseTests.serverThread.start();
    }

    // Closes server after tests complete
    @AfterAll
    public static void tearDown() {
        JsonResponseTests.serverThread.stopServer();
    }

    @Test
    public void correctOutput() {
        assertEquals("test message", RequestRunner.send("http://localhost:8888/test", "GET"));
    }

    @Test
    public void incorrectMethod() {
        assertNotEquals("test message\r", RequestRunner.send("http://localhost:8888/test", "POST"));
    }

    @Test
    public void postTest() {
        assertEquals("firstfirstValuesecondsecondValue", RequestRunner.send("http://localhost:8888/post", "POST", "first=firstValue&second=secondValue"));
    }
}
