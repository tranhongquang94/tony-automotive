package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Order_details;
import utils.DBUtil;

public class Order_detailsDAO {
	public int insertOrderDetails(int orderId, int carId, int quantity) {
		Connection conn = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		Order_details order_detail = null;
		
		try {
			conn = DBUtil.makeConnection();
			String sqlInsert = "INSERT INTO order_details (order_id, car_id, quantity) VALUES(? ,?, ?)";
			//Add generated keys to prepare statement to retrieve inserted id
			ps= conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, orderId);
			ps.setInt(2, carId);
			ps.setInt(3, quantity);
			
			ps.execute();
			//Retrieve any auto generated keys 
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				order_detail = new Order_details(orderId, carId, quantity);
				return 1;
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
