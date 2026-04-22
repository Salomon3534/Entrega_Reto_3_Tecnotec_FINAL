package util;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import model.*;

public class TotalManagerEuskalEncounter {

	private ManagerEncounters managerEncounters = new ManagerEncounters();
	private ManagerEvents managerEvents = new ManagerEvents();
	private ManagerGuests managerGuests = new ManagerGuests();
	private ManagerUsers managerUsers = new ManagerUsers();
	private Logger logger = new Logger();

	public TotalManagerEuskalEncounter() throws SQLException, IOException {
		logger.writeLog("SISTEMA - Gestores instanciados y listos.");
	}

	public String showLogFile() throws IOException {
		return logger.readLog();
	}

	// ENCUENTROS

	public String createEncounter(String location, Date dateStart, Date dateEnd) throws IOException {
		try {
			String result = managerEncounters.createEncounter(location, dateStart, dateEnd);
			logger.writeLog("CREAR ENCUENTRO - Ubicación: " + location + " | " + result);
			return result;
		} catch (SQLException e) {
			logger.writeLog("ERROR SQL (createEncounter): " + e.getMessage());
			return "Error al crear encuentro en la base de datos.";
		}
	}

	public String listEncounters() {
		return managerEncounters.listEncounters();
	}

	public String getEncounterById(int code) throws IOException {
		String result = managerEncounters.getEncounterById(code);
		logger.writeLog("BUSCAR ENCUENTRO - Código: " + code);
		return result;
	}

	public String updateEncounter(Encounter encounter) throws IOException {
		try {
			String result = managerEncounters.updateEncounter(encounter);
			logger.writeLog("ACTUALIZAR ENCUENTRO - Código: " + encounter.getCode() + " | " + result);
			return result;
		} catch (SQLException ex) {
			logger.writeLog("ERROR SQL (updateEncounter): " + ex.getMessage());
			return "Error al actualizar el encuentro.";
		}
	}

	public String deleteEncounter(int code) throws IOException {
		try {
			String result = managerEncounters.deleteEncounter(code);
			logger.writeLog("ELIMINAR ENCUENTRO - Código: " + code + " | " + result);
			return result;
		} catch (SQLException e) {
			logger.writeLog("ERROR SQL (deleteEncounter): " + e.getMessage());
			return "Error al eliminar el encuentro.";
		}
	}

	/**
	 * Obtiene el contador global de encuentros.
	 */
	public int getGlobalCounterEncounter() {
		return managerEncounters.getGlobalCounter();
	}

	// EVENTOS

	public String createEvent(Event event) throws IOException {
		try {
			String result = managerEvents.createEvent(event);
			logger.writeLog("CREAR EVENTO - Título: " + event.getTitle() + " | " + result);
			return result;
		} catch (SQLException ex) {
			logger.writeLog("ERROR SQL (createEvent): " + ex.getMessage());
			return "Error al crear el evento.";
		}
	}

	public String listEvents() {
		return managerEvents.listEvents();
	}

	public String getEventById(int id) throws IOException {
		String result = managerEvents.getEventById(id);
		logger.writeLog("BUSCAR EVENTO - ID: " + id);
		return result;
	}

	public String updateEvent(Event event) throws IOException {
		try {
			String result = managerEvents.updateEvent(event);
			logger.writeLog("ACTUALIZAR EVENTO - ID: " + event.getId() + " | " + result);
			return result;
		} catch (SQLException ex) {
			logger.writeLog("ERROR SQL (updateEvent): " + ex.getMessage());
			return "Error al actualizar el evento.";
		}
	}

	public String deleteEvent(int id) throws IOException {
		try {
			String result = managerEvents.deleteEvent(id);
			logger.writeLog("ELIMINAR EVENTO - ID: " + id + " | " + result);
			return result;
		} catch (SQLException e) {
			logger.writeLog("ERROR SQL (deleteEvent): " + e.getMessage());
			return "Error al eliminar el evento.";
		}
	}

	/**
	 * Obtiene el contador global de eventos.
	 */
	public int getGlobalCounterEvent() {
		return managerEvents.getGlobalCounter();
	}

	// INVITADOS

	public String createGuest(String username, String name, String surnames, String phoneNumber, String career,
			String email, String password) throws IOException {
		try {
			String result = managerGuests.createGuest(username, name, surnames, phoneNumber, career, email, password);
			logger.writeLog("CREAR INVITADO - Usuario: " + username + " | " + result);
			return result;
		} catch (SQLException e) {
			logger.writeLog("ERROR SQL (createGuest): " + e.getMessage());
			return "Error al registrar invitado.";
		}
	}

	public String listGuests() {
		return managerGuests.listGuests();
	}

	public String getGuestByUsername(String username) throws IOException {
		String result = managerGuests.getGuestByUsername(username);
		logger.writeLog("BUSCAR INVITADO - Usuario: " + username);
		return result;
	}

	public String updateGuest(Guest guest) throws IOException {
		try {
			String result = managerGuests.updateGuest(guest);
			logger.writeLog("ACTUALIZAR INVITADO - Usuario: " + guest.getUsername() + " | " + result);
			return result;
		} catch (SQLException e) {
			logger.writeLog("ERROR SQL (updateGuest): " + e.getMessage());
			return "Error al actualizar invitado.";
		}
	}

	public String deleteGuest(String username) throws IOException {
		try {
			String result = managerGuests.deleteGuest(username);
			logger.writeLog("ELIMINAR INVITADO - Usuario: " + username + " | " + result);
			return result;
		} catch (SQLException e) {
			logger.writeLog("ERROR SQL (deleteGuest): " + e.getMessage());
			return "Error al eliminar invitado.";
		}
	}

	// USUARIOS

	public String createUser(String dni, String name, String surnames, String email) throws IOException {
		try {
			String result = managerUsers.createUser(dni, name, surnames, email);
			logger.writeLog("CREAR USUARIO - DNI: " + dni + " | " + result);
			return result;
		} catch (SQLException e) {
			logger.writeLog("ERROR SQL (createUser): " + e.getMessage());
			return "Error al registrar usuario.";
		}
	}

	public String listUsers() {
		return managerUsers.listUsers();
	}

	public String getUserByDni(String dni) throws IOException {
		String result = managerUsers.getUserByDni(dni);
		logger.writeLog("BUSCAR USUARIO - DNI: " + dni);
		return result;
	}

	public String updateUser(User user) throws IOException {
		try {
			String result = managerUsers.updateUser(user);
			logger.writeLog("ACTUALIZAR USUARIO - DNI: " + user.getDni() + " | " + result);
			return result;
		} catch (SQLException e) {
			logger.writeLog("ERROR SQL (updateUser): " + e.getMessage());
			return "Error al actualizar usuario.";
		}
	}

	public String deleteUser(String dni) throws IOException {
		try {
			String result = managerUsers.deleteUser(dni);
			logger.writeLog("ELIMINAR USUARIO - DNI: " + dni + " | " + result);
			return result;
		} catch (SQLException e) {
			logger.writeLog("ERROR SQL (deleteUser): " + e.getMessage());
			return "Error al eliminar usuario.";
		}
	}
}