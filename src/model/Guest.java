package model;

import java.util.Objects;

public class Guest {

	private String username;
	private String name;
	private String surnames;
	private String phoneNumber;
	private String career;
	private String email;
	private String password;

	// constructor

	public Guest(String username, String name, String surnames, String phoneNumber, String career, String email,
			String password) {
		this.username = username;
		this.name = name;
		this.surnames = surnames;
		this.phoneNumber = phoneNumber;
		this.career = career;
		this.email = email;
		this.password = password;
	}

	// getters y Setters

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "\n" + "**************************************************\n" + "          FICHA DE INVITADO: "
				+ (username != null ? username.toUpperCase() : "N/A") + "\n"
				+ "**************************************************\n" + " > USUARIO:   " + username + "\n"
				+ " > NOMBRE:    " + name + " " + surnames + "\n" + " > TELÉFONO:  " + phoneNumber + "\n"
				+ " > CARRERA:   " + career + "\n" + " > EMAIL:     " + email + "\n"
				+ "**************************************************";
	}

	@Override
	public int hashCode() {
		return Objects.hash(username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Guest other = (Guest) obj;
		return Objects.equals(username, other.username);
	}
}