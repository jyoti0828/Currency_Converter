package com.CGU.PROJECT;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;


class ImageFrame extends JFrame {
    private Image backgroundImage;

    public ImageFrame(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        });
        setLayout(new BorderLayout());
    }

    public void setBackgroundImage(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
        repaint(); // Repaint the frame to show the new background
    }
}

public class CurrencyConverterUI extends ImageFrame implements ActionListener {

    private JTextField amountField;
    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;
    private JButton convertButton, reverseButton, copyButton, clearButton, historyButton, themeButton, exportButton;
    private JLabel resultLabel, rateLabel, dateTimeLabel, insightLabel;
    private JTextArea historyArea;
    private Set<String> favoriteCurrencies;
    private JButton multiConvertButton;

    private final CurrencyConverter currencyConverter;
    private final List<String> historyLog;

    private static final Color GREEN = new Color(60, 179, 113);
    private static final Color ORANGE = new Color(255, 165, 0);
    private static final Color DARK_TEXT = new Color(55, 223, 223);
    private static final Color DARK_INPUT = new Color(141, 174, 207);
    private static final Color LIGHT_INPUT = new Color(255, 255, 255);
    private boolean isDarkMode = false;

    private static final String LIGHT_MODE_IMAGE_PATH = "src/com/CGU/PROJECT/BACKGROUND.jpg"; // Light mode image path
    private static final String DARK_MODE_IMAGE_PATH = "src/com/CGU/PROJECT/DARK.png"; // Dark mode image path

    public CurrencyConverterUI() {
        super(LIGHT_MODE_IMAGE_PATH); // Start with light mode image
        setTitle("Currency Converter");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        currencyConverter = new CurrencyConverter();
        historyLog = new ArrayList<>();
        favoriteCurrencies = new HashSet<>();

        initializeUIComponents();
        setVisible(true);
    }

    private void initializeUIComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);  // Make the panel transparent

        JLabel titleLabel = new JLabel("Currency Converter");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(30, 144, 255));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        contentPanel.add(titleLabel, gbc);

        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField(10);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        contentPanel.add(amountLabel, gbc);
        gbc.gridx = 1;
        contentPanel.add(amountField, gbc);

        JLabel fromLabel = new JLabel("From Currency:");
        fromCurrency = new JComboBox<>(currencyConverter.getCurrencyCodes());
        gbc.gridx = 0; gbc.gridy = 3;
        contentPanel.add(fromLabel, gbc);
        gbc.gridx = 1;
        contentPanel.add(fromCurrency, gbc);

        JLabel toLabel = new JLabel("To Currency:");
        toCurrency = new JComboBox<>(currencyConverter.getCurrencyCodes());
        gbc.gridx = 0; gbc.gridy = 4;
        contentPanel.add(toLabel, gbc);
        gbc.gridx = 1;
        contentPanel.add(toCurrency, gbc);

        setDefaultCurrency();

        reverseButton = new CustomButton("↔ Reverse", ORANGE);
        reverseButton.addActionListener(this);
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        contentPanel.add(reverseButton, gbc);

        convertButton = new CustomButton("Convert", GREEN);
        convertButton.addActionListener(this);
        gbc.gridx = 0; // First column
        gbc.gridy = 6; // Same row
        gbc.gridwidth = 1; // Only one column wide
        contentPanel.add(convertButton, gbc);

        // Multi-Convert Button
        multiConvertButton = new CustomButton("Multi Convert", GREEN);
        multiConvertButton.addActionListener(this);
        gbc.gridx = 1; // Second column
        gbc.gridy = 6; // Same row
        gbc.gridwidth = 1; // Only one column wide
        contentPanel.add(multiConvertButton, gbc);

        resultLabel = new JLabel("Result: ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        resultLabel.setForeground(new Color(255, 69, 0));
        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        contentPanel.add(resultLabel, gbc);

        rateLabel = new JLabel("Exchange Rate: ");
        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 2;
        contentPanel.add(rateLabel, gbc);

        dateTimeLabel = new JLabel("Last Updated: ");
        gbc.gridx = 0; gbc.gridy = 9; gbc.gridwidth = 2;
        contentPanel.add(dateTimeLabel, gbc);

        insightLabel = new JLabel("Insight: ");
        gbc.gridx = 0; gbc.gridy = 10; gbc.gridwidth = 2;
        contentPanel.add(insightLabel, gbc);

        historyButton = new CustomButton("Show History", new Color(100, 149, 237));
        historyButton.addActionListener(this);
        gbc.gridx = 0; gbc.gridy = 11; gbc.gridwidth = 1;
        contentPanel.add(historyButton, gbc);

        themeButton = new CustomButton("Toggle Theme", new Color(169, 169, 169));
        themeButton.addActionListener(this);
        gbc.gridx = 1; gbc.gridy = 11;
        contentPanel.add(themeButton, gbc);

        copyButton = new CustomButton("Copy Result", new Color(255, 105, 180));
        copyButton.addActionListener(this);
        gbc.gridx = 0; gbc.gridy = 12; gbc.gridwidth = 1;
        contentPanel.add(copyButton, gbc);

        clearButton = new CustomButton("Clear", new Color(220, 20, 60));
        clearButton.addActionListener(this);
        gbc.gridx = 1; gbc.gridy = 12;
        contentPanel.add(clearButton, gbc);

        exportButton = new CustomButton("Export History", new Color(100, 149, 237));
        exportButton.addActionListener(this);
        gbc.gridx = 0; gbc.gridy = 13; gbc.gridwidth = 2;
        contentPanel.add(exportButton, gbc);

        historyArea = new JTextArea(8, 30);
        historyArea.setOpaque(true); // Ensure the text area is opaque
        historyArea.setBackground(LIGHT_INPUT);
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setOpaque(true);
        scrollPane.getViewport().setOpaque(true);
        gbc.gridx = 0; gbc.gridy = 14; gbc.gridwidth = 2;
        contentPanel.add(scrollPane, gbc);

        add(contentPanel);
    }

    private void setDefaultCurrency() {
        Locale locale = Locale.getDefault();
        String currencyCode = Currency.getInstance(locale).getCurrencyCode();
        String defaultCurrency = currencyConverter.getDefaultCurrency(currencyCode);

        if (defaultCurrency != null) {
            fromCurrency.setSelectedItem(defaultCurrency);
            toCurrency.setSelectedItem("USD 🇺🇸");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == themeButton) {
            toggleTheme();
        } else if (e.getSource() == reverseButton) {
            int fromIndex = fromCurrency.getSelectedIndex();
            int toIndex = toCurrency.getSelectedIndex();
            fromCurrency.setSelectedIndex(toIndex);
            toCurrency.setSelectedIndex(fromIndex);
        } else if (e.getSource() == convertButton) {
            convertCurrency();
        } else if (e.getSource() == copyButton) {
            copyResultToClipboard();
        } else if (e.getSource() == clearButton) {
            clearFields();
        } else if (e.getSource() == historyButton) {
            showHistory();
        } else if (e.getSource() == exportButton) {
            exportHistory();
        }else if (e.getSource() == multiConvertButton) {
            convertMultipleCurrencies(); // Step 3: Handle Multi-Convert Button Click
        }
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String from = fromCurrency.getSelectedItem().toString();
            String to = toCurrency.getSelectedItem().toString();
            double convertedAmount = currencyConverter.convert(amount, from, to);
            resultLabel.setText(String.format("Result: %.2f %s", convertedAmount, to));
            rateLabel.setText(String.format("Exchange Rate: 1 %s = %.4f %s", from, currencyConverter.getExchangeRate(from, to), to));
            dateTimeLabel.setText("Last Updated: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            historyLog.add(String.format("%s: %.2f %s -> %.2f %s", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), amount, from, convertedAmount, to));
            provideInsight(from, to);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount! Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void convertMultipleCurrencies() {
        // Step 1: Create a dialog to select target currencies
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JCheckBox usdCheckBox = new JCheckBox("USD 🇺🇸");
        JCheckBox inrCheckBox = new JCheckBox("INR 🇮🇳");
        JCheckBox eurCheckBox = new JCheckBox("EUR 🇪🇺");
        JCheckBox gbpCheckBox = new JCheckBox("GBP 🇬🇧");
        JCheckBox jpyCheckBox = new JCheckBox("JPY 🇯🇵");

        panel.add(usdCheckBox);
        panel.add(inrCheckBox);
        panel.add(eurCheckBox);
        panel.add(gbpCheckBox);
        panel.add(jpyCheckBox);

        int result = JOptionPane.showConfirmDialog(this, panel, "Select Target Currencies", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // Step 2: Perform the conversion for selected currencies
            try {
                double amount = Double.parseDouble(amountField.getText());
                String from = fromCurrency.getSelectedItem().toString().split(" ")[0]; // Extract currency code
                StringBuilder conversionResults = new StringBuilder("Multi-Currency Conversion Results:\n");

                // Debug: Log the source currency and amount
                System.out.println("Converting from: " + from);
                System.out.println("Amount: " + amount);

                if (usdCheckBox.isSelected()) {
                    String to = "USD"; // Plain currency code
                    double convertedAmount = currencyConverter.convert(amount, from, to);
                    conversionResults.append(String.format("USD 🇺🇸: %.2f\n", convertedAmount));
                    System.out.println("USD Conversion: " + convertedAmount); // Debug log
                }
                if (inrCheckBox.isSelected()) {
                    String to = "INR"; // Plain currency code
                    double convertedAmount = currencyConverter.convert(amount, from, to);
                    conversionResults.append(String.format("INR 🇮🇳: %.2f\n", convertedAmount));
                    System.out.println("INR Conversion: " + convertedAmount); // Debug log
                }
                if (eurCheckBox.isSelected()) {
                    String to = "EUR"; // Plain currency code
                    double convertedAmount = currencyConverter.convert(amount, from, to);
                    conversionResults.append(String.format("EUR 🇪🇺: %.2f\n", convertedAmount));
                    System.out.println("EUR Conversion: " + convertedAmount); // Debug log
                }
                if (gbpCheckBox.isSelected()) {
                    String to = "GBP"; // Plain currency code
                    double convertedAmount = currencyConverter.convert(amount, from, to);
                    conversionResults.append(String.format("GBP 🇬🇧: %.2f\n", convertedAmount));
                    System.out.println("GBP Conversion: " + convertedAmount); // Debug log
                }
                if (jpyCheckBox.isSelected()) {
                    String to = "JPY"; // Plain currency code
                    double convertedAmount = currencyConverter.convert(amount, from, to);
                    conversionResults.append(String.format("JPY 🇯🇵: %.2f\n", convertedAmount));
                    System.out.println("JPY Conversion: " + convertedAmount); // Debug log
                }

                // Step 3: Display the results
                if (conversionResults.length() > 0) {
                    JOptionPane.showMessageDialog(this, conversionResults.toString(), "Multi-Currency Conversion Results", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No currencies selected!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount! Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void copyResultToClipboard() {
        String result = resultLabel.getText();
        if (!result.equals("Result: ")) {
            StringSelection stringSelection = new StringSelection(result);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
            JOptionPane.showMessageDialog(this, "Result copied to clipboard!", "Copy Successful", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void clearFields() {
        amountField.setText("");
        resultLabel.setText("Result: ");
        rateLabel.setText("Exchange Rate: ");
        dateTimeLabel.setText("Last Updated: ");
        insightLabel.setText("Insight: ");
    }

    private void showHistory() {
        historyArea.setText("");
        for (String entry : historyLog) {
            historyArea.append(entry + "\n");
        }
    }

    private void exportHistory() {
        String filePath = JOptionPane.showInputDialog(this, "Enter file path to save history (e.g., history.txt):");
        if (filePath != null && !filePath.trim().isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (String entry : historyLog) {
                    writer.write(entry);
                    writer.newLine();
                }
                JOptionPane.showMessageDialog(this, "History exported successfully!", "Export Successful", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error exporting history: " + ex.getMessage(), "Export Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void provideInsight(String from, String to) {
        if (from.equals("USD") && to.equals("INR")) {
            insightLabel.setText("Insight: USD to INR rates are rising, consider exchanging now!");
        } else if (from.equals("EUR") && to.equals("USD")) {
            insightLabel.setText("Insight: EUR to USD rates are stable, it might be a good time to exchange.");
        } else if (from.equals("GBP") && to.equals("EUR")) {
            insightLabel.setText("Insight: GBP to EUR rates are fluctuating, monitor the market closely.");
        } else if (from.equals("USD") && to.equals("GBP")) {
            insightLabel.setText("Insight: USD to GBP rates are currently favorable, consider exchanging.");
        } else if (from.equals("USD") && to.equals("JPY")) {
            insightLabel.setText("Insight: USD to JPY rates are fluctuating, consider timing your exchange.");
        } else if (from.equals("INR") && to.equals("USD")) {
            insightLabel.setText("Insight: INR to USD rates are currently low, consider exchanging soon.");
        } else if (from.equals("EUR") && to.equals("GBP")) {
            insightLabel.setText("Insight: EUR to GBP rates are currently stable, it might be a good time to exchange.");
        } else if (from.equals("JPY") && to.equals("USD")) {
            insightLabel.setText("Insight: JPY to USD rates are currently low, monitor for favorable rates.");
        } else {
            insightLabel.setText("Insight: No specific insights available.");
        }
    }

    private void toggleTheme() {
        Timer fadeOutTimer = new Timer(50, null);
        fadeOutTimer.addActionListener(new ActionListener() {
            int alpha = 255;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (alpha > 0) {
                    alpha -= 15;
                    setComponentsAlpha(alpha);
                } else {
                    fadeOutTimer.stop();

                    // Change theme
                    if (isDarkMode) {
                        setBackgroundImage(LIGHT_MODE_IMAGE_PATH);
                        amountField.setBackground(LIGHT_INPUT);
                        resultLabel.setForeground(Color.BLACK);
                        rateLabel.setForeground(Color.BLACK);
                        dateTimeLabel.setForeground(Color.BLACK);
                        insightLabel.setForeground(Color.BLACK);
                        historyArea.setBackground(LIGHT_INPUT);
                        historyArea.setForeground(Color.BLACK);
                    } else {
                        setBackgroundImage(DARK_MODE_IMAGE_PATH);
                        amountField.setBackground(DARK_INPUT);

                        // Ensure key labels are white in dark mode
                        resultLabel.setForeground(Color.WHITE);
                        rateLabel.setForeground(Color.WHITE);
                        dateTimeLabel.setForeground(Color.WHITE);
                        insightLabel.setForeground(Color.WHITE);

                        historyArea.setBackground(DARK_INPUT);
                        historyArea.setForeground(Color.WHITE);

                        // Dark mode button color adjustments
                        convertButton.setBackground(new Color(0, 128, 0));     // Dark Green
                        reverseButton.setBackground(new Color(255, 140, 0));   // Dark Orange
                        historyButton.setBackground(new Color(70, 130, 180));  // Steel Blue
                        themeButton.setBackground(Color.LIGHT_GRAY);
                        copyButton.setBackground(new Color(255, 20, 147));     // Deep Pink
                        clearButton.setBackground(new Color(139, 0, 0));       // Dark Red
                        exportButton.setBackground(new Color(70, 130, 180));   // Steel Blue
                    }


                    isDarkMode = !isDarkMode;

                    Timer fadeInTimer = new Timer(50, null);
                    fadeInTimer.addActionListener(new ActionListener() {
                        int alpha = 0;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (alpha < 255) {
                                alpha += 15;
                                setComponentsAlpha(alpha);
                            } else {
                                fadeInTimer.stop();
                            }
                        }
                    });
                    fadeInTimer.start();
                }
            }
        });
        fadeOutTimer.start();
    }

    private void setComponentsAlpha(int alpha) {
        for (Component component : getContentPane().getComponents()) {
            component.setForeground(new Color(0, 0, 0, alpha));
            if (component instanceof JPanel) {
                for (Component innerComponent : ((JPanel) component).getComponents()) {
                    innerComponent.setForeground(new Color(0, 0, 0, alpha));
                }
            }
        }
        repaint();
    }
}
