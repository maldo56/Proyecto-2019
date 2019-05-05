package bean.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import bean.database.PostgresBeanLocal;
import obj.dto.DtoAdmin;
import obj.dto.DtoClient;
import obj.dto.DtoMovimiento;
import obj.dto.DtoParm;
import obj.dto.DtoUsuario;


/**
 * Session Bean implementation class UserCtrlBean
 */
@Stateless
@LocalBean
public class UserCtrlBean implements UserCtrlBeanLocal {

	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/PostgresBean!bean.database.PostgresBeanLocal")
	private PostgresBeanLocal database;
	
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
    
    public Boolean ABMParametro(char operation, DtoParm parm) {
    	return database.ABMParametro(operation, parm);
    }
    
    //-------------------------------  GET  ----------------------------------------------------//
    
    public List<DtoMovimiento> obtenerMovimientos(String cliente) {
    	return database.obtenerMovimientos(cliente);
    }
    
    public DtoClient obtenerCliente(String username) {
    	return database.obtenerCliente(username);
    }
    
    public DtoParm obtenerParametro(String key) {
    	return database.obtenerParametro(key);
    }
    
    public Float obtenerTiempoDisponible(String username) {
    	return database.obtenerTiempoDisponible(username);
    }
}
