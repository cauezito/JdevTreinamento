package br.com.cauezito.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cauezito.dao.LoginDao;
 
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao = new LoginDao();
       

    public Login() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		try {
			if(loginDao.validateLogin(login, password)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
				dispatcher.forward(request, response);
				
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				request.setAttribute("msg", "Nome de usu�rio ou senha incorretos");
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}