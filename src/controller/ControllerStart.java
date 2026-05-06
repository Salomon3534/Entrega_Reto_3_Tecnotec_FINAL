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
		Logger.writeLog("Opening events view from start menu.");
		controllerEvents.showEvents();
		this.view.dispose();
	}

	private void openEncounters() {
		ViewEncounters viewEncounters = new ViewEncounters();
		ControllerEncounters controllerEncounters = new ControllerEncounters(viewEncounters);
		Logger.writeLog("Opening encounters view from start menu.");
		controllerEncounters.showEncounters();
		this.view.dispose();
	}

	private void openGuests() {
		ViewGuestList viewGuestList = new ViewGuestList();
		ControllerGuests controllerGuests = new ControllerGuests(viewGuestList);
		Logger.writeLog("Opening guest list view from start menu.");
		controllerGuests.showGuestList();
		this.view.dispose();
	}

	private void openUserRegistrations() {
		String username = SessionManager.getInstance().getActiveUsername();
		ViewEvents viewEvents = new ViewEvents();
		ControllerEvents controllerEvents = new ControllerEvents(viewEvents);
		if (username != null) {
			controllerEvents.setUserFilter(username);
			Logger.writeLog("Opening user registrations view for user: " + username);
		}
		controllerEvents.showEvents();
		this.view.dispose();
	}

	public void showStart() {
		Logger.writeLog("Opening start menu view.");
		this.view.setVisible(true);
	}

	public void closeStart() {
		Logger.writeLog("Closing start menu view.");
		this.view.dispose();
	}
}