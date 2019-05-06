package obj.dto;

import java.util.List;

public class DtoGeometria {

	private String type;
	private List<DtoPunto> puntos;
	
	
	
	public DtoGeometria() {
	}

	public DtoGeometria(String type, List<DtoPunto> puntos) {
		super();
		this.type = type;
		this.puntos = puntos;
	}

	

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public List<DtoPunto> getPuntos() {
		return puntos;
	}


	public void setPuntos(List<DtoPunto> puntos) {
		this.puntos = puntos;
	}
	
}
