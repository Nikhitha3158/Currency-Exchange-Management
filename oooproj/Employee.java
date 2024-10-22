package oooproj;

import pack2.conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee {
    // Add employee
    public void addEmployee(String name, String position, String airport) {
        try (Connection con = conn.getConnection()) {
            String query = "INSERT INTO employees (name, position, airport) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, position);
            ps.setString(3, airport);
            ps.executeUpdate();
            System.out.println("Employee added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View employees
    public void viewEmployees() {
        try (Connection con = conn.getConnection()) {
            String query = "SELECT * FROM employees";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                                   ", Name: " + rs.getString("name") +
                                   ", Position: " + rs.getString("position") +
                                   ", Airport: " + rs.getString("airport"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getName(){
        return this.name;
    }
    public String getPosition(){
        return this.position;
    }
    public String getAirport(){
         return this.airport;
    }
}


