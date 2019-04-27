package obj.entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class alquiler {

	@Id
	private String guid;
	private Timestamp timestamp;
	private Time duration;
	private Float price;
	
	@ManyToOne
	private scooter scooter;
	
	@ManyToOne
	private cliente cliente;
	
	@OneToOne
	private movimiento movimiento;
	
	public alquiler() {
		UUID uuid = UUID.randomUUID();
		this.guid = uuid.toString();
	}

	public alquiler(Timestamp timestamp, Time duration, Float price) {
		super();
		UUID uuid = UUID.randomUUID();
		this.guid = uuid.toString();
		this.timestamp = timestamp;
		this.duration = duration;
		this.price = price;
	}

	

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Time getDuration() {
		return duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getGuid() {
		return guid;
	}

	public scooter getScooter() {
		return scooter;
	}

	public void setScooter(scooter scooter) {
		this.scooter = scooter;
	}

	public cliente getCliente() {
		return cliente;
	}

	public void setCliente(cliente cliente) {
		this.cliente = cliente;
	}

	public movimiento getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(movimiento movimiento) {
		this.movimiento = movimiento;
	}
	
}
