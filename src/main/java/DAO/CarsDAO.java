package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Car;
import utils.Constant;
import utils.DBUtil;

public class CarsDAO {
	public List<Car> getListOfCars () {
		List<Car> cars = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		Car car = null;

		try {
			String sql = "SELECT * FROM car";
			rs = DBUtil.retrieveRecordFromDatabase(conn, ps, sql, 0);
			
			while (rs.next()) {
				car = new Car();
				car.setId(rs.getInt("id"));
				car.setName(rs.getString("name"));
				car.setPrice(rs.getDouble("price"));
				car.setImageURL(rs.getString("imageURL"));
				
				cars.add(car);
			}
			return cars;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return null;
	};
	
	public List<Car> getCarsByCategoryId(int categoryId) {
		List<Car> cars = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		Car car = null;
		
		try {
			
			String sql = "SELECT * FROM car WHERE category_id = ?";
			rs = DBUtil.retrieveRecordFromDatabase(conn, ps, sql, categoryId);
			
			while (rs.next()) {
				car = new Car();
				car.setId(rs.getInt("id"));
				car.setName(rs.getString("name"));
				car.setPrice(rs.getDouble("price"));
				car.setImageURL(rs.getString("imageURL"));
				
				cars.add(car);
			}
			return cars;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return null;
	}
	
	public Car getCarById(int carId) {
		Connection conn = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		Car car = null;
		
		try {
			String sql = "SELECT * FROM car WHERE id = ?";
			rs = DBUtil.retrieveRecordFromDatabase(conn, ps, sql, carId);
			
			if (rs.next()) {
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				String imageURL  = rs.getString("imageURL");
				String make = rs.getString("make");
				String model = rs.getString("model");
				int year = rs.getInt("year");
				String createdAt = rs.getString("created_at");
				int categoryId = rs.getInt("category_id");
				int stock = rs.getInt("stock");
				
				car = new Car(carId, name, price, imageURL, make, model, year, createdAt, categoryId, stock);
			}
			return car;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return null;
	}
	
	public int getNumOfPages() {
		Connection conn = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		int pageNum = 0;
		try {
			
			String sql = "SELECT ceil(count(*)/2) FROM car";
			rs = DBUtil.retrieveRecordFromDatabase(conn, ps, sql, 0);
			
			if(rs.next()) {
				pageNum = rs.getInt(1);
			}
			return pageNum;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return 0;
	}
	
	public List<Car> getCarByPages(int page) {
		List<Car> cars = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		Car car = null;
		
		try {
			conn = DBUtil.makeConnection();
			String sql = "SELECT * from car LIMIT ?, ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page - 1)* Constant.TOTAL_CARS_PER_PAGE);
			ps.setInt(2, Constant.TOTAL_CARS_PER_PAGE);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				car = new Car();
				car.setId(rs.getInt("id"));
				car.setName(rs.getString("name"));
				car.setPrice(rs.getDouble("price"));
				car.setImageURL(rs.getString("imageURL"));
				
				cars.add(car);
			}
			return cars;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return null;
	}
	
	public List<Car> getMostOrderedCars() {
		List<Car> cars = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		Car car = null;
		try {
			String sql = "SELECT c.id, c.name, c.price, c.imageURL "
					+ "FROM car c "
					+ "JOIN order_details od ON c.id = od.car_id "
					+ "GROUP BY c.name "
					+ "ORDER BY sum(quantity) DESC "
					+ "LIMIT 4";
			rs = DBUtil.retrieveRecordFromDatabase(conn, ps, sql, 0);
			while (rs.next()) { 
				car = new Car();
				car.setId(rs.getInt("id"));
				car.setName(rs.getString("name"));
				car.setPrice(rs.getDouble("price"));
				car.setImageURL(rs.getString("imageURL"));
				
				cars.add(car);
			}
			return cars;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return null;
	}
}
