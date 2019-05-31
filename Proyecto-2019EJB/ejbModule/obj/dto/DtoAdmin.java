package obj.dto;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class DtoAdmin extends DtoUsuario {

	private Boolean isSuperuser;
	
	public DtoAdmin() {}

	public DtoAdmin(String username, String password, String email, Boolean isSuperuser) {
		super(username, password, email);
		this.isSuperuser = isSuperuser;
	}
	

	public Boolean getIsSuperuser() {
		return isSuperuser;
	}

	public void setIsSuperuser(Boolean isSuperuser) {
		this.isSuperuser = isSuperuser;
	}
	
}
