package bean.business;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import bean.database.PostgresBeanLocal;
import bean.database.mongo.MongoBeanLocal;
import obj.dto.DtoHistorialTarifa;
import obj.dto.DtoInfoScooters;
import obj.dto.DtoRakingUsuarios;



@Stateless
@LocalBean
public class ReportesCtrlBean implements ReportesCtrlBeanLocal {

	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/PostgresBean!bean.database.PostgresBeanLocal")
	private PostgresBeanLocal database;

	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/MongoBean!bean.database.mongo.MongoBeanLocal")
	private MongoBeanLocal mongo;

	
	
    public ReportesCtrlBean() {
        // TODO Auto-generated constructor stub
    }

    public DtoInfoScooters infoScooters() throws Exception {
    	return database.reporteInfoScooter();
    }
    
    public float ganancias(Timestamp inicio, Timestamp fin) throws Exception {
    	return database.reporteGanancias(inicio, fin);
    }
    
    public double cantAlquileres(Timestamp inicio, Timestamp fin) throws Exception {
    	return database.reportesCantAlquileres(inicio, fin);
    }
    
    public List<DtoRakingUsuarios> rakingUsuarios() throws Exception {
    	return database.reporteRakingUsuarios();
    }
    
    public List<DtoHistorialTarifa> historialTarifa() throws Exception {
    	return mongo.historialParametro("tarifa-actual");
    }
}
