package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.Order;
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
	
	public void insertBatchOrderDetails(int newOrderId, List<Integer> carIdList) {
		Connection conn = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		Order_details order_detail = null;
		
		try {
			conn = DBUtil.makeConnection();
			String sqlInsert = "INSERT INTO order_details (order_id, car_id, quantity) VALUES(? ,? ,1)";
			ps = conn.prepareStatement(sqlInsert);
			for (int i = 0; i < carIdList.size(); i++) {
				ps.setInt(1, newOrderId);
				ps.setInt(2, carIdList.get(i));
				ps.addBatch();
				order_detail = new Order_details(newOrderId, carIdList.get(i), 1);
			}
			ps.executeBatch();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, ps, rs);
		}
	}
	
	public void insert (Order order, List<Order_details> orderDetailsList) throws SQLException {
		Connection conn = null;
		PreparedStatement ps= null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		int orderId = 0;
		
		try {
			conn = DBUtil.makeConnection();
			
			conn.setAutoCommit(false);
			
			//Insert Order
			String insertSQL = "INSERT INTO `order` (user_id, order_time) VALUES(?, ?)";
			ps = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, order.getUserId());
			ps.setString(2, order.getOrderTime());
			
			ps.execute();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				orderId = rs.getInt(1);
			}
			
			// Insert Order_details
			insertSQL = "INSERT INTO order_details (order_id, car_id, quantity) VALUES(? ,? ,1)";
			ps2 = conn.prepareStatement(insertSQL);
			for (Order_details od : orderDetailsList ) {
				ps2.setInt(1, orderId);
				ps2.setInt(2, od.getCarId());
				ps2.addBatch();
			}
			
			ps2.executeBatch();
			
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			conn.rollback();
		}
	}
}
