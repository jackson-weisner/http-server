package weisner.httpserver;

public class TestRoute {
    @Route(uri="/test")
    public static void testMethod() {
        System.out.println("running testMethod");
    }
}
