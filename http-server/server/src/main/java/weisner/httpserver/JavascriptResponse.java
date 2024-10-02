package weisner.httpserver;

public class JavascriptResponse extends FileResponse {
    public JavascriptResponse(int code, String fileName) {
        super(code, fileName);
        this.addHeader("Content-Type", "text/javascript");
    }
}
