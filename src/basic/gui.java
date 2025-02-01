package basic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui extends JFrame {
    private JTextField textField;
    private JButton button;
    private JLabel promptLabel;

    public gui() {
        createView();
        setTitle("Simple Input GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(400, 200));
        setLocationRelativeTo(null); // Center the window
        setResizable(false);
    }

    private void createView() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        promptLabel = new JLabel("Enter something:");
        panel.add(promptLabel);

        textField = new JTextField(20);
        panel.add(textField);

        button = new JButton("Submit");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textField.getText();
                JOptionPane.showMessageDialog(null, "You entered: " + inputText);
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
