package servicios.business;

import javax.ejb.Local;

@Local
public interface ServicioCtrlBeanLocal {

	void addPoint(String scooterGuid, String alquilerGuid, float x, float y) throws Exception;
	String estaAlquilado(String guid) throws Exception;
}
