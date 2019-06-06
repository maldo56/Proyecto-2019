package bean.business;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ws.rs.QueryParam;

import obj.dto.DtoClient;
import obj.dto.DtoMovimiento;
import obj.dto.DtoParm;
import obj.dto.DtoUsuario;
import obj.dto.DtoAdmin;



@Local
public interface UserCtrlBeanLocal {

	DtoUsuario login(String username, String password, String type) throws Exception;
	
	Boolean ABMClient(char operation, DtoClient client) throws Exception;
	Boolean ABMAdmin(char operation, DtoAdmin admin) throws Exception;
	Boolean createMovimiento(DtoMovimiento movimiento) throws Exception;
	Boolean ABMParametro(char operation, String admin,DtoParm parm) throws Exception;
	Boolean recargarSaldo(String username, String guidpaypal, float monto, String moneda) throws Exception;
	Boolean recargarSaldoAdmin(String admin, String password, String usernameCliente, float monto) throws Exception;
	
	List<DtoMovimiento> obtenerMovimientos(String cliente) throws Exception;
	DtoClient obtenerCliente(String username) throws Exception;
	DtoAdmin obtenerAdmin(String username) throws Exception;
	DtoParm obtenerParametro(String key) throws Exception;
	List<DtoParm> obtenerParametros() throws Exception;
	Float obtenerTiempoDisponible(String username) throws Exception;
	
}
