package servicios.business;

import javax.ejb.Local;

@Local
public interface ServicioCtrlBeanLocal {

	void addPoint(String scooterGuid, String alquilerGuid, float x, float y) throws Exception;
	void reloadLocation(String guidScooter) throws Exception;
	void updateBateryLevel(String scooterGuid, double value) throws Exception;
	String estaAlquilado(String guid) throws Exception;
}
