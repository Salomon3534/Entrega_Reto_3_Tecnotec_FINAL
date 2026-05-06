package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import dao.DaoGuests;
import dao.DatabaseConnector;
import dao.Logger;
import model.Guest;
import view.ViewGuestList;
import view.ViewStart;

public class ControllerGuests {

	private ViewGuestList view;

	public ControllerGuests(ViewGuestList viewGuestList) {
		this.view = viewGuestList;

		loadGuests();

		this.view.getBTN_back().addActionListener(_ -> {
			backToStart();
		});

		this.view.getGuestList().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				updateDetail();
			}
		});

		this.view.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				DatabaseConnector.closeConnection();
				System.exit(0);
			}
		});
	}

	private void loadGuests() {
		view.getListModel().clear();
		ArrayList<Guest> guests = DaoGuests.listGuestsAsList();
		for (Guest guest : guests) {
			view.getListModel().addElement(guest);
		}
		Logger.writeLog("Guest list loaded: " + guests.size() + " guests displayed.");
	}

	private void updateDetail() {
		Guest selected = view.getGuestList().getSelectedValue();
		if (selected != null) {
			view.getGuestNameLabel().setText(selected.getName() + " " + selected.getSurnames());
			view.getGuestEmailLabel().setText(selected.getEmail());
			Logger.writeLog("Guest selected: " + selected.getUsername());
		}
	}

	private void backToStart() {
		Logger.writeLog("Returning to start menu from guest list.");
		ViewStart viewStart = new ViewStart();
		ControllerStart controllerStart = new ControllerStart(viewStart);
		controllerStart.showStart();
		this.view.dispose();
	}

	public void showGuestList() {
		Logger.writeLog("Opening guest list view.");
		this.view.setVisible(true);
	}

	public void closeGuestList() {
		Logger.writeLog("Closing guest list view.");
		this.view.dispose();
	}
}