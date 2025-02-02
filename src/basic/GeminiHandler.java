package basic;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.Properties;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.*;

public class GeminiHandler {
    public static void main(String[] args)
        throws IOException,
        InterruptedException {
        Properties prop = new Properties();
        try (InputStream input = GeminiHandler.class.getClassLoader()
            .getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            prop.load(input);
            String apiKey = prop.getProperty("api_key");
            System.out.println("API Key: " + apiKey);

            String requestBody = "{\n" + "  \"contents\": [{\n"
                + "    \"parts\": [{\"text\": \"Explain how AI works\"}]\n"
                + "  }]\n" + "}";

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(
                "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key="
                    + apiKey)).header("Content-Type", "application/json").POST(
                        BodyPublishers.ofString(requestBody)).build();

            HttpClient client = HttpClient.newHttpClient();

            try {
                HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
                System.out.println("Response status code: " + response
                    .statusCode()); // TODO: Remove this

                JSONObject jsonObj = new JSONObject(response.body());

                JSONArray candidates = jsonObj.getJSONArray("candidates");
                if (candidates.length() > 0) {
                    JSONObject firstCandidate = candidates.getJSONObject(0);
                    JSONObject content = firstCandidate.getJSONObject(
                        "content");
                    JSONArray parts = content.getJSONArray("parts");
                    if (parts.length() > 0) {
                        JSONObject firstPart = parts.getJSONObject(0);
                        String text = firstPart.getString("text");

                        // Output the extracted text
                        System.out.println(text);
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static String ask(String request) throws IOException {
        Properties prop = new Properties();
        try (InputStream input = GeminiHandler.class.getClassLoader()
            .getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return "ERROR, Could not reach Api Key";
            }
            prop.load(input);
            String apiKey = prop.getProperty("api_key");

            String requestBody = "{\n" + "  \"contents\": [{\n"
                + "    \"parts\": [{\"text\": \"" + request + "\"}]\n"
                + "  }]\n" + "}";

            HttpRequest request1 = HttpRequest.newBuilder().uri(URI.create(
                "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key="
                    + apiKey)).header("Content-Type", "application/json").POST(
                        BodyPublishers.ofString(requestBody)).build();

            HttpClient client = HttpClient.newHttpClient();

            try {
                HttpResponse<String> response = client.send(request1,
                    HttpResponse.BodyHandlers.ofString());
                System.out.println("Response status code: " + response
                    .statusCode()); // TODO: Remove this

                JSONObject jsonObj = new JSONObject(response.body());

                JSONArray candidates = jsonObj.getJSONArray("candidates");
                if (candidates.length() > 0) {
                    JSONObject firstCandidate = candidates.getJSONObject(0);
                    JSONObject content = firstCandidate.getJSONObject(
                        "content");
                    JSONArray parts = content.getJSONArray("parts");
                    if (parts.length() > 0) {
                        JSONObject firstPart = parts.getJSONObject(0);
                        String text = firstPart.getString("text");

                        // Output the extracted text
                        System.out.println(text);
                        return text;
                    }
                }
                return "No Text Provided by AI Model";
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "No Recipes could be gathered";
    }
}
