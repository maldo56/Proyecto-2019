package obj.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class usuario {

	@Id
	protected String username;
	protected String password;
	protected String email;
	
	
	public usuario() {}
	
	public usuario(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
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
	
}
