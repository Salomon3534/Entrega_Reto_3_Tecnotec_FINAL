package model;

import java.sql.Date;
import java.sql.Time;

public class RoundTable extends Event {

	private int conferenceNumber;

	public RoundTable(int id, String title, String location, String description, Date dateStart, Date dateEnd,
			Time hourStart, Time hourEnd, int eCode, int numConf) {
		super(id, title, location, description, dateStart, dateEnd, hourStart, hourEnd, eCode);
		this.conferenceNumber = numConf;
	}

	public int getConferenceNumber() {
		return conferenceNumber;
	}

	public void setConferenceNumber(int conferenceNumber) {
		this.conferenceNumber = conferenceNumber;
	}

	@Override
	public String toString() {
		return "[Mesa Redonda] " + getTitle();
	}
}