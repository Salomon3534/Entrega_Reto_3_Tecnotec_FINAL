package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import dao.DatabaseConnector;
import view.ViewGuestList;
import view.ViewStart;

public class ControllerGuests {

	private ViewGuestList view;

	public ControllerGuests(ViewGuestList vg) {
		this.view = vg;

		this.view.getBTN_back().addActionListener(_ -> {
			volverAlInicio();
		});

		this.view.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				DatabaseConnector.cerrarConexion();
			}
		});
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
