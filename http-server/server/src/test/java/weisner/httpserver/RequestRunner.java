package weisner.httpserver;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestRunner {

    public static String send(String urlString, String method) {
        return RequestRunner.send(urlString, method, null);
    }
    public static String send(String urlString, String method, String body) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            if (method.equals("POST")) {
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                OutputStream out = connection.getOutputStream();
                out.write((body == null) ? "\r\n\r\n".getBytes() : body.getBytes());
                out.close();
            }
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
//                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            System.out.println("Error");
            return "ERROR";
        }
    }
}
