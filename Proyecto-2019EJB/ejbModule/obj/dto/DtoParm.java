package obj.dto;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class DtoParm {

	private String code;
	private String unit;
	private String value;
	
	
	public DtoParm() {}

	public DtoParm(String code, String unit, String value) {
		super();
		this.code = code;
		this.unit = unit;
		this.value = value;
	}

	public DtoParm(String parm) {
		
		System.out.println(parm);
		
		try {
			JsonReader jsonReader = Json.createReader(new StringReader(parm));
			JsonObject object = jsonReader.readObject();
			
			this.code = object.getString("code");
			this.unit = object.getString("unit");
			this.value = object.getString("value");
		
			jsonReader.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
