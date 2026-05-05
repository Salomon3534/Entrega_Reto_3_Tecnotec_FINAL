package controller;

import view.ViewEvents;
import view.ViewStart;
import view.ViewEncounters;
import view.ViewGuestList;

public class ControllerStart {
	private ViewStart vista;

	public ControllerStart(ViewStart vs) {
		this.vista = vs;

		// Listener para la sección de Eventos
		this.vista.getBTN_show_events().addActionListener(e -> {
			abrirEvents();
		});

		// Listener para la sección de Encuentros
		this.vista.getBTN_show_encounters().addActionListener(e -> {
			abrirEncounters();
		});

		// Listener para la sección de Invitados
		this.vista.getBTN_show_guests().addActionListener(e -> {
			abrirGuests();
		});
	}

	private void abrirEvents() {
		ViewEvents ve = new ViewEvents();
		ControllerEvents ec = new ControllerEvents(ve);
		ec.showEvents();
		this.vista.dispose();
	}

	private void abrirEncounters() {
		ViewEncounters ven = new ViewEncounters();
		ControllerEncounters cec = new ControllerEncounters(ven);
		cec.showEncounters();
		this.vista.dispose();
	}

	private void abrirGuests() {
		ViewGuestList vg = new ViewGuestList();
		ControllerGuests cgc = new ControllerGuests(vg);
		cgc.showGuestList();
		this.vista.dispose();
	}

	public void showStart() {
		this.vista.setVisible(true);
	}

	public void closeStart() {
		this.vista.dispose();
	}
}