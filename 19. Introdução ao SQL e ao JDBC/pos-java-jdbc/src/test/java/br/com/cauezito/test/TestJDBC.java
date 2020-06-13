package br.com.cauezito.test;

import java.util.List;

import org.junit.Test;

import br.com.cauezito.dao.UserDAO;
import br.com.cauezito.jdbc.SingleConnection;
import br.com.cauezito.model.Phone;
import br.com.cauezito.model.User;
import br.com.cauezito.model.UserPhone;

public class TestJDBC {
	UserDAO dao = null;
	User user = null;
	
	@Test
	public void save() {
		dao = new UserDAO();
		user = new User();
		
		user.setName("Lucas");
		user.setEmail("santosssss@santos");
		dao.save(user);
	}
	
	@Test
	public void findAll() {
		dao = new UserDAO();
		System.out.println(dao.findAll());
	}
	
	@Test
	public void findById() {
		dao = new UserDAO();
		user = dao.findById(11L);
		System.out.println(user);
	}
	
	@Test
	public void update() {
		dao = new UserDAO();
		user = dao.findById(11L);
		user.setName("Mudei o nome!!!");
		dao.update(user);
	}
	
	@Test
	public void delete() {
		dao = new UserDAO();
		dao.delete(11L);
	}
	
	@Test
	public void savePhone() {
		Phone phone = new Phone();
		phone.setNumber("58932972");
		phone.setType("residencial");
		phone.setOwner(14L);
		
		UserDAO dao = new UserDAO();
		dao.savePhone(phone);
	}
	
	@Test
	public void loadUserPhone() {
		dao = new UserDAO();
		List<UserPhone> list = dao.findPhoneById(14L);
		//tipo, nome, objeto a ser iterado
		for(UserPhone phone : list) {
			System.out.println(phone);
			System.out.println("----------------------");
		}
	}
	
	@Test
	public void deleteUserPhone() {
		dao = new UserDAO();
		dao.deletePhoneByUser(14L);
	}
}
