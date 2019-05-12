package bean.scooterclient.database;

import java.util.List;

import javax.ejb.Local;

import obj.dto.DtoLocation;

@Local
public interface MongoBeanLocal {

	String add(String user) throws Exception;
	Boolean mongo() throws Exception;
	
	void servicioAddPunto(String guid, String alquilerGuid, float x, float y) throws Exception;
	
	List<DtoLocation> obtenerPuntos(String alquilerGuid) throws Exception;
}
