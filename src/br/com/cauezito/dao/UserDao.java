package br.com.cauezito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cauezito.beans.AddressBean;
import br.com.cauezito.beans.PhotoBean;
import br.com.cauezito.beans.UserBean;
import br.com.cauezito.jdbc.SingleConnection;

public class UserDao {

	private Connection connection;
	
	
	public UserDao() {
		connection =  SingleConnection.getConnection();
	}
	
	public boolean save(UserBean user) {
		try {
			String sql = "insert into users (login, password, name, last_name, gender, phone) values (?, ?, ?, ?, ?, ?)";
			PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, user.getLogin());
			st.setString(2,  user.getPassword());
			st.setString(3,  user.getName());
			st.setString(4, user.getLastName());
			st.setString(5, user.getGender());
			st.setString(6, user.getPhone());
			st.execute();

			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				user.setId(rs.getLong(1));
			}
			
			connection.commit();
			
			sql = "insert into adresses (zip_code, address, area, locality, federated_unit, id_user) values (?, ?, ?, ?, ?, ?)";
			st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, user.getAddress().getZipCode());
			st.setString(2, user.getAddress().getAddress());
			st.setString(3, user.getAddress().getArea());
			st.setString(4, user.getAddress().getLocality());
			st.setString(5, user.getAddress().getFederatedUnit());
			st.setInt(6, Integer.parseInt(user.getId().toString()));
			st.execute();
			
			rs = st.getGeneratedKeys();
			if(rs.next()) {
				user.getAddress().setId(rs.getInt(1));
			}
			
			connection.commit();
			if(user.getPhoto() != null) {
				sql = "insert into photos (base64, content_type, id_user) values (?, ?, ?)";
				st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				st.setString(1, user.getPhoto().getBase64());
				st.setString(2, user.getPhoto().getContentType());
				st.setInt(3, Integer.parseInt(user.getId().toString()));
				st.execute();
				
				rs = st.getGeneratedKeys();
				if(rs.next()) {
					user.getPhoto().setId(rs.getInt(1));
				}
				
				connection.commit();
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
			String sql = "delete from users where login <> 'cauezito' and id = " + id;
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
	
	public List<UserBean> findByName (String name){
		String sql = "select * from users where login <> 'cauezito' and name ilike '%" + name + "%'";
		return returnListUsers(sql);
	}
	
	public List<UserBean> findAll(){		
		String sql = "select * from users where login <> 'cauezito'";
		return returnListUsers(sql);
	}

	private List<UserBean> returnListUsers(String sql){
		List<UserBean> list = new ArrayList<UserBean>();
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				UserBean user = new UserBean();
				user.setId(rs.getLong("id"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setLastName(rs.getString("last_name"));
				user.setGender(rs.getString("gender"));
				user.setPhone(rs.getString("phone"));			
				

				user.setAddress(this.findAddressById(Integer.parseInt(user.getId().toString())));	
				user.setPhoto(this.findPhotoById(Integer.parseInt(user.getId().toString())));
				list.add(user);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	
		return list;
	}
	
	public AddressBean findAddressById (Integer id){
		AddressBean address = null;
		try {
			String sql = "Select * from adresses where id_user = " + id;
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				address = new AddressBean();
				address.setId(rs.getInt("id"));
				address.setAddress(rs.getString("address"));
				address.setArea(rs.getString("area"));
				address.setZipCode(rs.getString("zip_code"));
				address.setLocality(rs.getString("locality"));
				address.setFederatedUnit(rs.getString("federated_unit"));
				
			}	
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return address;
	}
	
	public PhotoBean findPhotoById (Integer id){
		PhotoBean photo = null;
		try {
			String sql = "Select * from photos where id_user = " + id;
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				photo = new PhotoBean();
				photo.setId(rs.getInt("id"));
				photo.setBase64(rs.getString("base64"));
				photo.setContentType(rs.getString("content_type"));				
			}	
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return photo;
	}

	public UserBean findById(Long id) {
		try {
			String sql = "Select * from users where id = " + id + " and login <> 'cauezito'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				UserBean user = new UserBean();
				user.setId(rs.getLong("id"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setLastName(rs.getString("last_name"));
				user.setGender(rs.getString("gender"));
				user.setPhone(rs.getString("phone"));				
				user.setAddress(this.findAddressById(Integer.parseInt(id.toString())));
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

	public boolean update(UserBean user) {		
		try {
			String sql = "update users set login = ?, password = ?, name = ?, last_name = ?, gender = ?, phone = ? "
					+ " where id = " + user.getId();
			PreparedStatement ps;
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getGender());
			ps.setString(6, user.getPhone());
			ps.executeUpdate();		
			connection.commit();	
			this.updateAddress(Integer.parseInt(user.getId().toString()) ,user.getAddress());		
			
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
	
	private void updateAddress(Integer id, AddressBean address) throws SQLException {

			String sql = "update adresses set zip_code = ?, address = ?, area = ?, locality = ?, federated_unit = ?"
					+ " where id_user = " + id;
			PreparedStatement ps;
			ps = connection.prepareStatement(sql);
			ps.setString(1, address.getZipCode());
			ps.setString(2, address.getAddress());
			ps.setString(3, address.getArea());
			ps.setString(4, address.getLocality());
			ps.setString(5, address.getFederatedUnit());			
			ps.executeUpdate();
			connection.commit();
	}
	
	public boolean validateNewUser(String login) {
		try {
			String sql = "select count(1) as qtd from users where login = '" + login + "'";
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
	
	public boolean validateUpdate(String login, Long id) {
		try {
			String sql = "select count(1) as qtd from users where login = '" + login + "'"
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
