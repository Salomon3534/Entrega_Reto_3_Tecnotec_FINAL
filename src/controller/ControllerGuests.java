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
		Logger.writeLog("Lista de invitados cargada: " + guests.size() + " invitados mostrados.");
	}

	private void updateDetail() {
		Guest selected = view.getGuestList().getSelectedValue();
		if (selected != null) {
			view.getGuestNameLabel().setText(selected.getName() + " " + selected.getSurnames());
			view.getGuestEmailLabel().setText(selected.getEmail());
			Logger.writeLog("Invitado seleccionado: " + selected.getUsername());
		}
	}

	private void backToStart() {
		Logger.writeLog("Regresando al menú principal desde la lista de invitados.");
		ViewStart viewStart = new ViewStart();
		ControllerStart controllerStart = new ControllerStart(viewStart);
		controllerStart.showStart();
		this.view.dispose();
	}

	public void showGuestList() {
		Logger.writeLog("Abriendo vista de lista de invitados.");
		this.view.setVisible(true);
	}

	public void closeGuestList() {
		Logger.writeLog("Cerrando vista de lista de invitados.");
		this.view.dispose();
	}
}