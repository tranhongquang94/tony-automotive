package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Users;
import utils.DBUtil;

public class UsersDAO {
	public Users getUsersByEmailAndPassword (String email, String password) throws SQLException {
		// access to db to get student records
		Connection conn = null;
		
		//Object to set parameter for sql string.
		PreparedStatement ps= null;
		
		// Object to receive return records from db.
		ResultSet rs = null;
		
		Users users = null;
		
		try {
			conn = DBUtil.makeConnection();
			String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				int id = rs.getInt("id");
				String fullName = rs.getString("full_name");
				String birthDate = rs.getString("birth_date");
				String gender = rs.getString("gender");
				
				users = new Users(id, fullName, email, password, birthDate, gender);
			} else {
				return null;
			}
			return users;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return null;
	}
	
	public int insertUsers (String fullName, String email, String password, String birthDate, String gender) {
		Connection conn = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		Users users = null;
		
		try {
			conn = DBUtil.makeConnection();
			String sqlInsert = "INSERT INTO users (full_name, email, password, birth_date, gender) VALUES(?, ?, ? ,?, ?)";
			//Add generated keys to prepare statement to retrieve inserted id
			ps= conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, fullName);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, birthDate);
			ps.setString(5, gender);
			
			ps.execute();
			//Retrieve any auto generated keys 
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int newId = rs.getInt(1);
				users = new Users(newId, fullName, email, password, birthDate, gender);
				return 1;
			} else {
				return 0;				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return 0;
	}
	
	protected static void close(Connection conn, Statement ps, ResultSet rs) {
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