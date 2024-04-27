/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author raishahaque
 */
public class GamePanel {

    JFrame screen;
    JPanel currentPanel, titlePanel, menuPanel; //,startButtonPanel
    JLabel titleLabel, menuLabel;
    JTextField userInput;
    Container container;
    private NewAdventurePanel advPanel;

    public static void main(String[] args) throws FileNotFoundException {
        GamePanel gp = new GamePanel();
        
    }

    public GamePanel() throws FileNotFoundException {
        //creating a game screen
        screen = new JFrame();
        screen.setSize(600, 600); //800x600 pixels
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setResizable(false);
        screen.setTitle("Pokémon - Kanto Adventure");
        screen.getContentPane().setBackground(Color.black);
        screen.setLocationRelativeTo(null);

        // Container
        container = screen.getContentPane();
        container.setLayout(new BorderLayout()); //for better layout management

        // User Input
        userInput = new JTextField();
        userInput.setBackground(Color.LIGHT_GRAY);
        userInput.addActionListener((ActionEvent e) -> {
            handleUserInput(userInput.getText());
        });

        // Title Screen
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.black);
        titleLabel = new JLabel();
        titleLabel.setForeground(Color.green);
        String titleText = getASCII("GameTitle.txt");
        titleLabel.setText("<html><pre>" + titleText + "</pre></html>"); //wrap text in html for preserve formatting
        titlePanel.add(titleLabel);

        // Menu Screen
        menuPanel = new JPanel();
        menuPanel.setBackground(Color.black);
        menuLabel = new JLabel();
        menuLabel.setForeground(Color.green);
        String menuText = getASCII("MenuPanel.txt");
        menuLabel.setText("<html><pre>" + menuText + "</pre></html>");
        menuPanel.add(menuLabel);

        // New Adventure Screen
        advPanel = new NewAdventurePanel(container);

        // Container (keep it in reverse order)
        container.add(advPanel, BorderLayout.CENTER);
        container.add(menuPanel, BorderLayout.CENTER);
        container.add(titlePanel, BorderLayout.CENTER);
        container.add(userInput, BorderLayout.SOUTH);

        screen.setVisible(true);
    }

    // Printing ASCII Text File
    public static String getASCII(String filePath) throws FileNotFoundException {
        StringBuilder builder = new StringBuilder();
        try (Scanner sc = new Scanner(new File(filePath))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                builder.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    // Enter MENU
    private void updateTitleToMenu() {
        currentPanel = titlePanel;
        container.remove(titlePanel);
        container.add(menuPanel, BorderLayout.CENTER);
        currentPanel = menuPanel;
        container.revalidate(); // refresh the screen
        container.repaint();
        userInput.setText(""); //clear the input field
    }

    // Exit Menu
    private void exitMenu() {
        currentPanel = menuPanel;
        container.remove(menuPanel);
        container.add(titlePanel, BorderLayout.CENTER);
        currentPanel = titlePanel;
        container.revalidate(); // refresh the screen
        container.repaint();
        userInput.setText(""); //clear the input field
    }

    // Start New Adventure
    private void startAdventure() {
        currentPanel = menuPanel;
        container.remove(menuPanel);
        container.remove(userInput);
        container.add(advPanel, BorderLayout.CENTER);
        currentPanel = advPanel;
        container.revalidate(); // refresh the screen
        container.repaint();
        userInput.setText(""); //clear the input field
    }
    
    private void handleUserInput(String input) {
        input = input.trim();

        if (input.equalsIgnoreCase("enter")) {
            updateTitleToMenu(); // Move to menu
        } else if (currentPanel == menuPanel) { // Check if current panel is menu
            switch (input) {
                case "1" :
                    startAdventure();
                    break;
                case "3" : 
                    exitMenu();
                    break;
                default : 
                    JOptionPane.showMessageDialog(screen, "Invalid command!");
                    userInput.setText("");
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(screen, "Invalid command!");
            userInput.setText("");
        }
    }
}
