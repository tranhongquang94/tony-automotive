package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	private static String DB_URL = "jdbc:mysql://localhost:3306/cars";
	private static String USERNAME = "root";
	private static String PASSWORD = "root";
	
	public static Connection makeConnection () {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
}
