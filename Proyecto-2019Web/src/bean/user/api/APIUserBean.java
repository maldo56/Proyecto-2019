package bean.user.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import bean.business.UserCtrlBeanLocal;
import exceptions.AuthorizationTokenException;
import exceptions.ImageException;
import io.jsonwebtoken.Claims;
import notificaciones.api.APINotificacionesBean;
import obj.dto.DtoClient;
import obj.dto.DtoMovimiento;
import obj.dto.DtoParm;
import obj.dto.DtoUsuario;
import security.JWTManage;
import obj.dto.DtoAdmin;
import obj.dto.DtoAlquiler;


@Stateless
@LocalBean
@Path("users")
@Consumes({"application/xml", "application/json"})
@Produces({"application/xml", "application/json"})
public class APIUserBean {

	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/UserCtrlBean!bean.business.UserCtrlBeanLocal")
	private UserCtrlBeanLocal buissnes;
	
	@Inject
	APINotificacionesBean notifications;
    
    public APIUserBean() {
        // TODO Auto-generated constructor stub
    }
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> login(@QueryParam("username") String username, @QueryParam("password") String password) {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		DtoUsuario a = buissnes.login(username, password);
        	String token = JWTManage.createJWT(a);
        	
    		resp.put("success", true);
    		resp.put("message", "");
    		resp.put("auth", token);
    		resp.put("body", a);
    		
    	} catch (Exception e) {
    		resp.put("success", false);
    		resp.put("message", e.getMessage() + ".");
    		resp.put("body", null);
    	}
 	
    	return resp;
    }
    
    @POST
	@Path("/client/abm/{operation}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> createClient(@HeaderParam("Authorization") String token, @PathParam("operation") char operation, DtoClient client) {
    	
    	Map<String, Object> resp = new HashMap();
    	boolean a = false;
    	
    	try {
    		try {
    			if ( operation != 'A' ) {
    				JWTManage.decodeJWT(token);
    			}
    		} catch (Exception e) {
    			throw new AuthorizationTokenException("Autorización fallida");
    		}
    		
    		a = buissnes.ABMClient(operation, client);
    		resp.put("success", true);
    		resp.put("message", "");
    		resp.put("body", a);
    		
    		if ( operation == 'A' ) {
    			notifications.sendNotification("client", client.getUsername(), "Usted se ha registrado correctamente.");
    		} else if ( operation == 'M' ) {
    			notifications.sendNotification("client", client.getUsername(), "Sus datos se han actualizado correctamente.");
    		}
    		
    		
    	} catch (Exception e) {
    		
    		if (e instanceof ImageException) {
    			
    			ImageException ie = (ImageException) e;
    			
    			resp.put("success", ie.isSuccess());
        		resp.put("message", e.getMessage() + ".");
        		resp.put("body", a);
    		} else {
    			resp.put("success", false);
        		resp.put("message", e.getMessage() + ".");
        		resp.put("body", null);
    		}
    		
    	}
    	
    	return resp;
    }
    
    @POST
	@Path("/admin/abm/{operation}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> createAdmin(@HeaderParam("Authorization") String token, @PathParam("operation") char operation, DtoAdmin admin) throws IOException {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		try {
    			JWTManage.decodeJWT(token);
    		} catch (Exception e) {
    			throw new AuthorizationTokenException("Autorización fallida");
    		}
    		
    		boolean a = buissnes.ABMAdmin(operation, admin);
    		resp.put("success", true);
    		resp.put("message", "");
    		resp.put("body", a);
    		
    		if ( operation != 'A' ) {
    			notifications.sendNotification("admin", admin.getUsername(), "Usted se ha registrado correctamente.");
    		} else if ( operation != 'M' ) {
    			notifications.sendNotification("client", admin.getUsername(), "Sus datos se han actualizado correctamente.");
    		}
    		
    	} catch (Exception e) {
    		resp.put("success", false);
    		resp.put("message", e.getMessage() + ".");
    		resp.put("body", null);
    	}
    	
    	return resp;
    }

    @POST
	@Path("/movimiento")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> createMovimiento(@HeaderParam("Authorization") String token, DtoMovimiento movimiento) {
    	    	
    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		try {
    			JWTManage.decodeJWT(token);
    		} catch (Exception e) {
    			throw new AuthorizationTokenException("Autorización fallida");
    		}
    		
    		boolean a = buissnes.createMovimiento(movimiento);
    		resp.put("success", true);
    		resp.put("message", "");
    		resp.put("body", a);
    		
    	} catch (Exception e) {
    		resp.put("success", false);
    		resp.put("message", e.getMessage() + ".");
    		resp.put("body", null);
    	}
    	
    	return resp;
    }
    
    @POST
    @Path("/parametro/abm/{operation}")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> ABMParametro(@HeaderParam("Authorization") String token, @PathParam("operation") char operation, @QueryParam("admin") String admin, DtoParm parm) throws IOException {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		try {
    			JWTManage.decodeJWT(token);
    		} catch (Exception e) {
    			throw new AuthorizationTokenException("Autorización fallida");
    		}
    		
    		boolean a = buissnes.ABMParametro(operation, admin, parm);
    		resp.put("success", true);
    		resp.put("message", "");
    		resp.put("body", a);
    		
    		if ( operation == 'M' ) {
        		notifications.sendNotification("admin", "", "Se ha cambiado el parametro '" + parm.getCode() + "' a " + parm.getValue());
        	}
    		
    	} catch (Exception e) {
    		resp.put("success", false);
    		resp.put("message", e.getMessage() + ".");
    		resp.put("body", null);
    	}
    	
    	return resp;
    }
    
    @POST
    @Path("/recargar")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> recargarSaldo(@HeaderParam("Authorization") String token, @QueryParam("username") String username, @QueryParam("guidpaypal") String guidpaypal, @QueryParam("monto") float monto, @QueryParam("moneda") String moneda) {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		try {
    			JWTManage.decodeJWT(token);
    		} catch (Exception e) {
    			throw new AuthorizationTokenException("Autorización fallida");
    		}
    		
    		boolean a = buissnes.recargarSaldo(username, guidpaypal, monto, moneda);
    		resp.put("success", true);
    		resp.put("message", "");
    		resp.put("body", a);
    		
    		notifications.sendNotification("client", username, "Se le ha acreditado a su cuenta: " + monto);
    		
    	} catch (Exception e) {
    		resp.put("success", false);
    		resp.put("message", e.getMessage() + ".");
    		resp.put("body", null);
    	}
    	
    	return resp;
    }
    
    @POST
    @Path("/recargarSaldo/admin")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> recargarSaldoAdmin(@HeaderParam("Authorization") String token, @QueryParam("admin") String admin, @QueryParam("password") String password, @QueryParam("usernameCliente") String usernameCliente, @QueryParam("monto") float monto) {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		try {
    			JWTManage.decodeJWT(token);
    		} catch (Exception e) {
    			throw new AuthorizationTokenException("Autorización fallida");
    		}
    		
    		boolean a = buissnes.recargarSaldoAdmin(admin, password, usernameCliente, monto);
    		resp.put("success", true);
    		resp.put("message", "");
    		resp.put("body", a);
    		
    		notifications.sendNotification("client", usernameCliente, "Se le ha acreditado a su cuenta: " + monto);
    		
    	} catch (Exception e) {
    		resp.put("success", false);
    		resp.put("message", e.getMessage() + ".");
    		resp.put("body", null);
    	}
    	
    	return resp;
    }
    
    
    //--------------------------------------  GET  ---------------------------------------------------------//
    
    @GET
    @Path("/movimientos")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> obtenerMovimientos(@HeaderParam("Authorization") String token, @QueryParam("client") String client) {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		try {
    			JWTManage.decodeJWT(token);
    		} catch (Exception e) {
    			throw new AuthorizationTokenException("Autorización fallida");
    		}
    		
    		if ( client.equals("") || client == null ) {
        		System.out.println("Todos los movimientos");
        	} else {
        		System.out.println("Movimientos de " + client);
        	}
        	
        	List<DtoMovimiento> a = buissnes.obtenerMovimientos(client);
    		resp.put("success", true);
    		resp.put("message", "");
    		resp.put("body", a);
    		
    	} catch (Exception e) {
    		resp.put("success", false);
    		resp.put("message", e.getMessage() + ".");
    		resp.put("body", null);
    	}
    	
    	return resp;
    }
    
    @GET
    @Path("/cliente")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> obtenerCliente(@HeaderParam("Authorization") String token, @QueryParam("username") String username) {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		try {
    			JWTManage.decodeJWT(token);
    		} catch (Exception e) {
    			throw new AuthorizationTokenException("Autorización fallida");
    		}
    		
    		DtoClient a = buissnes.obtenerCliente(username);
    		resp.put("success", true);
    		resp.put("message", "");
    		resp.put("body", a);
    		
    	} catch (Exception e) {
    		resp.put("success", false);
    		resp.put("message", e.getMessage() + ".");
    		resp.put("body", null);
    	}
    	
    	return resp;
    }
    
    @GET
    @Path("/admin")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> obtenerAdmin(@HeaderParam("Authorization") String token, @QueryParam("username") String username) {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		try {
    			JWTManage.decodeJWT(token);
    		} catch (Exception e) {
    			throw new AuthorizationTokenException("Autorización fallida");
    		}
    		
    		DtoAdmin a = buissnes.obtenerAdmin(username);
    		resp.put("success", true);
    		resp.put("message", "");
    		resp.put("body", a);
    		
    	} catch (Exception e) {
    		resp.put("success", false);
    		resp.put("message", e.getMessage() + ".");
    		resp.put("body", null);
    	}
    	
    	return resp;
    }

    @GET
    @Path("/parametros")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> obtenerParametros(@HeaderParam("Authorization") String token) {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		try {
    			JWTManage.decodeJWT(token);
    		} catch (Exception e) {
    			throw new AuthorizationTokenException("Autorización fallida");
    		}
    		
    		List<DtoParm> a = buissnes.obtenerParametros();
    		resp.put("success", true);
    		resp.put("message", "");
    		resp.put("body", a);
    		
    	} catch (Exception e) {
    		resp.put("success", false);
    		resp.put("message", e.getMessage() + ".");
    		resp.put("body", null);
    	}
    	
    	return resp;
    	
    }
    
    @GET
    @Path("/parametro")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> obtenerParametro(@HeaderParam("Authorization") String token, @QueryParam("key") String key) {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		try {
    			JWTManage.decodeJWT(token);
    		} catch (Exception e) {
    			throw new AuthorizationTokenException("Autorización fallida");
    		}
    		
    		DtoParm a = buissnes.obtenerParametro(key);
    		resp.put("success", true);
    		resp.put("message", "");
    		resp.put("body", a);
    		
    	} catch (Exception e) {
    		resp.put("success", false);
    		resp.put("message", e.getMessage() + ".");
    		resp.put("body", null);
    	}
    	
    	return resp;
    }
    
    @GET
    @Path("/tiempodisponible")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> obtenerTiempoDisponible(@HeaderParam("Authorization") String token, @QueryParam("username") String username) {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		try {
    			JWTManage.decodeJWT(token);
    		} catch (Exception e) {
    			throw new AuthorizationTokenException("Autorización fallida");
    		}
    		
    		float a = buissnes.obtenerTiempoDisponible(username);
    		resp.put("success", true);
    		resp.put("message", "");
    		resp.put("body", (int) a);
    		
    	} catch (Exception e) {
    		resp.put("success", false);
    		resp.put("message", e.getMessage() + ".");
    		resp.put("body", null);
    	}
    	
    	return resp;
    }
    
}
