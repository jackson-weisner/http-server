package weisner.httpserver;

public class DebugOutput {
    public static void info(String message) {
        DebugOutput.print("INFO", message);
    }

    public static void error(String message) {
        DebugOutput.print("ERROR", message);
    }

    private static void print(String t, String message) {
        System.out.println("[" + t + "] " + message);
    }
}
