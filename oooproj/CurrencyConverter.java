package oooproj;

import pack2.conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyConverter {

    // Fetch bid and ask rates from the database
    public double[] getCurrencyRates(String fromCurrency, String toCurrency) {
        double[] rates = new double[2];  // [bid_rate, ask_rate]
        try (Connection con = conn.getConnection()) {
            String query = "SELECT bid_rate, ask_rate FROM currency_rates WHERE from_currency = ? AND to_currency = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, fromCurrency);
            ps.setString(2, toCurrency);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                rates[0] = rs.getDouble("bid_rate");
                rates[1] = rs.getDouble("ask_rate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rates;
    }

    // Convert currency using bid/ask rates
    public double convertCurrency(String fromCurrency, String toCurrency, double amount, boolean isBuying) {
        double[] rates = getCurrencyRates(fromCurrency, toCurrency);
        double rate = isBuying ? rates[1] : rates[0];
        return amount * rate;
    }

    public double exchangeCurrencyForPassenger(String fromCurrency, String toCurrency, double amount) {
        boolean isBuying = true;  // Assume the passenger is buying foreign currency
        return convertCurrency(fromCurrency, toCurrency, amount, isBuying);
    }
}

