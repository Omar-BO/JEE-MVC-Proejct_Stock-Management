package com.InventoryManagement.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.InventoryManagement.Beans.Category;

public class CategoryDAO {
	private static final String INSERT_CATEGORY_SQL = "INSERT INTO category (label, description) VALUES (?, ?);";
	private static final String SELECT_CATEGORY_BY_ID = "select * from category where idCategory =?";
	private static final String SELECT_ALL_CATEGORY = "select * from category";
	private static final String DELETE_CATEGORY_SQL = "delete from category where idCategory = ?;";
	private static final String UPDATE_CATEGORY_SQL = "update category set label = ?,description= ? where idCategory = ?;";

	public CategoryDAO() {
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

	public void insertCategory(Category category) throws SQLException {
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY_SQL)) {
			preparedStatement.setString(1, category.getLabel());
			preparedStatement.setString(2, category.getDescription());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Category selectCategory(int id) {
		Category category = null;
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID);) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String label = rs.getString("label");
				String description = rs.getString("description");
				category = new Category(id, label, description);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return category;
	}

	public List<Category> selectAllCategorys() {
		List<Category> Categorys = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY);) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id 			= rs.getInt(1);
				String label = rs.getString(2);
				String description = rs.getString(3);
				Categorys.add(new Category(id, label, description));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return Categorys;
	}

	public boolean deleteCategory(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_CATEGORY_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateCategory(Category category) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORY_SQL);) {
			statement.setString(1, category.getLabel());
			statement.setString(2, category.getDescription());
			statement.setInt(3, category.getIdCategory());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	public List<Map<String, String>> selectAllCategory() {
		List<Map<String, String>> maListe = new ArrayList<Map<String,String>>();
		Map<String, String> news;
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT idCategory,label from category");) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String id = rs.getString(1);
				String label = rs.getString(2);
				news = new HashMap<String, String>();
				news.put("id", id);
				news.put("label", label);
				maListe.add(news);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return maListe;
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
