package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import dao.DaoEvents;
import dao.Logger;
import model.Event;
import model.KeynoteSpeech;
import model.PracticalWorkshop;
import model.ProjectPresentation;
import model.RoundTable;
import util.SessionManager;
import view.ViewEventDetailed;

public class ControllerEventDetailed implements ActionListener {

	private ViewEventDetailed view;
	private Event event;

	public ControllerEventDetailed(ViewEventDetailed view, Event event) {
		this.view = view;
		this.event = event;

		loadData();
		updateButtonStates();

		this.view.getBTN_back().addActionListener(this);
		this.view.getBTN_action().addActionListener(this);
		this.view.getBTN_cancel().addActionListener(this);
	}

	private void loadData() {
		view.getLblTitle().setText(event.getTitle());
		view.getLblLocation().setText("Ubicación: " + event.getLocation());
		view.getLblDates().setText(event.getDateStart() + " | " + event.getHourStart() + " - " + event.getHourEnd());
		view.getTxtDescription().setText(event.getDescription());

		if (event instanceof KeynoteSpeech keynoteSpeech) {
			view.getLblSpecificInfo().setText("Tipo: Conferencia Magistral - " + keynoteSpeech.getSpeechType());
		} else if (event instanceof PracticalWorkshop workshop) {
			view.getLblSpecificInfo().setText("Tipo: Taller Práctico nº " + workshop.getWorkshopNumber());
		} else if (event instanceof ProjectPresentation presentation) {
			view.getLblSpecificInfo().setText("Tipo: Proyecto - " + presentation.getProjectType());
		} else if (event instanceof RoundTable roundTable) {
			view.getLblSpecificInfo().setText("Tipo: Mesa Redonda nº " + roundTable.getConferenceNumber());
		} else {
			view.getLblSpecificInfo().setText("Tipo: Evento General");
		}
		Logger.writeLog("Vista detallada de evento abierta: " + event.getTitle());
	}

	private void updateButtonStates() {
		String username = SessionManager.getInstance().getActiveUsername();
		if (username != null) {
			boolean isRegistered = DaoEvents.isUserRegistered(event.getId(), username);
			view.getBTN_action().setVisible(!isRegistered);
			view.getBTN_cancel().setVisible(isRegistered);
		} else {
			view.getBTN_action().setVisible(true);
			view.getBTN_cancel().setVisible(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == view.getBTN_back()) {
			Logger.writeLog("Cerrando vista detallada de evento.");
			view.dispose();
		} else if (e.getSource() == view.getBTN_action()) {
			register();
		} else if (e.getSource() == view.getBTN_cancel()) {
			unregister();
		}
	}

	private void register() {
		String username = SessionManager.getInstance().getActiveUsername();
		if (username == null) {
			JOptionPane.showMessageDialog(view, "No hay usuario registrado", "Error", JOptionPane.ERROR_MESSAGE);
			Logger.writeLog("Intento de inscripción sin usuario activo para el evento: " + event.getTitle());
			return;
		}

		if (DaoEvents.registerUserInEvent(event.getId(), username)) {
			JOptionPane.showMessageDialog(view, "¡Inscripción realizada exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			Logger.writeLog("Usuario inscrito en evento: " + username + " -> " + event.getTitle());
			updateButtonStates();
		} else {
			JOptionPane.showMessageDialog(view, "Error al inscribirse en el evento", "Error", JOptionPane.ERROR_MESSAGE);
			Logger.writeLog("ERROR: No se pudo inscribir " + username + " en el evento: " + event.getTitle());
		}
	}

	private void unregister() {
		String username = SessionManager.getInstance().getActiveUsername();
		if (username == null) {
			JOptionPane.showMessageDialog(view, "No hay usuario registrado", "Error", JOptionPane.ERROR_MESSAGE);
			Logger.writeLog("Intento de desinscripción sin usuario activo para el evento: " + event.getTitle());
			return;
		}

		if (DaoEvents.unregisterUserFromEvent(event.getId(), username)) {
			JOptionPane.showMessageDialog(view, "¡Desinscripción realizada exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			Logger.writeLog("Usuario desinscrito del evento: " + username + " -> " + event.getTitle());
			updateButtonStates();
		} else {
			JOptionPane.showMessageDialog(view, "Error al desinscribirse del evento", "Error", JOptionPane.ERROR_MESSAGE);
			Logger.writeLog("ERROR: No se pudo desuscribir " + username + " del evento: " + event.getTitle());
		}
	}
}