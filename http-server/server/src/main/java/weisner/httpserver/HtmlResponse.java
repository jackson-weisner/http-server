package weisner.httpserver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HtmlResponse extends Response {
    private final String fileName;
    public HtmlResponse(int code, String fileName) {
        super(code);
        this.fileName = fileName;
        this.addHeader("Content-Type", "text/html");
    }

    @Override
    protected String getResponseData() {
        try {
            return Files.readString(Paths.get("../" + this.fileName));
        } catch (IOException e) {
            DebugOutput.error("can't locate " + this.fileName);
            return "ERROR";
        }
    }

}
