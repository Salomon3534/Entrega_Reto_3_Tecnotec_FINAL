package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Guest;

public class DaoGuests {

	public static String getGuestByUsername(String username) {
		String query = "SELECT * FROM invitados WHERE NOMBRE_USUARIO = ?";
		try (Connection con = DatabaseConnector.getConexion(); PreparedStatement ps = con.prepareStatement(query)) {

			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Guest g = resultSetToGuest(rs);
					return g.toString();
				}
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al buscar invitado '" + username + "': " + e.getMessage());
		}
		return "No se encontró ningún invitado con el nombre de usuario: " + username;
	}

	public static String createGuest(Guest g) {
		String query = "INSERT INTO invitados (NOMBRE_USUARIO, NOMBRE, APELLIDOS, TELEFONO, DESCRIPCION_RECORRIDO, EMAIL, CONTRASENA) VALUES (?,?,?,?,?,?,?)";

		try (Connection con = DatabaseConnector.getConexion(); PreparedStatement ps = con.prepareStatement(query)) {

			ps.setString(1, g.getUsername());
			ps.setString(2, g.getName());
			ps.setString(3, g.getSurnames());
			ps.setString(4, g.getPhoneNumber());
			ps.setString(5, g.getCareer());
			ps.setString(6, g.getEmail());
			ps.setString(7, g.getPassword());

			ps.executeUpdate();
			Logger.writeLog("Invitado creado: " + g.getUsername());
			return "Invitado '" + g.getUsername() + "' creado con éxito.";
		} catch (SQLException e) {
			Logger.writeLog("ERROR al crear invitado '" + g.getUsername() + "': " + e.getMessage());
			return "Error al crear invitado: " + e.getMessage();
		}
	}

	public static String listGuests() {
		StringBuilder sb = new StringBuilder();
		String query = "SELECT * FROM invitados";

		try (Connection con = DatabaseConnector.getConexion();
				PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				sb.append(resultSetToGuest(rs).toString()).append("\n");
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al listar invitados: " + e.getMessage());
			return "Error al listar invitados.";
		}

		return sb.length() == 0 ? "No hay invitados registrados." : sb.toString();
	}

	public static ArrayList<Guest> listGuestsAsList() {
		ArrayList<Guest> lista = new ArrayList<>();
		String query = "SELECT * FROM invitados";

		try (Connection con = DatabaseConnector.getConexion();
				PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				lista.add(resultSetToGuest(rs));
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al listar invitados: " + e.getMessage());
		}

		return lista;
	}

	public static String updateGuest(Guest g) {
		String query = "UPDATE invitados SET NOMBRE=?, APELLIDOS=?, TELEFONO=?, DESCRIPCION_RECORRIDO=?, EMAIL=?, CONTRASENA=? WHERE NOMBRE_USUARIO=?";
		try (Connection con = DatabaseConnector.getConexion(); PreparedStatement ps = con.prepareStatement(query)) {

			ps.setString(1, g.getName());
			ps.setString(2, g.getSurnames());
			ps.setString(3, g.getPhoneNumber());
			ps.setString(4, g.getCareer());
			ps.setString(5, g.getEmail());
			ps.setString(6, g.getPassword());
			ps.setString(7, g.getUsername());

			if (ps.executeUpdate() > 0) {
				Logger.writeLog("Invitado actualizado: " + g.getUsername());
				return "Datos de '" + g.getUsername() + "' actualizados correctamente.";
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al actualizar invitado '" + g.getUsername() + "': " + e.getMessage());
		}
		return "Error: El usuario no existe o no se pudo actualizar.";
	}

	public static String deleteGuest(String username) {
		String query = "DELETE FROM invitados WHERE NOMBRE_USUARIO = ?";
		try (Connection con = DatabaseConnector.getConexion(); PreparedStatement ps = con.prepareStatement(query)) {

			ps.setString(1, username);
			if (ps.executeUpdate() > 0) {
				Logger.writeLog("Invitado eliminado: " + username);
				return "Invitado '" + username + "' eliminado correctamente.";
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al eliminar invitado '" + username + "': " + e.getMessage());
		}
		return "Error: No se encontró al usuario '" + username + "'.";
	}

	private static Guest resultSetToGuest(ResultSet rs) throws SQLException {
		return new Guest(rs.getString("NOMBRE_USUARIO"), rs.getString("NOMBRE"), rs.getString("APELLIDOS"),
				rs.getString("TELEFONO"), rs.getString("DESCRIPCION_RECORRIDO"), rs.getString("EMAIL"),
				rs.getString("CONTRASENA"));
	}
}