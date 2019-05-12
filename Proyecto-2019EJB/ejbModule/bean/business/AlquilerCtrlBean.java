package bean.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import bean.database.PostgresBeanLocal;
import bean.scooterclient.database.MongoBeanLocal;
import obj.dto.DtoAlquiler;
import obj.dto.DtoLocation;


@Stateless
@LocalBean
public class AlquilerCtrlBean implements AlquilerCtrlBeanLocal {

	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/PostgresBean!bean.database.PostgresBeanLocal")
	private PostgresBeanLocal database;
	
	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/MongoBean!bean.scooterclient.database.MongoBeanLocal")
	private MongoBeanLocal mongo;
	
    
    public AlquilerCtrlBean() {
        // TODO Auto-generated constructor stub
    }

    public DtoAlquiler alquiler(char operation, DtoAlquiler alquiler) throws Exception {
    	
    	if ( operation == 'E' ) {
    		
    		return database.altaAlquiler(alquiler);
        	
    	} else if ( operation == 'T' ) {
    		
    		List<DtoLocation> ubicaciones = mongo.obtenerPuntos(alquiler.getGuid());
    		
    		return database.terminarAlquiler(alquiler, ubicaciones);
    	}
    	
    	return null;
    }
    
    public List<DtoAlquiler> obtenerAlquileres(String username) throws Exception {
    	return database.obtenerAlquileres(username);
    }
}
