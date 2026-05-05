package model;

import java.sql.Date;
import java.sql.Time;

public class ProjectPresentation extends Event {

	private String projectType;
	private String projectDescription;

	public ProjectPresentation(int id, String title, String location, String description, Date dateStart, Date dateEnd,
			Time hourStart, Time hourEnd, int eCode, String projectType, String projectDescription) {
		super(id, title, location, description, dateStart, dateEnd, hourStart, hourEnd, eCode);
		this.projectType = projectType;
		this.projectDescription = projectDescription;
	}

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
		return "[Proyecto] " + getTitle();
	}
}