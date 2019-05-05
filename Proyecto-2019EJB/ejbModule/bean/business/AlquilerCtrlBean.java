package bean.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import bean.database.PostgresBeanLocal;
import obj.dto.DtoAlquiler;


@Stateless
@LocalBean
public class AlquilerCtrlBean implements AlquilerCtrlBeanLocal {

	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/PostgresBean!bean.database.PostgresBeanLocal")
	private PostgresBeanLocal database;
	
    
    public AlquilerCtrlBean() {
        // TODO Auto-generated constructor stub
    }

    public Boolean alquiler(char operation, DtoAlquiler alquiler) {
    	
    	if ( operation == 'E' ) {
    		
    		database.altaAlquiler(alquiler);
        	
    	} else if ( operation == 'T' ) {
    		
    		database.terminarAlquiler(alquiler);
    	}
    	
    	
    	return true;
    }
    
    public List<DtoAlquiler> obtenerAlquileres(String username) {
    	return database.obtenerAlquileres(username);
    }
}
