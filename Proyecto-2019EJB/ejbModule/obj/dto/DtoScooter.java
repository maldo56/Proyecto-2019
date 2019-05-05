package obj.dto;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class DtoScooter {

	private String guid;
	private float bateryLevel;
	private Boolean isRented;
	private Boolean isAvailable;
	
	
	public DtoScooter() {}
	
	public DtoScooter(String parm) {
		
		System.out.println(parm);
		
		try {
			JsonReader jsonReader = Json.createReader(new StringReader(parm));
			JsonObject object = jsonReader.readObject();
			
			JsonNumber aux = object.getJsonNumber("bateryLevel");
			this.bateryLevel = (float) aux.doubleValue();
			
			this.isRented = object.getBoolean("isRented");
			this.isAvailable = object.getBoolean("isAvailable");
		
			jsonReader.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public DtoScooter(float bateryLevel, Boolean isRented, Boolean isAvailable) {
		super();
		this.bateryLevel = bateryLevel;
		this.isRented = isRented;
		this.isAvailable = isAvailable;
	}
	


	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public float getBateryLevel() {
		return bateryLevel;
	}

	public void setBateryLevel(float bateryLevel) {
		this.bateryLevel = bateryLevel;
	}

	public Boolean getIsRented() {
		return isRented;
	}

	public void setIsRented(Boolean isRented) {
		this.isRented = isRented;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
}
