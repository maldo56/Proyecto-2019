package dto;

public class Mensaje {

	private String estado;
	private String id;
	private String alquiler;
	private float latitude;
	private float longitude;
	
	
	public Mensaje() { }
	
	public Mensaje(String estado, String id, String alquiler, float latitude, float longitude) {
		super();
		this.estado = estado;
		this.id = id;
		this.alquiler = alquiler;
		this.latitude = latitude;
		this.longitude = longitude;
	}


	
	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getAlquiler() {
		return alquiler;
	}


	public void setAlquiler(String alquiler) {
		this.alquiler = alquiler;
	}


	public float getLatitude() {
		return latitude;
	}


	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}


	public float getLongitude() {
		return longitude;
	}


	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
}
