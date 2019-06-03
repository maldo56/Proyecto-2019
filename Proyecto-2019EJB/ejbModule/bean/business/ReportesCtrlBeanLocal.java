package bean.business;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ws.rs.QueryParam;

import obj.dto.DtoHistorialTarifa;
import obj.dto.DtoInfoScooters;
import obj.dto.DtoRakingUsuarios;

@Local
public interface ReportesCtrlBeanLocal {

	DtoInfoScooters infoScooters() throws Exception;
	float ganancias(Timestamp inicio, Timestamp fin) throws Exception;
	double cantAlquileres(Timestamp inicio, Timestamp fin) throws Exception;
	List<DtoRakingUsuarios> rakingUsuarios() throws Exception;
	List<DtoHistorialTarifa> historialTarifa() throws Exception;
}
