package com.InventoryManagement.Beans;

public class LoginBean {
	 /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private int idUser;
    private int role;

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

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
}

