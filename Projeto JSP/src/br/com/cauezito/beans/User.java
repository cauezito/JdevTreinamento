package br.com.cauezito.beans;

public class User {
	private String login;
	private String password;
	
	public boolean validateLogin(String login, String password) {
		if(login.equals("admin") && password.equals("admin")) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
