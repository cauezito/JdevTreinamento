package br.com.cauezito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.cauezito.beans.UserBean;
import br.com.cauezito.jdbc.SingleConnection;

public class LoginDao {
	
	private Connection connection;
	
	public LoginDao() {
		connection = SingleConnection.getConnection();
	}
	
	public UserBean validateLogin(String login, String password) {
		UserBean userBean;
		String sql = "Select * from users where login = '" + login + "' and password = '" + password + "'";
		
		try {
			PreparedStatement ps;
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {		
				userBean = new UserBean();
				userBean.setLogin(rs.getString("login"));
				userBean.setPassword(rs.getString("password"));
				return userBean;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
