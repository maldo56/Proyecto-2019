package obj.entity;

import javax.persistence.Entity;


@Entity
public class cliente extends usuario {

	private String name;
	private String surname;
	private String urlphoto;
	private String cellphone;
	private Double saldo;
	
//	@Column(columnDefinition="Point")
//	private Point punto;
	
//	private Spatial pu;
	
	public cliente() {}
	
	public cliente(String username, String password, String email, String name, String surname, String urlphoto, String cellphone, Double saldo) {
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

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
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
