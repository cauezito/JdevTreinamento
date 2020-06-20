package br.com.cauezito.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.cauezito.beans.UserBean;
import br.com.cauezito.dao.LoginDao;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao = new LoginDao();


	public Login() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		System.out.println(login);
		UserBean user = loginDao.validateLogin(login, password);
		
		if(user != null) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/PrivatePages/main.jsp");
			request.setAttribute("loginUser", user.getLogin());
			dispatcher.forward(request, response);

		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/PublicPages/login.jsp");
			request.setAttribute("msg", "Nome de usuário ou senha incorretos");
			dispatcher.forward(request, response);
		}		
	}

}
