package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static ConnectionFactory instance;
	
	private ConnectionFactory() {}
	
	public static synchronized ConnectionFactory getInstance() {
		if (instance == null) {
			return instance = new ConnectionFactory();
		}
		
		return instance;
	}
	
	public static Connection getConnection() {
		Connection connection = null;
		
		try {
			// Load Drive
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Path database
			String url = "jdbc:mysql://localhost/product";
			
			
			// Return object connect
			connection = DriverManager.getConnection(url, "root", "rootpassword");
			
		}
		catch (SQLException ex) {
			System.out.println("Erro on open connection");
			throw new RuntimeException("Error on open connection: ", ex);
		}
		catch (Exception ex) {
			System.out.println("Internal server error");
			throw new RuntimeException("Internal server error: ", ex);
		}
		
		return connection;
	}

}
