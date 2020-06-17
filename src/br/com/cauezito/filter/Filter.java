package br.com.cauezito.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import br.com.cauezito.jdbc.SingleConnection;

//Todas as urls serão interceptadas e passarão pelo filter.
@WebFilter(urlPatterns = {"/*"})
public class Filter implements javax.servlet.Filter{

	private static Connection connection = SingleConnection.getConnection();
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		try {
			arg2.doFilter(arg0, arg1);
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
	public void init(FilterConfig config) throws ServletException{
		SingleConnection.getConnection();
	}

	@Override
	public void destroy() {
		
		
	}

}
