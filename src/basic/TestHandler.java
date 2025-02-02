package basic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestHandler {
    public static void main(String[] args) {
       GeminiHandler gh = new GeminiHandler(); 
       try {
        System.out.println(GeminiHandler.ask("Describe the country of france"));
    }
    catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } 
        
    }
}
