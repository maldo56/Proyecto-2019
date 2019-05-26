package dto;

import javax.websocket.Session;

public class WSSession {

	private String username;
	private String rol;
	private Session session;
	
	
	public WSSession() {}
	
	public WSSession(String username, String rol, Session session) {
		super();
		this.username = username;
		this.session = session;
		this.rol = rol;
	}
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
}
