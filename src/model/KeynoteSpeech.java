package model;

import java.sql.Date;
import java.sql.Time;

public class KeynoteSpeech extends Event {

	private String speechType;

	public KeynoteSpeech(int id, String title, String location, String description, Date dateStart, Date dateEnd,
			Time hourStart, Time hourEnd, int eCode, String speechType) {
		super(id, title, location, description, dateStart, dateEnd, hourStart, hourEnd, eCode);
		this.speechType = speechType;
	}

	public String getSpeechType() {
		return speechType;
	}

	public void setSpeechType(String speechType) {
		this.speechType = speechType;
	}

	@Override
	public String toString() {
		return "[Conferencia magistral] " + getTitle();
	}
}