package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import dao.DaoEncounters;
import dao.DatabaseConnector;
import dao.Logger;
import model.Encounter;
import view.ViewEncounters;
import view.ViewEvents;
import view.ViewStart;

public class ControllerEncounters {
	private ViewEncounters view;

	public ControllerEncounters(ViewEncounters view) {
		this.view = view;

		loadEncounters();

		this.view.getBTN_back().addActionListener(_ -> backToStart());

		this.view.getViewEventsBtn().addActionListener(_ -> {
			Encounter selected = view.getEncounterList().getSelectedValue();
			if (selected != null) {
				viewEventsOfEncounter(selected);
			} else {
				JOptionPane.showMessageDialog(view, "Por favor selecciona un encuentro", "Advertencia", JOptionPane.WARNING_MESSAGE);
			}
		});

		this.view.getEncounterList().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				updateDetail();
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

	private void loadEncounters() {
		view.getListModel().clear();
		ArrayList<Encounter> encounters = DaoEncounters.listEncountersAsList();
		for (Encounter encounter : encounters) {
			view.getListModel().addElement(encounter);
		}
		Logger.writeLog("Lista de encuentros cargada: " + encounters.size() + " encuentros mostrados.");
	}

	private void updateDetail() {
		Encounter selected = view.getEncounterList().getSelectedValue();
		if (selected != null) {
			view.getEncounterNameLabel().setText(selected.getLocation());
			view.getEncounterDatesLabel().setText(selected.getDateStart() + " al " + selected.getDateEnd());
			Logger.writeLog("Encuentro seleccionado: Código " + selected.getCode() + " - " + selected.getLocation());
		}
	}

	private void viewEventsOfEncounter(Encounter encounter) {
		ViewEvents viewEvents = new ViewEvents();
		ControllerEvents controllerEvents = new ControllerEvents(viewEvents);
		controllerEvents.setEncounterFilter(encounter.getCode());
		Logger.writeLog("Visualizando eventos del encuentro: " + encounter.getLocation());
		controllerEvents.showEvents();
		this.view.dispose();
	}

	private void backToStart() {
		Logger.writeLog("Regresando al menú principal desde la vista de encuentros.");
		ViewStart viewStart = new ViewStart();
		new ControllerStart(viewStart);
		viewStart.setVisible(true);
		this.view.dispose();
	}

	public void showEncounters() {
		Logger.writeLog("Abriendo vista de encuentros.");
		this.view.setVisible(true);
	}
}
