package servicios.business;

import javax.ejb.Local;

@Local
public interface ServicioCtrlBeanLocal {

	void addPoint(String scooterGuid, float x, float y);
}
