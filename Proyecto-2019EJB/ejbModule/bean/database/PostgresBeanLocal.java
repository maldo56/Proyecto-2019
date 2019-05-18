package bean.database;

import java.util.List;

import javax.ejb.Local;

import obj.dto.DtoAdmin;
import obj.dto.DtoAlquiler;
import obj.dto.DtoClient;
import obj.dto.DtoGeometria;
import obj.dto.DtoLocation;
import obj.dto.DtoMovimiento;
import obj.dto.DtoParm;
import obj.dto.DtoScooter;
import obj.dto.DtoUsuario;



@Local
public interface PostgresBeanLocal {

	DtoUsuario login(String username, String password) throws Exception;
	
	Boolean ABScooter(char operation, String guid) throws Exception;
	Boolean MScooter(String campo, String guid, String value) throws Exception;
	Boolean ABMClient(char operation, DtoClient client) throws Exception;
	Boolean ABMAdmin(char operation, DtoAdmin client) throws Exception;
	Boolean ABMParametro(char operation, DtoParm parm) throws Exception;
	Boolean createMovimiento(DtoMovimiento movimiento) throws Exception;
	DtoAlquiler altaAlquiler(DtoAlquiler alquiler) throws Exception;
	Boolean abArea(char operation, DtoGeometria geometry) throws Exception;
	
	Boolean recargarSaldoCliente(String username, float monto) throws Exception;
	
	DtoAlquiler terminarAlquiler(DtoAlquiler alquiler, List<DtoLocation> ubicaciones) throws Exception;
	
	List<DtoMovimiento> obtenerMovimientos(String cliente) throws Exception;
	DtoClient obtenerCliente(String username) throws Exception;
	DtoParm obtenerParametro(String key) throws Exception;
	List<DtoAlquiler> obtenerAlquileres(String username) throws Exception;
	List<DtoScooter> scootersDisponibles() throws Exception;
	float obtenerTiempoDisponible(String username) throws Exception;
	String scooterEstaAlquilado(String guid) throws Exception;
	DtoAlquiler obtenerAlquiler(String guid) throws Exception;
	DtoGeometria obtenerArea() throws Exception;
	
}
