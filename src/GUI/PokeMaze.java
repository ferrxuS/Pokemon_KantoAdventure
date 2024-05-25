/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Stack;
/**
 *
 * @author veronicca
 */

public class PokeMaze extends JPanel implements KeyListener {

    // Constants representing different characters in the maze
    private static final char WALL = '#';
    private static final char START = 'S';
    private static final char END = 'E';
    private static final char GHASTLY = 'G';
    private static final char PLAYER = 'Y';
    private static final char PATH = '.';

    // Maze layout 2D char array
    private static final char[][] maze = {
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'S', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.', '.', '#'},
        {'#', '.', '#', '.', '#', '.', '#', '#', '#', '#', '#', '.', '#', '#', '#', '#', '#', '#', '#', '.', '#'},
        {'#', '.', '#', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'G', '.', '#'},
        {'#', '.', '#', 'G', '#', '#', '#', '#', '#', '#', '#', '#', 'G', '#', '#', '#', '#', '.', '#', '.', '#'},
        {'#', '.', '#', '.', '.', '.', '.', '.', '#', '.', '.', '.', '#', '.', '#', '.', '.', '.', '.', '.', '#'},
        {'#', '.', '#', '#', '#', '.', '#', '#', '#', '.', '#', '.', '#', '.', '#', '.', '#', '#', '#', '.', '#'},
        {'#', '.', '.', '.', '.', '.', '#', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.', '.', '#', '.', '#'},
        {'#', '#', '#', '.', '#', '#', '#', '#', '#', '.', '#', '#', '#', '#', '#', '#', '#', '#', '#', '.', '#'},
        {'#', '.', '#', '.', '.', '.', '.', '.', '#', '.', '#', '.', '#', '.', '.', '.', '.', '.', '.', '.', '#'},
        {'#', '.', '#', '#', '#', '.', '#', '#', '#', '.', '#', '.', '#', '#', '#', '#', '#', '.', '#', '#', '#'},
        {'#', '.', '#', '.', '.', '.', '#', '.', '.', '.', 'G', '.', '.', '.', '.', '.', '#', '.', '.', '.', '#'},
        {'#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '#', '#', '.', '#', '#', '#', '.', '#'},
        {'#', '.', '.', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '.', '.', '.', '.', '#'},
        {'#', '.', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '.', '#', '#', '#', '.', '#', '#', '#'},
        {'#', '.', '.', '.', '#', '.', 'G', '.', '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'G', '.', '#'},
        {'#', '#', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '#', '#', '#', '#', '.', '#', '.', '#'},
        {'#', '.', '.', '.', '#', '.', '#', '.', '#', '.', '#', '.', 'G', '.', '.', '.', '.', '.', '#', '.', '#'},
        {'#', '.', '#', '#', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '#', '.', '#', '#', '.', '#'},
        {'#', '.', '.', '.', '.', '.', '#', '.', '.', '.', '#', '.', '#', '.', '#', '.', '.', '.', '.', '.', 'E'},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
    };

    private JLabel[][] mazeLabels;
    private final int[] playerPosition; //Player's current position
    final Stack<int[]> playerPath; //Stack to store the player's path

    //Setting for panel
    public PokeMaze() {
        setLayout(new GridLayout(maze.length, maze[0].length));
        setBackground(Color.BLACK);
        mazeLabels = new JLabel[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                char currentChar = maze[i][j];
                JLabel label = new JLabel(Character.toString(currentChar), SwingConstants.CENTER);
                label.setFont(new Font("Arial", Font.BOLD, 15));
                switch (currentChar) {
                    case 'S':
                        label.setForeground(Color.red);
                        break;
                    case 'E':
                        label.setForeground(Color.red);
                        break;
                    case '#':
                        label.setForeground(Color.green);
                        break;
                    case '.':
                        label.setForeground(Color.cyan);
                        break;
                    case 'G':
                        label.setForeground(Color.MAGENTA);
                        break;
                    default:
                        label.setForeground(Color.yellow);
                        break;
                }
                mazeLabels[i][j] = label; // Store the label in the array
                add(label); // Add the label to the panel
            }
        }

        setFocusable(true);
        addKeyListener(this);

        playerPosition = findStartPosition(); // Find and set player's initial position
        playerPath = new Stack<>(); // Initialize player's path stack
        updateMaze();
    }

    private void updateMaze() { // Update the maze to reflect the player's initial position
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (i == playerPosition[0] && j == playerPosition[1]) {
                    mazeLabels[i][j].setText(Character.toString(PLAYER)); // Set the label text to PLAYER character at the player's position
                } else {
                    mazeLabels[i][j].setText(Character.toString(maze[i][j])); // Set the label text to the corresponding maze character
                }
            }
        }
    }

    @Override
    public void move(int rowOffset, int colOffset) {
        int newRow = playerPosition[0] + rowOffset;
        int newCol = playerPosition[1] + colOffset;

        if (maze[newRow][newCol] != WALL) {
            if (!playerPath.isEmpty() && Arrays.equals(playerPath.peek(), new int[]{newRow, newCol})) {
                playerPath.pop();
            } else {
                playerPath.push(playerPosition.clone()); //Push current position to player's path stack
            }
            playerPosition[0] = newRow;
            playerPosition[1] = newCol;
            updateMaze();
        } else {
            System.out.println("Invalid move. Try again.");
        }
    }

    private int[] findStartPosition() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == START) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //No function
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //No function
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP:
                move(-1, 0);
                break;
            case KeyEvent.VK_DOWN:
                move(1, 0);
                break;
            case KeyEvent.VK_LEFT:
                if (playerPosition[1] > 0) {
                    move(0, -1);
                } else {
                    System.out.println("Invalid move. Try again.");
                }
                break;
            case KeyEvent.VK_RIGHT:
                move(0, 1);
                break;
        }

        if (maze[playerPosition[0]][playerPosition[1]] == GHASTLY) {
            System.out.println("Oh no! You encountered a Ghastly and got caught.\nGame Over.");
            showGameOverMessage();
            // Add the final ghastly position to the path
            playerPath.push(playerPosition.clone());
            printPlayerPath();

        } else if (maze[playerPosition[0]][playerPosition[1]] == END) {
            System.out.println("Congratulations! You've reached the end of the maze.");
            showCongratsMessage();
            // Add the final end position to the path
            playerPath.push(playerPosition.clone());
            printPlayerPath();

        }
    }

    private void showGameOverMessage() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);

        String message = "\n"
                + "                 ⠀⠀⠀⠀⠀⢸⠓⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "                 ⠀⠀⠀⠀⠀⢸⠀⠀⠑⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "                 ⠀⠀⠀⠀⠀⢸⡆⠀⠀⠀⠙⢤⡷⣤⣦⣀⠤⠖⠚⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "                 ⣠⡿⠢⢄⡀⠀⡇⠀⠀⠀⠀⠀⠉⠀⠀⠀⠀⠀⠸⠷⣶⠂⠀⠀⠀⣀⣀⠀⠀⠀\n"
                + "                 ⢸⣃⠀⠀⠉⠳⣷⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠉⠉⠉⠉⢉⡭⠋\n"
                + "                 ⠀⠘⣆⠀⠀⠀⠁⠀⢀⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠋⠀⠀\n"
                + "                 ⠀⠀⠘⣦⠆⠀⠀⢀⡎⢹⡀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⡀⣠⠔⠋⠀⠀⠀⠀\n"
                + "                 ⠀⠀⠀⡏⠀⠀⣆⠘⣄⠸⢧⠀⠀⠀⠀⢀⣠⠖⢻⠀⠀⠀⣿⢥⣄⣀⣀⣀⠀⠀\n"
                + "                 ⠀⠀⢸⠁⠀⠀⡏⢣⣌⠙⠚⠀⠀⠠⣖⡛⠀⣠⠏⠀⠀⠀⠇⠀⠀⠀⠀⢙⣣⠄\n"
                + "            ⠀      ⢸⡀⠀⠀⠳⡞⠈⢻⠶⠤⣄⣀⣈⣉⣉⣡⡔⠀⠀⢀⠀⠀⣀⡤⠖⠚⠀⠀\n"
                + "                 ⠀⠀⡼⣇⠀⠀⠀⠙⠦⣞⡀⠀⢀⡏⠀⢸⣣⠞⠀⠀⠀⡼⠚⠋⠁⠀⠀⠀⠀⠀\n"
                + "                 ⠀⢰⡇⠙⠀⠀⠀⠀⠀⠀⠉⠙⠚⠒⠚⠉⠀⠀⠀⠀⡼⠁⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "           ⠀      ⠀⢧⡀⠀⢠⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "                 ⠀⠀⠀⠙⣶⣶⣿⠢⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "           ⠀      ⠀⠀⠀⠀⠉⠀⠀⠀⠙⢿⣳⠞⠳⡄⠀⠀⠀⢀⡞⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "           ⠀⠀⠀⠀⠀⠀      ⠀⠀⠀⠀⠀⠉⠀⠀⠹⣄⣀⡤⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "\n          Oh no! You encountered a Ghastly and got caught\n"
                + "        ____    _    __  __ _____    _____     _______ ____  \n"
                + "       / ___|  / \\  |  \\/  | ____|  / _ \\ \\   / / ____|  _ \\ \n"
                + "      | |  _  / _ \\ | |\\/| |  _|   | | | \\ \\ / /|  _| | |_) |\n"
                + "      | |_| |/ ___ \\| |  | | |___  | |_| |\\ V / | |___|  _ < \n"
                + "       \\____/_/   \\_\\_|  |_|_____|  \\___/  \\_/  |_____|_| \\_\\\n";

        JTextArea textArea = new JTextArea(message);
        textArea.setFont(new Font("Monospaced", Font.BOLD, 14));
        textArea.setForeground(Color.MAGENTA);
        textArea.setBackground(Color.BLACK);
        textArea.setEditable(false);

        // Add button
        JButton tryAgainButton = new JButton("Try Again");
        tryAgainButton.addActionListener(e -> {
            System.out.println("Try Again is pressed");
            frame.dispose();
            initializeGame();

        });
        tryAgainButton.setForeground(Color.GREEN);
        tryAgainButton.setBackground(Color.BLACK);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            System.out.println("Exit is pressed");
            frame.dispose();
        });
        exitButton.setForeground(Color.RED);
        exitButton.setBackground(Color.BLACK);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(tryAgainButton);
        buttonPanel.add(exitButton);

        panel.add(textArea, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void showCongratsMessage() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);

        String message = "\n"
                + "                 ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣠⣤⣤⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "                 ⠀⠀⠀⠀⠀⠀⢀⣤⡶⠟⠛⠉⠉⠉⠉⠉⠙⠻⣷⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀                 ⠀⠀⠀⠀⣰⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣿⣷⡄⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀           ⠀    ⠀  ⠀⢰⣿⠀⠀⠀⠀⣠⣴⢶⣦⠀⠀⠀⠀⠀⢸⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀          ⠀       ⠀⠀⢸⣿⠀⠀⢀⣼⣟⣁⣀⣀⣀⠀⠀⠀⠀⢸⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀                 ⠀⠀⠀⢸⣿⠀⠀⠙⠛⠛⠛⠛⠛⠋⠀⠀⠀⠀⢸⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀     ⠀            ⠀⠀⣸⣿⣶⣶⣾⣿⣿⣿⣿⣶⣶⣤⣤⣀⣀⣸⣿⣿⣿⡀⠀⠀⠀⠀⠀⠀\n"
                + "             ⠀    ⣰⣾⣿⣿⣿⡿⠿⠿⠿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢿⣿⣿⣿⣿⡷⠆⠀⠀⠀⠀\n"
                + "               ⠀  ⠈⠉⣩⣿⣿⡄⠀⠀⠀⢸⡿⠟⠋⠉⠉⠉⠀⠀⢸⣿⣿⣿⣿⣦⡀⠀⠀⠀⠀\n"
                + "⠀                 ⠀⠘⣿⣿⣿⡇⣾⠟⣷⡄⠀⠀⠀⣾⠛⣷⡀⠀⠛⠋⣿⣿⣿⣿⣿⣦⡀⠀⠀\n"
                + "                 ⠀⢀⣴⣿⣿⣿⡇⣿⣦⣿⡇⠀⠀⠀⣿⣆⣿⡇⠀⠀⢀⣿⣿⣿⣿⣿⣿⣿⠗⠀\n"
                + "                 ⠀⠙⠻⢿⣿⣿⠁⠛⠛⠛⠁⠀⠀⠀⠛⠛⠛⠀⠀⣤⣾⣿⣿⣿⣯⠉⠀⠀⠀⠀\n"
                + "⠀⠀                 ⠀⠀⠈⣿⡀⠀⠀⢾⣿⣿⣿⣿⣿⠀⠀⠀⠀⢸⣿⣿⠿⠿⠛⠃⠀⠀⠀⠀\n"
                + "⠀            ⠀ ⠀⠀    ⠀⠙⢷⣦⣄⡈⠛⠿⠿⠟⢁⣀⣤⣴⡶⠿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀                  ⠀⠀⠀⠀⠈⠙⠻⢷⣶⣶⠾⠛⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "\n        Congratulations! You've reached the end of the maze\n"
                + "           ____ ___  _   _  ____ ____      _  _____ ____  \n"
                + "          / ___/ _ \\| \\ | |/ ___|  _ \\    / \\|_   _/ ___| \n"
                + "         | |  | | | |  \\| | |  _| |_) |  / _ \\ | | \\___ \\ \n"
                + "         | |__| |_| | |\\  | |_| |  _ <  / ___ \\| |  ___) |\n"
                + "          \\____\\___/|_| \\_|\\____|_| \\_\\/_/   \\_\\_| |____/ ";

        JTextArea textArea = new JTextArea(message);
        textArea.setFont(new Font("Monospaced", Font.BOLD, 14));
        textArea.setForeground(Color.GREEN);
        textArea.setBackground(Color.BLACK);
        textArea.setEditable(false);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            System.out.println("Exit is pressed");
            frame.dispose();
        });
        exitButton.setForeground(Color.GREEN);
        exitButton.setBackground(Color.BLACK);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(exitButton);

        panel.add(textArea, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void printPlayerPath() {
        System.out.println("Player's path:");
        for (int[] position : playerPath) {
            System.out.println("(" + position[0] + ", " + position[1] + ")");
        }
    }

    // Create PokeMaze object and call this method in Lavendertown to run this class
    public void initializeGame() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("PokeMaze");
            PokeMaze mazeGame = new PokeMaze();
            frame.getContentPane().add(mazeGame);
            frame.setSize(600, 600);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    //tester
//    public static void main(String[] args) {
//        PokeMaze maze = new PokeMaze();
//        maze.initializeGame();
//    }
}
