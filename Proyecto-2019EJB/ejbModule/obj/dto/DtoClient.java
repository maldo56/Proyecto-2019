package obj.dto;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;


public class DtoClient extends DtoUsuario {

	private String name;
	private String surname;
	private String urlphoto;
	private String cellphone;
	private float saldo;
	
	public DtoClient() {}

	public DtoClient(String username, String password, String email, String name, String surname, String urlphoto,
			String cellphone, float saldo) {
		super(username, password, email);
		this.name = name;
		this.surname = surname;
		this.urlphoto = urlphoto;
		this.cellphone = cellphone;
		this.saldo = saldo;
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
