package bean.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import bean.database.PostgresUserBeanLocal;
import obj.dto.DtoAdmin;
import obj.dto.DtoClient;
import obj.dto.DtoMovimiento;
import obj.dto.DtoUsuario;


/**
 * Session Bean implementation class UserCtrlBean
 */
@Stateless
@LocalBean
public class UserCtrlBean implements UserCtrlBeanLocal {

	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/PostgresUserBean!bean.database.PostgresUserBeanLocal")
	private PostgresUserBeanLocal database;
	
    public UserCtrlBean() {
        // TODO Auto-generated constructor stub
    }
    
    public DtoUsuario login(String username, String password) {
    	return database.login(username, password);
    }
    
    
  //-------------------------------  ABM  ----------------------------------------------------//
    
    public Boolean ABMClient(char operation, DtoClient client) {
    	
    	return database.ABMClient(operation, client);
    }

    public Boolean ABMAdmin(char operation, DtoAdmin admin) {
    	
    	return database.ABMAdmin(operation, admin);
    }
    
    public Boolean createMovimiento(DtoMovimiento movimiento) {
    	
    	return database.createMovimiento(movimiento);
    }
    
    
    //-------------------------------  GET  ----------------------------------------------------//
    
    public List<DtoMovimiento> obtenerMovimientos(String cliente) {
    	return database.obtenerMovimientos(cliente);
    }
}
