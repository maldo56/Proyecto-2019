package obj.dto;

public class DtoInfoScooters {

	private int scootersdisponibles;
	private int scootersenUso;
	private int scootersrotos;
	
	
	public DtoInfoScooters() {}
	
	public DtoInfoScooters(int scootersDisponibles, int scootersEnUso, int scootersDa�ados) {
		scootersdisponibles = scootersDisponibles;
		scootersenUso = scootersEnUso;
		scootersrotos = scootersDa�ados;
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

	public int getScootersDa�ados() {
		return scootersrotos;
	}

	public void setScootersDa�ados(int scootersDa�ados) {
		scootersrotos = scootersDa�ados;
	}
	
}
