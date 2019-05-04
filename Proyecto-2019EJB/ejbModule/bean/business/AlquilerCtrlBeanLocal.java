package bean.business;

import javax.ejb.Local;

import obj.dto.DtoAlquiler;

@Local
public interface AlquilerCtrlBeanLocal {

	Boolean alquiler(char operation, DtoAlquiler alquiler);
	
}
