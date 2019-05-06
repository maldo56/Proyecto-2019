package bean.business;

import java.util.List;

import javax.ejb.Local;

import obj.dto.DtoClient;
import obj.dto.DtoMovimiento;
import obj.dto.DtoParm;
import obj.dto.DtoUsuario;
import obj.dto.DtoAdmin;



@Local
public interface UserCtrlBeanLocal {

	DtoUsuario login(String username, String password);
	
	Boolean ABMClient(char operation, DtoClient client);
	Boolean ABMAdmin(char operation, DtoAdmin admin);
	Boolean createMovimiento(DtoMovimiento movimiento);
	Boolean ABMParametro(char operation, DtoParm parm);
	Boolean recargarSaldo(String username, String guidpaypal, float monto, String moneda);
	
	
	List<DtoMovimiento> obtenerMovimientos(String cliente);
	DtoClient obtenerCliente(String username);
	DtoParm obtenerParametro(String key);
	Float obtenerTiempoDisponible(String username);
	
}
