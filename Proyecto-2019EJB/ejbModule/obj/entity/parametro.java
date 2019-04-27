package obj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class parametro {

	@Id
	private String code;
	private String unit;
	
	@Column(nullable = false)
	private String value;
	
	
	public parametro() {}
	
	public parametro(String code, String unit, String value) {
		super();
		this.code = code;
		this.unit = unit;
		this.value = value;
	}

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
