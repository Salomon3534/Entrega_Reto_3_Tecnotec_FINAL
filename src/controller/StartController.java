package controller;

import view.ViewEvents;
import view.ViewStart;

public class StartController {
	ViewStart vista;

	public StartController(ViewStart vs) {
		this.vista = vs;

		this.vista.getBTN_show_events().addActionListener(e -> {
			abrirEvents();
		});
	}

	private void abrirEvents() {
		ViewEvents ve = new ViewEvents();
		EventController ec = new EventController(ve);

		ec.showEvents();
		this.vista.dispose();
	}

	public void showStart() {
		this.vista.setVisible(true);
	}

	public void closeStart() {
		this.vista.dispose();
	}
}