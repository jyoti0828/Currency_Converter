# Currency Converter Application

A comprehensive Java-based Currency Converter application featuring a user-friendly GUI, real-time exchange rates, multi-currency conversion, and various helpful insights. Developed using Java Swing, AWT, and the Collections Framework, this application includes functionalities like history export, theme switching, and multi-currency conversion.

## Features

- **Currency Conversion**: Convert between multiple currencies with real-time exchange rates.
- **Multi-Currency Conversion**: Simultaneously calculate conversion for multiple currencies.
- **Interactive GUI**: Developed using Java Swing with customized buttons and enhanced UI design.
- **Login System**: Secure access through a custom-built login page.
- **History Log**: Tracks previous conversions and allows exporting to .txt or .csv.
- **Theme Switching**: Toggle between light and dark mode for improved visibility.
- **Market Insights**: Displays useful insights on exchange rate trends.
- **Favorite Currencies**: Quickly access frequently used currency pairs.

## Installation

1. Clone the repository:
   ```bash
   git clone <https://github.com/anupam-pujari/Currency-converter>
   ```
2. Navigate to the project directory:
   ```bash
   cd CurrencyConverter
   ```
3. Compile the project files:
   ```bash
   javac com/CGU/PROJECT/*.java
   ```
4. Run the application:
   ```bash
   java com.CGU.PROJECT.LoginPage
   ```

## File Structure
```
CurrencyConverter
├── src
│   └── com
│       └── CGU
│           └── PROJECT
│               ├── CurrencyConverter.java
│               ├── CurrencyConverterUI.java
│               ├── CustomButton.java
│               ├── LoginPage.java
│               ├── BACKGROUND.jpg
│               └── DARK.png
├── README.md
```

## How to Use
1. Login: Use default credentials (user / 123) to log in.
2. Currency Conversion: Enter the amount, select the source currency, and the target currency, then click Convert.
3. Multi-Currency Conversion: Click Multi Convert and select the desired target currencies.
4. Theme Switch: Click Toggle Theme to switch between light and dark mode.
5. History Management: Click Show History to view previous conversions or Export History to save them to a file.
6. Market Insights: Conversion results display helpful insights about exchange rate trends.

## Dependencies
- Java SE Development Kit (JDK) 17 or higher
- Java Swing for GUI components

