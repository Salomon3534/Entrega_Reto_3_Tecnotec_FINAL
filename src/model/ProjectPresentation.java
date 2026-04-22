package model;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class ProjectPresentation extends Event {

	private String projectType;
	private String projectDescription;

	// constructor

	public ProjectPresentation(int id, String title, String location, String description, Date dateStart, Date dateEnd,
			Time hourStart, Time hourEnd, int eCode, String projectType, String projectDescription) {
		super(id, title, location, description, dateStart, dateEnd, hourStart, hourEnd, eCode);
		this.projectType = projectType;
		this.projectDescription = projectDescription;
	}

	// getters y Setters
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	@Override
	public String toString() {
		return "\n**************************************************\n" + "      PRESENTACIÓN PROYECTO: "
				+ (getTitle() != null ? getTitle().toUpperCase() : "N/A") + "\n"
				+ "**************************************************\n" + " > ID EVENTO:      " + getId() + "\n"
				+ " > TIPO PROYECTO:  " + projectType + "\n" + " > DESC. PROYECTO: " + projectDescription + "\n"
				+ " > LUGAR:          " + getLocation() + "\n" + " > FECHA:          " + getDateStart() + " al "
				+ getDateEnd() + "\n" + " > HORARIO:        " + getHourStart() + " - " + getHourEnd() + "\n"
				+ " > ENCUENTRO:      " + getEncounterCode() + "\n" + " > DESC. GENERAL:  " + getDescription() + "\n"
				+ "**************************************************";
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), projectType, projectDescription);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		if (!super.equals(obj))
			return false;
		ProjectPresentation other = (ProjectPresentation) obj;
		return Objects.equals(projectType, other.projectType)
				&& Objects.equals(projectDescription, other.projectDescription);
	}
}