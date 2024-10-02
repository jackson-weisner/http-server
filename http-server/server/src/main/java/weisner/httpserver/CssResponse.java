package weisner.httpserver;

public class CssResponse extends FileResponse {
    public CssResponse(int code, String fileName) {
        super(code, fileName);
        this.addHeader("Content-Type", "text/css");
    }
}
