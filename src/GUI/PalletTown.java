/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import static GUI.GamePanel.*;
import static GUI.Graph.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class PalletTown extends JPanel {

    private JLabel label;
    private JTextArea console;
    private JTextField inputField;
    private JScrollPane scroll;
    private Container container;
    private ViridianCity viridianCity;
    private CinnabarIsland cinnabarIsland;

    public PalletTown(Container container) throws FileNotFoundException {
        this.container = container;
        this.viridianCity = viridianCity;
        this.cinnabarIsland = cinnabarIsland;
        setBackground(Color.black);
        setLayout(new BorderLayout());
        setBackground(Color.black);
        setLayout(new BorderLayout());

        // Adv Label
        label = new JLabel();
        label.setForeground(Color.CYAN);
        String advText = getASCII("PalletTown.txt");
        label.setText("<html><pre>" + advText + "</pre></html>");
        add(label, BorderLayout.NORTH);

        // Adv Console
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

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        graph.addEdge(v1, v2);
        graph.addEdge(v1, v3);

        
        // Input Field
        inputField = new JTextField();
        inputField.setBackground(Color.LIGHT_GRAY);
        console.append("    [1] Move to: \n");
        for (Vertex vertex : graph.getAdjVertices(v1)) {
            console.append("        " + list++ + ". " + vertex.getName() + "\n");
        }
        console.append("    [2] Talk to Mom [Your Hometown has no Gym] \n"
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
                    moveToViridianCity();
                    break;
                case "1b":
                    console.append("    Your choice: " + input
                            + "\n  +---------------------------------------------------------------------+  \n");
                    moveToCinnabarIsland();
                    break;
                case "2":
                    console.append("  +---------------------------------------------------------------------+  \n"
                            + "    Mom: amamamamam \n" //finish the dialogue
                            + "  +---------------------------------------------------------------------+  \n"
                    );
                    break;
                case "4a":
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
                            + " [**Pallet Town**]          |                                     |\n"
                            + "     |                      |                                     |\n"
                            + "     |            [Fuchsia City]----------------------------------|\n"
                            + "     |                      |\n"
                            + "     |                      |\n"
                            + "  [Cinnabar Island]---------|\n"
                            + "  +---------------------------------------------------------------------+  \n"
                    );
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Invalid command!");
            }

            inputField.setText(""); // Clear the input field
        });

        add(inputField, BorderLayout.SOUTH);
    }

    private void moveToViridianCity() {
        // Create a new instance of the ViridianCity panel
        ViridianCity viridianCityPanel;
        try {
            viridianCityPanel = new ViridianCity(container);
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Handle file not found exception
            return;
        }

        // Remove the current PalletTown panel from the container
        container.remove(this);

        // Add the ViridianCity panel to the container
        container.add(viridianCityPanel, BorderLayout.CENTER);

        // Revalidate and repaint the container to reflect the changes
        container.revalidate();
        container.repaint();
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
}
