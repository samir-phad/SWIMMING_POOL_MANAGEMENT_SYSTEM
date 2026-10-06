package com.swimmingpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
		
	private static final String URL = "jdbc:mysql://localhost:3306";
	private static final String USER = "root";
	private static final String PASSWORD = "Password";
	
	public static Connection getConnection() {
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(
					URL,
					USER,
					PASSWORD
			);
		}catch(SQLException e) {
			System.out.println("Database Connection Failed!");
		}
		return connection;
		
	}
	
}
