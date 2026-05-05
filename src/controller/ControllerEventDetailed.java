package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Event;
import model.KeynoteSpeech;
import model.PracticalWorkshop;
import model.ProjectPresentation;
import model.RoundTable;
import view.ViewEventDetailed;

public class ControllerEventDetailed implements ActionListener {

	private ViewEventDetailed view;
	private Event event;

	public ControllerEventDetailed(ViewEventDetailed view, Event event) {
		this.view = view;
		this.event = event;

		loadData();

		this.view.getBTN_back().addActionListener(this);
		this.view.getBTN_action().addActionListener(this);
		this.view.getBTN_cancel().addActionListener(this);
	}

	private void loadData() {
		view.getLblTitle().setText(event.getTitle());
		view.getLblLocation().setText("Ubicación: " + event.getLocation());
		view.getLblDates().setText(event.getDateStart() + " | " + event.getHourStart() + " - " + event.getHourEnd());
		view.getTxtDescription().setText(event.getDescription());

		if (event instanceof KeynoteSpeech k) {
			view.getLblSpecificInfo().setText("Tipo: Conferencia Magistral - " + k.getSpeechType());
		} else if (event instanceof PracticalWorkshop w) {
			view.getLblSpecificInfo().setText("Tipo: Taller Práctico nº " + w.getWorkshopNumber());
		} else if (event instanceof ProjectPresentation p) {
			view.getLblSpecificInfo().setText("Tipo: Proyecto - " + p.getProjectType());
		} else if (event instanceof RoundTable r) {
			view.getLblSpecificInfo().setText("Tipo: Mesa Redonda nº " + r.getConferenceNumber());
		} else {
			view.getLblSpecificInfo().setText("Tipo: Evento General");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == view.getBTN_back()) {
			view.dispose();
		} else if (e.getSource() == view.getBTN_action()) {
			System.out.println("Inscripción: " + event.getId());
		} else if (e.getSource() == view.getBTN_cancel()) {
			System.out.println("Cancelación: " + event.getId());
		}
	}
}