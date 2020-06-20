package br.com.cauezito.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import br.com.cauezito.beans.AddressBean;
import br.com.cauezito.beans.PhotoBean;
import br.com.cauezito.beans.UserBean;
import br.com.cauezito.dao.UserDao;
import br.com.cauezito.util.ConversionByType;

@WebServlet("/manageUser")
@MultipartConfig
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
				request.setAttribute("msgSuccess", "Usuário deletado com sucesso!");
			}			
			RequestDispatcher rd = request.getRequestDispatcher("/PrivatePages/users.jsp");
			request.setAttribute("users", dao.findAll());
			rd.forward(request, response);
		} else if(action.equals("update")) {
			UserBean user = dao.findById(Long.parseLong(id));			
			RequestDispatcher rd = request.getRequestDispatcher("/PrivatePages/users.jsp");
			request.setAttribute("update", true);
			request.setAttribute("user", user);
			rd.forward(request, response);
		} else if(action.equals("listAll")) {
			RequestDispatcher rd = request.getRequestDispatcher("/PrivatePages/users.jsp");
			request.setAttribute("users", dao.findAll());
			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//User
		String id = request.getParameter("id");
		String login = request.getParameter("login").trim();
		String password = request.getParameter("password").trim();
		String name = request.getParameter("name");
		String lastName = request.getParameter("lastName");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone").trim();
		
		//Address
		String zipCode = request.getParameter("zipCode");
		String address = request.getParameter("address");
		String area = request.getParameter("area");
		String locality = request.getParameter("locality");
		String federatedUnit = request.getParameter("federatedUnit");
		

		List<String> msg = new ArrayList<String>();
		boolean okToInsert = true;
		UserBean user = null;
		AddressBean addressBean = null;
		
		//If there is an ID it means that the user wants to make an update
		if(!this.checkAttribute(id)) {
			user = dao.findById(Long.parseLong(id));
		}
		
		//VALIDATION USER
		if(this.checkAttribute(login)) {
			msg.add("É obrigatório informar um login.");
			okToInsert = false;
		} if (this.checkAttribute(password)) {
			msg.add("É obrigatório informar uma senha.");
			okToInsert = false;
		} if(this.checkAttribute(name)) {
			msg.add("É obrigatório informar um nome.");
			okToInsert = false;
		} if(this.checkAttribute(lastName)) {
			msg.add("É obrigatório informar um sobrenome.");
			okToInsert = false;
		} 
		
		//VALIDATION ADDRESS
		if(!this.checkAttribute(zipCode)) {
			if(this.checkAttribute(address)) {
				msg.add("É obrigatório informar uma rua.");
				okToInsert = false;
			} if (this.checkAttribute(area)) {
				msg.add("É obrigatório informar um bairro.");
				okToInsert = false;
			} if(this.checkAttribute(locality)) {
				msg.add("É obrigatório informar uma cidade.");
				okToInsert = false;
			} if(this.checkAttribute(federatedUnit)) {
				msg.add("É obrigatório informar uma unidade federativa (UF).");
				okToInsert = false;
			} 
		}
		
		
		
		if(okToInsert) {	
			user = new UserBean();
			user.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
			user.setLogin(login);
			user.setPassword(password);
			user.setName(name);		
			user.setLastName(lastName);
			user.setGender(gender);
			user.setPhone(phone);	
			
			addressBean  = new AddressBean();
			addressBean.setAddress(address);
			addressBean.setArea(area);
			addressBean.setFederatedUnit(federatedUnit);
			addressBean.setLocality(locality);
			addressBean.setZipCode(zipCode);
			
			user.setAddress(addressBean);
			
			if(ServletFileUpload.isMultipartContent(request)) {
				Part partPhoto = request.getPart("photo");
				if (partPhoto != null && partPhoto.getInputStream().available() > 0) {
					PhotoBean photoBean = new PhotoBean();
					/*String base64 = new Base64().encodeBase64String(
						ConversionByType.streamToByte(partPhoto.getInputStream()));*/
					
					String base64 = new Base64().encodeBase64String(this.streamToByte(partPhoto.getInputStream()));
				
					photoBean.setBase64(base64);
					photoBean.setContentType(partPhoto.getContentType());
					user.setPhoto(photoBean);		
				}					
			}	

			if(this.checkAttribute(id) && !dao.validateNewUser(login)) {
				request.setAttribute("user", user);
				request.setAttribute("update", true);
				this.generateMessageError(request);
			} else if(this.checkAttribute(id) && dao.validateNewUser(login)) {
				if(dao.save(user)) {
					this.generateMessageSuccessSave(request);
					okToInsert = true;
				}
			} else if(!this.checkAttribute(id)){
				if(!dao.validateUpdate(login, Long.parseLong(id))) {
					this.generateMessageError(request);
					request.setAttribute("user", user);
					request.setAttribute("update", true);
				} else {
					if(dao.update(user)) {
						this.generateMessageSuccessUpdate(request);
						okToInsert = true;
					}
				}			
			}
		}		
		
		if(!okToInsert) {
			request.setAttribute("user", user);
			request.setAttribute("update", true);
			request.setAttribute("msgValidation", msg);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/PrivatePages/users.jsp?action=listAll");
		request.setAttribute("users", dao.findAll());			
		
		rd.forward(request, response);	
	}	
	
	private byte[] streamToByte(InputStream data) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		
		try {
			int reads = data.read();
			reads = data.read();
			while(reads != -1) {
				stream.write(reads);
				reads = data.read();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return stream.toByteArray();
	}

	private boolean checkAttribute(String attribute) {
		return attribute == null || attribute.isEmpty();
	}

	private void generateMessageError(HttpServletRequest request) {
		request.setAttribute("msgError", "Desculpe, esse login já está sendo utilizado. Tente outro!");
	}

	private void generateMessageSuccessSave(HttpServletRequest request) {
		request.setAttribute("msgSuccess", "Usuário cadastrado com sucesso.");
	}

	private void generateMessageSuccessUpdate(HttpServletRequest request) {
		request.setAttribute("msgSuccess", "As informações do usuário foram atualizadas com sucesso.");
	}
}
