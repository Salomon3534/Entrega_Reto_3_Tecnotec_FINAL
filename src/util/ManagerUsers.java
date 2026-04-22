package util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import db.DatabaseConnector;
import model.User;

public class ManagerUsers {

	private ArrayList<User> usersList = new ArrayList<>();

	public ManagerUsers() throws SQLException {
		loadUsers();
	}

	/**
	 * busca un usuario por su DNI en la lista.
	 */
	public String getUserByDni(String dni) {
		for (User u : usersList) {
			if (u.getDni().equalsIgnoreCase(dni)) {
				return u.toString();
			}
		}
		return "No se encontró ningún usuario con el DNI: " + dni;
	}

	public void loadUsers() throws SQLException {
		usersList.clear();
		String query = "SELECT * FROM USUARIO";
		try (PreparedStatement ps = DatabaseConnector.getConexion().prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				usersList.add(new User(rs.getString("DNI"), rs.getString("NOMBRE"), rs.getString("APELLIDO"),
						rs.getString("EMAIL")));
			}
		}
	}

	public String createUser(String dni, String name, String surname, String email) throws SQLException {
		String query = "INSERT INTO USUARIO (DNI, NOMBRE, APELLIDO, EMAIL) VALUES (?, ?, ?, ?)";
		try (PreparedStatement ps = DatabaseConnector.getConexion().prepareStatement(query)) {
			ps.setString(1, dni);
			ps.setString(2, name);
			ps.setString(3, surname);
			ps.setString(4, email);
			ps.executeUpdate();
		}
		loadUsers();
		return "¡Usuario '" + name + "' creado con éxito!";
	}

	public String listUsers() {
		if (usersList.isEmpty())
			return "No hay usuarios registrados.";
		StringBuilder sb = new StringBuilder();
		for (User u : usersList)
			sb.append(u.toString()).append("\n");
		return sb.toString();
	}

	public String updateUser(User u) throws SQLException {
		String query = "UPDATE USUARIO SET NOMBRE=?, APELLIDO=?, EMAIL=? WHERE DNI=?";
		try (PreparedStatement ps = DatabaseConnector.getConexion().prepareStatement(query)) {
			ps.setString(1, u.getName());
			ps.setString(2, u.getSurname());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getDni());
			if (ps.executeUpdate() > 0) {
				loadUsers();
				return "Datos de '" + u.getName() + "' actualizados correctamente.";
			}
		}
		return "Error: El usuario no existe.";
	}

	public String deleteUser(String dni) throws SQLException {
		String query = "DELETE FROM USUARIO WHERE DNI = ?";
		try (PreparedStatement ps = DatabaseConnector.getConexion().prepareStatement(query)) {
			ps.setString(1, dni);
			if (ps.executeUpdate() > 0) {
				loadUsers();
				return "El usuario con DNI: '" + dni + "' se ha eliminado correctamente.";
			}
		}
		return "Error: No se encontró al usuario con el dni '" + dni + "'.";
	}
}