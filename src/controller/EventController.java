package controller;

import view.ViewEvents;

public class EventController {
	ViewEvents view;

	public EventController(ViewEvents view) {
		this.view = view;
	}

	public void showEvents() {
        this.view.setVisible(true);
    }

}
