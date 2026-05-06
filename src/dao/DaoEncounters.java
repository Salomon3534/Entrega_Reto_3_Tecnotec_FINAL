package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Encounter;

public class DaoEncounters {

	public static String createEncounter(String location, Date dateStart, Date dateEnd) {
		String sql = "INSERT INTO encuentro (FECHA_INICIO, FECHA_FIN, UBICACION) VALUES (?, ?, ?)";
		try (Connection connection = DatabaseConnector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setDate(1, dateStart);
			preparedStatement.setDate(2, dateEnd);
			preparedStatement.setString(3, location);
			preparedStatement.executeUpdate();

			Logger.writeLog("Encuentro creado en " + location);
			return "Encuentro creado correctamente.";
		} catch (SQLException e) {
			Logger.writeLog("ERROR al crear encuentro: " + e.getMessage());
			return "Error: " + e.getMessage();
		}
	}

	public static String getEncounterById(int code) {
		String sql = "SELECT CODIGO, FECHA_INICIO, FECHA_FIN, UBICACION FROM encuentro WHERE CODIGO = ?";
		try (Connection connection = DatabaseConnector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, code);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					Encounter encounter = resultSetToEncounter(resultSet);
					return encounter.toString();
				}
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al buscar encuentro " + code + ": " + e.getMessage());
		}
		return "No se encontró el encuentro con código: " + code;
	}

	public static String listEncounters() {
		StringBuilder stringBuilder = new StringBuilder();
		String sql = "SELECT CODIGO, FECHA_INICIO, FECHA_FIN, UBICACION FROM encuentro";
		try (Connection connection = DatabaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				stringBuilder.append(resultSetToEncounter(resultSet).toString()).append("\n");
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al listar encuentros: " + e.getMessage());
			return "Error al listar.";
		}
		return stringBuilder.length() == 0 ? "No hay encuentros." : stringBuilder.toString();
	}

	public static ArrayList<Encounter> listEncountersAsList() {
		ArrayList<Encounter> encounterList = new ArrayList<>();
		String sql = "SELECT CODIGO, FECHA_INICIO, FECHA_FIN, UBICACION FROM encuentro";
		try (Connection connection = DatabaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				encounterList.add(resultSetToEncounter(resultSet));
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al listar encuentros: " + e.getMessage());
		}
		return encounterList;
	}

	public static String updateEncounter(Encounter encounter) {
		String sql = "UPDATE encuentro SET FECHA_INICIO = ?, FECHA_FIN = ?, UBICACION = ? WHERE CODIGO = ?";
		try (Connection connection = DatabaseConnector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setDate(1, encounter.getDateStart());
			preparedStatement.setDate(2, encounter.getDateEnd());
			preparedStatement.setString(3, encounter.getLocation());
			preparedStatement.setInt(4, encounter.getCode());

			int rows = preparedStatement.executeUpdate();
			if (rows > 0) {
				Logger.writeLog("Encuentro actualizado: ID " + encounter.getCode());
				return "Encuentro actualizado.";
			}
		} catch (SQLException exception) {
			Logger.writeLog("ERROR al actualizar encuentro: " + exception.getMessage());
		}
		return "No se pudo actualizar.";
	}

	public static String deleteEncounter(int code) {
		String sql = "DELETE FROM encuentro WHERE CODIGO = ?";
		try (Connection connection = DatabaseConnector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, code);
			int rows = preparedStatement.executeUpdate();
			if (rows > 0) {
				Logger.writeLog("Encuentro eliminado: ID " + code);
				return "Encuentro eliminado.";
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al eliminar encuentro: " + e.getMessage());
		}
		return "No se encontró el identificador.";
	}

	public static int getGlobalCounter() {
		String sql = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES "
				+ "WHERE TABLE_SCHEMA = 'euskalencounter' AND TABLE_NAME = 'evento'";
		try (Connection connection = DatabaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			if (resultSet.next())
				return resultSet.getInt("AUTO_INCREMENT");
		} catch (SQLException e) {
			Logger.writeLog("ERROR al obtener contador: " + e.getMessage());
		}
		return -1;
	}

	private static Encounter resultSetToEncounter(ResultSet resultSet) throws SQLException {
		return new Encounter(resultSet.getInt("CODIGO"), resultSet.getString("UBICACION"), resultSet.getDate("FECHA_INICIO"),
				resultSet.getDate("FECHA_FIN"));
	}
}