package weisner.httpserver;

public class Request {
    private RequestMethod method;
    private String uri;

    public Request(String s) {}

    public RequestMethod getMethod() {
        return this.method;
    }

    public String getUri() {
        return this.uri;
    }
}
