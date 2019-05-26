package obj.dto;

import java.sql.Timestamp;

public class DtoHistorialTarifa {

	private String parametro;
    private String admin;
    private String oldValue;
    private String newValue;
    private Timestamp timestamp;
    
    
    public DtoHistorialTarifa() {}
    
	public DtoHistorialTarifa(String parametro, String admin, String oldValue, String newValue, Timestamp timestamp) {
		super();
		this.parametro = parametro;
		this.admin = admin;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.timestamp = timestamp;
	}

	
	
	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
    
}
