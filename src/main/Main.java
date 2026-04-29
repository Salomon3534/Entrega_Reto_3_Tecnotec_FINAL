package main;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.SQLException;

import controller.ControllerStart;
import controller.TotalDAO;
import dao.DatabaseConnector;
import view.ViewStart;

public class Main {

	public static void main(String[] args) {
<<<<<<< Updated upstream
		// Inicio de la interfaz gráfica de usuario
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ViewStart vistaPrincipal = new ViewStart();
					StartController control = new StartController(vistaPrincipal);
					control.showStart();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		/* * Se comenta la lógica de conexión manual y consola ya que el flujo 
		 * ahora es gestionado por los controladores de la vista.
		 */
		/*
=======
		

		
>>>>>>> Stashed changes
		try {
			Connection connection = DatabaseConnector.getConexion();

			if (connection == null || connection.isClosed()) {
				throw new SQLException(
						"No se pudo establecer comunicación con el servidor SQL. Verifique que la base de datos existe.");
			}

<<<<<<< Updated upstream
			// TotalManagerEuskalEncounter managerTotal = new TotalManagerEuskalEncounter();
			// ViewEuskalEncounter view = new ViewEuskalEncounter(managerTotal);
			// view.mainMenu();
=======
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					try {
						TotalDAO tm = new TotalDAO();
						
						ViewStart vistaPrincipal = new ViewStart();
						ControllerStart control = new ControllerStart(vistaPrincipal, tm);
						control.showStart();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
>>>>>>> Stashed changes


		} catch (SQLException e) {
			System.err.println("\n[ERROR DE BASE DE DATOS]");
			System.err.println(
					"No se puede iniciar la aplicación porque la base de datos SQL no existe o no responde.");
			System.err.println("Detalles: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("\n[ERROR INESPERADO]");
			System.err.println("Causa: " + e.getMessage());
		}
		*/
	}
}