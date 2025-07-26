package com.CGU.PROJECT;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.Scanner;
import org.json.JSONObject;

public class CurrencyConverter {
    private final Map<String, Double> exchangeRates;
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/c238f1f25889644bf5d5aec9/latest/USD";

    public CurrencyConverter() {
        exchangeRates = new HashMap<>();
        fetchLiveExchangeRates();
    }

    private void fetchLiveExchangeRates() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            Scanner scanner = new Scanner(connection.getInputStream());
            StringBuilder jsonResponse = new StringBuilder();
            while (scanner.hasNext()) {
                jsonResponse.append(scanner.nextLine());
            }
            scanner.close();

            JSONObject response = new JSONObject(jsonResponse.toString());
            JSONObject rates = response.getJSONObject("conversion_rates");

            for (String currency : rates.keySet()) {
                exchangeRates.put(currency, rates.getDouble(currency));
            }
        } catch (IOException e) {
            System.err.println("Error fetching live exchange rates: " + e.getMessage());
            initializeFallbackRates();
        }
    }

    private void initializeFallbackRates() {
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.92);
        exchangeRates.put("INR", 87.18);
        exchangeRates.put("GBP", 0.77);
        exchangeRates.put("JPY", 148.22);
    }

    public double convert(double amount, String from, String to) {
        double fromRate = exchangeRates.getOrDefault(from, 1.0);
        double toRate = exchangeRates.getOrDefault(to, 1.0);
        return amount * (toRate / fromRate);
    }

    public double getExchangeRate(String from, String to) {
        return exchangeRates.getOrDefault(to, 1.0) / exchangeRates.getOrDefault(from, 1.0);
    }

    public String[] getCurrencyCodes() {
        Set<String> currencyCodes = exchangeRates.keySet();
        return currencyCodes.toArray(new String[0]);
    }

    public String getDefaultCurrency(String currencyCode) {
        for (String currency : exchangeRates.keySet()) {
            if (currency.startsWith(currencyCode)) {
                return currency;
            }
        }
        return null;
    }
}
