package bean.business;

import java.sql.Timestamp;
import java.util.Map;

import javax.ejb.Local;

import obj.dto.DtoInfoScooters;

@Local
public interface ReportesCtrlBeanLocal {

	DtoInfoScooters infoScooters() throws Exception;
	float ganancias(Timestamp inicio, Timestamp fin) throws Exception;
}
