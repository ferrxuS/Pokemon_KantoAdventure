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

public class NewAdventurePanel extends JPanel {

    private JLabel advLabel;
    private JTextArea advConsole;
    private JTextField inputField;
    private JScrollPane advScroll;
    private String currentState;
    private PalletTown palletTown;
    private Container container;

    public NewAdventurePanel(Container container) throws FileNotFoundException {
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

        palletTown = new PalletTown(container);

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
                } else if (input.equals("2")) {
                    advConsole.append("  +---------------------------------------------------------------------+  \n"
                            + "    OAK:     You chose Squirtle, an amazing choice. Best of luck!\n");
                } else if (input.equals("3")) {
                    advConsole.append("  +---------------------------------------------------------------------+  \n"
                            + "    OAK:     You chose Charmander, an amazing choice. Best of luck!\n");
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
                    updateAdvToPalletTown(); // to Enter MENU
                }
            }
            inputField.setText("");
        });

        add(inputField, BorderLayout.SOUTH);
    }

    private void updateAdvToPalletTown() {
        // Remove current panel (NewAdventurePanel)
        container.remove(this);

        // Add the new menuPanel
        container.add(palletTown, BorderLayout.CENTER);

        // Revalidate and repaint container
        container.revalidate();
        container.repaint();
    }
}
