package br.com.cauezito.beans;

public class ProductBean {
	private Long id;
	private String name;
	private String desc;
	private Integer quantity;
	private Double value;
	private CategoryBean category;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
	public CategoryBean getCategory() {
		return category;
	}
	
	public void setCategory(CategoryBean category) {
		this.category = category;
	}
}
