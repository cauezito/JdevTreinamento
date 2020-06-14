package br.com.cauezito.dao;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cauezito.beans.UserBean;
import br.com.cauezito.jdbc.SingleConnection;

public class UserDao {

	private Connection connection;
	
	public UserDao() {
		connection =  SingleConnection.getConnection();
	}
	
	public void save(UserBean user) {
		try {
			String sql = "insert into users (login, password, name) values (?, ?, ?)";
			PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, user.getLogin());
			st.setString(2,  user.getPassword());
			st.setString(3,  user.getName());
			st.execute();
			connection.commit();
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				user.setId(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void delete(Long id) {
		try {
			String sql = "delete from users where id = " + id;
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.execute();	
			connection.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public List<UserBean> findAll(){
		List<UserBean> list = new ArrayList<UserBean>();
		
		try {
			String sql = "Select * from users";
			PreparedStatement ps;
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				UserBean user = new UserBean();
				user.setId(rs.getLong("id"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return list;
	}

	public UserBean update(Long id) {
		try {
			String sql = "Select * from users where id = " + id;
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				UserBean user = new UserBean();
				user.setId(rs.getLong("id"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				return user;
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

	public void update(UserBean user) {		
		try {
			String sql = "update users set login = ?, password = ?, name = ? where id = " + user.getId();
			PreparedStatement ps;
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		
	}
}
