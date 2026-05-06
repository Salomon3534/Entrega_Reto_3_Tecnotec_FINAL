package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import model.Event;
import model.KeynoteSpeech;
import model.PracticalWorkshop;
import model.ProjectPresentation;
import model.RoundTable;

public class DaoEvents {

	public static ArrayList<Event> listEventsAsList() {
		ArrayList<Event> eventList = new ArrayList<>();
		String query = "SELECT e.*, c.TIPO_DE_CONFERENCIA, t.NUMERO_TALLER, p.TIPO_DE_PROYECTO, p.DESCRIPCION_PROYECTO, m.NUMERO_CONFERENCIA FROM evento e "
				+ "LEFT JOIN conferencia_magistral c ON e.ID = c.ID LEFT JOIN talleres_practicos t ON e.ID = t.ID "
				+ "LEFT JOIN presentacion_de_proyecto p ON e.ID = p.ID LEFT JOIN mesa_redonda m ON e.ID = m.ID";

		try (Connection connection = DatabaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				eventList.add(resultSetToEvent(resultSet));
			}
		} catch (SQLException exception) {
			System.err.println("Error al listar eventos: " + exception.getMessage());
		}
		return eventList;
	}

	public static ArrayList<Event> listEventsByEncounter(int encounterCode) {
		ArrayList<Event> eventList = new ArrayList<>();
		String query = "SELECT e.*, c.TIPO_DE_CONFERENCIA, t.NUMERO_TALLER, p.TIPO_DE_PROYECTO, p.DESCRIPCION_PROYECTO, m.NUMERO_CONFERENCIA FROM evento e "
				+ "LEFT JOIN conferencia_magistral c ON e.ID = c.ID LEFT JOIN talleres_practicos t ON e.ID = t.ID "
				+ "LEFT JOIN presentacion_de_proyecto p ON e.ID = p.ID LEFT JOIN mesa_redonda m ON e.ID = m.ID "
				+ "WHERE e.ENCUENTRO_CODIGO = ?";

		try (Connection connection = DatabaseConnector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, encounterCode);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					eventList.add(resultSetToEvent(resultSet));
				}
			}
		} catch (SQLException exception) {
			System.err.println("Error al listar eventos del encuentro " + encounterCode + ": " + exception.getMessage());
		}
		return eventList;
	}

	public static ArrayList<Event> listEventsByUsername(String username) {
		ArrayList<Event> eventList = new ArrayList<>();
		String query = "SELECT e.*, c.TIPO_DE_CONFERENCIA, t.NUMERO_TALLER, p.TIPO_DE_PROYECTO, p.DESCRIPCION_PROYECTO, m.NUMERO_CONFERENCIA "
				+ "FROM evento e LEFT JOIN conferencia_magistral c ON e.ID = c.ID LEFT JOIN talleres_practicos t ON e.ID = t.ID "
				+ "LEFT JOIN presentacion_de_proyecto p ON e.ID = p.ID LEFT JOIN mesa_redonda m ON e.ID = m.ID "
				+ "INNER JOIN PARTICIPANTES_EN_EVENTOS pe ON e.ID = pe.EVENTO_ID WHERE pe.NOMBRE_INVITADO = ?";

		try (Connection connection = DatabaseConnector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, username);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					eventList.add(resultSetToEvent(resultSet));
				}
			}
		} catch (SQLException exception) {
			System.err.println("Error al listar eventos del usuario " + username + ": " + exception.getMessage());
		}
		return eventList;
	}

	public static boolean isUserRegistered(int eventId, String username) {
		String query = "SELECT * FROM PARTICIPANTES_EN_EVENTOS WHERE EVENTO_ID = ? AND NOMBRE_INVITADO = ?";
		try (Connection connection = DatabaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, eventId);
			preparedStatement.setString(2, username);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.next();
			}
		} catch (SQLException e) {
			Logger.writeLog("Error al verificar inscripción: " + e.getMessage());
		}
		return false;
	}

	public static boolean registerUserInEvent(int eventId, String username) {
		String query = "INSERT INTO PARTICIPANTES_EN_EVENTOS (EVENTO_ID, NOMBRE_INVITADO) VALUES (?, ?)";
		try (Connection connection = DatabaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, eventId);
			preparedStatement.setString(2, username);
			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			Logger.writeLog("Error al registrar inscripción: " + e.getMessage());
		}
		return false;
	}

	public static boolean unregisterUserFromEvent(int eventId, String username) {
		String query = "DELETE FROM PARTICIPANTES_EN_EVENTOS WHERE EVENTO_ID = ? AND NOMBRE_INVITADO = ?";
		try (Connection connection = DatabaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, eventId);
			preparedStatement.setString(2, username);
			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			Logger.writeLog("Error al cancelar inscripción: " + e.getMessage());
		}
		return false;
	}

	private static Event resultSetToEvent(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("ID");
		String title = resultSet.getString("TITULO");
		String location = resultSet.getString("UBICACION");
		String description = resultSet.getString("DESCRIPCION");
		Date startDate = resultSet.getDate("FECHA_INICIO");
		Date endDate = resultSet.getDate("FECHA_FIN");
		Time startTime = resultSet.getTime("HORARIO_INICIO");
		Time endTime = resultSet.getTime("HORARIO_FIN");
		int encounterCode = resultSet.getInt("ENCUENTRO_CODIGO");

		if (resultSet.getString("TIPO_DE_CONFERENCIA") != null) {
			return new KeynoteSpeech(id, title, location, description, startDate, endDate, startTime, endTime, encounterCode,
					resultSet.getString("TIPO_DE_CONFERENCIA"));
		} else if (resultSet.getObject("NUMERO_TALLER") != null) {
			return new PracticalWorkshop(id, title, location, description, startDate, endDate, startTime, endTime, encounterCode,
					resultSet.getInt("NUMERO_TALLER"));
		} else if (resultSet.getString("TIPO_DE_PROYECTO") != null) {
			return new ProjectPresentation(id, title, location, description, startDate, endDate, startTime, endTime, encounterCode,
					resultSet.getString("TIPO_DE_PROYECTO"), resultSet.getString("DESCRIPCION_PROYECTO"));
		} else if (resultSet.getObject("NUMERO_CONFERENCIA") != null) {
			return new RoundTable(id, title, location, description, startDate, endDate, startTime, endTime, encounterCode,
					resultSet.getInt("NUMERO_CONFERENCIA"));
		} else {
			return new Event(id, title, location, description, startDate, endDate, startTime, endTime, encounterCode);
		}
	}
}