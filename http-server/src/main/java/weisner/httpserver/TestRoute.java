package weisner.httpserver;

public class TestRoute {
    public TestRoute() {}
    @Route(uri="/test")
    public static void testMethod() {
        System.out.println("running testMethod");
    }
}
