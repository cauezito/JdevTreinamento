package br.com.cauezito.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.cauezito.beans.UserBean;
import br.com.cauezito.jdbc.SingleConnection;

//Todas as urls ser�o interceptadas e passar�o pelo filter.
@WebFilter(urlPatterns = {"/PrivatePages/*"})
public class Filter implements javax.servlet.Filter{

	private static Connection connection = SingleConnection.getConnection();
	
	@Override
	public void init(FilterConfig config) throws ServletException{
		SingleConnection.getConnection();
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			UserBean user = (UserBean) session.getAttribute("user");
				
			if(user == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/PublicPages/login.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			chain.doFilter(request, response);
			connection.commit();
		} catch (Exception e) {
			try {
				e.printStackTrace();
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}		
	}

	@Override
	public void destroy() {
		
		
	}

}
