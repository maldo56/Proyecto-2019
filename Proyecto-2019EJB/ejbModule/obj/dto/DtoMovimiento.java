package obj.dto;

import java.io.StringReader;
import java.sql.Timestamp;

import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class DtoMovimiento {

	private Timestamp timestamp;
	private Double mount;
	private String paypalguid;
	private String moneda;
	private String username;
	
	public DtoMovimiento() {}
	
	public DtoMovimiento(Timestamp timestamp, Double mount, String paypalguid, String moneda, String username) {
		super();
		this.timestamp = timestamp;
		this.mount = mount;
		this.paypalguid = paypalguid;
		this.moneda = moneda;
		this.username = username;
	}

	public DtoMovimiento(String parm) {
		
		System.out.println(parm);
		
		try {
			JsonReader jsonReader = Json.createReader(new StringReader(parm));
			JsonObject object = jsonReader.readObject();
			
			this.username = object.getString("username");
			this.paypalguid = object.getString("paypalguid");
			this.moneda = object.getString("moneda");
			
			JsonNumber aux = object.getJsonNumber("mount");
			this.mount = aux.doubleValue();
			
			String date = object.getString("timestamp");
			this.timestamp = Timestamp.valueOf(date);
			
			
			jsonReader.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}


	
	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Double getMount() {
		return mount;
	}

	public void setMount(Double mount) {
		this.mount = mount;
	}

	public String getPaypalguid() {
		return paypalguid;
	}

	public void setPaypalguid(String paypalguid) {
		this.paypalguid = paypalguid;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
