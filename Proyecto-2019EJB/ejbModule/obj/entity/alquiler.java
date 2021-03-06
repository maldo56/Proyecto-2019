package obj.entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
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
	private float price;
	private float tarifa;
	
	@ManyToOne
	private scooter scooter;
	
	@ManyToOne
	private cliente cliente;
	
	@OneToOne
	private movimiento movimiento;
	
	
	public alquiler() {
		UUID uuid = UUID.randomUUID();
		this.guid = uuid.toString();
		
		Time duration = new Time(0);
		this.duration = duration;
	}

	public alquiler(Timestamp timestamp, Float price, Float tarifa) {
		super();
		UUID uuid = UUID.randomUUID();
		this.guid = uuid.toString();
		this.timestamp = timestamp;
		this.price = price;
		this.tarifa = tarifa;
		
		Time duration = new Time(0);
		this.duration = duration;
	}

	

	public Timestamp getTimestamp() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp.getTime());
		cal.add(Calendar.HOUR, -3);
		Timestamp t = new Timestamp(cal.getTime().getTime());
		return t;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
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

	public float getTarifa() {
		return tarifa;
	}

	public void setTarifa(float tarifa) {
		this.tarifa = tarifa;
	}

}
