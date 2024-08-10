package weisner.httpserver;

public class Request {
    private Method method;
    private String uri;

    public Request(String s) {}

    public Method getMethod() {
        return this.method;
    }

    public String getUri() {
        return this.uri;
    }
}
