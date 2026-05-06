package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

	private static Connection connection;

	private static final String URL = "jdbc:mysql://localhost:3306/euskalencounter";
	private static final String USER = "root";
	private static final String PASSWORD = "1DAW3";

	public static Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			connect();
		}
		return connection;
	}

	public static void connect() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USER, PASSWORD);

			Logger.writeLog("Conexión establecida con éxito.");

		} catch (ClassNotFoundException e) {
			Logger.writeLog("ERROR: No se encontró el driver JDBC.");
			throw new SQLException("Driver no encontrado", e);
		} catch (SQLException e) {
			Logger.writeLog("ERROR SQL: " + e.getMessage() + " [Código: " + e.getErrorCode() + "]");
			throw e;
		}
	}

	public static void closeConnection() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
				Logger.writeLog("Conexión cerrada.");
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al cerrar conexión: " + e.getMessage());
		}
	}
}