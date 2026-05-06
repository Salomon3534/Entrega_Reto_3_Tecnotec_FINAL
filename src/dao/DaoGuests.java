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
		try (Connection connection = DatabaseConnector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, username);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					Guest guest = resultSetToGuest(resultSet);
					return guest.toString();
				}
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al buscar invitado '" + username + "': " + e.getMessage());
		}
		return "No se encontró ningún invitado con el nombre de usuario: " + username;
	}

	public static String createGuest(Guest guest) {
		String query = "INSERT INTO invitados (NOMBRE_USUARIO, NOMBRE, APELLIDOS, TELEFONO, DESCRIPCION_RECORRIDO, EMAIL, CONTRASENA) VALUES (?,?,?,?,?,?,?)";

		try (Connection connection = DatabaseConnector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, guest.getUsername());
			preparedStatement.setString(2, guest.getName());
			preparedStatement.setString(3, guest.getSurnames());
			preparedStatement.setString(4, guest.getPhoneNumber());
			preparedStatement.setString(5, guest.getCareer());
			preparedStatement.setString(6, guest.getEmail());
			preparedStatement.setString(7, guest.getPassword());

			preparedStatement.executeUpdate();
			Logger.writeLog("Invitado creado: " + guest.getUsername());
			return "Invitado '" + guest.getUsername() + "' creado con éxito.";
		} catch (SQLException e) {
			Logger.writeLog("ERROR al crear invitado '" + guest.getUsername() + "': " + e.getMessage());
			return "Error al crear invitado: " + e.getMessage();
		}
	}

	public static String listGuests() {
		StringBuilder stringBuilder = new StringBuilder();
		String query = "SELECT * FROM invitados";

		try (Connection connection = DatabaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				stringBuilder.append(resultSetToGuest(resultSet).toString()).append("\n");
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al listar invitados: " + e.getMessage());
			return "Error al listar invitados.";
		}

		return stringBuilder.length() == 0 ? "No hay invitados registrados." : stringBuilder.toString();
	}

	public static ArrayList<Guest> listGuestsAsList() {
		ArrayList<Guest> guestList = new ArrayList<>();
		String query = "SELECT * FROM invitados";

		try (Connection connection = DatabaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				guestList.add(resultSetToGuest(resultSet));
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al listar invitados: " + e.getMessage());
		}

		return guestList;
	}

	public static String updateGuest(Guest guest) {
		String query = "UPDATE invitados SET NOMBRE=?, APELLIDOS=?, TELEFONO=?, DESCRIPCION_RECORRIDO=?, EMAIL=?, CONTRASENA=? WHERE NOMBRE_USUARIO=?";
		try (Connection connection = DatabaseConnector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, guest.getName());
			preparedStatement.setString(2, guest.getSurnames());
			preparedStatement.setString(3, guest.getPhoneNumber());
			preparedStatement.setString(4, guest.getCareer());
			preparedStatement.setString(5, guest.getEmail());
			preparedStatement.setString(6, guest.getPassword());
			preparedStatement.setString(7, guest.getUsername());

			if (preparedStatement.executeUpdate() > 0) {
				Logger.writeLog("Invitado actualizado: " + guest.getUsername());
				return "Datos de '" + guest.getUsername() + "' actualizados correctamente.";
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al actualizar invitado '" + guest.getUsername() + "': " + e.getMessage());
		}
		return "Error: El usuario no existe o no se pudo actualizar.";
	}

	public static String deleteGuest(String username) {
		String query = "DELETE FROM invitados WHERE NOMBRE_USUARIO = ?";
		try (Connection connection = DatabaseConnector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, username);
			if (preparedStatement.executeUpdate() > 0) {
				Logger.writeLog("Invitado eliminado: " + username);
				return "Invitado '" + username + "' eliminado correctamente.";
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al eliminar invitado '" + username + "': " + e.getMessage());
		}
		return "Error: No se encontró al usuario '" + username + "'.";
	}

	public static Guest getGuestByUsernameAndPassword(String username, String password) {
		String query = "SELECT * FROM invitados WHERE NOMBRE_USUARIO = ? AND CONTRASENA = ?";
		try (Connection connection = DatabaseConnector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return resultSetToGuest(resultSet);
				}
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al validar credenciales de '" + username + "': " + e.getMessage());
		}
		return null;
	}

	private static Guest resultSetToGuest(ResultSet resultSet) throws SQLException {
		return new Guest(resultSet.getString("NOMBRE_USUARIO"), resultSet.getString("NOMBRE"), resultSet.getString("APELLIDOS"),
				resultSet.getString("TELEFONO"), resultSet.getString("DESCRIPCION_RECORRIDO"), resultSet.getString("EMAIL"),
				resultSet.getString("CONTRASENA"));
	}
}