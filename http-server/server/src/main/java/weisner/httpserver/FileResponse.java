package weisner.httpserver;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public abstract class FileResponse extends Response {
    private final String fileName;
//    private static final String pathPrefix = System.getProperty("user.dir") + "/../../../../content/";
    private static final String path = "client/content/";
    protected FileResponse(int code, String fileName) {
        super(code);
        this.fileName = fileName;
    }
    @Override
    protected String getResponseData() {
        try {
            // TODO: prevent injection
            File inputFile = new File(FileResponse.path + this.fileName);
            Scanner fileScanner = new Scanner(inputFile);
            StringBuilder sb = new StringBuilder();
            while (fileScanner.hasNextLine()) {
                sb.append(fileScanner.nextLine());
            }
            return sb.toString();
        } catch (IOException e) {
            return "ERROR";
        }
    }
}
