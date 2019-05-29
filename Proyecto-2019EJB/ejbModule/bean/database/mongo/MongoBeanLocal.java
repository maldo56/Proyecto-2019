package bean.database.mongo;

import java.util.List;

import javax.ejb.Local;

import obj.dto.DtoHistorialTarifa;
import obj.dto.DtoLocation;
import obj.dto.DtoParm;

@Local
public interface MongoBeanLocal {

	String add(String user) throws Exception;
	Boolean mongo() throws Exception;
	
	void servicioAddPunto(String guid, String alquilerGuid, float x, float y) throws Exception;
	Boolean addRegistroParametros(DtoParm parm, String oldValue, String admin) throws Exception;
	
	List<DtoLocation> obtenerPuntos(String alquilerGuid) throws Exception;
	List<DtoLocation> ultimosNPuntos(int cant, String scooterGuid) throws Exception;
	List<DtoHistorialTarifa> historialParametro(String parmCode) throws Exception;
}
