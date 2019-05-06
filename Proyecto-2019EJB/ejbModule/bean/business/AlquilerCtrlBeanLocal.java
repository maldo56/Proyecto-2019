package bean.business;

import java.util.List;

import javax.ejb.Local;

import obj.dto.DtoAlquiler;

@Local
public interface AlquilerCtrlBeanLocal {

	DtoAlquiler alquiler(char operation, DtoAlquiler alquiler);
	
	List<DtoAlquiler> obtenerAlquileres(String username);
}
