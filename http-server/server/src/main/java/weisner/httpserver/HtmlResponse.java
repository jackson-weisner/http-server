package weisner.httpserver;

public class HtmlResponse extends FileResponse {
    public HtmlResponse(String fileName) {
        super(fileName);
        this.addHeader("Content-Type", "text/html");
    }
}
