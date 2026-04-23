package controller;

import view.EventsView;

public class EventController {
	EventsView view;
	
	public EventController(EventsView view) {
		this.view = view;
	}
	
	public void showEvents() {
        this.view.setVisible(true);
    }
	
}
