package bean.business;

import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import bean.database.PostgresBeanLocal;
import obj.dto.DtoInfoScooters;



@Stateless
@LocalBean
public class ReportesCtrlBean implements ReportesCtrlBeanLocal {

	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/PostgresBean!bean.database.PostgresBeanLocal")
	private PostgresBeanLocal database;

    public ReportesCtrlBean() {
        // TODO Auto-generated constructor stub
    }

    public DtoInfoScooters infoScooters() throws Exception {
    	return database.reporteInfoScooter();
    }
    
    public float ganancias(Timestamp inicio, Timestamp fin) throws Exception {
    	return database.reporteGanancias(inicio, fin);
    }
    
    public int cantAlquileres(Timestamp inicio, Timestamp fin) throws Exception {
    	return database.reportesCantAlquileres(inicio, fin);
    }
    
}
