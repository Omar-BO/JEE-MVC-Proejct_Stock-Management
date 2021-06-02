package com.InventoryManagement.Beans;

public class Provider {
	
	private int idProvider;
	private String name;
	private String address;
	private String mobile;
	
	
	public Provider(int idProvider, String name, String address, String mobile) {
		super();
		this.idProvider = idProvider;
		this.name = name;
		this.setAddress(address);
		this.mobile = mobile;
	}
	
	public Provider(String name, String address, String mobile) {
		super();
		this.name = name;
		this.setAddress(address);
		this.mobile = mobile;
	}

	public int getIdProvider() {
		return idProvider;
	}
	public void setIdProvider(int idProvider) {
		this.idProvider = idProvider;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

}
