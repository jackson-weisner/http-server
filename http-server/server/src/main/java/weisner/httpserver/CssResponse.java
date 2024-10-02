package weisner.httpserver;

public class CssResponse extends FileResponse {
    public CssResponse(String fileName) {
        super(fileName);
        this.addHeader("Content-Type", "text/css");
    }
}
