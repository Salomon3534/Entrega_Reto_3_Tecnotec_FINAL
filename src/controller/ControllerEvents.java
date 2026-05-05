package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import dao.DaoEvents;
import dao.DatabaseConnector;
import model.Event;
import view.ViewEvents;
import view.ViewStart;

public class ControllerEvents {
	private ViewEvents view;

	public ControllerEvents(ViewEvents view) {
		this.view = view;

		cargarEventos();

		this.view.getBTN_back().addActionListener(_ -> volverAlInicio());

		this.view.getEventList().addListSelectionListener(e -> {
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

	private void cargarEventos() {
		view.getListModel().clear();
		List<Event> eventos = DaoEvents.listEventsAsList();
		for (Event ev : eventos) {
			view.getListModel().addElement(ev);
		}
	}

	private void actualizarDetalle() {
		Event seleccionado = view.getEventList().getSelectedValue();
		if (seleccionado != null) {
			view.getEventNameLabel().setText(seleccionado.getTitle());
			view.getEventDescLabel().setText(seleccionado.getLocation());
		}
	}

	private void volverAlInicio() {
		ViewStart vs = new ViewStart();
		new ControllerStart(vs);
		vs.setVisible(true);
		this.view.dispose();
	}

	public void showEvents() {
		this.view.setVisible(true);
	}
}