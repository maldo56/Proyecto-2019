package bean.scooterclient.database;

import java.util.List;

import javax.ejb.Local;

import obj.dto.DtoLocation;

@Local
public interface MongoBeanLocal {

	String add(String user);
	Boolean mongo();
	
	void servicioAddPunto(String guid, String alquilerGuid, float x, float y);
	
	List<DtoLocation> obtenerPuntos(String alquilerGuid);
}
