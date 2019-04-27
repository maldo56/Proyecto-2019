package obj.entity;

import javax.persistence.Entity;

@Entity
public class administrador extends usuario {

	private Boolean isSuperuser;

	public administrador() {}
	
	public administrador(String username, String password, String email, Boolean isSuperuser) {
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
