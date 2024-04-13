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
    private String currentState;
    private Graph graph;
    private Container container;
    private ViridianCity viridianCity;

    public PalletTown(Container container) throws FileNotFoundException {
        this.container = container;
        setBackground(Color.black);
        setLayout(new BorderLayout());
        setBackground(Color.black);
        setLayout(new BorderLayout());
        currentState = "name";

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

        // Moving to Viridian City
        viridianCity = new ViridianCity();

        // Moving to Cinnabar Island
        
         
        // Input Field
        inputField = new JTextField();
        inputField.setBackground(Color.LIGHT_GRAY);
        console.append("    [1] Move to: \n");
        for (Vertex vertex : graph.getAdjVertices(v1)) {
            console.append("        " + list++ + ". " + vertex.getName() + "\n");
        }
        console.append("    [2] Talk to Mom [Your Hometown has no Gym] \n"
                + "    [3] Fight Wild Pokémon \n" + "    [4] Player Options \n"
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
            currentState = "MoveTo";
            if (currentState.equals("MoveTo")) {
                if (input.equals("1a")) {
                    console.append("    Your choice: " + input + 
                            "\n  +---------------------------------------------------------------------+  \n");
                    moveToViridianCity();
                }
            } 
            inputField.setText(""); // Clear the input field
        });

        add(inputField, BorderLayout.SOUTH);
    }

    private void moveToViridianCity() {
        container.remove(this);
        container.add(viridianCity, BorderLayout.CENTER);

        // Revalidate and repaint container
        container.revalidate();
        container.repaint();
    }
}
