package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Car;
import utils.DBUtil;

public class CarsDAO {
	public List<Car> getListOfCars () {
		List<Car> cars = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		Car car = null;
		
		try {
			conn = DBUtil.makeConnection();
			String sql = "SELECT * FROM car";
			ps = conn.prepareStatement(sql);
			ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				String imageURL  = rs.getString("imageURL");
				String make = rs.getString("make");
				String model = rs.getString("model");
				int year = rs.getInt("year");
				String createdAt = rs.getString("created_at");
				
				car = new Car(id, name, price, imageURL, make, model, year, createdAt);
				cars.add(car);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			UsersDAO.close(conn, ps, rs);
		}
		return cars;
	}
}
