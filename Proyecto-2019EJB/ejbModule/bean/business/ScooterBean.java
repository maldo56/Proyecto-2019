package bean.business;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import bean.database.PostgresUserBeanLocal;
import bean.scooterclient.database.MongoScooterBeanLocal;
import obj.dto.DtoScooter;

/**
 * Session Bean implementation class ScooterBean
 */
@Stateless
@LocalBean
public class ScooterBean implements ScooterBeanLocal {

	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/PostgresUserBean!bean.database.PostgresUserBeanLocal")
	private PostgresUserBeanLocal postgres;
	
	
    public ScooterBean() {
        // TODO Auto-generated constructor stub
    }
    
    public Boolean AB(char operation, String guid) {
    	
    	return postgres.ABScooter(operation, guid);
    }
    
    public Boolean M(String campo, String guid, String value) {
    	return postgres.MScooter(campo, guid, value);
    }
}
