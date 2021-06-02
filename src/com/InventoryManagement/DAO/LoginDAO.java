package com.InventoryManagement.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.InventoryManagement.Beans.CurrentUser;
import com.InventoryManagement.Beans.LoginBean;

public class LoginDAO {
		
	public boolean validate() throws ClassNotFoundException {
        boolean status = false;
    	String username = CurrentUser.username;
    	String password = CurrentUser.password;

        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e){
			System.out.println("Impossible de charger le pilote");
		}
       

        try (
        	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jee_project?serverTimezone=UTC", "root", "");

            PreparedStatement preparedStatement = connection
            .prepareStatement("select * from user where username = ? and password = ? ")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();
            System.out.println(status);

        } catch (SQLException e) {
        	System.out.println("Impossible de se connecter ");
            printSQLException(e);
        }
        return status;
    }
	
	public void role() throws ClassNotFoundException {
        int status=04;
    	String username = CurrentUser.username;


        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e){
			System.out.println("Impossible de charger le pilote");
		}
       

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jee_project?serverTimezone=UTC", "root", "");

            PreparedStatement preparedStatement = connection
            .prepareStatement("select role from user where username = ? ; ")) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(rs.next());
            CurrentUser.role = rs.getInt(1);
        } catch (SQLException e) {
        	System.out.println("Impossible de se connecter ");
            printSQLException(e);
        }
    }


    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
