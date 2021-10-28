package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

	private static String url = "jdbc:sqlite:baseDeDatos.db";
	private static Connection connection;
	
	public static Connection getConnection() throws SQLException {
		connection = DriverManager.getConnection(url);			
		return connection;
	}

}