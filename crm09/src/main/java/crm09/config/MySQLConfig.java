package crm09.config;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConfig {
	
	// Database URL, username, and password
	private static final String url = "jdbc:mysql://localhost:3307/crmapp";
	private static final String user = "root";
	private static final String password = "admin123";
	public static Connection getConnection() throws ConnectException {
		Connection connection = null;
		try {
			// Load MySQL JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Establish connection to the database
			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("Error connecting to the database: " + e.getMessage());
		}
		return connection;
	}
}
