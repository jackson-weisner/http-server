package weisner.httpserver;

public class Request {
    private RequestMethod method;
    private final String uri;

    public Request(String s) {
        String[] components = s.split(" ");
        this.uri = components[1];
    }

    public RequestMethod getMethod() {
        return this.method;
    }

    public String getUri() {
        return this.uri;
    }
}
