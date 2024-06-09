/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author raishahaque
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileNotFoundException;
import Trainer.Trainer;
import PokemonBattle_LevelUp.*;
import static GUI.GamePanel.*;
import static GUI.Graph.*;

public class ViridianCity extends JPanel {

    private JLabel label;
    private JTextArea console;
    private JTextField inputField;
    private JScrollPane scroll;
    private Container container;
    private PalletTown palletTown;
    private PewterCity pewterCity;
    private Trainer trainer;
    private Location location;

    public ViridianCity(Container container, Trainer trainer, Location location) throws FileNotFoundException {
        this.container = container;
        this.trainer = trainer;
        this.location = location;

        this.trainer.setCurrentLocation(location);
        this.location.loadPokemons(trainer);

        setBackground(Color.black);
        setLayout(new BorderLayout());

        // Label
        label = new JLabel();
        label.setForeground(Color.CYAN);
        String advText = getASCII("ViridianCity.txt");
        label.setText("<html><pre>" + advText + "</pre></html>");
        add(label, BorderLayout.NORTH);

        // Console
        console = new JTextArea();
        console.setEditable(false);
        console.setBackground(Color.BLACK);
        console.setForeground(Color.CYAN);
        Font consoleFont = new Font("Monospaced", Font.PLAIN, 14);
        console.setFont(consoleFont);
        scroll = new JScrollPane(console);
        scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(scroll, BorderLayout.CENTER);

        // Graph
        Graph graph = new Graph();
        char list = 'a';
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v5);
        graph.addEdge(v2, v1);
        graph.addEdge(v2, v5);

        // Input Field
        inputField = new JTextField();
        inputField.setBackground(Color.LIGHT_GRAY);

        console.append("    [1] Move to: \n");
        for (Vertex vertex : graph.getAdjVertices(v2)) {
            console.append("        " + list++ + ". " + vertex.getName() + "\n");
        }

        console.append("    [2] Challenge Gym Leader [Giovanni - Ground Type] \n"
                + "    [3] Fight Wild Pokemon \n"
                + "    [4] Player Options \n"
                + "        a. Show Map        b. Show My Pokémon \n"
                + "        c. Show My Badges        d. Save and Exit \n"
                + "  +---------------------------------------------------------------------+  \n");

        inputField.addActionListener((ActionEvent e) -> {
            String input = inputField.getText();
            if (input.isEmpty()) {
                return;
            }
            console.append("> " + input + "\n");
            switch (input) {
                case "1a":
                    console.append("    +---------------------------------------------------------------------+  \n");
                    this.moveToPalletTown();
                    break;
                case "1b":
                    console.append("  +---------------------------------------------------------------------+  \n");
                    this.moveToPewterCity();
                    break;
                case "2":
                    console.append("  +---------------------------------------------------------------------+  \n");
                    this.startChallengeGymLeader();
                    break;
                case "3":
                    console.append("  +---------------------------------------------------------------------+  \n");
                    this.startFightWildPokemon();
                    break;
                case "4a":
                    this.showMap();
                    break;
                case "4b":
                    this.showMyPokemon();
                    break;
                case "4c":
                    this.showMyBadges();
                    break;
                case "4d":
                    GamePanel.saveAndExit(container, trainer);
                    break;
                default:
                    inputField.setText("");
                    break; // Add break statement here
            }

            inputField.setText(""); // Clear the input field
        });

        add(inputField, BorderLayout.SOUTH);
    }

    private void moveToPewterCity() {
        Location pewterCityLocation = new Location(Location.PEWTER_CITY);
        PewterCity pewterCityPanel;
        try {
            pewterCityPanel = new PewterCity(container, trainer, pewterCityLocation);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        container.remove(this);
        container.add(pewterCityPanel, BorderLayout.CENTER);
        container.revalidate();
        container.repaint();
    }

    private void moveToPalletTown() {
        Location palletTownLocation = new Location(Location.PALLET_TOWN);
        PalletTown palletTownPanel;
        try {
            palletTownPanel = new PalletTown(container, trainer, palletTownLocation);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        container.remove(this);
        container.add(palletTownPanel, BorderLayout.CENTER);
        container.revalidate();
        container.repaint();
    }

    private void startChallengeGymLeader() {
        new Thread(() -> {
            System.out.println("Before battle: " + console.getText()); // Track execution flow

            PokemonBattle pokemonBattle = new PokemonBattle(trainer, false, location, console, inputField);
            pokemonBattle.battle(); // Perform the battle

            System.out.println("After battle: " + console.getText()); // Verify battle log in console
        }).start();
    }

    private void startFightWildPokemon() {
        new Thread(() -> {
            System.out.println("Before battle: " + console.getText()); // Track execution flow

            PokemonBattle pokemonBattle = new PokemonBattle(trainer, true, location, console, inputField);
            pokemonBattle.battle(); // Perform the battle

            System.out.println("After battle: " + console.getText()); // Verify battle log in console
        }).start();
    }

    private void showMap() {
        console.append("  +---------------------------------------------------------------------+  \n"
                + "  [Pewter City]---------------------[Cerulean City]---------------|\n"
                + "     |                                     |                      |\n"
                + "     |                                     |                      |\n"
                + "     |                                     |                      |\n"
                + "     |                                     |                      |\n"
                + "     |            [Celadon City]-----[Saffron City]-----[Lavender Town]\n"
                + "     |                      |              |                      |\n"
                + "  [**Viridian City**]       |              |                      |\n"
                + "     |                      |              |                      |\n"
                + "     |                      |        [Vermillion City]------------|\n"
                + "     |                      |                                     |\n"
                + " [Pallet Town]              |                                     |\n"
                + "     |                      |                                     |\n"
                + "     |            [Fuchsia City]----------------------------------|\n"
                + "     |                      |\n"
                + "     |                      |\n"
                + "  [Cinnabar Island]---------|\n"
                + "  +---------------------------------------------------------------------+  \n");
    }

    private void showMyPokemon() {
        console.append(trainer.showPokemonList());
    }

    private void showMyBadges() {
        console.append(trainer.showBadges());
    }

}
