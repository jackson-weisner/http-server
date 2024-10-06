package weisner.httpserver;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public abstract class FileResponse extends Response {
    private final String fileName;
//    private static final String pathPrefix = System.getProperty("user.dir") + "/../../../../content/";
    private static final String path = "client/content/";
    protected FileResponse(String fileName) {
        this.fileName = fileName;
    }
    @Override
    protected String getResponseData() throws Exception {
        File inputFile = new File(FileResponse.path + this.fileName);
        Scanner fileScanner = new Scanner(inputFile);
        StringBuilder sb = new StringBuilder();
        while (fileScanner.hasNextLine()) {
            sb.append(fileScanner.nextLine());
        }
        return sb.toString();
    }
}
