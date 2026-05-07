package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import dao.DaoGuests;
import dao.DatabaseConnector;
import dao.Logger;
import model.Guest;
import util.SessionManager;
import view.ViewLogin;
import view.ViewStart;

public class ControllerLogin {
	private ViewLogin view;

	public ControllerLogin(ViewLogin view) {
		this.view = view;

		this.view.getLoginButton().addActionListener(_ -> validateLogin());

		this.view.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				DatabaseConnector.closeConnection();
				System.exit(0);
			}
		});
	}

	private void validateLogin() {
		String username = view.getUsername().trim();
		String password = view.getPassword();

		if (username.isEmpty() || password.isEmpty()) {
			view.showMessage("Por favor rellena todos los campos.");
			Logger.writeLog("Intento de login con campos vacíos.");
			return;
		}

		Guest guest = DaoGuests.getGuestByUsernameAndPassword(username, password);

		if (guest != null) {
			SessionManager.getInstance().setActiveUser(guest);
			view.clearMessage();
			Logger.writeLog("Usuario inició sesión correctamente: " + username);
			openMainMenu();
		} else {
			view.showMessage("Usuario o contraseña incorrectos.");
			view.clearFields();
			Logger.writeLog("Intento de login fallido para el usuario: " + username);
		}
	}

	private void openMainMenu() {
		ViewStart viewStart = new ViewStart();
		new ControllerStart(viewStart);
		viewStart.setVisible(true);
		this.view.dispose();
	}

	public void showLogin() {
		this.view.setVisible(true);
	}
}
