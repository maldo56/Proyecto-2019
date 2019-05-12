package bean.business;

import java.sql.Timestamp;
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
    
    public DtoUsuario login(String username, String password) throws Exception {
    	return database.login(username, password);
    }
    
    
  //-------------------------------  ABM  ----------------------------------------------------//
    
    public Boolean ABMClient(char operation, DtoClient client) throws Exception {
    	
    	return database.ABMClient(operation, client);
    }

    public Boolean ABMAdmin(char operation, DtoAdmin admin) throws Exception {
    	
    	return database.ABMAdmin(operation, admin);
    }
    
    public Boolean createMovimiento(DtoMovimiento movimiento) throws Exception {
    	
    	return database.createMovimiento(movimiento);
    }
    
    public Boolean ABMParametro(char operation, DtoParm parm) throws Exception {
    	return database.ABMParametro(operation, parm);
    }
    
    
    public Boolean recargarSaldo(String username, String guidpaypal, float monto, String moneda) throws Exception {
    	
    	DtoMovimiento movimiento = new DtoMovimiento();
    	movimiento.setMoneda(moneda);
    	movimiento.setMount(monto);
    	movimiento.setPaypalguid(guidpaypal);
    	movimiento.setUsername(username);
    	movimiento.setTimestamp(new Timestamp(System.currentTimeMillis()));
    	
    	database.createMovimiento(movimiento);
    	
    	return database.recargarSaldoCliente(username, monto);
    }
    
    //-------------------------------  GET  ----------------------------------------------------//
    
    public List<DtoMovimiento> obtenerMovimientos(String cliente) throws Exception {
    	return database.obtenerMovimientos(cliente);
    }
    
    public DtoClient obtenerCliente(String username) throws Exception {
    	return database.obtenerCliente(username);
    }
    
    public DtoParm obtenerParametro(String key) throws Exception {
    	return database.obtenerParametro(key);
    }
    
    public Float obtenerTiempoDisponible(String username) throws Exception {
    	return database.obtenerTiempoDisponible(username);
    }
}
