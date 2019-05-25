package bean.business;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.management.ObjectName;
import javax.security.sasl.AuthenticationException;

import bean.database.PostgresBeanLocal;
import exceptions.ImageException;
import obj.dto.DtoAdmin;
import obj.dto.DtoClient;
import obj.dto.DtoMovimiento;
import obj.dto.DtoParm;
import obj.dto.DtoUsuario;
import sun.misc.BASE64Decoder;


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
    	
    	boolean result = false;
    	
    	try {
    		
    		if ( client.getUrlphoto() == null || client.getUrlphoto().isEmpty() ) {
    			client.setUrlphoto("https://res.cloudinary.com/dnieertcs/image/upload/v1558049741/user-default.png");
    		} else {
    			
    			try {
    				
    				String[] aux = client.getUrlphoto().split(",");
    				
    				if ( aux[1].matches("^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$") ) {
        				
    					try {
    						byte[] imageByte;
    						BufferedImage image = null;
    						
            				BASE64Decoder decoder = new BASE64Decoder();
            				imageByte = decoder.decodeBuffer(client.getUrlphoto());
            				
            				String parts[] = client.getUrlphoto().split(",");

            				imageByte = decoder.decodeBuffer(parts[1]);
            				ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            				image = ImageIO.read(bis);
            				bis.close();

            				UUID uuid = UUID.randomUUID();
            				String imgName = uuid.toString() + ".png";

            				File outputfile = new File("C:\\images\\" + imgName);
            				ImageIO.write(image, "png", outputfile);
            				
            				
            				DtoParm host = database.obtenerParametro("host");
            				DtoParm port = database.obtenerParametro("port");
            				
            				
            				client.setUrlphoto("http://" + host.getValue() +":" + port.getValue() + "/resources/images/" + imgName);
            				
    					} catch ( Exception e ) {
    						System.out.println(e.getMessage());
    						
    						client.setUrlphoto("https://res.cloudinary.com/dnieertcs/image/upload/v1558049741/user-default.png");
            				result = database.ABMClient(operation, client);
            				
            				ImageException ie = new ImageException("Error: Ha ocurrido un error al cargar su imagen de perfil.");
            				ie.setSuccess(result);
            				
            				throw ie;
    					}
						
        			} else {
        				client.setUrlphoto("https://res.cloudinary.com/dnieertcs/image/upload/v1558049741/user-default.png");
        				result = database.ABMClient(operation, client);
        				
        				ImageException ie = new ImageException("Error: Ha ocurrido un error al cargar su imagen de perfil.");
        				ie.setSuccess(result);
        				
        				throw ie;
        			}
    				
    			} catch ( Exception e) {
    				throw e;
    			}
    		}
     		
    	} catch ( Exception e ) {
    		throw e;
    	}
    	
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
    
    public Boolean recargarSaldoAdmin(String admin, String password, String usernameCliente, float monto) throws Exception {
    	
    	DtoAdmin Auxadmin = database.obtenerAdmin(admin);
    	
    	if ( Auxadmin != null && Auxadmin.getPassword() != password ) {
    		throw new AuthenticationException("Usuario o contraseña incorrecto.");
    	}
    	
    	
    	DtoMovimiento movimiento = new DtoMovimiento();
    	movimiento.setMoneda("U$S");
    	movimiento.setMount(monto);
    	movimiento.setPaypalguid("");
    	movimiento.setUsername(usernameCliente);
    	movimiento.setTimestamp(new Timestamp(System.currentTimeMillis()));
    	
    	database.createMovimiento(movimiento);
    	
    	return database.recargarSaldoCliente(usernameCliente, monto);
    }
    
    //-------------------------------  GET  ----------------------------------------------------//
    
    public List<DtoMovimiento> obtenerMovimientos(String cliente) throws Exception {
    	return database.obtenerMovimientos(cliente);
    }
    
    public DtoClient obtenerCliente(String username) throws Exception {
    	return database.obtenerCliente(username);
    }
    
    public DtoAdmin obtenerAdmin(String username) throws Exception {
    	return database.obtenerAdmin(username);
    }
    
    public DtoParm obtenerParametro(String key) throws Exception {
    	return database.obtenerParametro(key);
    }
    
    public List<DtoParm> obtenerParametros() throws Exception {
    	return database.obtenerParametros();
    }
    
    public Float obtenerTiempoDisponible(String username) throws Exception {
    	return database.obtenerTiempoDisponible(username);
    }
}
