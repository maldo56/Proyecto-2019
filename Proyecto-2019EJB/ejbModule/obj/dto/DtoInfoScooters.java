package obj.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DtoInfoScooters {

	@Id
	private int scootersdisponibles;
	private int scootersenUso;
	private int scootersrotos;
	
	
	public DtoInfoScooters() {}
	
	public DtoInfoScooters(int scootersDisponibles, int scootersEnUso, int scootersrotos) {
		scootersdisponibles = scootersDisponibles;
		scootersenUso = scootersEnUso;
		scootersrotos = scootersrotos;
	}

	
	
	public int getScootersDisponibles() {
		return scootersdisponibles;
	}

	public void setScootersDisponibles(int scootersDisponibles) {
		scootersdisponibles = scootersDisponibles;
	}

	public int getScootersEnUso() {
		return scootersenUso;
	}

	public void setScootersEnUso(int scootersEnUso) {
		scootersenUso = scootersEnUso;
	}

	public int getScootersrotos() {
		return scootersrotos;
	}

	public void setScootersrotos(int scootersrotos) {
		this.scootersrotos = scootersrotos;
	}
	
}
