package servicios.business;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import bean.database.PostgresBeanLocal;
import bean.scooterclient.database.MongoBeanLocal;


@Stateless
@LocalBean
public class ServicioCtrlBean implements ServicioCtrlBeanLocal {

	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/PostgresBean!bean.database.PostgresBeanLocal")
	private PostgresBeanLocal postgres;
	
	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/MongoBean!bean.scooterclient.database.MongoBeanLocal")
	private MongoBeanLocal mongo;
	
   
    public ServicioCtrlBean() {
        // TODO Auto-generated constructor stub
    }

    public void addPoint(String scooterGuid, String alquilerGuid, float x, float y)  throws Exception {
    	
    	mongo.servicioAddPunto(scooterGuid,alquilerGuid, x, y);
    }
    
    public String estaAlquilado(String guid) throws Exception {
    	return postgres.scooterEstaAlquilado(guid);
    }
}
