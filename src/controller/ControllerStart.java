package controller;

import view.ViewEvents;
import view.ViewStart;
import view.ViewEncounters;
import view.ViewGuestList;
import dao.Logger;
import util.SessionManager;

public class ControllerStart {
	private ViewStart view;

	public ControllerStart(ViewStart viewStart) {
		this.view = viewStart;

		this.view.getBTN_show_events().addActionListener(_ -> {
			openEvents();
		});

		this.view.getBTN_show_encounters().addActionListener(_ -> {
			openEncounters();
		});

		this.view.getBTN_show_registrations().addActionListener(_ -> {
			openUserRegistrations();
		});

		this.view.getBTN_show_guests().addActionListener(_ -> {
			openGuests();
		});
	}

	private void openEvents() {
		ViewEvents viewEvents = new ViewEvents();
		ControllerEvents controllerEvents = new ControllerEvents(viewEvents);
		Logger.writeLog("Abriendo vista de eventos desde el menú principal.");
		controllerEvents.showEvents();
		this.view.dispose();
	}

	private void openEncounters() {
		ViewEncounters viewEncounters = new ViewEncounters();
		ControllerEncounters controllerEncounters = new ControllerEncounters(viewEncounters);
		Logger.writeLog("Abriendo vista de encuentros desde el menú principal.");
		controllerEncounters.showEncounters();
		this.view.dispose();
	}

	private void openGuests() {
		ViewGuestList viewGuestList = new ViewGuestList();
		ControllerGuests controllerGuests = new ControllerGuests(viewGuestList);
		Logger.writeLog("Abriendo vista de lista de invitados desde el menú principal.");
		controllerGuests.showGuestList();
		this.view.dispose();
	}

	private void openUserRegistrations() {
		String username = SessionManager.getInstance().getActiveUsername();
		ViewEvents viewEvents = new ViewEvents();
		ControllerEvents controllerEvents = new ControllerEvents(viewEvents);
		if (username != null) {
			controllerEvents.setUserFilter(username);
			Logger.writeLog("Abriendo vista de inscripciones de usuario para: " + username);
		}
		controllerEvents.showEvents();
		this.view.dispose();
	}

	public void showStart() {
		Logger.writeLog("Abriendo vista del menú principal.");
		this.view.setVisible(true);
	}

	public void closeStart() {
		Logger.writeLog("Cerrando vista del menú principal.");
		this.view.dispose();
	}
}