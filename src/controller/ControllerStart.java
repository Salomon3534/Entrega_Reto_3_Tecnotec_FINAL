package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import dao.DatabaseConnector;
import view.ViewEvents;
import view.ViewGuestList;
import view.ViewStart;

public class ControllerStart {
	ViewStart vista;
	TotalDAO totalManager;

	public ControllerStart(ViewStart vs, TotalDAO tm) {
		this.vista = vs;
		this.totalManager = tm;

		this.vista.getBTN_show_events().addActionListener(_ -> {
			openViewEvents();
		});

		this.vista.getBTN_show_guests().addActionListener(_ -> {
			openViewGuests();
		});

		this.vista.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				DatabaseConnector.cerrarConexion();
				closeStart();
			}
		});
	}

	private void openViewEvents() {
		ViewEvents ve = new ViewEvents();
		ControllerEvents ce = new ControllerEvents(ve);
		ce.showEvents();
		this.vista.dispose();
	}

	private void openViewGuests() {
		ViewGuestList vg = new ViewGuestList();
		ControllerGuests cg = new ControllerGuests(vg);
		cg.showGuestList();
		this.vista.dispose();
	}

	public void showStart() {
		this.vista.setVisible(true);
	}

	public void closeStart() {
		this.vista.dispose();
	}
}