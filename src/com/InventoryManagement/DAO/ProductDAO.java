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

import com.InventoryManagement.Beans.Product;

public class ProductDAO {
	private static final String INSERT_PRODUCTS_SQL 	= "INSERT INTO product (label, description, quantity, price, VAT, idCategory, idProvider) "
													+ "VALUES (?, ?, ?, ?, ?, ?, ?);";
	
	private static final String SELECT_PRODUCT_BY_ID = "select idPorduct,label,description,quantity,price,VAT,idCategory,idProvider from product where idPorduct =?";
	private static final String SELECT_ALL_PRODUCTS = "select * from product";
	private static final String DELETE_PRODUCTS_SQL = "delete from product where idPorduct = ?;";
	
	private static final String UPDATE_PRODUCTS_SQL 	= "update product set label = ?,description= ?, quantity =?, price =?, VAT =?"
													+ ", idCategory =?, idProvider =? "
													+ "where idporduct = ?;";

	public ProductDAO() {
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

	public void insertproduct(Product product) throws SQLException {
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTS_SQL)) {
			preparedStatement.setString(1, product.getLabel());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setInt(3, product.getQuantity());
			preparedStatement.setString(4, product.getPrice());
			preparedStatement.setString(5, product.getVAT());
			preparedStatement.setInt(6, product.getIdCategory());
			preparedStatement.setInt(7, product.getIdProvider());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Product selectproduct(int id) {
		Product product = null;
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String label 		= rs.getString("label");
				String description 	= rs.getString("description");
				int quantity 		= rs.getInt("quantity");
				String price 		= rs.getString("price");
				String VAT 			= rs.getString("VAT");
				int idCategory 		= rs.getInt("idCategory");
				int idProvider 		= rs.getInt("idProvider");
				product = new Product(id, label, description, quantity, price, VAT, idCategory, idProvider);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return product;
	}

	public List<Product> selectAllPRODUCTS() {
		List<Product> products = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id 				= rs.getInt(1);
				String label 		= rs.getString(2);
				String description 	= rs.getString(3);
				int quantity 		= rs.getInt(4);
				String price 		= rs.getString(5);
				String VAT 			= rs.getString(6);
				int idCategory 		= rs.getInt(7);
				int idProvider 		= rs.getInt(8);
				products.add(new Product(id, label, description, quantity, price, VAT, idCategory, idProvider));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return products;
	}
	
	public List<Map<String, String>> selectAllProductS() {
		List<Map<String, String>> maListe = new ArrayList<Map<String,String>>();
		Map<String, String> news;
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select idPorduct,product.label,product.description,quantity,price,VAT,category.label,name from product join category on product.idCategory=category.idCategory join provider on product.idProvider=provider.idProvider;");) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String id 			= String.valueOf(rs.getInt(1));
				String label 		= rs.getString(2);
				String description 	= rs.getString(3);
				String quantity 	= String.valueOf(rs.getInt(4));
				String price 		= rs.getString(5);
				String VAT 			= rs.getString(6);
				String idCategory 	= rs.getString(7);
				String idProvider 	= rs.getString(8);
				
				news = new HashMap<String, String>();
				news.put("id", id);
				news.put("label", label);
				news.put("description", description);
				news.put("quantity", quantity);
				news.put("price", price);
				news.put("VAT", VAT);
				news.put("idCategory", idCategory);
				news.put("idProvider", idProvider);
				maListe.add(news);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return maListe;
	}
	
	public boolean deleteproduct(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCTS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateproduct(Product product) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCTS_SQL);) {
			statement.setString(1, product.getLabel());
			statement.setString(2, product.getDescription());
			statement.setInt(3, product.getQuantity());
			statement.setString(4, product.getPrice());
			statement.setString(5, product.getVAT());
			statement.setInt(6, product.getIdCategory());
			statement.setInt(7, product.getIdProvider());
			statement.setInt(8, product.getIdPorduct());

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
