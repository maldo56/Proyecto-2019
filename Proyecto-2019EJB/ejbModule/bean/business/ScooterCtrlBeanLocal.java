package bean.business;

import java.util.List;

import javax.ejb.Local;

import obj.dto.DtoGeometria;
import obj.dto.DtoScooter;

@Local
public interface ScooterCtrlBeanLocal {

	Boolean AB(char operation, String guid) throws Exception;
	Boolean M(String campo, String guid, String value) throws Exception;
	Boolean abArea(char operation, DtoGeometria geometry) throws Exception;
	
	List<DtoScooter> scootersDisponibles() throws Exception;
	String estaAlquilado(String guid) throws Exception;
	DtoGeometria obtenerArea() throws Exception;
	
}
