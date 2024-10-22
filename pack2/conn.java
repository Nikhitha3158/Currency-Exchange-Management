package pack2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface conn {
    static Connection getConnection() {
        Connection con = null;
        try {
            String url = "jdbc:mysql://localhost:3306/airport_exchange";
            String user = "root";
            String password = "password";
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
