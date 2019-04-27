package obj.dto;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class DtoAdmin extends DtoUsuario {

	private String username;
	private String password;
	private String email;
	private Boolean isSuperuser;
	
	
	public DtoAdmin() {}

	public DtoAdmin(String username, String password, String email, Boolean isSuperuser) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.isSuperuser = isSuperuser;
	}
	
	public DtoAdmin(String parm) {
		
		System.out.println(parm);
		
		try {
			JsonReader jsonReader = Json.createReader(new StringReader(parm));
			JsonObject object = jsonReader.readObject();
			
			this.username = object.getString("username");
			this.password = object.getString("password");
			this.email = object.getString("email");
			this.isSuperuser = object.getBoolean("isSuperuser");
			
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

	public Boolean getIsSuperuser() {
		return isSuperuser;
	}

	public void setIsSuperuser(Boolean isSuperuser) {
		this.isSuperuser = isSuperuser;
	}
	
}
