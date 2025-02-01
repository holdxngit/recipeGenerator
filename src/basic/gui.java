package basic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui extends JFrame {
    private JTextField textField;
    private JButton button;
    private JLabel promptLabel;
    private Font defualtFont; 

    public gui() {
        createView();
        setTitle("Simple Input GUI - Dark Mode");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(400, 200));
        setLocationRelativeTo(null); // Center the window
        setResizable(false);
    }

    private void createView() {
        Color backColor = new Color(10, 10, 10); 
        Color tabColor = new Color(35, 35, 35); 
        Font defualtFont = new Font("Arial", Font.PLAIN, 20); 
        
        JPanel panel = new JPanel();
        panel.setBackground(backColor);  // Set the background to dark gray
        getContentPane().add(panel);
        
        promptLabel = new JLabel("Enter something:");
        promptLabel.setFont(defualtFont); 
        promptLabel.setForeground(Color.WHITE);  // Set text color to light gray for visibility
        panel.add(promptLabel);

        textField = new JTextField(20);
        textField.setBackground(tabColor);  // Set a slightly lighter gray than the background
        textField.setForeground(Color.WHITE);  // White text for better visibility
        panel.add(textField);

        button = new JButton("Submit");
        button.setForeground(Color.DARK_GRAY);  // Dark text on the button
        button.setBackground(Color.LIGHT_GRAY);  // Light gray button background
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textField.getText();
                JOptionPane.showMessageDialog(null, "You entered: " + inputText, "Input", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel.add(button);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new gui().setVisible(true);
            }
        });
    }
}
