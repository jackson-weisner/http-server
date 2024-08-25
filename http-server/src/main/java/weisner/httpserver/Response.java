package weisner.httpserver;

import java.util.HashMap;
import java.util.Map;

public abstract class Response {
    private final static String escapeCode = "\r\n";
    private final static Map<Integer, String> responseCodeMap = Map.of(
            200, "OK"
    );
    protected Map<String, String> responseHeaders;
    protected final int code;

    {
        this.responseHeaders = new HashMap<>();
    }

    // this method is for subclasses to determine how to get the content they are providing
    protected abstract String getResponseData();
    protected Response(int code) {
        this.code = code;
    }

    // adds "\r\n" to a response line
    private String formatResponseLine(String line) {
        return line + Response.escapeCode;
    }

    // adds a key value pair header to the response
    protected void addHeader(String k, String v) {
        this.responseHeaders.put(k, v);
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder(this.formatResponseLine(Server.httpVersion + " " + this.code + " " + Response.responseCodeMap.get(this.code)));
        for (var e : this.responseHeaders.entrySet()) {
            response.append(this.formatResponseLine(e.getKey() + ": " + e.getValue()));
        }
        response.append(this.formatResponseLine(""));
        response.append(this.formatResponseLine(this.getResponseData()));
        return response.toString();
    }
}
