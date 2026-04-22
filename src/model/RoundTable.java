package model;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class RoundTable extends Event {

	private int conferenceNumber;

	// constructor

	public RoundTable(int id, String title, String location, String description, Date dateStart, Date dateEnd,
			Time hourStart, Time hourEnd, int eCode, int numConf) {
		super(id, title, location, description, dateStart, dateEnd, hourStart, hourEnd, eCode);
		this.conferenceNumber = numConf;
	}

	// getters y setters

	public int getConferenceNumber() {
		return conferenceNumber;
	}

	public void setConferenceNumber(int conferenceNumber) {
		this.conferenceNumber = conferenceNumber;
	}

	@Override
	public String toString() {
		return "\n**************************************************\n" + "             MESA REDONDA: "
				+ (getTitle() != null ? getTitle().toUpperCase() : "N/A") + "\n"
				+ "**************************************************\n" + " > ID EVENTO:      " + getId() + "\n"
				+ " > NÚM. CONF:      " + conferenceNumber + "\n" + " > LUGAR:          " + getLocation() + "\n"
				+ " > FECHA:          " + getDateStart() + " al " + getDateEnd() + "\n" + " > HORARIO:        "
				+ getHourStart() + " - " + getHourEnd() + "\n" + " > ENCUENTRO:      " + getEncounterCode() + "\n"
				+ " > DESCRIPCIÓN:    " + getDescription() + "\n"
				+ "**************************************************";
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), conferenceNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		if (!super.equals(obj))
			return false;
		RoundTable other = (RoundTable) obj;
		return conferenceNumber == other.conferenceNumber;
	}
}