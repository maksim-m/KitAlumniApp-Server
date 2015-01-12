package edu.kit.isco.KitAlumniApp.server.db;

import java.sql.*;

public class DbHandler {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/";

	// Database credentials
	static final String USER = "alumniAppServer";
	static final String PASS = "12345";
	
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public DbHandler() {
			try {
				Class.forName(JDBC_DRIVER);
				
				// Open a connection
				connection = DriverManager.getConnection(DB_URL, USER, PASS);
				
				// Execute a query
				statement = connection.createStatement();
				String sql = "CREATE DATABASE TEST";
				statement.executeUpdate(sql);
				
				connection.close();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (statement != null) {
						statement.close();
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				try {
					if (connection != null) {
						connection.close();
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
	}
}
