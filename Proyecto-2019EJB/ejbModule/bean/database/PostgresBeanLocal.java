package bean.database;

import java.util.List;

import javax.ejb.Local;

import obj.dto.DtoAdmin;
import obj.dto.DtoClient;
import obj.dto.DtoMovimiento;
import obj.dto.DtoParm;
import obj.dto.DtoScooter;
import obj.dto.DtoUsuario;



@Local
public interface PostgresBeanLocal {

	DtoUsuario login(String username, String password);
	
	Boolean ABScooter(char operation, String guid);
	Boolean MScooter(String campo, String guid, String value);
	Boolean ABMClient(char operation, DtoClient client);
	Boolean ABMAdmin(char operation, DtoAdmin client);
	Boolean ABMParametro(char operation, DtoParm parm);
	Boolean createMovimiento(DtoMovimiento movimiento);
	
	
	List<DtoMovimiento> obtenerMovimientos(String cliente);
	
}
