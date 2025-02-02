package basic;

import java.io.IOException;

public class ProjectRunner {
    private GeminiHandler gem;
    private IngHandler ing;
    private int numOfRecipes;

    public ProjectRunner() {
        gem = new GeminiHandler();
        try {
            ing = new IngHandler();
        }
        catch (IOException e) {
            System.err.print("Ingredient Handler could not be created");
            e.printStackTrace();
        }
        numOfRecipes = 3;
    }


    // Give me # of recipes based on the current ingredients in my house, you do
    // not have to use every ingredient:
    public String getRecipes() {
        StringBuilder query = new StringBuilder("Give me " + numOfRecipes
            + " recipes based on the current ingredient list "
            + "in my house, you do not have to use every ingredient. List of ingredients in the house: ");
        query.append(ing.ingredientLine()); 
        
        try {
            return GeminiHandler.ask(query.toString());
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        return null; 
    }
}
