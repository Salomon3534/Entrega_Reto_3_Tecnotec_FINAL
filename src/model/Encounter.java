package model;

import java.sql.Date;
import java.util.Objects;

public class Encounter {

	private int code;
	private Date dateStart;
	private Date dateEnd;
	private String location;

	// constructor

	public Encounter(int code, String location, Date dateStart, Date dateEnd) {
		this.code = code;
		this.location = location;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}

	// getters y Setters
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	@Override
	public String toString() {
		return "\n**************************************************\n" + "                ENCUENTRO #" + code + "\n"
				+ "**************************************************\n" + " > LUGAR:         " + location + "\n"
				+ " > FECHAS:        " + dateStart + " al " + dateEnd + "\n"
				+ "**************************************************";
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, location, dateStart, dateEnd);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Encounter other = (Encounter) obj;
		return code == other.code;
	}
}