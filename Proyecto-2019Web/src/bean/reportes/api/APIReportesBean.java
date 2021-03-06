package bean.reportes.api;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import bean.business.ReportesCtrlBeanLocal;
import bean.business.ScooterCtrlBeanLocal;
import exceptions.AuthorizationTokenException;
import io.jsonwebtoken.Claims;
import obj.dto.DtoHistorialTarifa;
import obj.dto.DtoInfoScooters;
import obj.dto.DtoMovimiento;
import obj.dto.DtoRakingUsuarios;
import security.JWTManage;

@Stateless
@LocalBean
@Path("reportes")
@Consumes({"application/xml", "application/json"})
@Produces({"application/xml", "application/json"})
public class APIReportesBean {


	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/ReportesCtrlBean!bean.business.ReportesCtrlBeanLocal")
	private ReportesCtrlBeanLocal business;
	
	
    public APIReportesBean() {
        // TODO Auto-generated constructor stub
    }
    
    
    @GET
	@Path("/infoscooters")
    @Consumes( {"application/json"} )
	@Produces( {"application/json"} )
	public Map<String, Object> infoScooters(@HeaderParam("Authorization") String token) {

    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		try {
    			JWTManage.decodeJWT(token);
    		} catch (Exception e) {
    			throw new AuthorizationTokenException("Autorización fallida");
    		}
    		
    		DtoInfoScooters a = business.infoScooters();
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
	@Path("/ganancias")
    @Consumes( {"application/json"} )
	@Produces( {"application/json"} )
	public Map<String, Object> ganancias(@HeaderParam("Authorization") String token, @QueryParam("inicio") Timestamp inicio, @QueryParam("fin") Timestamp fin) {

    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		try {
    			JWTManage.decodeJWT(token);
    		} catch (Exception e) {
    			throw new AuthorizationTokenException("Autorización fallida");
    		}
    		
    		float a = business.ganancias(inicio, fin);
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
	@Path("/cantalquileres")
    @Consumes( {"application/json"} )
	@Produces( {"application/json"} )
	public Map<String, Object> cantAlquileres(@HeaderParam("Authorization") String token, @QueryParam("inicio") Timestamp inicio, @QueryParam("fin") Timestamp fin) {

    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		try {
    			JWTManage.decodeJWT(token);
    		} catch (Exception e) {
    			throw new AuthorizationTokenException("Autorización fallida");
    		}
    		
    		double a = business.cantAlquileres(inicio, fin);
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
	@Path("/rakingUsuarios")
    @Consumes( {"application/json"} )
	@Produces( {"application/json"} )
	public Map<String, Object> rakingUsuarios(@HeaderParam("Authorization") String token) {

    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		try {
    			JWTManage.decodeJWT(token);
    		} catch (Exception e) {
    			throw new AuthorizationTokenException("Autorización fallida");
    		}
    		
    		List<DtoRakingUsuarios> a = business.rakingUsuarios();
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
	@Path("/historialtarifa")
    @Consumes( {"application/json"} )
	@Produces( {"application/json"} )
	public Map<String, Object> historialTarifa(@HeaderParam("Authorization") String token, @QueryParam("inicio") Timestamp inicio, @QueryParam("fin") Timestamp fin) {

    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		try {
    			JWTManage.decodeJWT(token);
    		} catch (Exception e) {
    			throw new AuthorizationTokenException("Autorización fallida");
    		}
    		
    		List<DtoHistorialTarifa> a = business.historialTarifa(inicio, fin);
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
    @Path("/movimientos")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> reportesMovimientos(@HeaderParam("Authorization") String token, @QueryParam("inicio") Timestamp inicio, @QueryParam("fin") Timestamp fin) {
    	
    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		try {
    			JWTManage.decodeJWT(token);
    		} catch (Exception e) {
    			throw new AuthorizationTokenException("Autorización fallida");
    		}
    		
    		List<DtoMovimiento> a = business.reportesMovimientos(inicio, fin);
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