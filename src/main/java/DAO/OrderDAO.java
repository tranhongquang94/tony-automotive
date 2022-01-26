package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Order;
import utils.DBUtil;

public class OrderDAO {
	public int insertOrder(int userId, String orderTime) {
		Connection conn = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		Order order = null;
		
		try {
			conn = DBUtil.makeConnection();
			String sqlInsert = "INSERT INTO `order` (user_id, order_time) VALUES(?, ?)";
			//Add generated keys to prepare statement to retrieve inserted id
			ps= conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, userId);
			ps.setString(2, orderTime);
			
			ps.execute();
			//Retrieve any auto generated keys 
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int newId = rs.getInt(1);
				order = new Order(newId, userId, orderTime);
				return newId;
			} else {
				return 0;				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return 0;
	}
}
