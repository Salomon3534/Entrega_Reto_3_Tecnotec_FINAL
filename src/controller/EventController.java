package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import dao.DatabaseConnector;
import view.ViewEvents;
import view.ViewStart;

public class EventController {
	ViewEvents view;

<<<<<<< Updated upstream:src/controller/EventController.java
	public EventController(ViewEvents view) {
		this.view = view;
		
		// Listener para volver atrás
		this.view.getBTN_back().addActionListener(e -> {
=======
	public ControllerEvents(ViewEvents ve) {
		this.view = ve;

		this.view.getBTN_back().addActionListener(_ -> {
>>>>>>> Stashed changes:src/controller/ControllerEvents.java
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
<<<<<<< Updated upstream:src/controller/EventController.java
		StartController sc = new StartController(vs);
=======
		ControllerStart sc = new ControllerStart(vs, null);
>>>>>>> Stashed changes:src/controller/ControllerEvents.java
		sc.showStart();
		this.view.dispose();
	}

	public void showEvents() {
		this.view.setVisible(true);
	}
}