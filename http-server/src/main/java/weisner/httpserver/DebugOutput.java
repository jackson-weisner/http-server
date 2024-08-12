package weisner.httpserver;

public class DebugOutput {
    public static void info(String message) {
        DebugOutput.print("INFO", message);
    }

    private static void print(String t, String message) {
        System.out.println("[" + t + "] " + message + "\n");
    }
}
