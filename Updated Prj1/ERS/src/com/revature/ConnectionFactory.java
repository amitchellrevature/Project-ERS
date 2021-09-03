

import java.sql.*;

public class ConnectionFactory {

    private static Connection connection = null;

    private ConnectionFactory() {
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            String url = "jdbc:mysql://localhost:3306/ers";
            String username = "root";
            String password = "root";
            try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }
}
