package br.com.cauezito.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cauezito.beans.UserBean;
import br.com.cauezito.dao.UserDao;

@WebServlet("/manageUser")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao dao = new UserDao();
  
    public User() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		
		if(action.equals("delete")) {
			dao.delete(Long.parseLong(id));
			RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
			request.setAttribute("users", dao.findAll());
			rd.forward(request, response);
		} else if(action.equals("update")) {
			UserBean user = dao.findById(Long.parseLong(id));			
			RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
			request.setAttribute("update", true);
			request.setAttribute("user", user);
			rd.forward(request, response);
		} else if(action.equals("listAll")) {
			RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
			request.setAttribute("users", dao.findAll());
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String lastName = request.getParameter("lastName");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		
		UserBean user = new UserBean();
		user.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
		user.setLogin(login);
		user.setPassword(password);
		user.setName(name);		
		user.setLastName(lastName);
		user.setGender(gender);
		if(this.checkAtribute(phone)) {	
			user.setPhone(phone);
		}
		
		if(this.checkAtribute(id) && !dao.validateNewUser(login)) {
			request.setAttribute("msg", "Esse usuário já existe!");
		} else if(this.checkAtribute(id) && dao.validateNewUser(login)) {
			dao.save(user);
		} else if(!this.checkAtribute(id)){
			dao.update(user);
		}		
		
		RequestDispatcher rd = request.getRequestDispatcher("/main.jsp?action=listAll");
		request.setAttribute("users", dao.findAll());
		rd.forward(request, response);
		
	}	
			
	 private boolean checkAtribute(String atribute) {
		return atribute == null || atribute.isEmpty();
	}

}
