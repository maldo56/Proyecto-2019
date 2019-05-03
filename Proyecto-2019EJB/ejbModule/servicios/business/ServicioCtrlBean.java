package servicios.business;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import bean.scooterclient.database.MongoBeanLocal;


@Stateless
@LocalBean
public class ServicioCtrlBean implements ServicioCtrlBeanLocal {

	
	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/MongoBean!bean.scooterclient.database.MongoBeanLocal")
	private MongoBeanLocal mongo;
	
   
    public ServicioCtrlBean() {
        // TODO Auto-generated constructor stub
    }

    public void addPoint(String scooterGuid, float x, float y) {
    	
    	mongo.servicioAddPunto(scooterGuid, x, y);
    }
}
