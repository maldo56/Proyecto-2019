package bean.user.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import bean.business.UserCtrlBeanLocal;
import exceptions.ImageException;
import obj.dto.DtoClient;
import obj.dto.DtoMovimiento;
import obj.dto.DtoParm;
import obj.dto.DtoUsuario;
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
	@Path("/client/abm/{operation}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> createClient(@PathParam("operation") char operation, DtoClient client) {
    	
    	Map<String, Object> resp = new HashMap();
    	boolean a = false;
    	
    	try {
    		a = buissnes.ABMClient(operation, client);
    		resp.put("success", true);
    		resp.put("message", "");
    		resp.put("body", a);
    		
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
    public Map<String, Object> createAdmin(@PathParam("operation") char operation, DtoAdmin admin) {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		boolean a = buissnes.ABMAdmin(operation, admin);
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
	@Path("/movimiento")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> createMovimiento(DtoMovimiento movimiento) {
    	    	
    	Map<String, Object> resp = new HashMap();
    	
    	try {
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
    public Map<String, Object> ABMParametro(@PathParam("operation") char operation, @QueryParam("admin") String admin, DtoParm parm) {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		boolean a = buissnes.ABMParametro(operation, admin, parm);
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
    @Path("/recargar")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> recargarSaldo(@QueryParam("username") String username, @QueryParam("guidpaypal") String guidpaypal, @QueryParam("monto") float monto, @QueryParam("moneda") String moneda) {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		boolean a = buissnes.recargarSaldo(username, guidpaypal, monto, moneda);
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
    @Path("/recargarSaldo/admin")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> recargarSaldoAdmin(@QueryParam("admin") String admin, @QueryParam("password") String password, @QueryParam("usernameCliente") String usernameCliente, @QueryParam("monto") float monto) {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		boolean a = buissnes.recargarSaldoAdmin(admin, password, usernameCliente, monto);
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
    
    
    //--------------------------------------  GET  ---------------------------------------------------------//
    
    @GET
    @Path("/movimientos")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> obtenerMovimientos(@QueryParam("client") String client) {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	try {
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
    public Map<String, Object> obtenerCliente(@QueryParam("username") String username) {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	try {
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
    public Map<String, Object> obtenerAdmin(@QueryParam("username") String username) {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	try {
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
    public Map<String, Object> obtenerParametros() {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	System.out.println("Llega <=============== API");
    	
    	try {
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
    public Map<String, Object> obtenerParametro(@QueryParam("key") String key) {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	try {
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
    public Map<String, Object> obtenerTiempoDisponible(@QueryParam("username") String username) {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	try {
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
