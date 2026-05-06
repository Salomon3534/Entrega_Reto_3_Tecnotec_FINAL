package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

public class DaoUsers {

	public static String getUserByDni(String dni) {
		String query = "SELECT * FROM USUARIO WHERE DNI = ?";
		try (Connection connection = DatabaseConnector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, dni);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					User user = resultSetToUser(resultSet);
					return user.toString();
				}
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al buscar usuario con DNI " + dni + ": " + e.getMessage());
		}
		return "No se encontró ningún usuario con el DNI: " + dni;
	}

	public static String createUser(User user) {
		String query = "INSERT INTO USUARIO (DNI, NOMBRE, APELLIDO, EMAIL) VALUES (?, ?, ?, ?)";
		try (Connection connection = DatabaseConnector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, user.getDni());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(3, user.getSurname());
			preparedStatement.setString(4, user.getEmail());

			preparedStatement.executeUpdate();
			Logger.writeLog("Usuario creado: " + user.getDni() + " (" + user.getName() + ")");
			return "¡Usuario '" + user.getName() + "' creado con éxito!";
		} catch (SQLException e) {
			Logger.writeLog("ERROR al crear usuario " + user.getDni() + ": " + e.getMessage());
			return "Error al crear usuario: " + e.getMessage();
		}
	}

	public static String listUsers() {
		StringBuilder stringBuilder = new StringBuilder();
		String query = "SELECT * FROM USUARIO";
		try (Connection connection = DatabaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				stringBuilder.append(resultSetToUser(resultSet).toString()).append("\n");
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al listar usuarios: " + e.getMessage());
			return "Error al listar usuarios.";
		}
		return stringBuilder.length() == 0 ? "No hay usuarios registrados." : stringBuilder.toString();
	}

	public static String updateUser(User user) {
		String query = "UPDATE USUARIO SET NOMBRE=?, APELLIDO=?, EMAIL=? WHERE DNI=?";
		try (Connection connection = DatabaseConnector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getSurname());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getDni());

			if (preparedStatement.executeUpdate() > 0) {
				Logger.writeLog("Usuario actualizado: " + user.getDni());
				return "Datos de '" + user.getName() + "' actualizados correctamente.";
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al actualizar usuario " + user.getDni() + ": " + e.getMessage());
		}
		return "Error: El usuario no existe o no se pudo actualizar.";
	}

	public static String deleteUser(String dni) {
		String query = "DELETE FROM USUARIO WHERE DNI = ?";
		try (Connection connection = DatabaseConnector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, dni);
			if (preparedStatement.executeUpdate() > 0) {
				Logger.writeLog("Usuario eliminado: " + dni);
				return "El usuario con DNI: '" + dni + "' se ha eliminado correctamente.";
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al eliminar usuario " + dni + ": " + e.getMessage());
		}
		return "Error: No se encontró al usuario con el DNI '" + dni + "'.";
	}

	private static User resultSetToUser(ResultSet resultSet) throws SQLException {
		return new User(resultSet.getString("DNI"), resultSet.getString("NOMBRE"), resultSet.getString("APELLIDO"), resultSet.getString("EMAIL"));
	}
}