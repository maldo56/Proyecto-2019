package bean.business;

import java.util.List;

import javax.ejb.Local;

import obj.dto.DtoClient;
import obj.dto.DtoMovimiento;
import obj.dto.DtoUsuario;
import obj.dto.DtoAdmin;



@Local
public interface UserCtrlBeanLocal {

	DtoUsuario login(String username, String password);
	
	Boolean ABMClient(char operation, DtoClient client);
	Boolean ABMAdmin(char operation, DtoAdmin admin);//String username, String password, String email, Boolean isSuperuser);
	Boolean createMovimiento(DtoMovimiento movimiento);
	
	
	List<DtoMovimiento> obtenerMovimientos(String cliente);
}
