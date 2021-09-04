import java.sql.*;

import org.hibernate.cfg.Configuration;

public class ConnectionFactory {

    private static Connection connection = null;
    private static Configuration cfg = null;

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
    
	public static Configuration configHibernate() {
		
		if(cfg == null) {
			
			cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
		}
		return cfg;
	}
}