package sql_implementation;
// import java.awt.BorderLayout;

// import java.awt.Dimension;
// import java.awt.FlowLayout;
// import java.awt.GridBagConstraints;
// import java.awt.GridBagLayout;
// import java.awt.Insets;

// import javax.swing.BorderFactory;
// import javax.swing.ImageIcon;
// import javax.swing.JButton;
// import javax.swing.JDialog;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JOptionPane;
// import javax.swing.JPanel;
// import javax.swing.JPasswordField;
// import javax.swing.JTextField;
// import javax.swing.SwingUtilities;

import java.awt.*;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import GUI.GamePanel;
import Trainer.Trainer;
import PokemonBattle_LevelUp.Location;
import pokemons.Pokemon;
import pokemons.Evolution;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    private static User loggedInUser;

    // public static Trainer trainer = new Trainer();
    public static void main(String[] args) {

        SwingUtilities.invokeLater(Main::showUserAuthenticationWindow);

    }

    public static void initializeMainFrame(JFrame thisFrame, String title) {
        thisFrame.setTitle(title);
        thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisFrame.setSize(800, 450);

        thisFrame.setLocationRelativeTo(null);
        // thisFrame.setVisible(true);
    }

    private static void showUserAuthenticationWindow() {
        JFrame frame = new JFrame();
        initializeMainFrame(frame, "User Autentication");
        // frame = new JFrame("User Authentication");
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(800, 450);

        //
        // trainer = null;

        // Adding background image
        ImageIcon background = new ImageIcon(new ImageIcon("background1.jpg").getImage()
                .getScaledInstance(frame.getWidth(), frame.getHeight(), java.awt.Image.SCALE_SMOOTH));
        JLabel backgroundLabel = new JLabel(background);
        frame.setContentPane(backgroundLabel);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 45, 30, 45);

        // Add company logo
        ImageIcon companyLogo = new ImageIcon(
                new ImageIcon("MewFiveLogo.png").getImage().getScaledInstance(250, 193, java.awt.Image.SCALE_SMOOTH));
        JLabel logoLabel = new JLabel(companyLogo);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(logoLabel, constraints);

        // Add Pokémon-themed graphics to buttons
        ImageIcon loginIcon = new ImageIcon(
                new ImageIcon("pokeBall.png").getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
        ImageIcon registerIcon = new ImageIcon(
                new ImageIcon("pokemonTrainer.png").getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
        ImageIcon exitIcon = new ImageIcon(
                new ImageIcon("doorIcon.jpg").getImage().getScaledInstance(20, 30, java.awt.Image.SCALE_SMOOTH));
        JButton loginButton = new JButton(" Login", loginIcon);
        JButton registerButton = new JButton(" Register", registerIcon);
        JButton exitButton = new JButton(" Exit Game", exitIcon);

        // Button Text Fonts
        Font buttonFont = new Font("Monospaced", Font.BOLD, 30);
        loginButton.setFont(buttonFont);
        registerButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);
        // Button Colours
        loginButton.setBackground(new Color(255, 223, 0)); // Yellow
        loginButton.setForeground(Color.BLUE);
        registerButton.setBackground(new Color(0, 162, 232)); // Blue
        registerButton.setForeground(Color.YELLOW);
        exitButton.setBackground(new Color(237, 28, 36)); // Red
        exitButton.setForeground(Color.WHITE);

        // Setting button size
        Dimension buttonSize = new Dimension(240, 50);
        loginButton.setMinimumSize(buttonSize);
        registerButton.setMinimumSize(buttonSize);
        exitButton.setMinimumSize(buttonSize);

        constraints.insets = new Insets(0, 30, 30, 30);
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        constraints.gridx = 0;
        panel.add(loginButton, constraints);

        constraints.gridx = 1;
        panel.add(registerButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(0, 10, 50, 10);
        JPanel exitPanel = new JPanel();
        exitPanel.add(exitButton);
        exitPanel.setOpaque(false);
        panel.add(exitPanel, constraints);

        loginButton.addActionListener(e -> showLoginDialog(frame));
        registerButton.addActionListener(e -> showRegisterDialog(frame));
        exitButton.addActionListener(e -> frame.dispose());

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        // frame.setResizable(false);
        frame.setVisible(true);
    }

    private static void showLoginDialog(JFrame parentFrame) {
        JDialog loginDialog = new JDialog(parentFrame, "Pokémon Center - Login", true);
        loginDialog.setLayout(new GridBagLayout());
        loginDialog.setSize(400, 230);
        loginDialog.getContentPane().setBackground(new Color(255, 223, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titleLabel = new JLabel("Welcome to the Pokémon Center!");
        titleLabel.setFont(new Font("DialogInput", Font.BOLD, 20));
        titleLabel.setBackground(new Color(255, 223, 0)); // new Color(34, 139, 34)
        titleLabel.setOpaque(true);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        titleLabel.setForeground(Color.BLUE); // Color.WHITE
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(new Color(255, 223, 0));
        titlePanel.add(titleLabel);

        Font promptFont = new Font("Helvitca", Font.BOLD, 15);
        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(promptFont);
        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(promptFont);
        JTextField userText = new JTextField();
        JPasswordField passText = new JPasswordField();
        JButton loginButton = new JButton("Login");

        // Custom button design
        loginButton.setBackground(new Color(34, 139, 34));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        loginButton.setFont(new Font("DialogInput", Font.BOLD, 20));

        loginButton.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passText.getPassword());
            UserAuthentication auth = new UserAuthentication();
            loggedInUser = auth.loginUser(username, password);
            if (loggedInUser != null) {
                JOptionPane.showMessageDialog(loginDialog, "Login successful!");
                loginDialog.dispose();
                // parentFrame.dispose();
                showWelcomeWindow(parentFrame);
            } else {
                JOptionPane.showMessageDialog(loginDialog, "Invalid username or password.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        gbc.insets = new Insets(0, 0, 15, 0);
        loginDialog.add(titlePanel, gbc);
        gbc.insets = new Insets(5, 25, 5, 0);
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginDialog.add(userLabel, gbc);
        gbc.gridx++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 20, 5, 25);
        loginDialog.add(userText, gbc);
        gbc.insets = new Insets(5, 25, 5, 0);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginDialog.add(passLabel, gbc);
        gbc.insets = new Insets(5, 20, 5, 25);
        gbc.gridx++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        loginDialog.add(passText, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 0, 0, 0);
        loginButton.setPreferredSize(new Dimension(85, 35));
        gbc.fill = GridBagConstraints.NONE;
        loginDialog.add(loginButton, gbc);

        loginDialog.setLocationRelativeTo(null);
        loginDialog.setVisible(true);
    }

    private static void showRegisterDialog(JFrame parentFrame) {
        JDialog registerDialog = new JDialog(parentFrame, "Pokémon Center - Register", true);
        registerDialog.setLayout(new GridBagLayout());
        registerDialog.setSize(400, 230);
        registerDialog.getContentPane().setBackground(new Color(0, 162, 232));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titleLabel = new JLabel("      Join the Adventure!     ");
        titleLabel.setFont(new Font("DialogInput", Font.BOLD, 20));
        titleLabel.setBackground(new Color(0, 162, 232)); // new Color(34, 139, 34)
        titleLabel.setOpaque(true);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        titleLabel.setForeground(Color.YELLOW); // Color.WHITE
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(new Color(0, 162, 232));
        titlePanel.add(titleLabel);

        Font promptFont = new Font("Helvetica", Font.BOLD, 15);
        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(promptFont);
        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(promptFont);
        JTextField userText = new JTextField();
        JPasswordField passText = new JPasswordField();
        JButton registerButton = new JButton("Register");

        // Custom button design
        registerButton.setBackground(new Color(34, 139, 34));
        registerButton.setForeground(Color.WHITE);
        registerButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        registerButton.setFont(new Font("DialogInput", Font.BOLD, 20));

        registerButton.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passText.getPassword());
            if (username.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(registerDialog, "Username or password cannot be blank",
                        "Empty Field Error", JOptionPane.ERROR_MESSAGE);
            } else {
                UserAuthentication auth = new UserAuthentication();
                if (auth.registerUser(username, password)) {
                    JOptionPane.showMessageDialog(registerDialog, "Registration successful!");
                    registerDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(registerDialog, "Registration failed. Username might already exist.",
                            "Duplicate Username Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        gbc.insets = new Insets(0, 0, 15, 0);
        registerDialog.add(titlePanel, gbc);
        gbc.insets = new Insets(5, 25, 5, 0);
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_START;
        registerDialog.add(userLabel, gbc);
        gbc.gridx++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 20, 5, 25);
        registerDialog.add(userText, gbc);
        gbc.insets = new Insets(5, 25, 5, 0);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_START;
        registerDialog.add(passLabel, gbc);
        gbc.insets = new Insets(5, 20, 5, 25);
        gbc.gridx++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        registerDialog.add(passText, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 0, 0, 0);
        registerButton.setPreferredSize(new Dimension(131, 35));
        gbc.fill = GridBagConstraints.NONE;
        registerDialog.add(registerButton, gbc);

        registerDialog.setLocationRelativeTo(null);
        registerDialog.setVisible(true);
    }

    private static void showWelcomeWindow(JFrame frame) {
        // JFrame welcomeFrame = new JFrame("Welcome Window");
        JFrame welcomeFrame = frame;
        welcomeFrame.setTitle("Welcome Window");
        // welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // welcomeFrame.setSize(800, 450);
        // welcomeFrame.setLayout(new BorderLayout());

        // Background Image
        ImageIcon backgroundIcon = new ImageIcon("background9.jpg");
        Image image = backgroundIcon.getImage().getScaledInstance(welcomeFrame.getWidth(), welcomeFrame.getHeight(),
                Image.SCALE_SMOOTH);
        backgroundIcon = new ImageIcon(image);
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new GridBagLayout()); // Use GridBagLayout to center components
        welcomeFrame.setContentPane(backgroundLabel);

        // Welcome Label
        JLabel welcomeLabel = new JLabel();
        welcomeLabel.setFont(new Font("DialogInput", Font.BOLD, 50));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 0); // Initial position at the center
        backgroundLabel.add(welcomeLabel, gbc);

        // Create a JPanel for the button
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setOpaque(false); // Make the panel transparent
        // Create the "Play Game" button
        JButton playButton = new JButton("Play Game");
        playButton.setFont(new Font("Monospaced", Font.BOLD, 30)); // Set the font of the button text
        playButton.setForeground(Color.WHITE); // Set the text color to white

        // Add a hover effect to the button
        playButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                playButton.setForeground(Color.YELLOW); // Change text color on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                playButton.setForeground(Color.WHITE); // Reset text color on exit
            }
        });

        // Add a click effect to the button
        playButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                playButton.setForeground(new Color(255, 223, 0)); // Change text color when pressed
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                playButton.setForeground(Color.WHITE); // Reset text color when released
            }
        });

        // Make the button transparent
        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        playButton.setForeground(new Color(255, 255, 255, 0)); // Set initial transparency to 0

        // Create a Timer for the fade-in animation
        Timer fadeInTimer = new Timer(30, new ActionListener() {
            private int alpha = 0; // Initial alpha value
            private final int targetAlpha = 255; // Target alpha value (fully opaque)

            @Override
            public void actionPerformed(ActionEvent e) {
                alpha += 5; // Increase alpha by a small amount
                if (alpha >= targetAlpha) {
                    alpha = targetAlpha; // Ensure we reach the target alpha
                    ((Timer) e.getSource()).stop(); // Stop the timer
                }
                playButton.setForeground(new Color(255, 255, 255, alpha)); // Update button color
            }
        });

        // Start the fade-in animation when the welcome label finishes blinking
        Timer blinkAnimation = new Timer(450, new ActionListener() {
            private boolean isVisible = true;
            private int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                isVisible = !isVisible;
                welcomeLabel.setForeground(new Color(255, 255, 255, isVisible ? 255 : 0));
                count++;
                if (count >= 2) { // Stop after two iterations (fade out and fade in)
                    ((Timer) e.getSource()).stop();
                    // Start the fade-in animation and show the button
                    fadeInTimer.start();
                    playButton.setVisible(true);
                    welcomeLabel.setForeground(new Color(255, 223, 0)); // Change the text color to yellow
                } else {
                    // Move the welcome label up by 10 pixels
                    gbc.insets = new Insets(-10 * count, 0, 0, 0);
                    backgroundLabel.remove(welcomeLabel); // Remove the label
                    backgroundLabel.add(welcomeLabel, gbc); // Add the label with updated constraints
                    backgroundLabel.revalidate(); // Refresh the layout
                }
            }
        });

        // Animate the welcome message
        Timer timer = new Timer(60, new ActionListener() {
            private int charIndex = 0;
            private String fullText = "Welcome Back, Adventurer!";

            @Override
            public void actionPerformed(ActionEvent e) {
                charIndex++; // Reveal one more character
                if (charIndex > fullText.length()) {
                    charIndex = fullText.length();
                    ((Timer) e.getSource()).stop();
                    // Start the blinking animation after the text appears
                    blinkAnimation.start();
                }
                welcomeLabel.setText(fullText.substring(0, charIndex));
                welcomeLabel.setForeground(new Color(255, 223, 0)); // Change the text color to yellow
            }
        });

        timer.start();

        // Create a Timer for the move-up animation
        Timer moveUpTimer = new Timer(30, new ActionListener() {
            private int moveAmount = 10; // Amount to move the label up
            private int moveCount = 0; // Counter for the number of moves

            @Override
            public void actionPerformed(ActionEvent e) {
                moveCount++;
                gbc.insets = new Insets(-moveAmount * moveCount, 0, 0, 0); // Incrementally move up
                backgroundLabel.remove(welcomeLabel); // Remove the label
                backgroundLabel.add(welcomeLabel, gbc); // Add the label with updated constraints
                backgroundLabel.revalidate(); // Refresh the layout

                if (moveCount >= 5) { // Stop after moving up 5 times
                    ((Timer) e.getSource()).stop();
                }
            }
        });

        // Start the move-up animation when the blink animation is complete
        blinkAnimation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveUpTimer.start();
            }
        });

        // Add an action listener to the button
        playButton.addActionListener(e -> {
            // welcomeFrame.dispose();
            showGameMenuWindow(welcomeFrame);
        });

        // Initially hide the button
        playButton.setVisible(false);

        // Add the button to the JPanel
        buttonPanel.add(playButton, BorderLayout.CENTER);

        // Add the JPanel to the background label
        gbc.insets = new Insets(30, 0, -50, 0);
        gbc.gridy = 1; // Position the panel below the welcome label
        backgroundLabel.add(buttonPanel, gbc);

        // Make the frame visible
        welcomeFrame.setResizable(false);
        // welcomeFrame.setLocationRelativeTo(null);
        welcomeFrame.setVisible(true);
    }

    private static void showGameMenuWindow(JFrame frame) {
        JFrame gameMenuFrame = frame;
        // JFrame gameMenuFrame = new JFrame("Game Menu");
        // gameMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // gameMenuFrame.setSize(800, 450);
        gameMenuFrame.setTitle("Game Menu");
        // Background Image
        ImageIcon background = new ImageIcon(new ImageIcon("background10.jpg").getImage()
                .getScaledInstance(gameMenuFrame.getWidth(), gameMenuFrame.getHeight(), Image.SCALE_SMOOTH));
        JLabel backgroundLabel = new JLabel(background);
        gameMenuFrame.setContentPane(backgroundLabel); // Set the background image as content

        // Set layout manager to BorderLayout
        gameMenuFrame.setLayout(new BorderLayout());

        // Create a panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, -540, 0, 0);
        gbc.gridwidth = 2;
        gbc.gridx = 0;

        // Label for Pokemon:
        JLabel pokemonLabel = new JLabel("Pokemon:");
        pokemonLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
        pokemonLabel.setForeground(Color.YELLOW);
        gbc.gridy = 0;
        mainPanel.add(pokemonLabel, gbc);

        // Label for Kanto Adventures
        JLabel kantoAdventuresLabel = new JLabel("Kanto Adventures");
        kantoAdventuresLabel.setFont(new Font("Comic Sans MS", Font.ITALIC, 30));
        kantoAdventuresLabel.setForeground(Color.GREEN);
        gbc.insets = new Insets(0, -450, 60, 0);
        gbc.gridy++;
        mainPanel.add(kantoAdventuresLabel, gbc);

        // Button for logout:
        ImageIcon logout1Icon = new ImageIcon(
                new ImageIcon("logout1.png").getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH));
        ImageIcon logout2Icon = new ImageIcon(
                new ImageIcon("logout2.png").getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH));
        JButton logoutButton = new JButton(logout2Icon);
        logoutButton.setOpaque(false);
        logoutButton.setBorderPainted(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setFocusPainted(false);
        logoutButton.setMinimumSize(new Dimension(45, 45));
        logoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutButton.setIcon(logout1Icon);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutButton.setIcon(logout2Icon);
            }
        });
        logoutButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(gameMenuFrame,
                    "Are you sure you would like to logout?", "Confirm logout",
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                gameMenuFrame.dispose();
                showUserAuthenticationWindow();
            }
        });

        gbc.insets = new Insets(0, 0, 0, -630);
        gbc.gridy = 0;
        gbc.gridx++;
        mainPanel.add(logoutButton, gbc);
        gbc.gridy++;

        // Button colors
        Color hoverColor = Color.YELLOW;
        Color textColor = Color.WHITE;

        // Button hover listener
        MouseAdapter hoverListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                button.setForeground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                button.setForeground(textColor);
            }
        };

        // Creating the buttons
        JButton newAdventureButton = new JButton("New Adventure");
        JButton loadAdventureButton = new JButton("Load Adventure");
        JButton deleteAdventureButton = new JButton("Delete Adventure");

        // Setting font and its colour
        Font buttonFont = new Font("Monospaced", Font.BOLD, 25);
        newAdventureButton.setFont(buttonFont);
        loadAdventureButton.setFont(buttonFont);
        deleteAdventureButton.setFont(buttonFont);
        newAdventureButton.setForeground(textColor);
        loadAdventureButton.setForeground(textColor);
        deleteAdventureButton.setForeground(textColor);

        // Making buttons transparent
        newAdventureButton.setContentAreaFilled(false);
        loadAdventureButton.setContentAreaFilled(false);
        deleteAdventureButton.setContentAreaFilled(false);
        newAdventureButton.setOpaque(false);
        loadAdventureButton.setOpaque(false);
        deleteAdventureButton.setOpaque(false);
        newAdventureButton.setBorderPainted(false);
        loadAdventureButton.setBorderPainted(false);
        deleteAdventureButton.setBorderPainted(false);
        newAdventureButton.setFocusPainted(false);
        loadAdventureButton.setFocusPainted(false);
        deleteAdventureButton.setFocusPainted(false);

        newAdventureButton.addMouseListener(hoverListener);
        loadAdventureButton.addMouseListener(hoverListener);
        deleteAdventureButton.addMouseListener(hoverListener);

        newAdventureButton.setPreferredSize(deleteAdventureButton.getPreferredSize());
        loadAdventureButton.setPreferredSize(deleteAdventureButton.getPreferredSize());
        newAdventureButton.setHorizontalAlignment(SwingConstants.LEFT);
        loadAdventureButton.setHorizontalAlignment(SwingConstants.LEFT);

        newAdventureButton.addActionListener(e -> {
            // gameMenuFrame.dispose();
            showAdventureWindow("New Adventure", gameMenuFrame);
        });
        loadAdventureButton.addActionListener(e -> {
            // gameMenuFrame.dispose();
            showAdventureWindow("Load Adventure", gameMenuFrame);
        });
        deleteAdventureButton.addActionListener(e -> {
            // gameMenuFrame.dispose();
            showAdventureWindow("Delete Adventure", gameMenuFrame);
        });

        // Adding the buttons to the panel
        gbc.insets = new Insets(0, -450, 5, 0);
        gbc.gridy++;
        mainPanel.add(newAdventureButton, gbc);
        gbc.gridy++;
        mainPanel.add(loadAdventureButton, gbc);
        gbc.gridy++;
        mainPanel.add(deleteAdventureButton, gbc);

        // Add main panel to the frame
        gameMenuFrame.add(mainPanel, BorderLayout.CENTER);

        // Make the frame visible
        gameMenuFrame.setResizable(false);
        // gameMenuFrame.setLocationRelativeTo(null);
        gameMenuFrame.setVisible(true);
    }

    private static void showAdventureWindow(String actionString, JFrame frame) {
        JFrame adventureFrame = frame;
        // JFrame adventureFrame = new JFrame(actionString);
        // adventureFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // adventureFrame.setSize(800, 450);
        adventureFrame.setTitle(actionString);
        int action = -1;
        if (actionString.equals("New Adventure")) {
            action = 0; // 0 for new save
        } else if (actionString.equals("Load Adventure")) {
            action = 1; // 1 for load save
        } else if (actionString.equals("Delete Adventure")) {
            action = 2; // 2 for delete save
        }

        String backgroundImage;
        if (action == 0) {
            backgroundImage = "background19.jpg";
        } else if (action == 1) {
            backgroundImage = "background20.jpg";
        } else {
            backgroundImage = "background15.jpg";
        }
        // Background Image
        ImageIcon background = new ImageIcon(new ImageIcon(backgroundImage).getImage()
                .getScaledInstance(adventureFrame.getWidth(), adventureFrame.getHeight(), Image.SCALE_SMOOTH));
        JLabel backgroundLabel = new JLabel(background);
        adventureFrame.setContentPane(backgroundLabel); // Set the background image as content

        // Set layout manager to BorderLayout
        adventureFrame.setLayout(new BorderLayout());

        // Create a panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(7, 0, 7, 0); // new Insets(7, -500, 7, 0);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        // // Panel hover listener
        // MouseAdapter hoverListener = new MouseAdapter() {
        // @Override
        // public void mouseEntered(MouseEvent e) {
        // JButton button = (JButton) e.getSource();
        // button.setForeground(hoverColor);
        // }

        // @Override
        // public void mouseExited(MouseEvent e) {
        // JButton button = (JButton) e.getSource();
        // button.setForeground(textColor);
        // }
        // };
        // Labels for saved adventures
        String[] saves = { "Save 1", "Save 2", "Save 3" };
        GameSaveManager gsm = new GameSaveManager();
        for (int i = 0; i < saves.length; i++) {
            final int slotNumber = i + 1; // Declare index as final
            final int ACTION = action;
            JPanel slotPanel = new JPanel(new BorderLayout());
            JPanel slotProgressPanel = new JPanel(new GridLayout(4, 1));
            slotPanel.setOpaque(false);
            slotPanel.setPreferredSize(new Dimension(200, 75));
            slotProgressPanel.setPreferredSize(new Dimension(200, 75));
            slotProgressPanel.setOpaque(false);
            String saveLabel = saves[i];
            JLabel slotLabel = new JLabel(saveLabel);
            slotLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
            int save_id = calculateSaveId(slotNumber);
            Image pokeballImage = new ImageIcon("pokeBall.png").getImage();
            CustomProgressBar progressBar = new CustomProgressBar() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    // g.setColor(Color.BLUE);
                    // String text = getString();
                    int width = getWidth();
                    int height = getHeight();
                    // FontMetrics fontMetrics = g.getFontMetrics();
                    // int stringWidth = fontMetrics.stringWidth(text);
                    // int stringHeight = fontMetrics.getAscent();
                    // g.drawString(text, (width - stringWidth) / 2, (height + stringHeight) / 2 -
                    // 2);
                    int fillWidth = (int) (width * (getPercentComplete()));
                    int iconSize = height; // Assuming the icon size should match the height of the progress bar
                    // if (fillWidth >= iconSize) {
                    // g.drawImage(pokeballImage, fillWidth - iconSize, 0, iconSize, iconSize,
                    // null);
                    // } else {
                    // g.drawImage(pokeballImage, 0, 0, iconSize, iconSize, null);
                    // }
                    if (getPercentComplete() > 0 && getPercentComplete() < 1.0) {
                        g.drawImage(pokeballImage, fillWidth - iconSize + 15, 0, iconSize, iconSize, null);
                    } else if (getPercentComplete() == 1.0) {
                        g.setColor(Color.WHITE);
                        g.setFont(new Font("Monospaced", Font.BOLD, 17));
                        FontMetrics fontMetrics = g.getFontMetrics();
                        String text = "Complete";
                        int stringWidth = fontMetrics.stringWidth(text);
                        int stringHeight = fontMetrics.getAscent();
                        g.drawString(text, (width - stringWidth) / 2, (height + stringHeight) / 2 - 2);
                    }
                }
            };
            progressBar.setVisible(false);
            if (!gsm.saveExists(save_id)) { // if empty slot
                slotLabel.setText(saveLabel + " does not exist");
                slotPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                slotLabel.setForeground(Color.LIGHT_GRAY);

                if (action == 0) { // To add mouse listener to all slots if new game
                    // slotPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    slotLabel.setForeground(Color.WHITE);
                    slotPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
                    slotPanel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            // System.out.println("Empty slot for new adventure");
                            handleNewGameOption(adventureFrame, save_id);
                        }

                        Color hoverColor = Color.GREEN;
                        Color defaultColor = Color.WHITE;

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            // slotPanel.setBorder(BorderFactory.createLineBorder(hoverColor));
                            slotPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, hoverColor));
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            // slotPanel.setBorder(BorderFactory.createLineBorder(defaultColor));
                            slotPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, defaultColor));
                        }
                    });
                }
            } else { // if slot not empty
                slotLabel.setForeground(Color.WHITE);
                slotPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
                // slotPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                if (action == 0) { // if new game set border to red
                    // slotPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
                    slotPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
                }
                progressBar.setVisible(true);
                Save progressSave = gsm.loadSave(save_id);

                Font progressLabelsFont = new Font("Comic Sans MS", Font.ITALIC, 11);

                JLabel trainerNameLabel = new JLabel("Trainer Name: " + progressSave.getTrainer_name());
                JLabel locationLabel = new JLabel("Location: " + progressSave.getCurrent_location());
                JLabel gymLeadersDefeatedLabel = new JLabel(
                        "Gym Leaders Defeated: " + progressSave.getBadges().size() + "/8");
                JLabel lastSavedLabel = new JLabel("Last saved: " + progressSave.getLast_saved());
                trainerNameLabel.setFont(progressLabelsFont);
                locationLabel.setFont(progressLabelsFont);
                gymLeadersDefeatedLabel.setFont(progressLabelsFont);
                lastSavedLabel.setFont(progressLabelsFont);
                trainerNameLabel.setForeground(Color.WHITE);
                locationLabel.setForeground(Color.WHITE);
                gymLeadersDefeatedLabel.setForeground(Color.WHITE);
                lastSavedLabel.setForeground(Color.WHITE);
                slotProgressPanel.add(trainerNameLabel);
                slotProgressPanel.add(locationLabel);
                slotProgressPanel.add(gymLeadersDefeatedLabel);
                slotProgressPanel.add(lastSavedLabel);
                // System.out.println(progressSave.getBadges().size() / 8 * 100);
                double progressPercentage = (progressSave.getBadges().size() / 8.0) * 100;
                progressBar.setValue((int) progressPercentage);
                progressBar.setStringPainted(false);
                // progressBar.setSize(new Dimens)
                // Set the colors
                progressBar.setBackground(Color.DARK_GRAY); // Background color
                // progressBar.setForeground(Color.YELLOW); // Fill color
                progressBar.setPreferredSize(new Dimension(148, 23));
                // progressBar.setBorderPainted(false);
                progressBar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                // if (progressBar.getPercentComplete() == 1.0) {
                // progressBar.setForeground(new Color(34, 139, 34));
                // }

                MouseAdapter slotPanelMouseListener = new MouseAdapter() {
                    boolean saveDeleted = false;

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // System.out.println("nonEmptySlot");
                        if (ACTION == 0) { // Confirm overwrite chosen save
                            int choice = JOptionPane.showConfirmDialog(adventureFrame,
                                    "Are you sure you would like to overwrite the current save?", "Confirm Overwrite",
                                    JOptionPane.YES_NO_OPTION);
                            if (choice == JOptionPane.YES_OPTION) {
                                handleNewGameOption(adventureFrame, slotNumber);
                            }
                        } else if (ACTION == 1) { // Load chosen save
                            handleLoadGameOption(gsm, adventureFrame, save_id);
                        } else { // Confirm delete chosen save
                            int choice = JOptionPane.showConfirmDialog(adventureFrame,
                                    "Are you sure you would like to delete the save?", "Confirm Deletion",
                                    JOptionPane.YES_NO_OPTION);
                            if (choice == JOptionPane.YES_OPTION) {
                                if (gsm.deleteSave(save_id)) {
                                    saveDeleted = true;
                                    slotPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                                    // slotLabel.setText("Save " + slotNumber + " does not exist");
                                    slotLabel.setText(saveLabel + " does not exist");
                                    slotLabel.setForeground(Color.LIGHT_GRAY);
                                    progressBar.setVisible(false);
                                    slotProgressPanel.setVisible(false);
                                    handleDeleteGameOption(gsm, save_id);
                                    slotPanel.removeMouseListener(this);
                                } else {
                                    JOptionPane.showMessageDialog(adventureFrame, "Error deleting save", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    }

                    Color hoverColor = Color.GREEN;

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        slotPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, hoverColor));
                        slotPanel.setBorder(BorderFactory.createLineBorder(hoverColor));
                        // JLabel trainerNameLabel = new JLabel("Trainer name: " +
                        // progressSave.getTrainer_name());
                        // JLabel gymLeadersDefeatedLabel = new JLabel("Gym Leaders Defeated: " +
                        // progressSave.getGym_leaders_defeated().size() + " / 8");
                        // JLabel lastLocationLabel = new JLabel("Location: " +
                        // progressSave.getCurrent_location());
                        // JLabel lastSavedLabel = new JLabel("Last saved: " +
                        // progressSave.getLast_saved());
                        // slotProgressPanel.add(trainerNameLabel, BorderLayout.PAGE_START);
                        // slotProgressPanel.add(gymLeadersDefeatedLabel, BorderLayout.LINE_START);
                        // slotProgressPanel.add(lastSavedLabel, BorderLayout.LINE_END);
                        // Add labels and corresponding values to slotProgressPanel
                        slotProgressPanel.setVisible(true);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        Color defaultColor = Color.WHITE;
                        if (ACTION == 0) {
                            defaultColor = Color.RED;
                        }
                        if (saveDeleted == true) {
                            defaultColor = Color.DARK_GRAY;
                        }
                        slotProgressPanel.setVisible(false);
                        // trainerNameLabel.setVisible(false);
                        // slotPanel.remove(trainerNameLabel);
                        // slotPanel.revalidate(); // Revalidate the slotPanel to reflect changes
                        slotPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, defaultColor));
                        // slotPanel.setBorder(BorderFactory.createLineBorder(defaultColor));
                    }
                };
                slotPanel.addMouseListener(slotPanelMouseListener);

            }
            slotPanel.add(progressBar);
            slotPanel.add(slotLabel);
            slotPanel.add(progressBar, BorderLayout.SOUTH);
            slotLabel.setHorizontalAlignment(SwingConstants.CENTER);
            // slotLabel.setVerticalAlignment(SwingConstants.CENTER);
            slotProgressPanel.setVisible(false);
            gbc.gridy = i;
            gbc.insets = new Insets(7, -500, 7, 0); // new Insets(7, -500, 7, 0);
            mainPanel.add(slotPanel, gbc);
            gbc.gridx = 0;
            gbc.insets = new Insets(7, -60, 7, 0); // new Insets(7, -500, 7, 0);
            mainPanel.add(slotProgressPanel, gbc);
            gbc.gridx = 1;
        }
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        backButton.setForeground(Color.WHITE);
        backButton.setContentAreaFilled(false);
        backButton.setOpaque(false);
        backButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 1),
                BorderFactory.createEmptyBorder(7, 10, 7, 10)));
        // backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        gbc.insets = new Insets(40, -500, 0, 0);
        gbc.gridy++;
        mainPanel.add(backButton, gbc);
        backButton.addActionListener(e -> {
            // adventureFrame.dispose();
            showGameMenuWindow(adventureFrame);
        });
        // Add a hover effect to the button
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setForeground(Color.RED); // Change text color on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setForeground(Color.WHITE); // Reset text color on exit
            }

        });

        // Add main panel to the frame
        adventureFrame.add(mainPanel, BorderLayout.CENTER);

        // Make the frame visible
        adventureFrame.setResizable(false);
        // adventureFrame.setLocationRelativeTo(null);
        adventureFrame.setVisible(true);
    }

    // private static boolean saveExists(int slotNumber) {
    // int save_id = (loggedInUser.getAccount_id() - 1) * 3 + (slotNumber);
    // GameSaveManager gsm = new GameSaveManager();
    // return gsm.saveExists(save_id);
    // }

    public static int calculateSaveId(int slotNumber) {
        return ((loggedInUser.getAccount_id() - 1) * 3 + (slotNumber));
    }

    public static void handleNewGameOption(JFrame frame, int save_id) {
        System.out.println("Creating new save in save id: " + save_id);
        Trainer trainer = new Trainer(save_id);
        // frame.dispose();
        // try {
        // GamePanel gp = new GamePanel(trainer);
        // } catch (FileNotFoundException e) {
        // e.printStackTrace();
        // }
        showLoadingScreen(frame, trainer, 20000, 30000);
    }

    public static void handleLoadGameOption(GameSaveManager gsm, JFrame frame, int save_id) {
        System.out.println("Loading save id: " + save_id);
        // frame.dispose();
        Save chosenSave = gsm.loadSave(save_id);
        // location
        Location currentLocation = new Location(chosenSave.getCurrent_location());
        // System.out.println(currentLocation.getName());
        Trainer trainer = new Trainer(save_id, chosenSave.getTrainer_name(), currentLocation);
        // pokemon
        Evolution evol = new Evolution();
        for (int i = 0; i < chosenSave.getPokemon_team().size(); i++) {
            String[] pokemon = chosenSave.getPokemon_team().get(i);
            System.out.println(pokemon[0]);
            trainer.addToList(pokemon[0]);
            Pokemon thisPokemon = trainer.getPokemonList().get(i);
            System.out.println(pokemon[2]);
            // System.out.println("Before: " + thisPokemon.getName() + " - " +
            // thisPokemon.getLevel());
            int level = Integer.parseInt(pokemon[1]);
            if (level < 10) {

            } else {
                int tempLevel;
                if (level >= 20) {
                    tempLevel = 20;
                } else {
                    tempLevel = 10;
                }
                thisPokemon.setLevel(tempLevel);
                evol.evolve(thisPokemon);
                // System.out.println(evol.evolve(thisPokemon));
            }
            thisPokemon.setLevel(level);
            thisPokemon.setHP(Integer.parseInt(pokemon[2]));
            thisPokemon.setXP(Integer.parseInt(pokemon[3]));
            thisPokemon.getMoveDamages().replace(pokemon[4], (Integer.parseInt(pokemon[5])));
            thisPokemon.getMoveDamages().replace(pokemon[6], (Integer.parseInt(pokemon[7])));
            // System.out.println("After: " + thisPokemon.getName() + " - " +
            // thisPokemon.getLevel());
            // System.out.println();
        }

        // for (Pokemon pokemon : Trainer.getPokemonList()) {
        // System.out.println(pokemon);
        // }
        // badges
        trainer.loadBadges(new ArrayList<>(chosenSave.getBadges()));
        // System.out.println(trainer.showBadges());
        trainer.setGymLeadersDefeated(new ArrayList<>(chosenSave.getGym_leaders_defeated()));
        // try {
        // GamePanel gp = new GamePanel(trainer);
        // } catch (FileNotFoundException e) {
        // e.printStackTrace();
        // }
        showLoadingScreen(frame, trainer, 30000, 50000);
    }

    public static void showLoadingScreen(JFrame frame, Trainer trainer, int minLoadingDelay, int maxLoadingDelay) {
        JFrame loadingScreenFrame = frame;
        loadingScreenFrame.setLayout(new BorderLayout());
    
        JProgressBar loadingProgress = new JProgressBar();
        loadingProgress.setPreferredSize(new Dimension(600, 30));
        loadingProgress.setForeground(Color.YELLOW);
        loadingProgress.setBorderPainted(false);
    
        ImageIcon[] pikachuImages = new ImageIcon[3];
        for (int i = 0; i < 3; i++) {
            pikachuImages[i] = new ImageIcon(new ImageIcon("pikachu" + (i + 1) + ".png").getImage().getScaledInstance(87, 69, Image.SCALE_SMOOTH));
        }
    
        JLabel pikachuLabel = new JLabel();
        pikachuLabel.setIcon(pikachuImages[0]);
    
        JLabel loadingLabel = new JLabel();
        loadingLabel.setForeground(Color.WHITE);
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loadingLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        String loadingText = "Loading...";
        loadingLabel.setText("");
    
        JPanel progressPanel = new JPanel();
        progressPanel.setLayout(new GridBagLayout());
        progressPanel.setBackground(Color.BLACK);
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(30, 30, 30, 30);
        gbc.gridx = 0;
        gbc.gridy = 0;
        progressPanel.add(loadingLabel, gbc);
    
        gbc.gridy = 1;
        progressPanel.add(pikachuLabel, gbc);
    
        gbc.gridy = 2;
        progressPanel.add(loadingProgress, gbc);
    
        loadingScreenFrame.setContentPane(progressPanel);
    
        Random rand = new Random();
        int loadingDelay = rand.nextInt(minLoadingDelay, maxLoadingDelay);
        loadingScreenFrame.setVisible(true);
    
        int steps = 100;
        final int[] remainingProgress = {100};
        final int[] remainingTime = {loadingDelay};
    
        Timer textTimer = new Timer(80, new ActionListener() {
            int index = 0;
    
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < loadingText.length()) {
                    loadingLabel.setText(loadingLabel.getText() + loadingText.charAt(index));
                    index++;
                } else {
                    Timer fadeTimer = new Timer(40, new ActionListener() {
                        float alpha = 1.0f;
                        boolean fadingOut = false;
    
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (fadingOut) {
                                alpha -= 0.05f;
                                if (alpha <= 0.0f) {
                                    alpha = 0.0f;
                                    fadingOut = false;
                                }
                            } else {
                                alpha += 0.05f;
                                if (alpha >= 1.0f) {
                                    alpha = 1.0f;
                                    fadingOut = true;
                                }
                            }
                            loadingLabel.setForeground(new Color(1.0f, 1.0f, 1.0f, alpha));
                        }
                    });
                    fadeTimer.start();
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        textTimer.start();
    
        // Timer for updating progress bar
        Timer progressBarTimer = new Timer(0, null);
        progressBarTimer.addActionListener(new ActionListener() {
            int progress = 0;
    
            @Override
            public void actionPerformed(ActionEvent e) {
                if (remainingProgress[0] <= 0) {
                    ((Timer) e.getSource()).stop();
                    loadingScreenFrame.dispose();
                    try {
                        GamePanel gp = new GamePanel(trainer);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    int progressIncrement = rand.nextInt(Math.min(remainingProgress[0], 10)) + 1;
                    progress += progressIncrement;
                    remainingProgress[0] -= progressIncrement;
                    loadingProgress.setValue(progress);
    
                    int timeIncrement = rand.nextInt(Math.min(remainingTime[0], loadingDelay / steps)) + 1;
                    remainingTime[0] -= timeIncrement;
                    ((Timer) e.getSource()).setDelay(timeIncrement);
                }
            }
        });
        progressBarTimer.setInitialDelay(rand.nextInt(300) + 100);
        progressBarTimer.start();
    
        // Timer for updating Pikachu animation
        Timer pikachuTimer = new Timer(200, new ActionListener() {
            int pikachuFrameIndex = 0;
    
            @Override
            public void actionPerformed(ActionEvent e) {
                pikachuLabel.setIcon(pikachuImages[pikachuFrameIndex]);
                pikachuFrameIndex = (pikachuFrameIndex + 1) % pikachuImages.length;
            }
        });
        pikachuTimer.start();
    }
    
    public static void handleDeleteGameOption(GameSaveManager gsm, int save_id) {
        System.out.println("Deleting save id: " + save_id);
        // if (gsm.deleteSave(save_id))
    }

    public static void saveGame(Trainer trainer) {
        GameSaveManager gsm = new GameSaveManager();
        ArrayList<String[]> pokemonTeam = new ArrayList<String[]>();
        // System.out.println("in savGame: ");
        // System.out.println(trainer.showPokemonList());
        for (Pokemon pokemon : trainer.getPokemonList()) {
            String[] thisPokemonData = new String[8];
            thisPokemonData[0] = pokemon.getName();
            thisPokemonData[1] = String.valueOf(pokemon.getLevel());
            System.out.println(thisPokemonData[0]);
            thisPokemonData[2] = String.valueOf(pokemon.getMaxHP());
            thisPokemonData[3] = String.valueOf(pokemon.getXP());
            ArrayList<String> moves = pokemon.getMoves();
            int i = 4;
            for (String move : moves) {
                thisPokemonData[i] = move;
                thisPokemonData[++i] = String.valueOf(pokemon.getMoveDamage(move));
                i++;
            }
            pokemonTeam.add(thisPokemonData);
        }
        Save save = new Save(trainer.getSaveID(), trainer.getTrainerName(), trainer.getCurrentLocation().getName(),
                pokemonTeam, trainer.getGymLeadersDefeated(), trainer.getBadges(), "");
        gsm.saveGame(save);
        // SwingUtilities.invokeLater(Main::showUserAuthenticationWindow);
        // trainer = null;
        trainer.resetTrainer();

        JFrame newFrame = new JFrame();
        initializeMainFrame(newFrame, "Game Menu");
        showGameMenuWindow(newFrame);
    }

}

class CustomProgressBar extends JProgressBar {
    public CustomProgressBar() {
        super(0, 100);
        setPreferredSize(new Dimension(600, 50));
        setForeground(Color.RED); // Set initial color
        setBorderPainted(false);
    }

    @Override
    public void setValue(int n) {
        super.setValue(n);
        if (n >= 30 && n < 60) {
            setForeground(Color.BLUE); // Change color at 25%
        } else if (n >= 60 && n < 100) {
            setForeground(Color.YELLOW); // Change color at 50%
        } else if (n == 100) {
            setForeground(Color.GREEN); // Change color at 75%
        }
    }
}