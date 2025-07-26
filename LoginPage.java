package com.CGU.PROJECT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Fill the panel with background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

public class LoginPage extends JFrame implements ActionListener {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPage() {
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Welcome to Currency Converter", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(30, 144, 255));
        add(titleLabel, BorderLayout.NORTH);

        // Center Panel for Form with Background Image
        BackgroundPanel centerPanel = new BackgroundPanel("src/com/CGU/PROJECT/LOGIN_BACKGROUND.jpg"); // Update with your image path
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        // Username
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(userLabel, gbc);

        usernameField = new JTextField(15);
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), 2));
        usernameField.setToolTipText("Enter your username");
        gbc.gridx = 1;
        centerPanel.add(usernameField, gbc);

        // Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(passLabel, gbc);

        passwordField = new JPasswordField(15);
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), 2));
        passwordField.setToolTipText("Enter your password");
        gbc.gridx = 1;
        centerPanel.add(passwordField, gbc);

        // Forgot Password Link
        JLabel forgotPasswordLabel = new JLabel("<html><a href=''>Forgot Password?</a></html>");
        forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPasswordLabel.setForeground(new Color(30, 144, 255));
        forgotPasswordLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JOptionPane.showMessageDialog(LoginPage.this, "Password recovery is not implemented yet.");
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        centerPanel.add(forgotPasswordLabel, gbc);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 248, 255));

        loginButton = new JButton("Login") {
            @Override
            protected void paintComponent(Graphics g) {
                // Create a gradient background
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(30, 144, 255), 0, getHeight(), new Color(0, 102, 204));
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(120, 40);
            }
        };

        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setToolTipText("Click to log in");

        // Hover Effect
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(0, 102, 204));
                loginButton.setForeground(Color.YELLOW);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setForeground(Color.WHITE);
            }
        });

        loginButton.addActionListener(this);
        buttonPanel.add(loginButton);

        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        if (username.equals("user") && password.equals("123")) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            // Open Currency Converter
            new CurrencyConverterUI(); // Ensure this class exists
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::new);
    }
}
