package weisner.httpserver;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ImageResponse extends FileResponse {
    // set to check for valid file extensions
    private final Set<String> extensionSet = new HashSet<>(Arrays.asList("png", "jpeg", "gif"));
    public ImageResponse(String fileName) {
        super(fileName);
        String extension = fileName.substring(fileName.indexOf('.')+1);
        if (extensionSet.contains(extension)) {
            this.addHeader("Content-Type", "image/" + extension);
        } else {
            DebugOutput.error("Invalid image type");
            this.addHeader("Content-Type", "text/plain");
        }
    }

    @Override
    protected byte[] getResponseData() throws Exception {
        return Files.readAllBytes(Paths.get(super.fileName));
    }
}
