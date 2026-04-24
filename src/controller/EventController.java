package controller;

import view.ViewEvents;
import view.ViewStart;

public class EventController {
	ViewEvents view;

	public EventController(ViewEvents view) {
		this.view = view;
		
		// Listener para volver atrás
		this.view.getBTN_back().addActionListener(e -> {
			volverAlInicio();
		});
	}
	
	private void volverAlInicio() {
		ViewStart vs = new ViewStart();
		StartController sc = new StartController(vs);
		sc.showStart();
		this.view.dispose();
	}

	public void showEvents() {
		this.view.setVisible(true);
	}
}