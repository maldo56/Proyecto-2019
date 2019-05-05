package obj.dto;

import com.mongodb.BasicDBObject;

public class DtoLocation extends BasicDBObject {
	private static final long serialVersionUID = -3396134863899363699L;
	
	
	private String scooterGuid;
	private String alquilerGuid;
	private float lat;
	private float lng;
	
	
	public DtoLocation() {
		
	}
	
	public DtoLocation(String scooterGuid, String alquilerGuid, float lat, float lng) {
		super();
		this.scooterGuid = scooterGuid;
		this.alquilerGuid = alquilerGuid;
		this.lat = lat;
		this.lng = lng;
	}



	public String getScooterGuid() {
		return scooterGuid;
	}
	public void setScooterGuid(String scooterGuid) {
		this.scooterGuid = scooterGuid;
	}
	public String getAlquilerGuid() {
		return alquilerGuid;
	}
	public void setAlquilerGuid(String alquilerGuid) {
		this.alquilerGuid = alquilerGuid;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}
	
}
