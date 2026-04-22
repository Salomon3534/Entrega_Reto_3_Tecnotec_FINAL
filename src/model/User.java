package model;

import java.util.Objects;

public class User {

	private String dni;
	private String name;
	private String surname;
	private String email;

	// constructores
	public User(String dni, String name, String surname, String email) {
		this.dni = dni;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}

	// getters y setters

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "\n" + "**************************************************\n" + "         FICHA DE USUARIO: "
				+ (dni != null ? dni.toUpperCase() : "N/A") + "\n"
				+ "**************************************************\n" 
				+ " > DNI:       " + dni + "\n" + " > NOMBRE:    " + name + " " + surname + "\n" + " > EMAIL:     "
				+ email + "\n" + "**************************************************";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(dni, other.dni);
	}


}