/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileNotFoundException;
import Trainer.Trainer;
import PokemonBattle_LevelUp.*;
import static GUI.GamePanel.*;
import static GUI.Graph.*;

public class PewterCity extends JPanel {

    private JLabel label;
    private JTextArea console;
    private JTextField inputField;
    private JScrollPane scroll;
    private Container container;
    private ViridianCity viridianCity;
    private CeruleanCity ceruleanCity;
    private Trainer trainer;
    private Location location;

    public PewterCity(Container container, Trainer trainer, Location location) throws FileNotFoundException {
        this.container = container;
        this.trainer = trainer;
        this.location = location;
        this.viridianCity = viridianCity;
        this.ceruleanCity = ceruleanCity;
        setBackground(Color.black);
        setLayout(new BorderLayout());

        // Label
        label = new JLabel();
        label.setForeground(Color.CYAN);
        String advText = getASCII("PewterCity.txt");
        label.setText("<html><pre>" + advText + "</pre></html>");
        add(label, BorderLayout.NORTH);

        // Console
        console = new JTextArea();
        console.setEditable(false);
        console.setBackground(Color.BLACK);
        console.setForeground(Color.CYAN);

        // Matching the Font with the ASCII Text Files
        Font consoleFont = new Font("Monospaced", Font.PLAIN, 14);
        console.setFont(consoleFont);

        scroll = new JScrollPane(console);
        scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(scroll, BorderLayout.CENTER);

        // Graph
        Graph graph = new Graph();
        char list = 'a';

        graph.addVertex(v5);
        graph.addVertex(v2);
        graph.addVertex(v6);

        graph.addEdge(v5, v2);
        graph.addEdge(v5, v6);

        // Input Field
        inputField = new JTextField();
        inputField.setBackground(Color.LIGHT_GRAY);
        console.append("    [1] Move to: \n");
        for (Vertex vertex : graph.getAdjVertices(v5)) {
            console.append("        " + list++ + ". " + vertex.getName() + "\n");
        }
        console.append("    [2] Challenge Gym Leader [Brock - Rock Type] \n"
                + "    [3] Fight Wild Pokemon \n"
                + "    [4] Player Options \n"
                + "        a. Show Map        b. Show My Pokémon \n"
                + "        c. Show My Badges        d. Save and Exit \n"
                + "  +---------------------------------------------------------------------+  \n"
        );

        inputField.addActionListener((ActionEvent e) -> {
            String input = inputField.getText();
            if (input.isEmpty()) {
                return;
            }
            console.append("> " + input + "\n");
            switch (input) {
                case "1a":
                    console.append("    Your choice: " + input
                            + "\n  +---------------------------------------------------------------------+  \n");
                    moveToViridianCity();
                    break;
                case "1b":
                    console.append("    Your choice: " + input
                            + "\n  +---------------------------------------------------------------------+  \n");
                    moveToCeruleanCity();
                    break;
                case "2":
                    console.append("    Your choice: " + input
                            + "\n  +---------------------------------------------------------------------+  \n");
                    challengeGymLeader();
                    break;
                case "3":
                    console.append("    Your choice: " + input
                            + "\n  +---------------------------------------------------------------------+  \n");
                    fightWildPokemon();
                    break;
                case "4a":
                    showMap();
                    break;
                case "4b":
                    showMyPokemon();
                    break;
                case "4c":
                    showMyBadges();
                    break;
                case "4d":
                    saveAndExit();
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Invalid command!");
                    inputField.setText("");
                    break;
            }

            inputField.setText(""); // Clear the input field
        });

        add(inputField, BorderLayout.SOUTH);
    }

    private void moveToViridianCity() {
        Location viridianCityLocation = new Location(Location.VIRIDIAN_CITY);
        ViridianCity viridianCityPanel;
        try {
            viridianCityPanel = new ViridianCity(container, trainer, viridianCityLocation);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        container.remove(this);
        container.add(viridianCityPanel, BorderLayout.CENTER);

        container.revalidate();
        container.repaint();
    }

    private void moveToCeruleanCity() {
        Location ceruleanCityLocation = new Location(Location.CERULEAN_CITY);
        CeruleanCity ceruleanCityPanel;
        try {
            ceruleanCityPanel = new CeruleanCity(container, trainer, ceruleanCityLocation);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        container.remove(this);
        container.add(ceruleanCityPanel, BorderLayout.CENTER);

        container.revalidate();
        container.repaint();
    }

    private void challengeGymLeader() {
        PokemonBattle pokemonBattle = new PokemonBattle(trainer, false, location);
        pokemonBattle.challengeGymLeader(trainer, location);
    }

    private void fightWildPokemon() {
        PokemonBattle pokemonBattle = new PokemonBattle(trainer, true, location);
        pokemonBattle.fightWildPokemon(trainer, location);
    }

    private void showMap() {
        console.append("  +---------------------------------------------------------------------+  \n"
                + "  [**Pewter City**]-----------------[Cerulean City]---------------|\n"
                + "     |                                     |                      |\n"
                + "     |                                     |                      |\n"
                + "     |                                     |                      |\n"
                + "     |                                     |                      |\n"
                + "     |            [Celadon City]-----[Saffron City]-----[Lavender Town]\n"
                + "     |                      |              |                      |\n"
                + "  [Viridian City]           |              |                      |\n"
                + "     |                      |              |                      |\n"
                + "     |                      |              |                      |\n"
                + "     |                      |        [Vermillion City]------------|\n"
                + "     |                      |                                     |\n"
                + " [Pallet Town]              |                                     |\n"
                + "     |                      |                                     |\n"
                + "     |            [Fuchsia City]----------------------------------|\n"
                + "     |                      |\n"
                + "     |                      |\n"
                + "  [Cinnabar Island]---------|\n"
                + "  +---------------------------------------------------------------------+  \n"
        );
    }

    private void showMyPokemon() {
        console.append(trainer.showPokemonList());
    }

    private void showMyBadges() {
        console.append(trainer.showBadges());
    }

    private void saveAndExit() {
        // Implement save and exit logic here
        System.exit(0);
    }
}
