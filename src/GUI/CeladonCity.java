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

public class CeladonCity extends JPanel {

    private JLabel label;
    private JTextArea console;
    private JTextField inputField;
    private JScrollPane scroll;
    private Container container;
    private SaffronCity saffronCity;
    private FuchsiaCity fuchsiaCity;
    private LavenderTown lavenderTown;
    private Trainer trainer;
    private Location location;

    public CeladonCity(Container container, Trainer trainer, Location location) throws FileNotFoundException {
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
        String advText = getASCII("CeladonCity.txt");
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

        graph.addVertex(v8);
        graph.addVertex(v10);
        graph.addVertex(v9);

        graph.addEdge(v8, v10);
        graph.addEdge(v8, v9);

        // Input Field
        inputField = new JTextField();
        inputField.setBackground(Color.LIGHT_GRAY);
        console.append("    [1] Move to: \n");
        for (Vertex vertex : graph.getAdjVertices(v8)) {
            console.append("        " + list++ + ". " + vertex.getName() + "\n");
        }
        console.append("    [2] Challenge Gym Leader [Erika - Grass Type] \n"
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
                    console.append("    +---------------------------------------------------------------------+  \n");
                    this.moveToSaffronCity();
                    break;
                case "1b":
                    console.append("    +---------------------------------------------------------------------+  \n");
                    this.moveToFuchsiaCity();
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
                    this.saveAndExit();
                    break;
                default:
                    inputField.setText("");
                    break; // Add break statement here
            }

            inputField.setText(""); // Clear the input field
        });

        add(inputField, BorderLayout.SOUTH);
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

        container.revalidate();
        container.repaint();
    }

    private void moveToFuchsiaCity() {
        Location fuchsiaCityLocation = new Location(Location.FUCHSIA_CITY);
        FuchsiaCity fuchsiaCityPanel;
        try {
            fuchsiaCityPanel = new FuchsiaCity(container, trainer, fuchsiaCityLocation);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        container.remove(this);
        container.add(fuchsiaCityPanel, BorderLayout.CENTER);

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
                + "     |          [**Celadon City**]----[Saffron City]----[Lavender Town]\n"
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
