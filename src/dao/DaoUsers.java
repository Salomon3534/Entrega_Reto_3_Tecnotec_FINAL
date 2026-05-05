package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

public class DaoUsers {

	public static String getUserByDni(String dni) {
		String query = "SELECT * FROM USUARIO WHERE DNI = ?";
		try (Connection con = DatabaseConnector.getConexion(); PreparedStatement ps = con.prepareStatement(query)) {

			ps.setString(1, dni);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					User u = resultSetToUser(rs);
					return u.toString();
				}
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al buscar usuario con DNI " + dni + ": " + e.getMessage());
		}
		return "No se encontró ningún usuario con el DNI: " + dni;
	}

	public static String createUser(User u) {
		String query = "INSERT INTO USUARIO (DNI, NOMBRE, APELLIDO, EMAIL) VALUES (?, ?, ?, ?)";
		try (Connection con = DatabaseConnector.getConexion(); PreparedStatement ps = con.prepareStatement(query)) {

			ps.setString(1, u.getDni());
			ps.setString(2, u.getName());
			ps.setString(3, u.getSurname());
			ps.setString(4, u.getEmail());

			ps.executeUpdate();
			Logger.writeLog("Usuario creado: " + u.getDni() + " (" + u.getName() + ")");
			return "¡Usuario '" + u.getName() + "' creado con éxito!";
		} catch (SQLException e) {
			Logger.writeLog("ERROR al crear usuario " + u.getDni() + ": " + e.getMessage());
			return "Error al crear usuario: " + e.getMessage();
		}
	}

	public static String listUsers() {
		StringBuilder sb = new StringBuilder();
		String query = "SELECT * FROM USUARIO";
		try (Connection con = DatabaseConnector.getConexion();
				PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				sb.append(resultSetToUser(rs).toString()).append("\n");
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al listar usuarios: " + e.getMessage());
			return "Error al listar usuarios.";
		}
		return sb.length() == 0 ? "No hay usuarios registrados." : sb.toString();
	}

	public static String updateUser(User u) {
		String query = "UPDATE USUARIO SET NOMBRE=?, APELLIDO=?, EMAIL=? WHERE DNI=?";
		try (Connection con = DatabaseConnector.getConexion(); PreparedStatement ps = con.prepareStatement(query)) {

			ps.setString(1, u.getName());
			ps.setString(2, u.getSurname());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getDni());

			if (ps.executeUpdate() > 0) {
				Logger.writeLog("Usuario actualizado: " + u.getDni());
				return "Datos de '" + u.getName() + "' actualizados correctamente.";
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al actualizar usuario " + u.getDni() + ": " + e.getMessage());
		}
		return "Error: El usuario no existe o no se pudo actualizar.";
	}

	public static String deleteUser(String dni) {
		String query = "DELETE FROM USUARIO WHERE DNI = ?";
		try (Connection con = DatabaseConnector.getConexion(); PreparedStatement ps = con.prepareStatement(query)) {

			ps.setString(1, dni);
			if (ps.executeUpdate() > 0) {
				Logger.writeLog("Usuario eliminado: " + dni);
				return "El usuario con DNI: '" + dni + "' se ha eliminado correctamente.";
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al eliminar usuario " + dni + ": " + e.getMessage());
		}
		return "Error: No se encontró al usuario con el DNI '" + dni + "'.";
	}

	private static User resultSetToUser(ResultSet rs) throws SQLException {
		return new User(rs.getString("DNI"), rs.getString("NOMBRE"), rs.getString("APELLIDO"), rs.getString("EMAIL"));
	}
}