package com.InventoryManagement.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.InventoryManagement.Beans.User;

public class UserDAO {
	private static final String INSERT_USERS_SQL = "INSERT INTO user (username, password, role) VALUES (?, ?, ?);";
	private static final String SELECT_USER_BY_ID = "select idUser,username,password,role from user where idUser =?";
	private static final String SELECT_ALL_USERS = "select * from user";
	private static final String DELETE_USERS_SQL = "delete from user where idUser = ?;";
	private static final String UPDATE_USERS_SQL = "update user set username = ?,password= ?, role =? where idUser = ?;";

	public UserDAO() {
	}

	protected Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e){
			System.out.println("Impossible de charger le pilote");
			System.exit(1);
			}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jee_project?serverTimezone=UTC", "root", "");
		}catch (SQLException e){
			System.out.println("Impossible de se connecter ");
			System.exit(1);
			}
		return connection;
	}

	public void insertUser(User user) throws SQLException {
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getRole());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public User selectUser(int id) {
		User user = null;
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String role = rs.getString("role");
				user = new User(id, username, password, role);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	public List<User> selectAllUsers() {
		List<User> users = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id 			= rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				String role 	= rs.getString(4);
				users.add(new User(id, username, password, role));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}

	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getRole());
			statement.setInt(4, user.getIdUser());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
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
