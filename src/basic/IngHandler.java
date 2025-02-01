package basic;

import java.util.ArrayList;

public class IngHandler
{
    private ArrayList<String> ingredients;
    private FileReader reader;

    public IngHandler()
    {
       ArrayList<String> storedIng = reader.readFile();
       ingredients = new ArrayList<String>();
       // add in stored ingredients from earlier
       if (!storedIng.isEmpty()) {
           ingredients.addAll(storedIng);
       }
    }
    
    public boolean removeIngredient(String ing)
    {
        reader.remove(ing);
        return ingredients.remove(ing);
    }
    
    public void addIngredient(String ing)
    {
        reader.add(ing);
        ingredients.add(ing);
    }

}
