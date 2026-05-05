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
		ArrayList<Event> lista = new ArrayList<>();
		String query = "SELECT e.*, c.TIPO_DE_CONFERENCIA, t.NUMERO_TALLER, p.TIPO_DE_PROYECTO, p.DESCRIPCION_PROYECTO, m.NUMERO_CONFERENCIA FROM evento e "
				+ "LEFT JOIN conferencia_magistral c ON e.ID = c.ID LEFT JOIN talleres_practicos t ON e.ID = t.ID "
				+ "LEFT JOIN presentacion_de_proyecto p ON e.ID = p.ID LEFT JOIN mesa_redonda m ON e.ID = m.ID";

		try (Connection con = DatabaseConnector.getConexion();
				PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				lista.add(resultSetToEvent(rs));
			}
		} catch (SQLException ex) {
			System.err.println("Error al listar eventos: " + ex.getMessage());
		}
		return lista;
	}

	private static Event resultSetToEvent(ResultSet rs) throws SQLException {
		int id = rs.getInt("ID");
		String title = rs.getString("TITULO");
		String loc = rs.getString("UBICACION");
		String desc = rs.getString("DESCRIPCION");
		Date dStart = rs.getDate("FECHA_INICIO");
		Date dEnd = rs.getDate("FECHA_FIN");
		Time hStart = rs.getTime("HORARIO_INICIO");
		Time hEnd = rs.getTime("HORARIO_FIN");
		int eCode = rs.getInt("ENCUENTRO_CODIGO");

		if (rs.getString("TIPO_DE_CONFERENCIA") != null) {
			return new KeynoteSpeech(id, title, loc, desc, dStart, dEnd, hStart, hEnd, eCode,
					rs.getString("TIPO_DE_CONFERENCIA"));
		} else if (rs.getObject("NUMERO_TALLER") != null) {
			return new PracticalWorkshop(id, title, loc, desc, dStart, dEnd, hStart, hEnd, eCode,
					rs.getInt("NUMERO_TALLER"));
		} else if (rs.getString("TIPO_DE_PROYECTO") != null) {
			return new ProjectPresentation(id, title, loc, desc, dStart, dEnd, hStart, hEnd, eCode,
					rs.getString("TIPO_DE_PROYECTO"), rs.getString("DESCRIPCION_PROYECTO"));
		} else if (rs.getObject("NUMERO_CONFERENCIA") != null) {
			return new RoundTable(id, title, loc, desc, dStart, dEnd, hStart, hEnd, eCode,
					rs.getInt("NUMERO_CONFERENCIA"));
		} else {
			return new Event(id, title, loc, desc, dStart, dEnd, hStart, hEnd, eCode);
		}
	}
}