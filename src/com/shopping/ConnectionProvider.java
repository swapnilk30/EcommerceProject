package com.shopping;

import java.sql.Connection;
import java.sql.DriverManager;



public class ConnectionProvider {
	
	static Connection connection;
	
	public static Connection createConnection() {
		try {
			//step 1: load the Driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			//step 2: Establish the connection
			String user = "root";
			String password = "root";
			String url = "jdbc:mysql://localhost:3306/store";
			connection =DriverManager.getConnection(url,user,password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
		
	}
}
