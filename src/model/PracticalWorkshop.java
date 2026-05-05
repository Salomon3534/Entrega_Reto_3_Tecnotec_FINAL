package model;

import java.sql.Date;
import java.sql.Time;

public class PracticalWorkshop extends Event {

	private int workshopNumber;

	public PracticalWorkshop(int id, String title, String location, String description, Date dateStart, Date dateEnd,
			Time hourStart, Time hourEnd, int eCode, int workshopNumber) {
		super(id, title, location, description, dateStart, dateEnd, hourStart, hourEnd, eCode);
		this.workshopNumber = workshopNumber;
	}

	public int getWorkshopNumber() {
		return workshopNumber;
	}

	public void setWorkshopNumber(int workshopNumber) {
		this.workshopNumber = workshopNumber;
	}

	@Override
	public String toString() {
		return "[Taller] " + getTitle();
	}
}