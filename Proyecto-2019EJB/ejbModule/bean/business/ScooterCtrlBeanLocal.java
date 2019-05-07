package bean.business;

import java.util.List;

import javax.ejb.Local;

import obj.dto.DtoScooter;

@Local
public interface ScooterCtrlBeanLocal {

	Boolean AB(char operation, String guid);
	Boolean M(String campo, String guid, String value);
	
	List<DtoScooter> scootersDisponibles();
	String estaAlquilado(String guid);
	
}
