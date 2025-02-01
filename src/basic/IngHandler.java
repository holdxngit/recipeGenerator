package basic;

import java.io.IOException;
import java.util.List;

public class IngHandler {
    private List<String> ingredients;
    private FileHandler reader;

    public IngHandler() throws IOException {
        ingredients = reader.readCsvFromFile();
    }


    public boolean isListEmpty() {
        return ingredients.isEmpty();
    }


    public boolean removeIngredient(String ing) {
        if (ingredients.contains(ing)) {
            ingredients.remove(ing);
            this.saveIngredients();
            return true;
        }
        return false;
        
    }


    public void addIngredient(String ing) {
        ingredients.add(ing);
        this.saveIngredients();
    }
    
    public void saveIngredients() { 
        try {
            reader.writeCsvToFile(ingredients);
        }
        catch (IOException e) {
            System.err.println("Failed to Save to CSV file correctly");
            e.printStackTrace();
        }
    }

}
