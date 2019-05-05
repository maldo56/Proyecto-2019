package obj.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class movimiento {

	@Id
	private String guid;
	private Timestamp timestamp;
	private float mount;
	private String paypalguid;
	private String moneda;
	
	@ManyToOne
	private cliente cliente;
	
	public movimiento() {
		UUID uuid = UUID.randomUUID();
		this.guid = uuid.toString();
	}

	public movimiento(Timestamp timestamp, float mount, String paypalguid, String moneda) {
		super();
		UUID uuid = UUID.randomUUID();
		this.guid = uuid.toString();
		this.timestamp = timestamp;
		this.mount = mount;
		this.paypalguid = paypalguid;
		this.moneda = moneda;
	}

	

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public float getMount() {
		return mount;
	}

	public void setMount(float mount) {
		this.mount = mount;
	}

	public String getPaypalguid() {
		return paypalguid;
	}

	public void setPaypalguid(String paypalguid) {
		this.paypalguid = paypalguid;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getGuid() {
		return guid;
	}

	public cliente getCliente() {
		return cliente;
	}

	public void setCliente(cliente cliente) {
		this.cliente = cliente;
	}
	
}
