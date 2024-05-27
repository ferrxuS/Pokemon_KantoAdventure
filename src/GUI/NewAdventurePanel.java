/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import static GUI.GamePanel.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import Trainer.Trainer;
import pokemons.*;
import PokemonBattle_LevelUp.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewAdventurePanel extends JPanel {

    private JLabel advLabel;
    private JTextArea advConsole;
    private JTextField inputField;
    private JScrollPane advScroll;
    private String currentState;
    private PalletTown palletTown;
    private Container container;
    public static Trainer trainer; //I do a object to store the name because for the future saving progress
    private Pokemon selectedPokemon;
    private Location location;

    public NewAdventurePanel(Container container) throws FileNotFoundException {
        this.trainer = new Trainer();
        this.container = container;
        setBackground(Color.black);
        setLayout(new BorderLayout());
        currentState = "name";

        // Adv Label
        advLabel = new JLabel();
        advLabel.setForeground(Color.CYAN);
        String advText = getASCII("NewAdventure.txt");
        advLabel.setText("<html><pre>" + advText + "</pre></html>");
        add(advLabel, BorderLayout.NORTH);

        // Adv Console
        advConsole = new JTextArea();
        advConsole.setEditable(false);
        advConsole.setBackground(Color.BLACK);
        advConsole.setForeground(Color.CYAN);

        // Matching the Font with the ASCII Text Files
        Font consoleFont = new Font("Monospaced", Font.PLAIN, 14);
        advConsole.setFont(consoleFont);

        advScroll = new JScrollPane(advConsole);
        advScroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(advScroll, BorderLayout.CENTER);

        // Input Field
        inputField = new JTextField();
        inputField.setBackground(Color.LIGHT_GRAY);
        inputField.addActionListener((ActionEvent e) -> {
            String input = inputField.getText();
            if (input.isEmpty()) {
                return;
            }
            advConsole.append("> " + input + "\n");
            if (currentState.equals("name")) {
                this.trainer.setTrainerName(input);
                advConsole.append("  +---------------------------------------------------------------------+  \n"
                        + "    OAK:     Right! So your name is " + input + "! Welcome to the world of Pokémon. "
                        + "\n    It's time to choose your starting Pokémon. \n"
                );
                advConsole.append("  +---------------------------------------------------------------------+  \n"
                        + "    [1] Bulbasaur [Grass - Level 5]\n" + "    [2] Squirtle [Water - Level 5]\n"
                        + "    [3] Charmander [Fire - Level 5]\n" + "  +---------------------------------------------------------------------+  \n"
                );
                currentState = "pokemonChoice";
            } else if (currentState.equals("pokemonChoice")) {
                if (input.equals("1")) {
                    advConsole.append("  +---------------------------------------------------------------------+  \n"
                            + "    OAK:     You chose Bulbasaur, an amazing choice. Best of luck!\n");
                    selectedPokemon = new Bulbasaur();
                } else if (input.equals("2")) {
                    advConsole.append("  +---------------------------------------------------------------------+  \n"
                            + "    OAK:     You chose Squirtle, an amazing choice. Best of luck!\n");
                    selectedPokemon = new Squirtle();
                } else if (input.equals("3")) {
                    advConsole.append("  +---------------------------------------------------------------------+  \n"
                            + "    OAK:     You chose Charmander, an amazing choice. Best of luck!\n");
                    selectedPokemon = new Charmander();
                } else {
                    advConsole.append("  +---------------------------------------------------------------------+  \n"
                            + "    OAK:     Please enter a valid input.\n"
                            + "  +---------------------------------------------------------------------+  \n");
                    inputField.setText(""); // Clear the input field
                    return; // Don't proceed further
                }
                currentState = "palletTown";
                advConsole.append("  +---------------------------------------------------------------------+  \n"
                        + "  Type NEXT to continue\n");
            } else if (currentState.equals("palletTown")) {
                if (input.equalsIgnoreCase("next")) {
                    try {
                        updateAdvToPalletTown();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(NewAdventurePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (!input.equalsIgnoreCase("next")) {
                    JOptionPane.showMessageDialog(this, "Invalid command!");
                }
            }
            inputField.setText("");
        });

        add(inputField, BorderLayout.SOUTH);
    }

    private void updateAdvToPalletTown() throws FileNotFoundException {
        // Create a Location for Pallet Town
        Location palletTownLocation = new Location(Location.PALLET_TOWN);
        
        // Create a new instance of PalletTown with the necessary arguments
        palletTown = new PalletTown(container, trainer, palletTownLocation);

        // Remove current panel (NewAdventurePanel)
        container.remove(this);

        // Add the new palletTown panel
        container.add(palletTown, BorderLayout.CENTER);

        // Set the selected Pokémon in Trainer class
        trainer.setSelectedPokemon(selectedPokemon);

        // Revalidate and repaint container
        container.revalidate();
        container.repaint();

        inputField.setText("");
        inputField.requestFocusInWindow();
        inputField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
    }

    private String getASCII(String filename) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}

