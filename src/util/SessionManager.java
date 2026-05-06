package util;

import model.Guest;

public class SessionManager {
	private static SessionManager instance;
	private Guest activeUser;

	private SessionManager() {
	}

	public static SessionManager getInstance() {
		if (instance == null) {
			instance = new SessionManager();
		}
		return instance;
	}

	public void setActiveUser(Guest user) {
		this.activeUser = user;
	}

	public Guest getActiveUser() {
		return activeUser;
	}

	public String getActiveUsername() {
		return activeUser != null ? activeUser.getUsername() : null;
	}

	public void logout() {
		this.activeUser = null;
	}

	public boolean isLoggedIn() {
		return activeUser != null;
	}
}
