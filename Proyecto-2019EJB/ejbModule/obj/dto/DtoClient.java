package obj.dto;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;


public class DtoClient extends DtoUsuario {

	private String username;
	private String password;
	private String email;
	private String name;
	private String surname;
	private String urlphoto;
	private String cellphone;
	private float saldo;
	
	public DtoClient() {}

	public DtoClient(String username, String password, String email, String name, String surname, String urlphoto,
			String cellphone, float saldo) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.urlphoto = urlphoto;
		this.cellphone = cellphone;
		this.saldo = saldo;
	}

	public DtoClient(String parm) {
		
		System.out.println(parm);
		
		try {
			JsonReader jsonReader = Json.createReader(new StringReader(parm));
			JsonObject object = jsonReader.readObject();
			
			this.username = object.getString("username");
			this.password = object.getString("password");
			this.email = object.getString("email");
			this.name = object.getString("name");
			this.surname = object.getString("surname");
			this.urlphoto = object.getString("urlphoto");
			this.cellphone = object.getString("cellphone");
			
			JsonNumber aux = object.getJsonNumber("saldo");
			this.saldo = (float) aux.doubleValue();
			
			jsonReader.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUrlphoto() {
		return urlphoto;
	}

	public void setUrlphoto(String urlphoto) {
		this.urlphoto = urlphoto;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
}
