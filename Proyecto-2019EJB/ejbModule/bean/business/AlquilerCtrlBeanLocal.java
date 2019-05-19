package bean.business;

import java.util.List;

import javax.ejb.Local;

import obj.dto.DtoAlquiler;

@Local
public interface AlquilerCtrlBeanLocal {

	DtoAlquiler alquiler(char operation, DtoAlquiler alquiler) throws Exception;
	
	DtoAlquiler obtenerAlquiler(String guid) throws Exception;
	List<DtoAlquiler> obtenerAlquileres(String username) throws Exception;
	DtoAlquiler obtenerAlquilerActivo(String username) throws Exception;
}
