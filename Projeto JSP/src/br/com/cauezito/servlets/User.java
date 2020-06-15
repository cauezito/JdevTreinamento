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
			if(dao.delete(Long.parseLong(id))){
				request.setAttribute("msgSuccess", "Usu�rio deletado com sucesso!");
			}			
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
		String login = request.getParameter("login").trim();
		String password = request.getParameter("password").trim();
		String name = request.getParameter("name");
		String lastName = request.getParameter("lastName");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone").trim();

		UserBean user = new UserBean();
		user.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
		user.setLogin(login);
		user.setPassword(password);
		user.setName(name);		
		user.setLastName(lastName);
		user.setGender(gender);

		if(this.checkAttribute(phone)) {	
			user.setPhone(phone);
		}

		if(this.checkAttribute(id) && !dao.validateNewUser(login)) {
			request.setAttribute("user", user);
			request.setAttribute("update", true);
			this.generateMessageError(request);
		} else if(this.checkAttribute(id) && dao.validateNewUser(login)) {
			if(dao.save(user)) {
				this.generateMessageSuccessSave(request);
			}
		} else if(!this.checkAttribute(id)){
			if(!dao.validateUpdate(login, Long.parseLong(id))) {
				this.generateMessageError(request);
				request.setAttribute("user", user);
				request.setAttribute("update", true);
			} else {
				if(dao.update(user)) {
					this.generateMessageSuccessUpdate(request);
				}
			}			
		}

		RequestDispatcher rd = request.getRequestDispatcher("/main.jsp?action=listAll");
		request.setAttribute("users", dao.findAll());
		rd.forward(request, response);	
	}	

	private boolean checkAttribute(String attribute) {
		return attribute == null || attribute.isEmpty();
	}

	private void generateMessageError(HttpServletRequest request) {
		request.setAttribute("msgError", "Desculpe, esse login j� est� sendo utilizado. Tente outro!");
	}

	private void generateMessageSuccessSave(HttpServletRequest request) {
		request.setAttribute("msgSuccess", "Usu�rio cadastrado com sucesso.");
	}

	private void generateMessageSuccessUpdate(HttpServletRequest request) {
		request.setAttribute("msgSuccess", "As informa��es do usu�rio foram atualizadas com sucesso.");
	}
}
