package obj.dto;

public class DtoInfoScooters {

	private int scootersdisponibles;
	private int scootersenUso;
	private int scootersrotos;
	
	
	public DtoInfoScooters() {}
	
	public DtoInfoScooters(int scootersDisponibles, int scootersEnUso, int scootersDañados) {
		scootersdisponibles = scootersDisponibles;
		scootersenUso = scootersEnUso;
		scootersrotos = scootersDañados;
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

	public int getScootersDañados() {
		return scootersrotos;
	}

	public void setScootersDañados(int scootersDañados) {
		scootersrotos = scootersDañados;
	}
	
}
