package weisner.httpserver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HtmlResponse extends FileResponse {
    public HtmlResponse(int code, String fileName) {
        super(code, fileName);
        this.addHeader("Content-Type", "text/html");
    }
}
