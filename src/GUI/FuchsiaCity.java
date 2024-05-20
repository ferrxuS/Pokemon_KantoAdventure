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
import static GUI.GamePanel.*;
import static GUI.Graph.*;
import java.io.FileNotFoundException;

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
    private SafariZone safarizone;

    public FuchsiaCity(Container container) throws FileNotFoundException {
        this.container = container;
        this.cinnabarIsland = cinnabarIsland;
        this.vermillionCity = vermillionCity;
        this.celadonCity = celadonCity;
        this.lavenderTown = lavenderTown;
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
                + "  +---------------------------------------------------------------------+  \n"
        );

        inputField.addActionListener((ActionEvent e) -> {
            String input = inputField.getText();
            if (input.isEmpty()) {
                return;
            }
            switch (input) {
                case "1a":
                    console.append("> " + input + "\n");
                    console.append("    Your choice: " + input
                            + "\n  +---------------------------------------------------------------------+  \n");
                    moveToCinnabarIsland();
                    break;
                case "1b":
                    console.append("> " + input + "\n");
                    console.append("    Your choice: " + input
                            + "\n  +---------------------------------------------------------------------+  \n");
                    moveToVermillionCity();
                    break;
                case "1c":
                    console.append("> " + input + "\n");
                    console.append("    Your choice: " + input
                            + "\n  +---------------------------------------------------------------------+  \n");
                    moveToCeladonCity();
                    break;
                case "1d":
                    console.append("> " + input + "\n");
                    console.append("    Your choice: " + input
                            + "\n  +---------------------------------------------------------------------+  \n");
                    moveToLavenderTown();
                    break;
                case "4a":
                    console.append("> " + input + "\n");
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
                            + "  +---------------------------------------------------------------------+  \n"
                    );
                    break;
                case "5":
                    console.append("> " + input + "\n");
                    console.append("    Your choice: " + input
                            + "\n  +---------------------------------------------------------------------+  \n");
                    inputField.setText("");
                    console.append("    Welcome to the Safari Zone! Today's challenge: Sort the Pokemon!\n  +---------------------------------------------------------------------+  \n");
                    console.append("    Enter the Pokemon in your party (seperated by a comma): \n");
                    inputField.addActionListener((ActionEvent f) -> {
                        String pokemonList = inputField.getText();
                        console.append("\n    You entered: " + pokemonList);
                        console.append(SafariZone.result(pokemonList));
                    });
                    break;
            }

            inputField.setText(""); // Clear the input field
        });

        add(inputField, BorderLayout.SOUTH);
    }

    private void moveToCinnabarIsland() {

        CinnabarIsland cinnabarIslandPanel;
        try {
            cinnabarIslandPanel = new CinnabarIsland(container);
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

        VermillionCity vermillionCityPanel;
        try {
            vermillionCityPanel = new VermillionCity(container);
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

        CeladonCity celadonCityPanel;
        try {
            celadonCityPanel = new CeladonCity(container);
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

        LavenderTown lavenderTownPanel;
        try {
            lavenderTownPanel = new LavenderTown(container);
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

}
