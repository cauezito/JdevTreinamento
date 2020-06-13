package br.com.cauezito.beans;

public class TestBean {
	private String name;
	private String gender;
	
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public int calc(int number) {
		return number*2;
	}
}
