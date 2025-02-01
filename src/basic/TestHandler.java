package basic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestHandler {
    public static void main(String[] args) {
        FileHandler manager = new FileHandler("test.csv");
        try {
            List<String> storedIng = manager.readCsvFromFile();
            System.out.println(storedIng.get(0));
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
}
