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
    private Map<String, String> uriParameters;

    public Request(String s) {
        this.requestLines = s.lines().collect(Collectors.toList());
        this.uriParameters = new HashMap<>();
        this.parseFirstLine();
    }

    // parses the request method and uri from the request lines
    private void parseFirstLine() {
        String[] lineComponents = this.requestLines.get(0).split(" ");
        this.method = lineComponents[0];
//        switch (lineComponents[0]) {
//            case "GET":     this.method = RequestMethod.GET; break;
//            case "POST":    this.method = RequestMethod.POST; break;
//        }
        this.parseUri(lineComponents[1]);
    }

    // called from parseFirstLine()
    // parses the uri segment of the request
    // extracts the uri and puts the key value pairs of the parameters into a map if they are present
    private void parseUri(String fullUri) {
        int index = fullUri.indexOf("?");
        if (index != -1) {
            this.uri = fullUri.substring(0, index);

            // put the parameters into the hashmap
            String parameterSubstring = fullUri.substring(index+1);
            String[] splitParameters = parameterSubstring.split("&");
            for (String pair : splitParameters) {
                String[] keyValue = pair.split("=");
                this.uriParameters.put(keyValue[0], keyValue[1]);
            }
        } else {
            this.uri = fullUri;
        }
    }

    public String getMethod() { return this.method; }

    public Map<String, String> getUriParameters() { return this.uriParameters; }

    public String getUri() { return this.uri; }
}
