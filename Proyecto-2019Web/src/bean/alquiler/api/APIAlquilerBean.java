package bean.alquiler.api;

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

import bean.business.AlquilerCtrlBeanLocal;
import exceptions.AuthorizationTokenException;
import exceptions.DateTimeException;
import exceptions.ImageException;
import exceptions.MovimientoException;
import io.jsonwebtoken.Claims;
import notificaciones.api.APINotificacionesBean;
import obj.dto.DtoAlquiler;
import security.JWTManage;
import servicios.api.APIServiciosBean;


@Stateless
@LocalBean
@Path("alquileres")
@Consumes({"application/xml", "application/json"})
@Produces({"application/xml", "application/json"})
public class APIAlquilerBean {

	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/AlquilerCtrlBean!bean.business.AlquilerCtrlBeanLocal")
	private AlquilerCtrlBeanLocal buissnes;
	
	@Inject
	APINotificacionesBean notifications;
	
	@Inject
	APIServiciosBean scooterAction;
	
	
    public APIAlquilerBean() {
        // TODO Auto-generated constructor stub
    }

    
    @POST
    @Path("/alquiler/{operation}")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> alquiler(@HeaderParam("Authorization") String token, @PathParam("operation") char operation, DtoAlquiler alquiler) {
    	
    	Map<String, Object> resp = new HashMap();
      
    	try {
    		try {
    			JWTManage.decodeJWT(token);
    		} catch (Exception e) {
    			throw new AuthorizationTokenException("Autorización fallida");
    		}
    		
    		DtoAlquiler a = buissnes.alquiler(operation, alquiler);
    		resp.put("success", true);
    		resp.put("message", "");
    		resp.put("body", a);
    		
    		if ( operation == 'E' ) {
    			notifications.sendNotification("client", alquiler.getCliente(), "Ha comenzado su alquiler");
    			scooterAction.sendAction("startTravel", a.getGuid(), a.getCliente(), a.getGuidscooter());
    			
    		} else {
    			notifications.sendNotification("client", alquiler.getCliente(), "Ha finalizado su alquiler");
    			scooterAction.sendAction("shutdown", a.getGuid(), a.getCliente(), a.getGuidscooter());
    		}
    		
    	} catch (DateTimeException e) {
    		resp.put("success", false);
    		resp.put("message", e.getMessage() + ".");
    		resp.put("body", e.getAlquiler());
    	} catch (MovimientoException e) {
    		resp.put("success", false);
    		resp.put("message", e.getMessage() + ".");
    		resp.put("body", e.getAlquiler());
    	} catch (Exception e) {
    		resp.put("success", false);
    		resp.put("message", e.getMessage() + ".");
    		resp.put("body", null);
    	}
    	
    	return resp;
    }
    
    
    //--------------------------------------  GET  ---------------------------------------------------------//

    @GET
    @Path("/find")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> obtenerAlquiler(@HeaderParam("Authorization") String token, @QueryParam("guid") String guid) {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	
    	try {
    		try {
    			JWTManage.decodeJWT(token);
    		} catch (Exception e) {
    			throw new AuthorizationTokenException("Autorización fallida");
    		}
    		
    		DtoAlquiler a = buissnes.obtenerAlquiler(guid);
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
    @Path("/porcliente")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> obtenerAlquileres(@HeaderParam("Authorization") String token, @QueryParam("username") String username) {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	
    	try {
    		try {
    			JWTManage.decodeJWT(token);
    		} catch (Exception e) {
    			throw new AuthorizationTokenException("Autorización fallida");
    		}
    		
    		List<DtoAlquiler> a = buissnes.obtenerAlquileres(username);
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
    @Path("/activoporcliente")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> obtenerAlquilerActivo(@HeaderParam("Authorization") String token, @QueryParam("username") String username) {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		try {
    			JWTManage.decodeJWT(token);
    		} catch (Exception e) {
    			throw new AuthorizationTokenException("Autorización fallida");
    		}
    		
    		DtoAlquiler a = buissnes.obtenerAlquilerActivo(username);
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
}
