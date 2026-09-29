\# 💱 Currency Converter



A Java-based desktop \*\*Currency Converter application\*\* built with

\*\*Java Swing\*\*, \*\*AWT\*\*, and the \*\*Collections Framework\*\*. The

application provides currency conversion using exchange-rate data

fetched from an external API, along with multi-currency conversion,

conversion history, theme switching, clipboard support, history export,

and a simple login interface.



\## ✨ Features



\-   💱 \*\*Currency Conversion\*\* --- Convert an amount between supported

&#x20;   currencies.

\-   🌍 \*\*Multi-Currency Conversion\*\* --- Convert one amount into

&#x20;   multiple selected currencies at once.

\-   🔄 \*\*Reverse Conversion\*\* --- Quickly swap the source and target

&#x20;   currencies.

\-   📈 \*\*Exchange Rate Display\*\* --- Shows the exchange rate used for

&#x20;   the conversion.

\-   🕒 \*\*Last Updated Timestamp\*\* --- Displays the time at which the

&#x20;   conversion was performed.

\-   💡 \*\*Market Insight\*\* --- Displays a simple insight related to the

&#x20;   selected currency pair.

\-   📜 \*\*Conversion History\*\* --- Keeps a record of completed

&#x20;   conversions.

\-   📤 \*\*Export History\*\* --- Export conversion history to a file.

\-   📋 \*\*Copy Result\*\* --- Copy the conversion result to the clipboard.

\-   🌓 \*\*Theme Switching\*\* --- Toggle between the available light/dark

&#x20;   UI themes.

\-   🧹 \*\*Clear Function\*\* --- Clear the current conversion

&#x20;   fields/results.

\-   🔐 \*\*Login Interface\*\* --- Starts the application through a custom

&#x20;   Java Swing login screen.

\-   🎨 \*\*Custom UI\*\* --- Uses custom buttons, backgrounds, and styled

&#x20;   Swing components.



\## 🛠️ Technologies Used



&#x20; Technology                 Usage

&#x20; -------------------------- -----------------------------------------

&#x20; ☕ Java                    Core application development

&#x20; 🖥️ Java Swing              Desktop GUI

&#x20; 🎨 AWT                     UI styling and event handling

&#x20; 🗂️ Collections Framework   Exchange-rate and history data handling

&#x20; 🌐 ExchangeRate-API        Exchange-rate data

&#x20; 📦 JSON                    Parsing API responses

&#x20; 🧰 IntelliJ IDEA           Development environment



\## 🔌 Exchange Rate Data



The application fetches exchange-rate data from \*\*ExchangeRate-API\*\*

when the application starts. The retrieved `conversion\_rates` are stored

in a Java `Map` and used for subsequent conversions.



If the API request fails, the application contains fallback

exchange-rate values so that the converter can still operate.



> ⚠️ \*\*Note:\*\* Exchange rates are dynamic and can change over time. The

> timestamp shown in the application is the local time at which the

> conversion was performed; it should not be interpreted as proof that

> the provider's underlying rates changed at that exact second.



\## 🔐 Login



The application starts with a login screen before opening the currency

converter.



For the current demo implementation:



``` text

Username: user

Password: 123

```



> ⚠️ This is a simple local/demo login implemented in the application

> code, not a production-grade authentication system.



\## 🚀 Getting Started



\### Prerequisites



\-   Java \*\*JDK 17 or higher\*\*

\-   IntelliJ IDEA or another Java IDE

\-   Internet connection for fetching exchange-rate data

\-   The JSON library required by the project (`org.json`)



\### Run with IntelliJ IDEA



1\.  Clone the repository:



``` bash

git clone https://github.com/jyoti0828/Currency\_Converter.git

```



2\.  Open the project in \*\*IntelliJ IDEA\*\*.

3\.  Make sure the Java source files are included in the project.

4\.  Make sure the required `org.json` dependency is available.

5\.  Run:



``` text

CurrencyConverterApp.java

```



The application opens the login screen first.



\### ▶️ Application Flow



``` text

CurrencyConverterApp

&#x20;       ↓

&#x20;  Login Page

&#x20;       ↓

&#x20;Currency Converter

&#x20;       ↓

&#x20;┌───────────────┬──────────────────┐

&#x20;│ Convert       │ Multi Convert     │

&#x20;└───────────────┴──────────────────┘

&#x20;       ↓

&#x20;Result + Exchange Rate + Insight

&#x20;       ↓

&#x20;History / Copy / Export / Theme

```



\## 📸 Screenshots



\### 💱 Currency Converter



!\[Currency Converter](screenshots/converter.png)



The main interface allows the user to enter an amount, choose the source

and target currencies, reverse the selection, and perform a conversion.



\### 🌍 Multi-Currency Conversion



!\[Multi-Currency Conversion](screenshots/multi-convert.png)



The multi-convert feature allows multiple target currencies to be

selected and displays their conversion results together.



\### 📜 Conversion History



!\[Conversion History](screenshots/history.png)



The application records completed conversions with their timestamps and

provides an option to export the history.



\### 🔐 Login Screen



!\[Login Screen](screenshots/login.png)



The application starts with a custom login interface before opening the

converter.



\## 📁 Project Structure



``` text

Currency\_Converter/

│

├── src/

│   └── com/

│      └── currencyconverter/

│               ├── CurrencyConverter.java

│               ├── CurrencyConverterApp.java

│               ├── CurrencyConverterUI.java

│               ├── CustomButton.java

│               ├── LoginPage.java

│               ├── BACKGROUND.jpg

│               ├── DARK.png

│               └── LOGIN\_BACKGROUND.jpg

│

├── screenshots/

│   ├── converter.png

│   ├── history.png

│   ├── login.png

│   └── multi-convert.png

│

├── README.md

└── .gitignore

```



> The exact folder layout may vary depending on whether the project is

> opened/imported through an IDE or compiled manually.



\## 🧩 Main Components



\### `CurrencyConverter.java`



Handles exchange-rate retrieval, stores the rates, calculates

conversions, and provides exchange-rate information.



\### `CurrencyConverterUI.java`



Contains the main Swing interface and handles conversion, reverse

conversion, multi-currency conversion, history, theme switching,

clipboard copying, clearing, and history export.



\### `CurrencyConverterApp.java`



Acts as the application entry point and launches the login screen

through Swing's event-dispatch thread.



\### `LoginPage.java`



Provides the login interface and opens the main converter after

successful demo authentication.



\### `CustomButton.java`



Provides the custom-styled button component used by the application.



\## 📌 Supported Currency Examples



The current multi-conversion interface provides options including:



\-   🇺🇸 USD

\-   🇮🇳 INR

\-   🇪🇺 EUR

\-   🇬🇧 GBP

\-   🇯🇵 JPY



The main currency selectors are populated from the exchange-rate data

returned by the API.



\## 🔒 Security \& Configuration Note



The current project is a learning/demo desktop application. For a

production application:



\-   🔑 Do not hard-code API keys directly in source code.

\-   🌱 Store secrets in environment variables or a secure configuration

&#x20;   system.

\-   🔐 Replace the hard-coded demo login with proper authentication.

\-   🛡️ Avoid storing real user passwords in plaintext.



\## 🎯 Learning Outcomes



This project demonstrates practical use of:



\-   Object-oriented Java programming

\-   Java Swing GUI development

\-   Event-driven programming

\-   HTTP requests and API integration

\-   JSON parsing

\-   Java Collections

\-   File handling

\-   Date/time handling

\-   Clipboard operations

\-   Modular class design

\-   Basic desktop application UX



\## 👩‍💻 Author



\*\*Jyoti\*\*



GitHub: \[@jyoti0828](https://github.com/jyoti0828)



\## ⭐ Repository



\[View the Currency Converter project on

GitHub](https://github.com/jyoti0828/Currency\_Converter)



