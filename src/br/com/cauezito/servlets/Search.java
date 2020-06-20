package br.com.cauezito.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cauezito.dao.UserDao;

@WebServlet("/search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao dao = new UserDao();
 
    public Search() {
        super();
      
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String description = request.getParameter("description");
		if(description != null) {
				RequestDispatcher rd = request.getRequestDispatcher("PrivateUsers/users.jsp");
				request.setAttribute("users", dao.findByName(description));
				rd.forward(request, response);
		}
	}

}
