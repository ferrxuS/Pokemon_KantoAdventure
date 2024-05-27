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
import static GUI.GamePanel.*;
import static GUI.Graph.*;
import PokemonBattle_LevelUp.*;

public class LavenderTown extends JPanel {

    private JLabel label;
    private JTextArea console;
    private JTextField inputField;
    private JScrollPane scroll;
    private Container container;
    private Trainer trainer;
    private Location location;

    public LavenderTown(Container container, Trainer trainer, Location location) throws FileNotFoundException {
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
        String advText = getASCII("LavenderTown.txt");
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

        graph.addVertex(v4);
        graph.addVertex(v6);
        graph.addVertex(v10);
        graph.addVertex(v7);
        graph.addVertex(v9);

        graph.addEdge(v4, v6);
        graph.addEdge(v4, v10);
        graph.addEdge(v4, v7);
        graph.addEdge(v4, v9);

        // Input Field
        inputField = new JTextField();
        inputField.setBackground(Color.LIGHT_GRAY);
        console.append("    [1] Move to: \n");
        for (Vertex vertex : graph.getAdjVertices(v4)) {
            console.append("        " + list++ + ". " + vertex.getName() + "\n");
        }
        console.append("    [2] Pokémaze \n"
                + "    [3] Player Options \n"
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
                    moveToCeruleanCity();
                    break;
                case "1b":
                    console.append("    Your choice: " + input
                            + "\n  +---------------------------------------------------------------------+  \n");
                    moveToSaffronCity();
                    break;
                case "1c":
                    console.append("    Your choice: " + input
                            + "\n  +---------------------------------------------------------------------+  \n");
                    moveToVermillionCity();
                    break;
                case "1d":
                    console.append("    Your choice: " + input
                            + "\n  +---------------------------------------------------------------------+  \n");
                    moveToFuchsiaCity();
                    break;
                case "2":
                    console.append("  +---------------------------------------------------------------------+  \n");
                    PokeMaze pokeMaze = new PokeMaze();
                    pokeMaze.initializeGame();
                    break;
                case "3a":
                    showMap();
                    break;
                case "3b":
                    showMyPokemon();
                    break;
                case "3c":
                    showMyBadges();
                    break;
                case "3d":
                    saveAndExit();
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Invalid command!");
                    inputField.setText("");
                    break; // Add break statement here
            }

            inputField.setText(""); // Clear the input field
        });

        add(inputField, BorderLayout.SOUTH);
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

    private void moveToVermillionCity() {
        Location vermilionCityLocation = new Location(Location.VERMILLION_CITY);
        VermillionCity vermilionCityPanel;
        try {
            vermilionCityPanel = new VermillionCity(container, trainer, vermilionCityLocation);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        container.remove(this);
        container.add(vermilionCityPanel, BorderLayout.CENTER);

        // Revalidate and repaint container
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

        // Revalidate and repaint container
        container.revalidate();
        container.repaint();
    }

    private void showMap() {
        console.append("  +---------------------------------------------------------------------+  \n"
                + "  [Pewter City]---------------------[Cerulean City]---------------|\n"
                + "     |                                     |                      |\n"
                + "     |                                     |                      |\n"
                + "     |                                     |                      |\n"
                + "     |                                     |                      |\n"
                + "     |            [Celadon City]----[Saffron City]----[**Lavender Town**]\n"
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
