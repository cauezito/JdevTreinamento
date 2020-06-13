package br.com.cauezito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cauezito.jdbc.SingleConnection;
import br.com.cauezito.model.Phone;
import br.com.cauezito.model.User;
import br.com.cauezito.model.UserPhone;

public class UserDAO {
	private Connection connection;

	// injeta a instância da conexão
	public UserDAO() {
		connection = SingleConnection.getConnection();
	}

	public void save(User user) {

		try {
			String sql = "insert into users (name, email) values  (?, ?)";

			// Possibilita que o insert aconteça.
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.execute();
			// salva no banco
			connection.commit();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				user.setId(rs.getInt(1));
			}
			System.out.println("id: " + user.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<User> findAll() {
		List<User> list = new ArrayList<User>();
		String sql = "select * from users";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				User user = new User();
				user.setId(result.getInt("id"));
				user.setName(result.getString("name"));
				user.setEmail(result.getString("email"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public User findById(Long id) {
		User user = null;
		String sql = "select * from users where id =" + id;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				user = new User();
				user.setId(result.getInt("id"));
				user.setName(result.getString("name"));
				user.setEmail(result.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	public void update(User user) {
		try {
			String sql = "update users set name = ? where id = " + user.getId();
			PreparedStatement st;
			st = connection.prepareStatement(sql);
			st.setString(1, user.getName());
			st.execute();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}
	
	public void delete(Long id) {
		
		try {
			String sql = "delete from users where id = " + id;
			PreparedStatement st;
			st = connection.prepareStatement(sql);
			st.execute();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
	
	public void savePhone(Phone phone) {
		try {
			String sql = "insert into phones (number, type, owner) values (?,?,?);";
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, phone.getNumber());
			st.setString(2, phone.getType());
			st.setLong(3, phone.getOwner());
			st.execute();
			connection.commit();
		}catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public List<UserPhone> findPhoneById(Long id) {
		List<UserPhone> listUserPhone = new ArrayList<UserPhone>();
		String sql = "select * from phones as ph " + 
				"inner join users as us " + 
				"on ph.owner = us.id " + 
				"where us.id =" + id;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				UserPhone userPhone = new UserPhone();
				userPhone.setEmail(rs.getString("email"));
				userPhone.setName(rs.getString("name"));
				userPhone.setNumber(rs.getString("number"));
				listUserPhone.add(userPhone);
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listUserPhone; 
		
	}
	
	//Respeitando a hierarquia de tabelas
	public void deletePhoneByUser(Long id) {
		String sqlPhone = "delete from phones where owner =" + id;
		String sqlUser = "delete from users where id =" + id;
		
		try {
			PreparedStatement ps = connection.prepareStatement(sqlPhone);
			ps.executeUpdate();
			connection.commit();
			
			ps = connection.prepareStatement(sqlUser);
			ps.executeUpdate();
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
}
