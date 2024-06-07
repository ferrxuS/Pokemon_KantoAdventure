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

public class FuchsiaCity extends JPanel {

    private JLabel label;
    private JTextArea console;
    private JTextField inputField;
    private JScrollPane scroll;
    private Container container;
    private CinnabarIsland cinnabarIsland;
    private VermillionCity vermillionCity;
    private CeladonCity celadonCity;
    private LavenderTown lavenderTown;
    private Trainer trainer;
    private Location location;
    private ActionListener defaultListener;

    public FuchsiaCity(Container container, Trainer trainer, Location location) throws FileNotFoundException {
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
        String advText = getASCII("FuchsiaCity.txt");
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

        graph.addVertex(v9);
        graph.addVertex(v3);
        graph.addVertex(v7);
        graph.addVertex(v8);
        graph.addVertex(v4);

        graph.addEdge(v9, v3);
        graph.addEdge(v9, v7);
        graph.addEdge(v9, v8);
        graph.addEdge(v9, v4);

        // Input Field
        inputField = new JTextField();
        inputField.setBackground(Color.LIGHT_GRAY);
        console.append("    [1] Move to: \n");
        for (Vertex vertex : graph.getAdjVertices(v9)) {
            console.append("        " + list++ + ". " + vertex.getName() + "\n");
        }
        console.append("    [2] Challenge Gym Leader [Koga - Poison Type] \n"
                + "    [3] Fight Wild Pokemon \n"
                + "    [4] Player Options \n"
                + "        a. Show Map        b. Show My Pokémon \n"
                + "        c. Show My Badges        d. Save and Exit \n"
                + "    [5] Safari Zone \n"
                + "  +---------------------------------------------------------------------+  \n");

        // Define the default action listener
        defaultListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                if (input.isEmpty()) {
                    return;
                }
                console.append("> " + input + "\n");
                switch (input) {
                    case "1a":
                        console.append(
                                "    +---------------------------------------------------------------------+  \n");
                        moveToCinnabarIsland();
                        break;
                    case "1b":
                        console.append(
                                "    +---------------------------------------------------------------------+  \n");
                        moveToVermillionCity();
                        break;
                    case "1c":
                        console.append(
                                "    +---------------------------------------------------------------------+  \n");
                        moveToCeladonCity();
                        break;
                    case "1d":
                        console.append(
                                "    +---------------------------------------------------------------------+  \n");
                        moveToLavenderTown();
                        break;
                    case "2":
                        startChallengeGymLeader();
                        break;
                    case "3":
                        startFightWildPokemon();
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
                        GamePanel.saveAndExit(container, trainer);
                        break;
                    case "5":
                        console.append("  +---------------------------------------------------------------------+  \n");
                        inputField.setText("");
                        console.append("    Welcome to the Safari Zone! Today's challenge: Sort the Pokemon!\n"
                                + "  +---------------------------------------------------------------------+  \n");
                        console.append("    Enter the Pokemon in your party (separated by a comma): \n"
                                + "  +---------------------------------------------------------------------+  \n");
                        // Remove the default action listener
                        inputField.removeActionListener(this);
                        // Add action listener for sorting Pokémon
                        ActionListener pokemonSortingListener = new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent f) {
                                String pokemonList = inputField.getText();
                                console.append("    You entered: " + pokemonList + "\n");
                                console.append(SafariZone.result(pokemonList));
                                // Clear the input field after Safari Zone runs
                                inputField.setText("");
                                // Remove the sorting listener and add back the default listener
                                inputField.removeActionListener(this);
                                inputField.addActionListener(defaultListener);
                            }
                        };
                        inputField.addActionListener(pokemonSortingListener);
                        break;
                    default:
                        inputField.setText("");
                        break;
                }

                inputField.setText(""); // Clear the input field
            }
        };

        // Add the default action listener to the input field
        inputField.addActionListener(defaultListener);

        add(inputField, BorderLayout.SOUTH);
    }

    private void moveToCinnabarIsland() {
        Location cinnabarIslandLocation = new Location(Location.CINNABAR_ISLAND);
        CinnabarIsland cinnabarIslandPanel;
        try {
            cinnabarIslandPanel = new CinnabarIsland(container, trainer, cinnabarIslandLocation);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        container.remove(this);
        container.add(cinnabarIslandPanel, BorderLayout.CENTER);

        // Revalidate and repaint container
        container.revalidate();
        container.repaint();
    }

    private void moveToVermillionCity() {
        Location vermillionCityLocation = new Location(Location.VERMILLION_CITY);
        VermillionCity vermillionCityPanel;
        try {
            vermillionCityPanel = new VermillionCity(container, trainer, vermillionCityLocation);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        container.remove(this);
        container.add(vermillionCityPanel, BorderLayout.CENTER);

        // Revalidate and repaint container
        container.revalidate();
        container.repaint();
    }

    private void moveToCeladonCity() {
        Location celadonCityLocation = new Location(Location.CELADON_CITY);
        CeladonCity celadonCityPanel;
        try {
            celadonCityPanel = new CeladonCity(container, trainer, celadonCityLocation);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        container.remove(this);
        container.add(celadonCityPanel, BorderLayout.CENTER);

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
                + "  [Pewter City]---------------------[Cerulean City]---------------|\n"
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
                + "     |            [**Fuchsia City**]------------------------------|\n"
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

    // private void saveAndExit() {
    // // Implement save and exit logic here
    // System.exit(0);
    // Main.saveGame(trainer);
    // }
}
