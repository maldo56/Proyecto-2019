package bean.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import bean.database.PostgresBeanLocal;
import bean.database.mongo.MongoBeanLocal;
import obj.dto.DtoAlquiler;
import obj.dto.DtoLocation;


@Stateless
@LocalBean
public class AlquilerCtrlBean implements AlquilerCtrlBeanLocal {

	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/PostgresBean!bean.database.PostgresBeanLocal")
	private PostgresBeanLocal database;
	
	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/MongoBean!bean.database.mongo.MongoBeanLocal")
	private MongoBeanLocal mongo;
	
    
    public AlquilerCtrlBean() {
        // TODO Auto-generated constructor stub
    }

    public DtoAlquiler alquiler(char operation, DtoAlquiler alquiler) throws Exception {
    	
    	if ( operation == 'E' ) {
    		
    		if ( !alquiler.getGuidscooter().isEmpty() ) {
    			database.reloadLocation(alquiler.getGuidscooter());
    		}
    		
    		return database.altaAlquiler(alquiler);
        	
    	} else if ( operation == 'T' ) {

    		List<DtoLocation> ubicaciones = mongo.obtenerPuntos(alquiler.getGuid());
    		if ( !ubicaciones.isEmpty() ) {
    			database.reloadLocation(ubicaciones.get(0));
    		}
    		
    		return database.terminarAlquiler(alquiler, ubicaciones);
    	}
    	
    	return null;
    }
    
    public DtoAlquiler obtenerAlquiler(String guid) throws Exception {
    	return database.obtenerAlquiler(guid);
    }
    
    public List<DtoAlquiler> obtenerAlquileres(String username) throws Exception {
    	return database.obtenerAlquileres(username);
    }
    
    public DtoAlquiler obtenerAlquilerActivo(String username) throws Exception {
    	return database.obtenerAlquilerActivo(username);
    }
}
