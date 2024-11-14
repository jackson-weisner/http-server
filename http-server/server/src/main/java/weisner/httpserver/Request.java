package weisner.httpserver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Request {
    private String method;
    private String uri;
    private final List<String> requestLines;
    private final Map<String, String> uriParameters;
    private final Map<String, String> requestHeaders;
    private final static String postContentType = "application/x-www-form-urlencoded";

    public Request(String s) {
        this.requestLines = s.lines().collect(Collectors.toList());
        this.uriParameters = new HashMap<>();
        this.requestHeaders = new HashMap<>();
        this.parseFirstLine();
        this.parseRequestHeaders();

        // checks if the request is a POST request
        // then checks the Content-Type of the request
        if (this.method.equals("POST")) {
            if (this.requestHeaders.get("Content-Type").equals(Request.postContentType)) {
                this.parseUri(this.requestLines.get(this.requestLines.size()-1));
            } else {
                DebugOutput.error("Server only accepts " + Request.postContentType);
            }
        }
    }

    // parses the request headers from the request lines
    private void parseRequestHeaders() {
        String line;
        for (int i = 1; i <= this.requestLines.size()-3; i++) {
            line = this.requestLines.get(i);
            if (line.isEmpty()) break;
            this.requestHeaders.put(line.substring(0,line.indexOf(":")), line.substring(line.indexOf(":")+2));
        }
    }

    // parses the request method and uri from the request lines
    private void parseFirstLine() {
        // prevents errors with invalid uri parameters
        String[] lineComponents = this.requestLines.get(0).split(" ");
        this.method = lineComponents[0];
        int index = lineComponents[1].indexOf("?");
        if (index != -1) {
            this.uri = lineComponents[1].substring(0, index);
            this.parseUri(lineComponents[1]);
        } else {
            this.uri = lineComponents[1];
        }
    }

    // called from parseFirstLine()
    // parses the uri segment of the request
    // extracts the uri and puts the key value pairs of the parameters into a map if they are present
    private void parseUri(String uri) {
        // put the parameters into the hashmap
        String[] splitParameters = uri.split("&");
        if (splitParameters.length > 1) {
            for (String pair : splitParameters) {
                String[] keyValue = pair.split("=");
                this.uriParameters.put(keyValue[0], keyValue[1]);
            }
        }
    }

    private void displayRequest() {
        this.requestLines.forEach(line -> System.out.println(line));
    }

    public String getMethod() { return this.method; }

    public Map<String, String> getUriParameters() { return this.uriParameters; }

    public String getUri() { return this.uri; }

    public Map<String, String> getRequestHeaders() { return this.requestHeaders; }
}
