package basic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IngHandler {
    private List<String> ingredients;
    private FileHandler reader;

    public IngHandler() throws IOException {
        reader = new FileHandler("recipes.csv");
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
    
    public List<String> getIngredients()
    {
        return ingredients;
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


    public String ingredientLine() {
        return FileHandler.joinStrings(ingredients);
    }
    

    public static <T> ArrayList<T> listToArrayList(List<T> list) {
        return new ArrayList<>(list);
    }

    public static <T> List<T> arrayListToList(ArrayList<T> arrayList) {
        return new ArrayList<>(arrayList);
    }

    public static void main(String[] args) {
        // Example usage of converting a List to an ArrayList
        List<String> list = Arrays.asList("Apple", "Banana", "Cherry");
        ArrayList<String> arrayList = listToArrayList(list);
        System.out.println("ArrayList: " + arrayList);

        // Example usage of converting an ArrayList to a List
        List<String> newList = arrayListToList(arrayList);
        System.out.println("List: " + newList);
    }
}
