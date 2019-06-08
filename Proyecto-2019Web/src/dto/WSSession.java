package dto;

import javax.websocket.Session;

public class WSSession {

	private String username;
	private Session session;
	
	
	public WSSession() {}
	
	public WSSession(String username, Session session) {
		super();
		this.username = username;
		this.session = session;
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

	
}
