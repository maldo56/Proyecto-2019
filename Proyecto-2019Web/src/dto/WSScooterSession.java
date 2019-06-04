package dto;

import javax.websocket.Session;

public class WSScooterSession {

	private String guidScooter;
	private double bateryLevel;
	private Session session;
	
	
	public WSScooterSession() {}
	
	public WSScooterSession(String guidScooter, double bateryLevel, Session session) {
		super();
		this.guidScooter = guidScooter;
		this.session = session;
	}

	
	public String getGuidScooter() {
		return guidScooter;
	}

	public void setGuidScooter(String guidScooter) {
		this.guidScooter = guidScooter;
	}

	public double getBateryLevel() {
		return bateryLevel;
	}

	public void setBateryLevel(double bateryLevel) {
		this.bateryLevel = bateryLevel;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
}
