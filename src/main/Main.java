package main;

import java.sql.Connection;
import java.sql.SQLException;

import controller.ControllerStart;
import dao.DatabaseConnector;
import view.ViewStart;

public class Main {

	public static void main(String[] args) {
		try {
			Connection connection = DatabaseConnector.getConexion();

			if (connection == null || connection.isClosed()) {
				throw new SQLException(
						"No se pudo establecer comunicación con el servidor SQL. Verifique que la base de datos existe.");
			}

			try {
				ViewStart vistaPrincipal = new ViewStart();
				ControllerStart control = new ControllerStart(vistaPrincipal);

				control.showStart();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			System.err.println("\n[ERROR DE BASE DE DATOS]");
			System.err
					.println("No se puede iniciar la aplicación porque la base de datos SQL no existe o no responde.");
			System.err.println("Detalles: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("\n[ERROR INESPERADO]");
			System.err.println("Causa: " + e.getMessage());
		}
	}
}