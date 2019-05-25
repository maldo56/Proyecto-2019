package obj.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DtoRakingUsuarios {

	@Id
	private String username;
	private String email;
	private String name;
	private String surname;
	private String urlphoto;
	private String cellphone;
	private int cantalquileres;
	
	public DtoRakingUsuarios() {} 
	
	public DtoRakingUsuarios(String username, String email, String name, String surname, String urlphoto,
			String cellphone, int cantalquileres) {
		super();
		this.username = username;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.urlphoto = urlphoto;
		this.cellphone = cellphone;
		this.cantalquileres = cantalquileres;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public int getCantalquileres() {
		return cantalquileres;
	}

	public void setCantalquileres(int cantalquileres) {
		this.cantalquileres = cantalquileres;
	}
}
