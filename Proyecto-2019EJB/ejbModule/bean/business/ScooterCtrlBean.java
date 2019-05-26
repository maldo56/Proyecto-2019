package bean.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import bean.database.PostgresBeanLocal;
import bean.scooterclient.database.MongoBeanLocal;
import obj.dto.DtoGeometria;
import obj.dto.DtoScooter;

/**
 * Session Bean implementation class ScooterBean
 */
@Stateless
@LocalBean
public class ScooterCtrlBean implements ScooterCtrlBeanLocal {

	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/PostgresBean!bean.database.PostgresBeanLocal")
	private PostgresBeanLocal postgres;
	
	
	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/MongoBean!bean.scooterclient.database.MongoBeanLocal")
	private MongoBeanLocal mongo;
	
    public ScooterCtrlBean() {
        // TODO Auto-generated constructor stub
    }
    
    public Boolean AB(char operation, String guid) throws Exception {
    	
    	return postgres.ABScooter(operation, guid);
    }
    
    public Boolean M(String campo, String guid, String value) throws Exception {
    	return postgres.MScooter(campo, guid, value);
    }
    
    public Boolean abArea(char operation, DtoGeometria geometry) throws Exception {
    	return postgres.abArea(operation, geometry);
    }
    
    public List<DtoScooter> allScooters() throws Exception {
    	return postgres.allScooters();
    }
    
    public List<DtoScooter> scootersDisponibles() throws Exception {
    	return postgres.scootersDisponibles();
    }
    
    public String estaAlquilado(String guid) throws Exception {
    	
    	System.out.println("estaAlquilado Ctrl: " + guid);
    	
    	return postgres.scooterEstaAlquilado(guid);
    }
    
    public DtoGeometria obtenerArea() throws Exception {
    	return postgres.obtenerArea();
    }
}
