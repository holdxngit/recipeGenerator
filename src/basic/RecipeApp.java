package basic;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class RecipeApp {

    private JFrame frame;
    private JPanel inputPanel, listPanel, recipePanel, recipeDetailsPanel;
    private JTextField ingredientField;
    private JButton viewButton, generateRecipeButton;
    private ArrayList<String> ingredients;
    private CardLayout cardLayout;  // Declare CardLayout at the class level

    public RecipeApp() {
        ingredients = new ArrayList<>();

        frame = new JFrame("Recipe App");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize CardLayout at the class level
        cardLayout = new CardLayout();
        frame.setLayout(cardLayout);

        // Initialize the input screen panel
        inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBackground(new Color(255, 255, 255));
        
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(255, 255, 255)); // Blue background for the top panel
        inputPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());
        centerPanel.setBackground(new Color(10, 10, 10)); // Blue background for the center panel

        JLabel ingredientLabel = new JLabel("Enter Ingredient:");
        ingredientLabel.setForeground(Color.WHITE); 
        centerPanel.add(ingredientLabel);

        ingredientField = new JTextField(15);
        centerPanel.add(ingredientField);

        viewButton = new JButton("View Ingredients");
        viewButton.setBackground(new Color(52, 52, 52)); // Light blue background for button
        viewButton.setForeground(Color.WHITE); // White text for the button
        centerPanel.add(viewButton);

        inputPanel.add(centerPanel, BorderLayout.CENTER);

        generateRecipeButton = new JButton("Generate Recipes");
        generateRecipeButton.setBackground(new Color(52, 52, 52)); // Light blue background for button
        generateRecipeButton.setForeground(Color.WHITE); // White text for the button
        inputPanel.add(generateRecipeButton, BorderLayout.SOUTH);

        // Initialize the list of ingredients panel
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS)); // Vertical layout for ingredients and buttons
        listPanel.setBackground(new Color(10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(listPanel);
        frame.add(scrollPane, "list");

        // Initialize the recipe panel
        recipePanel = new JPanel();
        recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS));  // Vertical layout for recipes
        recipePanel.setBackground(new Color(10, 10, 10));
        frame.add(recipePanel, "recipe");

        // Initialize the recipe details panel (will show when a recipe is clicked)
        recipeDetailsPanel = new JPanel();
        recipeDetailsPanel.setLayout(new BorderLayout());
        recipeDetailsPanel.setBackground(new Color(10, 10, 10)); 

        // Add all panels to the frame (initial screen is inputPanel)
        frame.add(inputPanel, "input");

        // Show input panel initially
        cardLayout.show(frame.getContentPane(), "input");

        // Button listeners
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateIngredientList();
                cardLayout.show(frame.getContentPane(), "list");
            }
        });

        generateRecipeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayRecipes();
                cardLayout.show(frame.getContentPane(), "recipe");
            }
        });
        
     // Add ActionListener to the ingredient input field to submit when Enter is pressed
        ingredientField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ingredient = ingredientField.getText();
                if (!ingredient.isEmpty()) {
                    ingredients.add(ingredient);  // Add ingredient to list
                    ingredientField.setText("");  // Clear the input field
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter an ingredient!");  // Display warning
                }
            }
        });

        frame.setVisible(true);
    }

    // Updates the ingredient list and adds remove buttons
    private void updateIngredientList() {
        listPanel.removeAll();  // Clear the listPanel before updating
        if (ingredients.isEmpty()) {
            listPanel.add(new JLabel("No ingredients added."));
        } else {
            for (String ingredient : ingredients) {
                JPanel ingredientPanel = new JPanel();
                ingredientPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                ingredientPanel.setBackground(new Color(10, 10, 10));
                
                JLabel ingredientLabel = new JLabel(ingredient);
                JButton removeButton = new JButton("x");
                removeButton.setBackground(new Color(255, 99, 71)); // Red color for Remove button
                removeButton.setForeground(Color.WHITE); // White text color for the button
                removeButton.setFont(new Font("Arial", Font.PLAIN, 12)); // Set font for the button
                ingredientLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Bold font for ingredients
                ingredientLabel.setForeground(Color.WHITE); // White text color
                
                // Remove ingredient when the button is clicked
                removeButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ingredients.remove(ingredient);
                        updateIngredientList();  // Refresh the list
                    }
                });

                ingredientPanel.add(ingredientLabel);
                ingredientPanel.add(removeButton);
                listPanel.add(ingredientPanel);
            }
        }

        // Add Back to Input button at the bottom of the list
        JButton backToInputButton = new JButton("Back to Input");
        backToInputButton.setBackground(new Color(52, 52, 52));
        backToInputButton.setForeground(Color.WHITE); // White text color
        backToInputButton.setFont(new Font("Arial", Font.BOLD, 14)); // Bold font

        
        backToInputButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(frame.getContentPane(), "input");
            }
        });
        listPanel.add(backToInputButton);

        // Revalidate and repaint to reflect changes
        listPanel.revalidate();
        listPanel.repaint();
    }

    // Displays 3 recipe buttons and opens a new JPanel with details when clicked
    private void displayRecipes() {
        recipePanel.removeAll();  // Clear the recipe panel before updating

        // Example recipes (these could be dynamically generated based on the user's input)
        String[] recipeNames = {"Recipe 1", "Recipe 2", "Recipe 3"};
        String[] recipeDetails = {
            "Recipe 1: Ingredients: Ingredient 1, Ingredient 2, Ingredient 3\nSteps: Step 1, Step 2",
            "Recipe 2: Ingredients: Ingredient 4, Ingredient 5, Ingredient 6\nSteps: Step 1, Step 2",
            "Recipe 3: Ingredients: Ingredient 7, Ingredient 8, Ingredient 9\nSteps: Step 1, Step 2"
        };

        for (int i = 0; i < recipeNames.length; i++) {
            JButton recipeButton = new JButton(recipeNames[i]);
            recipeButton.setBackground(new Color(52, 52, 52));
            recipeButton.setForeground(Color.WHITE); // White text color
            final String details = recipeDetails[i];
            recipeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showRecipeDetails(details);
                }
            });
            recipePanel.add(recipeButton);
        }

        // Add Back to Input button to the recipe panel
        JButton backToInputButton = new JButton("Back to Input");
        backToInputButton.setBackground(new Color(52, 52, 52)); // Light blue color for button
        backToInputButton.setForeground(Color.WHITE); // White text color
        backToInputButton.setFont(new Font("Arial", Font.BOLD, 14)); // Bold font
        backToInputButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(frame.getContentPane(), "input");
            }
        });
        recipePanel.add(backToInputButton);

        // Revalidate and repaint to reflect changes
        recipePanel.revalidate();
        recipePanel.repaint();
    }

    // Opens a new JPanel with the full details of the recipe
    private void showRecipeDetails(String recipeDetails) {
        JPanel recipeDetailPanel = new JPanel();
        recipeDetailPanel.setLayout(new BorderLayout());
        recipeDetailPanel.setBackground(new Color(10, 10, 10));
        
        // Create JTextArea to display recipe details
        JTextArea recipeTextArea = new JTextArea();
        recipeTextArea.setText(recipeDetails);
        recipeTextArea.setEditable(false);

        // Customize the font and appearance of the JTextArea
        recipeTextArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font style and size
        recipeTextArea.setForeground(Color.DARK_GRAY); // Set font color
        recipeTextArea.setBackground(new Color(255, 255, 240)); // Set a light yellow background color
        recipeTextArea.setLineWrap(true); // Enable line wrapping
        recipeTextArea.setWrapStyleWord(true); // Wrap words at the end of lines
        
        // Add the JTextArea to a JScrollPane to make it scrollable
        JScrollPane scrollPane = new JScrollPane(recipeTextArea);
        recipeDetailPanel.add(scrollPane, BorderLayout.CENTER);

        // Customize the back button to match the styling
        JButton backToRecipeButton = new JButton("Back to Recipe List");
        backToRecipeButton.setBackground(new Color(52, 52, 52)); // Light blue color for button
        backToRecipeButton.setForeground(Color.WHITE); // White text color
        backToRecipeButton.setFont(new Font("Arial", Font.BOLD, 14)); // Bold font


        // Add a listener to go back to the recipe list
        backToRecipeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(frame.getContentPane(), "recipe");
            }
        });

        // Add the button at the bottom of the recipe details panel
        recipeDetailPanel.add(backToRecipeButton, BorderLayout.SOUTH);

        // Set the padding for the panel (optional)
        recipeDetailPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Replace the current recipe panel with the recipe detail panel
        frame.getContentPane().add(recipeDetailPanel, "recipeDetails");
        cardLayout.show(frame.getContentPane(), "recipeDetails");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RecipeApp();
            }
        });
    }
}