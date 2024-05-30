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
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

    private static User loggedInUser;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(Main::showUserAuthenticationWindow);

    }

    private static void showUserAuthenticationWindow() {

        JFrame frame = new JFrame("User Authentication");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 450);

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
        frame.setResizable(false);
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
                parentFrame.dispose();
                showWelcomeWindow();
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

    private static void showWelcomeWindow() {
        JFrame welcomeFrame = new JFrame("Welcome Window");
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setSize(800, 450);
        welcomeFrame.setLayout(new BorderLayout());

        // Background Image
        ImageIcon backgroundIcon = new ImageIcon("background9.jpg");
        Image image = backgroundIcon.getImage().getScaledInstance(welcomeFrame.getWidth(), welcomeFrame.getHeight(),
                Image.SCALE_SMOOTH);
        backgroundIcon = new ImageIcon(image);
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new BorderLayout());
        welcomeFrame.setContentPane(backgroundLabel);

        // Welcome Label
        JLabel welcomeLabel = new JLabel();
        welcomeLabel.setFont(new Font("DialogInput", Font.BOLD, 50));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundLabel.add(welcomeLabel, BorderLayout.CENTER);

        // Blinking animation
        Timer blinkAnimation = new Timer(450, new ActionListener() {
            private boolean isVisible = true;
            private int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                isVisible = !isVisible;
                welcomeLabel.setForeground(new Color(255, 255, 255, isVisible ? 255 : 0));
                // welcomeLabel.setForeground(new Color(0, 0, 0));
                count++;
                if (count >= 2) { // Stop after two iterations (fade out and fade in)
                    ((Timer) e.getSource()).stop();
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
                welcomeLabel.setForeground(new Color(255, 255, 255, 255)); // White
                //welcomeLabel.setForeground(new Color(0, 0, 0));
            }
        });

        timer.start();

        JButton playButton = new JButton("Play Game");
        playButton.addActionListener(e -> {
            // Action to perform when the button is clicked
            
            welcomeFrame.dispose();
        });
        backgroundLabel.add(playButton, BorderLayout.SOUTH);

        // Make the frame visible
        welcomeFrame.setResizable(false);
        welcomeFrame.setLocationRelativeTo(null);
        welcomeFrame.setVisible(true);
    }

}