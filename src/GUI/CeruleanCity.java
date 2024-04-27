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

public class CeruleanCity extends JPanel {

    private JLabel label;
    private JTextArea console;
    private JTextField inputField;
    private JScrollPane scroll;
    private Container container;
    private PewterCity pewterCity;
    private SaffronCity saffronCity;
    private LavenderTown lavenderTown;

    public CeruleanCity(Container container) throws FileNotFoundException {
        this.container = container;
        this.pewterCity = pewterCity;
        this.saffronCity = saffronCity;
        this.lavenderTown = lavenderTown;
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
                    moveToPewterCity();
                    break;
                case "1b":
                    console.append("    Your choice: " + input
                            + "\n  +---------------------------------------------------------------------+  \n");
                    moveToSaffronCity();
                    break;
                case "1c":
                    console.append("    Your choice: " + input
                            + "\n  +---------------------------------------------------------------------+  \n");
                    moveToLavenderTown();
                case "4a":
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
                            + "  +---------------------------------------------------------------------+  \n"
                    );
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Invalid command!");
                    break; // Add break statement here
            }

            inputField.setText(""); // Clear the input field
        });

        add(inputField, BorderLayout.SOUTH);
    }

    private void moveToPewterCity() {
        
        PewterCity pewterCityPanel;
        try {
            pewterCityPanel = new PewterCity(container);
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

        SaffronCity saffronCityPanel;
        try {
            saffronCityPanel = new SaffronCity(container);
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
