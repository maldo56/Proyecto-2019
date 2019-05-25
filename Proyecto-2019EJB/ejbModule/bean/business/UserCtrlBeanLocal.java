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

	DtoUsuario login(String username, String password) throws Exception;
	
	Boolean ABMClient(char operation, DtoClient client) throws Exception;
	Boolean ABMAdmin(char operation, DtoAdmin admin) throws Exception;
	Boolean createMovimiento(DtoMovimiento movimiento) throws Exception;
	Boolean ABMParametro(char operation, DtoParm parm) throws Exception;
	Boolean recargarSaldo(String username, String guidpaypal, float monto, String moneda) throws Exception;
	
	List<DtoMovimiento> obtenerMovimientos(String cliente) throws Exception;
	DtoClient obtenerCliente(String username) throws Exception;
	DtoAdmin obtenerAdmin(String username) throws Exception;
	DtoParm obtenerParametro(String key) throws Exception;
	List<DtoParm> obtenerParametros() throws Exception;
	Float obtenerTiempoDisponible(String username) throws Exception;
	
}
