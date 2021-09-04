package requests;
import java.sql.*;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.EnhancedPatternLayout;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.cfg.Configuration;

public class ConnectionFactory {
	
    private static Connection connection = null;
    private static Configuration cfg = null;
    
    private static Logger logger = null;
    private static ConsoleAppender consoleAppender = null;
//    private static FileAppender fileAppender = null;
//    private static Properties properties = null;
//    private static final String LOG_FILE = "log4j.properties";
    
    private ConnectionFactory() {}

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
	
	public static Logger configLogger(Class className) {		    
		try {
			
			if(logger == null) { 
				
				logger = LogManager.getLogger(className);
				consoleAppender = new ConsoleAppender();
				consoleAppender.setThreshold(Level.INFO);
				consoleAppender.setLayout(new EnhancedPatternLayout("%5p [%F:%L] - %m%n"));
				consoleAppender.activateOptions();
				LogManager.getRootLogger().addAppender(consoleAppender);
				
//				fileAppender = new FileAppender();
//				fileAppender.setThreshold(Level.INFO);
//				fileAppender.setLayout(new EnhancedPatternLayout("%5p [%F:%L] - %m%n"));
//				fileAppender.activateOptions();
//				LogManager.getRootLogger().addAppender(fileAppender);
			}
//			if(properties == null) { properties = new Properties(); }			
//			properties.load(new FileInputStream(LOG_FILE));
//			PropertyConfigurator.configure(properties);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return logger;
	}
}