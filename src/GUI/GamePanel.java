package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Trainer.Trainer;
import PokemonBattle_LevelUp.Location;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class GamePanel {

    JFrame screen;
    JPanel currentPanel, titlePanel; // Removed menuPanel
    JLabel titleLabel;

    Container container;
    private NewAdventurePanel advPanel;

    // public static void main(String[] args) throws FileNotFoundException {
    // GamePanel gp = new GamePanel();
    // }

    public GamePanel(Trainer trainer) throws FileNotFoundException {
        // Creating a game screen
        screen = new JFrame();
        screen.setSize(600, 600); // 800x600 pixels
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setResizable(false);
        screen.setTitle("Pokémon - Kanto Adventure");
        screen.getContentPane().setBackground(Color.black);
        screen.setLocationRelativeTo(null);

        // Container
        container = screen.getContentPane();
        container.setLayout(new BorderLayout()); // for better layout management

        // Title Screen
        titlePanel = new JPanel(new BorderLayout()); // Use BorderLayout for titlePanel
        titlePanel.setBackground(Color.black);

        screen.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (trainer == null) {
                        startNewAdventure(); // Directly start the adventure on Enter key
                    } else {
                        startSavedAdventure(trainer);
                    }
                }
            }
        });

        // Add title label to the center of the title panel
        titleLabel = new JLabel();
        titleLabel.setForeground(Color.green);
        String titleText = getASCII("GameTitle.txt");
        titleLabel.setText("<html><pre>" + titleText + "</pre></html>"); // wrap text in html for preserve formatting
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        // New Adventure Screen
        advPanel = new NewAdventurePanel(container);

        // Initially, show the title panel
        container.add(titlePanel, BorderLayout.CENTER);

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

    // Start New Adventure
    public void startNewAdventure() {
        currentPanel = titlePanel;
        container.remove(titlePanel);
        container.add(advPanel, BorderLayout.CENTER);
        currentPanel = advPanel;
        container.revalidate(); // refresh the screen
        container.repaint();
    }

    public void startSavedAdventure(Trainer trainer) {
        Location savedLocation = trainer.getCurrentLocation(); // Assuming this method exists in the Trainer class to
                                                               // get the current location
        JPanel locationPanel = null;
        trainer.setSelectedPokemon(trainer.getPokemonList().get(0));

        String locationName = savedLocation.getName();
        String className = "GUI." + locationName.replace(" ", "");
        try {
            Class<?> locationClass = Class.forName(className);
            Constructor<?> constructor = locationClass.getConstructor(Container.class, Trainer.class, Location.class);
            locationPanel = (JPanel) constructor.newInstance(container, trainer, savedLocation);

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Unknown location: " + locationName, e);
        }


        if (locationPanel != null) {
            container.remove(titlePanel); // Assuming titlePanel is the current panel being displayed
            container.add(locationPanel, BorderLayout.CENTER);
            currentPanel = locationPanel;
            container.revalidate();
            container.repaint();
        }
    }

}
