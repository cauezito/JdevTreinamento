package br.com.cauezito.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cauezito.beans.ProductBean;
import br.com.cauezito.dao.ProductDao;

@WebServlet("/manageProduct")
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductDao dao = new ProductDao();
    
    public Product() {
        super();        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		
		
		if(action.equals("delete")) {
			if(dao.delete(Long.parseLong(id))){
				request.setAttribute("msgSuccess", "Produto deletado com sucesso!");
			}			
			RequestDispatcher rd = request.getRequestDispatcher("/products.jsp");
			request.setAttribute("products", dao.findAll());
			rd.forward(request, response);
		} else if(action.equals("update")) {
			ProductBean product = dao.findById(Long.parseLong(id));			
			RequestDispatcher rd = request.getRequestDispatcher("/products.jsp");
			request.setAttribute("update", true);
			request.setAttribute("product", product);
			rd.forward(request, response);
		} else if(action.equals("listAll")) {
			RequestDispatcher rd = request.getRequestDispatcher("/products.jsp");
			request.setAttribute("products", dao.findAll());
			rd.forward(request, response);
		}

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String desc = request.getParameter("desc");
		String quantity = request.getParameter("quantity").trim();
		String value = request.getParameter("value").trim();
		
		ProductBean product = new ProductBean();
		
		product.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
		product.setName(name);
		product.setDesc(desc);
		product.setQuantity(Integer.parseInt(quantity));
		product.setValue(Double.parseDouble(value));
		
		if(this.checkAttribute(id) && !dao.validateNewProduct(name)) {
			request.setAttribute("product", product);
			request.setAttribute("update", true);
			this.generateMessageError(request);
		} else if(this.checkAttribute(id) && dao.validateNewProduct(name)) {
			if(dao.save(product)) {
				this.generateMessageSuccessSave(request);
			}
		} else if(!this.checkAttribute(id)) {
			if(!dao.validateUpdate(name, Long.parseLong(id))) {
				this.generateMessageError(request);
				request.setAttribute("product", product);
				request.setAttribute("update", true);
			} else {
				if(dao.update(product)) {
					this.generateMessageSuccessUpdate(request);
				}
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/products.jsp?action=listAll");
		request.setAttribute("products", dao.findAll());
		rd.forward(request, response);	
		
		
	}
	
	//Work?
	private <T> boolean checkAttribute(T attribute) {
		return attribute == null || ((String) attribute).isEmpty();
	}
	
	private void generateMessageError(HttpServletRequest request) {
		request.setAttribute("msgError", "Esse produto já foi cadastrado.");
	}

	private void generateMessageSuccessSave(HttpServletRequest request) {
		request.setAttribute("msgSuccess", "Produto cadastrado com sucesso.");
	}

	private void generateMessageSuccessUpdate(HttpServletRequest request) {
		request.setAttribute("msgSuccess", "As informações do produto foram atualizadas com sucesso.");
	}
}
