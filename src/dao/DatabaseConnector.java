package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

	private static Connection conexion;

	// Configuraci�n de la base de datos
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
			// cargamos el driver de MySQL
			Class.forName("com.mysql.cj.jdbc.Driver");

			// establecemos la conexi�n
			conexion = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Conexi�n establecida con �xito.");

		} catch (ClassNotFoundException e) {
			System.err.println("ERROR: No se encontr� el driver JDBC.");
			throw new SQLException("Driver no encontrado", e);
		} catch (SQLException e) {
			System.err.println("Estado SQL: " + e.getSQLState());
			System.err.println("C�digo de error: " + e.getErrorCode());
			System.err.println("Mensaje: " + e.getMessage());
			throw e; // re-lanzamos la excepci�n para que el Manager sepa que fall�
		}
	}

	public static void cerrarConexion() {
		try {
			if (conexion != null && !conexion.isClosed()) {
				conexion.close();
				System.out.println("Conexi�n cerrada.");
			}
		} catch (SQLException e) {
			System.err.println("Error al cerrar la conexi�n: " + e.getMessage());
		}
	}
}