package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

	private static Connection conexion;

	private static final String URL = "jdbc:mysql://localhost:3306/euskalencounter";
	private static final String USER = "root";
	private static final String PASS = "1DAW3";

	public static Connection getConexion() throws SQLException {
		if (conexion == null || conexion.isClosed()) {
			conectar();
		}
		return conexion;
	}

	public static void conectar() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(URL, USER, PASS);

			Logger.writeLog("Conexión establecida con éxito.");

		} catch (ClassNotFoundException e) {
			Logger.writeLog("ERROR: No se encontró el driver JDBC.");
			throw new SQLException("Driver no encontrado", e);
		} catch (SQLException e) {
			Logger.writeLog("ERROR SQL: " + e.getMessage() + " [Código: " + e.getErrorCode() + "]");
			throw e;
		}
	}

	public static void cerrarConexion() {
		try {
			if (conexion != null && !conexion.isClosed()) {
				conexion.close();
				Logger.writeLog("Conexión cerrada.");
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al cerrar conexión: " + e.getMessage());
		}
	}
}