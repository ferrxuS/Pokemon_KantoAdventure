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

public class CeruleanCity extends JPanel {

    private JLabel label;
    private JTextArea console;
    private JTextField inputField;
    private JScrollPane scroll;
    private Container container;
    private PewterCity pewterCity;
    private SaffronCity saffronCity;
    private LavenderTown lavenderTown;
    private Trainer trainer;
    private Location location;

    public CeruleanCity(Container container, Trainer trainer, Location location) throws FileNotFoundException {
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
        String advText = getASCII("CeruleanCity.txt");
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

        graph.addVertex(v6);
        graph.addVertex(v5);
        graph.addVertex(v10);
        graph.addVertex(v4);

        graph.addEdge(v6, v5);
        graph.addEdge(v6, v10);
        graph.addEdge(v6, v4);

        // Input Field
        inputField = new JTextField();
        inputField.setBackground(Color.LIGHT_GRAY);
        console.append("    [1] Move to: \n");
        for (Vertex vertex : graph.getAdjVertices(v6)) {
            console.append("        " + list++ + ". " + vertex.getName() + "\n");
        }
        console.append("    [2] Challenge Gym Leader [Misty - Water Type] \n"
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
                    this.moveToPewterCity();
                    break;
                case "1b":
                    console.append("    +---------------------------------------------------------------------+  \n");
                    this.moveToSaffronCity();
                    break;
                case "1c":
                    console.append("    Your choice: " + input
                            + "\n  +---------------------------------------------------------------------+  \n");
                    this.moveToLavenderTown();
                    break;
                case "2":
                    this.startChallengeGymLeader();
                    break;
                case "3":
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
                    break;
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

        // Revalidate and repaint container
        container.revalidate();
        container.repaint();
    }

    private void moveToSaffronCity() {
        Location saffronCityLocation = new Location(Location.SAFFRON_CITY);
        SaffronCity saffronCityPanel;
        try {
            saffronCityPanel = new SaffronCity(container, trainer, saffronCityLocation);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        container.remove(this);
        container.add(saffronCityPanel, BorderLayout.CENTER);

        // Revalidate and repaint container
        container.revalidate();
        container.repaint();
    }

    private void moveToLavenderTown() {
        Location lavenderTownLocation = new Location(Location.LAVENDER_TOWN);
        LavenderTown lavenderTownPanel;
        try {
            lavenderTownPanel = new LavenderTown(container, trainer, lavenderTownLocation);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        container.remove(this);
        container.add(lavenderTownPanel, BorderLayout.CENTER);

        // Revalidate and repaint container
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
                + "  [Pewter City]-----------------[**Cerulean City**]---------------|\n"
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
                + "  +---------------------------------------------------------------------+  \n");
    }

    private void showMyPokemon() {
        console.append(trainer.showPokemonList());
    }

    private void showMyBadges() {
        console.append(trainer.showBadges());
    }

}
