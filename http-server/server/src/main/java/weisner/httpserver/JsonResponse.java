package weisner.httpserver;

public class JsonResponse extends Response {
    private final String data;
    public JsonResponse(int code, String jsonData) {
        super(code);
        this.data = jsonData;
        this.addHeader("Content-Type", "application/json");
    }

    @Override
    protected String getResponseData() {
        return this.data;
    }
}
