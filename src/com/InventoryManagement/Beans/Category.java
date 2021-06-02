package com.InventoryManagement.Beans;

public class Category {
	
	private int idCategory;
	private String label;
	private String description;
	
	public Category(String label, String description) {
		super();
		this.label = label;
		this.description = description;
	}
	public Category(int idCategory, String label, String description) {
		super();
		this.idCategory = idCategory;
		this.label = label;
		this.description = description;
	}
	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
