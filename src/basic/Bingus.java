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

public class Bingus {
    public static void main(String[] args)
        throws IOException,
        InterruptedException {
        Properties prop = new Properties();
        try (InputStream input = Bingus.class.getClassLoader()
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
                    .statusCode());
                System.out.println("Response body: " + response.body());
                 
                JSONObject jsonObj = new JSONObject(response.body());

                // Navigate through the JSON structure to get to the "text"
                JSONArray candidates = jsonObj.getJSONArray("candidates");
                if (candidates.length() > 0) {
                    JSONObject firstCandidate = candidates.getJSONObject(0);
                    JSONObject content = firstCandidate.getJSONObject("content");
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
}
