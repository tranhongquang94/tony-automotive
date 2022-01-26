package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
	};
	
	public static ResultSet retrieveRecordFromDatabase(Connection conn, PreparedStatement ps, String sql, int sqlParam) {
		ResultSet rs = null;
		
		try {
			conn = DBUtil.makeConnection();
			ps = conn.prepareStatement(sql);
			if (sqlParam != 0) {
				ps.setInt(1, sqlParam);
			}
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;
	}
	
	public static void close(Connection conn, Statement ps, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null ) {
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
