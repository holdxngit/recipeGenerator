package basic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestHandler {
    public static void main(String[] args) {
        try {
            IngHandler ing = new IngHandler();
            ing.addIngredient("Cucumber");
            ing.addIngredient("Mayo");
            ing.addIngredient("Dead Dog");
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
    }
}
