package br.com.cauezito.servlets;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/catchExceptions")
public class CatchExceptions extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CatchExceptions() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer.parseInt((request.getParameter("name")));
			response.setStatus(200); //Ok
			response.getWriter().write("Processada com sucesso!");
		} catch (Exception e) {
			response.setStatus(500); //erro interno
			response.getWriter().write("Erro ao processar: " + e.getMessage());
		}
		
	}

}
