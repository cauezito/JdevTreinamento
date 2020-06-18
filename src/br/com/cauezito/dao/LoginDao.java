package br.com.cauezito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.cauezito.jdbc.SingleConnection;

public class LoginDao {
	
	private Connection connection;
	
	public LoginDao() {
		connection = SingleConnection.getConnection();
	}
	
	public String validateLogin(String login, String password) {
		String sql = "Select * from users where login = '" + login + "' and password = '" + password + "'";
		
		try {
			PreparedStatement ps;
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {				
				return rs.getString("login");
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
