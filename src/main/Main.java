 package main;

import java.sql.Connection;
import java.sql.SQLException;

import controller.TotalManagerEuskalEncounter;
import dao.DatabaseConnector;
import view.ViewEuskalEncounter;

public class Main {

	public static void main(String[] args) {
		try {
			// Intentamos obtener la conexi�n
			Connection connection = DatabaseConnector.getConexion();

			// Verificamos si la conexi�n es nula o no es v�lida (la BD no existe o no es
			// accesible)
			if (connection == null || connection.isClosed()) {
				throw new SQLException(
						"No se pudo establecer comunicaci�n con el servidor SQL. Verifique que la base de datos existe.");
			}

			TotalManagerEuskalEncounter managerTotal = new TotalManagerEuskalEncounter();
			ViewEuskalEncounter view = new ViewEuskalEncounter(managerTotal);

			// Loop principal de la app
			view.mainMenu();

			// Fin y cierre
			DatabaseConnector.cerrarConexion();

		} catch (SQLException e) {
			System.err.println("\n[ERROR DE BASE DE DATOS]");
			System.err
					.println("No se puede iniciar la aplicaci�n porque la base de datos SQL no existe o no responde.");
			System.err.println("Detalles: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("\n[ERROR INESPERADO]");
			System.err.println("Causa: " + e.getMessage());
		}
	}
}