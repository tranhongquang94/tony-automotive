package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import utils.DBUtil;

public class CategoryDAO {
	public List<Category> getCategories() {
		List<Category> categories = new ArrayList<Category>();
		Connection conn = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		
		Category category = null;
		
		try {
			conn = DBUtil.makeConnection();
			String sql = "SELECT * FROM category WHERE `show` = 1";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				boolean show = rs.getBoolean("show");
				
				category = new Category(id, name, show);
				categories.add(category);
			};
			return categories;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return null;
	}
}
