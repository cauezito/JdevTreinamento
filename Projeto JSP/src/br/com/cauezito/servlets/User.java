package br.com.cauezito.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cauezito.beans.UserBean;
import br.com.cauezito.dao.UserDao;

@WebServlet("/saveUser")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao dao = new UserDao();
  
    public User() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		UserBean user = new UserBean();
		user.setLogin(login);
		user.setPassword(password);
		dao.save(user);
		
	}

}
