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

import com.InventoryManagement.Beans.Provider;

public class ProviderDAO {
	private static final String INSERT_PROVIDER_SQL = "INSERT INTO provider (name, address, mobile) VALUES (?, ?, ?);";
	private static final String SELECT_PROVIDER_BY_ID = "select * from provider where idProvider =?";
	private static final String SELECT_ALL_PROVIDERS = "select * from provider";
	private static final String DELETE_PROVIDER_SQL = "delete from provider where idProvider = ?;";
	private static final String UPDATE_PROVIDER_SQL = "update provider set name = ?,address= ?, mobile =? where idProvider = ?;";

	public ProviderDAO() {
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

	public void insertProvider(Provider provider) throws SQLException {
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROVIDER_SQL)) {
			preparedStatement.setString(1, provider.getName());
			preparedStatement.setString(2, provider.getAddress());
			preparedStatement.setString(3, provider.getMobile());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Provider selectProvider(int id) {
		Provider user = null;
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROVIDER_BY_ID);) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String address = rs.getString("address");
				String mobile = rs.getString("mobile");
				user = new Provider(id, name, address, mobile);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	public List<Provider> selectAllProviders() {
		List<Provider> Providers = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROVIDERS);) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id 			= rs.getInt(1);
				String name = rs.getString(2);
				String address = rs.getString(3);
				String mobile 	= rs.getString(4);
				Providers.add(new Provider(id, name, address, mobile));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return Providers;
	}

	public boolean deleteProvider(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PROVIDER_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateProvider(Provider provider) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_PROVIDER_SQL);) {
			statement.setString(1, provider.getName());
			statement.setString(2, provider.getAddress());
			statement.setString(3, provider.getMobile());
			statement.setInt(4, provider.getIdProvider());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	public List<Map<String, String>> selectAllProvider() {
		List<Map<String, String>> maListe = new ArrayList<Map<String,String>>();
		Map<String, String> news;
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT idProvider,name from provider");) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String id = rs.getString(1);
				String label = rs.getString(2);
				news = new HashMap<String, String>();
				news.put("id", id);
				news.put("name", label);
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
