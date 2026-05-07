package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JOptionPane;
import dao.DaoEvents;
import dao.DatabaseConnector;
import dao.Logger;
import model.Event;
import util.SessionManager;
import view.ViewEventDetailed;
import view.ViewEvents;
import view.ViewStart;

public class ControllerEvents {
	private ViewEvents view;
	private Integer filterEncounterCode = null;
	private String userFilterUsername = null;

	public ControllerEvents(ViewEvents view) {
		this.view = view;

		loadEvents();

		this.view.getBTN_back().addActionListener(_ -> backToStart());

		this.view.getEventList().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				updateDetail();
			}
		});

		this.view.getDetailBtn().addActionListener(_ -> {
			Event selected = view.getEventList().getSelectedValue();
			if (selected != null) {
				openEventDetail(selected);
			} else {
				JOptionPane.showMessageDialog(view, "Por favor selecciona un evento", "Advertencia",
						JOptionPane.WARNING_MESSAGE);
			}
		});

		this.view.getInscriptionBtn().addActionListener(_ -> {
			Event selected = view.getEventList().getSelectedValue();
			if (selected != null) {
				registerToEvent(selected);
			} else {
				JOptionPane.showMessageDialog(view, "Por favor selecciona un evento", "Advertencia",
						JOptionPane.WARNING_MESSAGE);
			}
		});

		this.view.getUninscriptionBtn().addActionListener(_ -> {
			Event selected = view.getEventList().getSelectedValue();
			if (selected != null) {
				unregisterFromEvent(selected);
			} else {
				JOptionPane.showMessageDialog(view, "Por favor selecciona un evento", "Advertencia",
						JOptionPane.WARNING_MESSAGE);
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

	private void loadEvents() {
		view.getListModel().clear();
		List<Event> events;
		if (userFilterUsername != null) {
			events = DaoEvents.listEventsByUsername(userFilterUsername);
			Logger.writeLog("Eventos filtrados cargados para el usuario: " + userFilterUsername + " (" + events.size()
					+ " eventos)");
		} else if (filterEncounterCode != null) {
			events = DaoEvents.listEventsByEncounter(filterEncounterCode);
			Logger.writeLog(
					"Eventos cargados del encuentro: " + filterEncounterCode + " (" + events.size() + " eventos)");
		} else {
			events = DaoEvents.listEventsAsList();
			Logger.writeLog("Todos los eventos cargados: " + events.size() + " eventos");
		}

		for (Event event : events) {
			view.getListModel().addElement(event);
		}
	}

	private void updateDetail() {
		Event selected = view.getEventList().getSelectedValue();
		if (selected != null) {
			view.getEventNameLabel().setText(selected.getTitle());
			view.getEventDescLabel().setText(selected.getLocation());
			Logger.writeLog("Detalle de evento actualizado: " + selected.getTitle());

			String username = SessionManager.getInstance().getActiveUsername();
			if (username != null) {
				boolean isRegistered = DaoEvents.isUserRegistered(selected.getId(), username);
				view.getInscriptionBtn().setVisible(!isRegistered);
				view.getUninscriptionBtn().setVisible(isRegistered);
			} else {
				view.getInscriptionBtn().setVisible(true);
				view.getUninscriptionBtn().setVisible(false);
			}
		}
	}

	private void openEventDetail(Event event) {
		Logger.writeLog("Abriendo vista detallada para el evento: " + event.getTitle());
		ViewEventDetailed viewEventDetailed = new ViewEventDetailed();
		ControllerEventDetailed controller = new ControllerEventDetailed(viewEventDetailed, event);
		viewEventDetailed.setVisible(true);
	}

	private void registerToEvent(Event event) {
		String username = SessionManager.getInstance().getActiveUsername();
		if (username == null) {
			JOptionPane.showMessageDialog(view, "No hay usuario registrado", "Error", JOptionPane.ERROR_MESSAGE);
			Logger.writeLog("Intento de inscripción sin usuario activo.");
			return;
		}

		if (DaoEvents.isUserRegistered(event.getId(), username)) {
			JOptionPane.showMessageDialog(view, "Ya estás inscrito en este evento", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			Logger.writeLog("Usuario ya inscrito en el evento: " + username + " -> ID del evento: " + event.getId());
			return;
		}

		if (DaoEvents.registerUserInEvent(event.getId(), username)) {
			JOptionPane.showMessageDialog(view, "¡Inscripción realizada exitosamente!", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
			Logger.writeLog("Usuario inscrito en el evento: " + username + " -> " + event.getTitle());
			updateDetail();
		} else {
			JOptionPane.showMessageDialog(view, "Error al inscribirse en el evento", "Error",
					JOptionPane.ERROR_MESSAGE);
			Logger.writeLog("ERROR: No se pudo inscribir al usuario en el evento: " + username + " -> ID del evento: "
					+ event.getId());
		}
	}

	private void unregisterFromEvent(Event event) {
		String username = SessionManager.getInstance().getActiveUsername();
		if (username == null) {
			JOptionPane.showMessageDialog(view, "No hay usuario registrado", "Error", JOptionPane.ERROR_MESSAGE);
			Logger.writeLog("Intento de desinscripción sin usuario activo.");
			return;
		}

		if (!DaoEvents.isUserRegistered(event.getId(), username)) {
			JOptionPane.showMessageDialog(view, "No estás inscrito en este evento", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			Logger.writeLog("Usuario no inscrito en el evento: " + username + " -> ID del evento: " + event.getId());
			return;
		}

		if (DaoEvents.unregisterUserFromEvent(event.getId(), username)) {
			JOptionPane.showMessageDialog(view, "¡Desinscripción realizada exitosamente!", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
			Logger.writeLog("Usuario desinscrito del evento: " + username + " -> " + event.getTitle());
			updateDetail();
		} else {
			JOptionPane.showMessageDialog(view, "Error al desinscribirse del evento", "Error",
					JOptionPane.ERROR_MESSAGE);
			Logger.writeLog("ERROR: No se pudo desuscribir al usuario del evento: " + username + " -> ID del evento: "
					+ event.getId());
		}
	}

	private void backToStart() {
		Logger.writeLog("Regresando al menú principal desde la vista de eventos.");
		ViewStart viewStart = new ViewStart();
		new ControllerStart(viewStart);
		viewStart.setVisible(true);
		this.view.dispose();
	}

	public void setEncounterFilter(int encounterCode) {
		this.filterEncounterCode = encounterCode;
		loadEvents();
	}

	public void setUserFilter(String username) {
		this.userFilterUsername = username;
		loadEvents();
	}

	public void showEvents() {
		Logger.writeLog("Abriendo vista de eventos.");
		this.view.setVisible(true);
	}
}