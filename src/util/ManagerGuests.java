package util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import db.DatabaseConnector;
import model.Guest;

public class ManagerGuests {

	private ArrayList<Guest> guestList = new ArrayList<>();

	public ManagerGuests() throws SQLException {
		loadGuests();
	}

	/**
	 * busca un invitado por su nombre de usuario en la lista.
	 */
	public String getGuestByUsername(String username) {
		for (Guest g : guestList) {
			if (g.getUsername().equalsIgnoreCase(username)) {
				return g.toString();
			}
		}
		return "No se encontró ningún invitado con el nombre de usuario: " + username;
	}

	public void loadGuests() throws SQLException {
		guestList.clear();
		String query = "SELECT * FROM invitados";

		try (PreparedStatement ps = DatabaseConnector.getConexion().prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				guestList.add(new Guest(rs.getString("NOMBRE_USUARIO"), rs.getString("NOMBRE"),
						rs.getString("APELLIDOS"), rs.getString("TELEFONO"), rs.getString("DESCRIPCION_RECORRIDO"),
						rs.getString("EMAIL"), rs.getString("CONTRASENA")));
			}
		}
	}

	public String createGuest(String username, String name, String surnames, String phone, String career, String email,
			String password) throws SQLException {
		String query = "INSERT INTO invitados (NOMBRE_USUARIO, NOMBRE, APELLIDOS, TELEFONO, DESCRIPCION_RECORRIDO, EMAIL, CONTRASENA) VALUES (?,?,?,?,?,?,?)";

		try (PreparedStatement ps = DatabaseConnector.getConexion().prepareStatement(query)) {
			ps.setString(1, username);
			ps.setString(2, name);
			ps.setString(3, surnames);
			ps.setString(4, phone);
			ps.setString(5, career);
			ps.setString(6, email);
			ps.setString(7, password);
			ps.executeUpdate();
		}
		loadGuests();
		return "¡Invitado '" + username + "' creado con éxito!";
	}

	public String listGuests() {
		if (guestList.isEmpty())
			return "No hay invitados registrados.";
		StringBuilder sb = new StringBuilder();
		for (Guest g : guestList)
			sb.append(g.toString());
		return sb.toString();
	}

	public String updateGuest(Guest g) throws SQLException {
		String query = "UPDATE invitados SET NOMBRE=?, APELLIDOS=?, TELEFONO=?, DESCRIPCION_RECORRIDO=?, EMAIL=?, CONTRASENA=? WHERE NOMBRE_USUARIO=?";
		try (PreparedStatement ps = DatabaseConnector.getConexion().prepareStatement(query)) {

			ps.setString(1, g.getName());
			ps.setString(2, g.getSurnames());
			ps.setString(3, g.getPhoneNumber());
			ps.setString(4, g.getCareer());
			ps.setString(5, g.getEmail());
			ps.setString(6, g.getPassword());
			ps.setString(7, g.getUsername());

			if (ps.executeUpdate() > 0) {
				loadGuests();
				return "Datos de '" + g.getUsername() + "' actualizados correctamente.";
			}
		}
		return "Error: El usuario no existe.";
	}

	public String deleteGuest(String username) throws SQLException {
		String query = "DELETE FROM invitados WHERE NOMBRE_USUARIO = ?";
		try (PreparedStatement ps = DatabaseConnector.getConexion().prepareStatement(query)) {

			ps.setString(1, username);
			if (ps.executeUpdate() > 0) {
				loadGuests();
				return "Invitado '" + username + "' eliminado correctamente.";
			}
		}
		return "Error: No se encontró al usuario '" + username + "'.";
	}
}