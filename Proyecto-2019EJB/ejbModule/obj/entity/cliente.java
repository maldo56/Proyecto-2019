package obj.entity;

import java.io.Serializable;

import javax.persistence.Entity;


@Entity
public class cliente extends usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6936952624555065730L;
	
	private String name;
	private String surname;
	private String urlphoto;
	private String cellphone;
	private float saldo;
	
	
	public cliente() {}
	
	public cliente(String username, String password, String email, String name, String surname, String urlphoto, String cellphone, float saldo) {
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

//	public Point getPunto() {
//		return punto;
//	}
//
//	public void setPunto(Point punto) {
//		this.punto = punto;
//	}
	
}
