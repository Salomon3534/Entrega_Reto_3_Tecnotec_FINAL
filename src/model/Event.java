package model;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Event {
	private int id;
	private String title;
	private String location;
	private String description;
	private Date dateStart;
	private Date dateEnd;
	private Time hourStart;
	private Time hourEnd;
	private int encounterCode;

	public Event(int id, String title, String location, String description, Date dateStart, Date dateEnd,
			Time hourStart, Time hourEnd, int encounterCode) {
		this.id = id;
		this.title = title;
		this.location = location;
		this.description = description;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.hourStart = hourStart;
		this.hourEnd = hourEnd;
		this.encounterCode = encounterCode;
	}

	// Getters y Setters
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getLocation() {
		return location;
	}

	public String getDescription() {
		return description;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public Time getHourStart() {
		return hourStart;
	}

	public Time getHourEnd() {
		return hourEnd;
	}

	public int getEncounterCode() {
		return encounterCode;
	}

	@Override
	public String toString() {
		return (title != null) ? title : "Evento sin título";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		return id == other.id;
	}
}