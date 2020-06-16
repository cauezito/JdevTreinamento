package br.com.cauezito.beans;

public class AddressBean {
	private Integer id;
	private String zipCode;
	private String address;
	private String area;
	private String locality;
	private String federatedUnit;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getFederatedUnit() {
		return federatedUnit;
	}
	public void setFederatedUnit(String federatedUnit) {
		this.federatedUnit = federatedUnit;
	}
	
	
}
