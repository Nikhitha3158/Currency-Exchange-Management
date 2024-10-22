package oooproj;

import pack2.conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Passenger {
    private String name;
    private String passportNumber;
    private String exchangeId;

    // Constructor
    public Passenger(String name, String passportNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
        this.exchangeId = generateExchangeId();
    }

    // Generate unique Exchange ID
    private String generateExchangeId() {
        return "EX-" + System.currentTimeMillis();  // Sample ID generation logic
    }
    // Insert passenger into database
    public void saveToDatabase(String currencyFrom, String currencyTo, double amount, double exchangedAmount) {
        try (Connection con = conn.getConnection()) {
            String query = "INSERT INTO passengers (name, passport_number, exchange_id, currency_from, currency_to, amount, exchanged_amount) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, this.name);
            ps.setString(2, this.passportNumber);
            ps.setString(3, this.exchangeId);
            ps.setString(4, currencyFrom);
            ps.setString(5, currencyTo);
            ps.setDouble(6, amount);
            ps.setDouble(7, exchangedAmount);
            ps.executeUpdate();
            System.out.println("Passenger details saved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve passenger from database by exchange ID
    public static void getPassengerByExchangeId(String exchangeId) {
        try (Connection con = conn.getConnection()) {
            String query = "SELECT * FROM passengers WHERE exchange_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, exchangeId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Passenger Name: " + rs.getString("name"));
                System.out.println("Currency From: " + rs.getString("currency_from"));
                System.out.println("Currency To: " + rs.getString("currency_to"));
                System.out.println("Amount: " + rs.getDouble("amount"));
                System.out.println("Exchanged Amount: " + rs.getDouble("exchanged_amount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getName(){
        return this.name;
    }
    public String getPassportNumber(){
        return this.passportNumber;
    }
    public String getExchangeId() {
        return this.exchangeId;
    }
}
