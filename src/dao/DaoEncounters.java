package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Encounter;

public class DaoEncounters {

	public static String createEncounter(String location, Date dateStart, Date dateEnd) {
		String sql = "INSERT INTO encuentro (FECHA_INICIO, FECHA_FIN, UBICACION) VALUES (?, ?, ?)";
		try (Connection conn = DatabaseConnector.getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setDate(1, dateStart);
			pstmt.setDate(2, dateEnd);
			pstmt.setString(3, location);
			pstmt.executeUpdate();

			Logger.writeLog("Encuentro creado en " + location);
			return "Encuentro creado correctamente.";
		} catch (SQLException e) {
			Logger.writeLog("ERROR al crear encuentro: " + e.getMessage());
			return "Error: " + e.getMessage();
		}
	}

	public static String getEncounterById(int code) {
		String sql = "SELECT CODIGO, FECHA_INICIO, FECHA_FIN, UBICACION FROM encuentro WHERE CODIGO = ?";
		try (Connection conn = DatabaseConnector.getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, code);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					Encounter e = resultSetToEncounter(rs);
					return e.toString();
				}
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al buscar encuentro " + code + ": " + e.getMessage());
		}
		return "No se encontró el encuentro con código: " + code;
	}

	public static String listEncounters() {
		StringBuilder sb = new StringBuilder();
		String sql = "SELECT CODIGO, FECHA_INICIO, FECHA_FIN, UBICACION FROM encuentro";
		try (Connection conn = DatabaseConnector.getConexion();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				sb.append(resultSetToEncounter(rs).toString()).append("\n");
			}
		} catch (SQLException e) {
			Logger.writeLog("ERROR al listar encuentros: " + e.getMessage());
			return "Error al listar.";
		}
		return sb.length() == 0 ? "No hay encuentros." : sb.toString();
	}

	public static String updateEncounter(Encounter e) {
		String sql = "UPDATE encuentro SET FECHA_INICIO = ?, FECHA_FIN = ?, UBICACION = ? WHERE CODIGO = ?";
		try (Connection conn = DatabaseConnector.getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setDate(1, e.getDateStart());
			pstmt.setDate(2, e.getDateEnd());
			pstmt.setString(3, e.getLocation());
			pstmt.setInt(4, e.getCode());

			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				Logger.writeLog("Encuentro actualizado: ID " + e.getCode());
				return "Encuentro actualizado.";
			}
		} catch (SQLException ex) {
			Logger.writeLog("ERROR al actualizar encuentro: " + ex.getMessage());
		}
		return "No se pudo actualizar.";
	}

	public static String deleteEncounter(int code) {
		String sql = "DELETE FROM encuentro WHERE CODIGO = ?";
		try (Connection conn = DatabaseConnector.getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, code);
			int rows = pstmt.executeUpdate();
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
		try (Connection conn = DatabaseConnector.getConexion();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next())
				return rs.getInt("AUTO_INCREMENT");
		} catch (SQLException e) {
			Logger.writeLog("ERROR al obtener contador: " + e.getMessage());
		}
		return -1;
	}

	private static Encounter resultSetToEncounter(ResultSet rs) throws SQLException {
		return new Encounter(rs.getInt("CODIGO"), rs.getString("UBICACION"), rs.getDate("FECHA_INICIO"),
				rs.getDate("FECHA_FIN"));
	}
}