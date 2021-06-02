package com.InventoryManagement.Beans;

public class Product {
	protected int idPorduct;
	protected String label;
	protected String description;
	protected int quantity;
	protected String price;
	protected String VAT;
	protected int idCategory;
	protected int idProvider;
	
	public Product(int idPorduct, String label, String description, int quantity, String price, String vAT,
			int idCategory, int idProvider) {
		super();
		this.idPorduct = idPorduct;
		this.label = label;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		VAT = vAT;
		this.idCategory = idCategory;
		this.idProvider = idProvider;
	}

	public Product(String label, String description, int quantity, String price, String vAT, int idCategory,
			int idProvider) {
		super();
		this.label = label;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		VAT = vAT;
		this.idCategory = idCategory;
		this.idProvider = idProvider;
	}

	public int getIdPorduct() {
		return idPorduct;
	}

	public void setIdPorduct(int idPorduct) {
		this.idPorduct = idPorduct;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getVAT() {
		return VAT;
	}

	public void setVAT(String vAT) {
		VAT = vAT;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public int getIdProvider() {
		return idProvider;
	}

	public void setIdProvider(int idProvider) {
		this.idProvider = idProvider;
	}
}
