package weisner.httpserver;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CssRoutes {
    @Route(uri = "/test.css", method = "GET")
    public static Response getFile(Request request) {
        return new CssResponse(RouteCommon.pathPrefix + "test.css");
    }
}

public class CssResponseTests {
    private static ServerThread serverThread;
    // Starts the server on a new thread
    @BeforeAll
    public static void setup() {
        CssResponseTests.serverThread = new ServerThread(new Server(new Class<?>[]{CssRoutes.class}));
        CssResponseTests.serverThread.start();
    }

    // Closes server after tests complete
    @AfterAll
    public static void tearDown() {
        CssResponseTests.serverThread.stopServer();
    }

    @Test
    public void correctResponse() {
        assertEquals("body {    background-color: gray;}", RequestRunner.send("http://localhost:8888/test.css", "GET"));
    }
}
