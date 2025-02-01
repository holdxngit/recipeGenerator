package basic;

import java.io.IOException;

public class TestHandler {
    public static void main(String[] args) {
        try {
            System.out.println(GeminiHandler.ask("Briefly Describe the country of Japan"));
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
