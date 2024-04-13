/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import static GUI.GamePanel.*;

public class ViridianCity extends JPanel{

    private final JLabel label;
    private final JTextArea console;
    private JTextField inputField;
    private final JScrollPane scroll;
    private String currentState;

    public ViridianCity() throws FileNotFoundException {
        setBackground(Color.black);
        setLayout(new BorderLayout());
        currentState = "name";

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

        // Matching the Font with the ASCII Text Files
        Font consoleFont = new Font("Monospaced", Font.PLAIN, 14);
        console.setFont(consoleFont);

        scroll = new JScrollPane(console);
        scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(scroll, BorderLayout.CENTER);
    }
}