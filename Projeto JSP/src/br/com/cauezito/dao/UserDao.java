package br.com.cauezito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.cauezito.beans.UserBean;
import br.com.cauezito.jdbc.SingleConnection;

public class UserDao {

	private Connection connection;
	
	public UserDao() {
		connection =  SingleConnection.getConnection();
	}
	
	public void save(UserBean user) {
		try {
			String sql = "insert into users (login, password) values (?, ?)";
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, user.getLogin());
			st.setString(2,  user.getPassword());
			st.execute();
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
