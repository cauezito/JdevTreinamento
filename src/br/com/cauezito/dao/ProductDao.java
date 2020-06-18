package br.com.cauezito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cauezito.beans.CategoryBean;
import br.com.cauezito.beans.ProductBean;
import br.com.cauezito.jdbc.SingleConnection;

public class ProductDao {
	private Connection connection;
	
	public ProductDao() {
		connection =  SingleConnection.getConnection();
	}
	
	public boolean save(ProductBean product) {
		try {
			String sql = "insert into products (name, description, quantity, value, category) values (?, ?, ?, ?, ?)";
			PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, product.getName());
			st.setString(2, product.getDesc());
			st.setInt	(3, product.getQuantity());
			st.setDouble(4, product.getValue());
			st.setInt(5, product.getCategory().getId());
			st.execute();
			connection.commit();
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				product.setId(rs.getLong(1));
			}		
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean delete(Long id) {
		try {
			String sql = "delete from products where id = " + id;
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.execute();	
			connection.commit();
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return false;
	}
	
	public List<ProductBean> findAll(){
		List<ProductBean> list = new ArrayList<ProductBean>();
		
		try {
			String sql = "Select * from products";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ProductBean product = new ProductBean();
				product.setId(rs.getLong("id"));
				product.setName(rs.getString("name"));
				product.setDesc(rs.getString("description"));
				product.setQuantity(rs.getInt("quantity"));
				product.setValue(rs.getDouble("value"));
				product.setCategory(this.findCategoryById(rs.getInt("category")));			
				
				list.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return list;
	}

	public ProductBean findById(Long id) {
		try {
			String sql = "Select * from products where id = " + id;
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ProductBean product = new ProductBean();
				product.setId(rs.getLong("id"));
				product.setName(rs.getString("name"));
				product.setDesc(rs.getString("description"));
				product.setQuantity(rs.getInt("quantity"));
				product.setValue(rs.getDouble("value"));
				product.setCategory(this.findCategoryById(rs.getInt("category")));
				return product;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return null;
	}

	public boolean update(ProductBean product) {		
		try {
			String sql = "update products set name = ?, description = ?, quantity = ?, value = ?, category = ? "
					+ " where id = " + product.getId();
			PreparedStatement ps;
			ps = connection.prepareStatement(sql);
			ps.setString(1, product.getName());
			ps.setString(2, product.getDesc());
			ps.setInt(3, product.getQuantity());
			ps.setDouble(4, product.getValue());
			ps.setInt(5, product.getCategory().getId());
			ps.executeUpdate();
			connection.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;		
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

	public CategoryBean findCategoryById(Integer id) {
		CategoryBean category = null;
		try {
			String sql = "select * from categories where id =" + id;
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				category = new CategoryBean();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
			}
			return category;
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	public boolean validateNewProduct(String name) {
		try {
			String sql = "select count(1) as qtd from products where name = '" + name + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt("qtd") <= 0 /*return true*/;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean validateUpdate(String name, Long id) {
		try {
			String sql = "select count(1) as qtd from products where name = '" + name + "'"
					+ " and id <> " + id;
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt("qtd") <= 0 /*return true*/;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
