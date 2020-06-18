package br.com.cauezito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cauezito.beans.CategoryBean;
import br.com.cauezito.jdbc.SingleConnection;

public class CategoryDao {
	
private Connection connection;
	
	public CategoryDao() {
		connection =  SingleConnection.getConnection();
	}	
	
	public List<CategoryBean> findAllCategories(){
		List<CategoryBean> list = new ArrayList<CategoryBean>();
		try {
			String sql = "select * from categories";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				CategoryBean cb = new CategoryBean();
				cb.setId(rs.getInt("id"));
				cb.setName(rs.getString("name"));
				list.add(cb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
