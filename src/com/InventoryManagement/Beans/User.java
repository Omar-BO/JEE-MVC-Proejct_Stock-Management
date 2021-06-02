package com.InventoryManagement.Beans;

public class User {
	protected int idUser;
	protected String username;
	protected String password;
	protected String role;
	
	public User(int idUser, String username, String password, String role) {
		super();
		this.idUser = idUser;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public User(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
