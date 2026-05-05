package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import dao.DaoEncounters;
import dao.DatabaseConnector;
import model.Encounter;
import view.ViewEncounters;
import view.ViewStart;

public class ControllerEncounters {
	private ViewEncounters view;

	public ControllerEncounters(ViewEncounters view) {
		this.view = view;

		cargarEncuentros();

		this.view.getBTN_back().addActionListener(_ -> volverAlInicio());

		this.view.getEncounterList().addListSelectionListener(e -> {
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

	private void cargarEncuentros() {
		view.getListModel().clear();
		ArrayList<Encounter> encuentros = DaoEncounters.listEncountersAsList();
		for (Encounter enc : encuentros) {
			view.getListModel().addElement(enc);
		}
	}

	private void actualizarDetalle() {
		Encounter seleccionado = view.getEncounterList().getSelectedValue();
		if (seleccionado != null) {
			view.getEncounterNameLabel().setText(seleccionado.getLocation());
			view.getEncounterDatesLabel().setText(seleccionado.getDateStart() + " al " + seleccionado.getDateEnd());
		}
	}

	private void volverAlInicio() {
		ViewStart vs = new ViewStart();
		new ControllerStart(vs);
		vs.setVisible(true);
		this.view.dispose();
	}

	public void showEncounters() {
		this.view.setVisible(true);
	}
}
