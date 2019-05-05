package obj.dto;

import java.io.StringReader;
import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;


public class DtoAlquiler {

	private String guid;
	private Timestamp timestamp;
	private Time duration;
	private float price;
	private String guidscooter;
	private String cliente;
	
	
	public DtoAlquiler() {
		
	}

	public DtoAlquiler(String parm) {
		
		System.out.println(parm);
		
		try {
			JsonReader jsonReader = Json.createReader(new StringReader(parm));
			JsonObject object = jsonReader.readObject();
			
			this.guid = object.getString("guid");
			this.guidscooter = object.getString("guidscooter");
			this.cliente = object.getString("cliente");
			
			
			this.duration = java.sql.Time.valueOf(LocalTime.now());
			
			
			Date date= new Date();
			long time = date.getTime();
			this.timestamp = new Timestamp(time);
		    
		    
			JsonNumber aux = object.getJsonNumber("price");
			this.price = (float) aux.doubleValue();
			
			jsonReader.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public DtoAlquiler(String guid, Timestamp timestamp, Time duration, float price, String guidscooter,
			String cliente) {
		super();
		this.guid = guid;
		this.timestamp = timestamp;
		this.duration = duration;
		this.price = price;
		this.guidscooter = guidscooter;
		this.cliente = cliente;
	}


	
	public String getGuid() {
		return guid;
	}


	public void setGuid(String guid) {
		this.guid = guid;
	}


	public Timestamp getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	public Time getDuration() {
		return duration;
	}


	public void setDuration(Time duration) {
		this.duration = duration;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public String getGuidscooter() {
		return guidscooter;
	}


	public void setGuidscooter(String guidscooter) {
		this.guidscooter = guidscooter;
	}


	public String getCliente() {
		return cliente;
	}


	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
}
