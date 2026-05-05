package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import dao.DaoGuests;
import dao.DatabaseConnector;
import model.Guest;
import view.ViewGuestList;
import view.ViewStart;

public class ControllerGuests {

	private ViewGuestList view;

	public ControllerGuests(ViewGuestList vg) {
		this.view = vg;

		cargarInvitados();

		this.view.getBTN_back().addActionListener(_ -> {
			volverAlInicio();
		});

		this.view.getGuestList().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				actualizarDetalle();
			}
		});

		this.view.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				DatabaseConnector.cerrarConexion();
				System.exit(0);
			}
		});
	}

	private void cargarInvitados() {
		view.getListModel().clear();
		ArrayList<Guest> invitados = DaoGuests.listGuestsAsList();
		for (Guest guest : invitados) {
			view.getListModel().addElement(guest);
		}
	}

	private void actualizarDetalle() {
		Guest seleccionado = view.getGuestList().getSelectedValue();
		if (seleccionado != null) {
			view.getGuestNameLabel().setText(seleccionado.getName() + " " + seleccionado.getSurnames());
			view.getGuestEmailLabel().setText(seleccionado.getEmail());
		}
	}

	private void volverAlInicio() {
		ViewStart vs = new ViewStart();
		ControllerStart sc = new ControllerStart(vs);
		sc.showStart();
		this.view.dispose();
	}

	public void showGuestList() {
		this.view.setVisible(true);
	}

	public void closeGuestList() {
		this.view.dispose();
	}
}