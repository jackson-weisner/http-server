package weisner.httpserver;

public class JavascriptResponse extends FileResponse {
    public JavascriptResponse(String fileName) {
        super(fileName);
        this.addHeader("Content-Type", "text/javascript");
    }
}
