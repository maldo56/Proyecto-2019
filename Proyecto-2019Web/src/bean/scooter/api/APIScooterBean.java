package bean.scooter.api;

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

import obj.dto.DtoGeometria;
import obj.dto.DtoScooter;
import bean.business.ScooterCtrlBeanLocal;

/**
 * Session Bean implementation class ScooterBean
 */
@Stateless
@LocalBean
@Path("scooter")
@Consumes({"application/xml", "application/json"})
@Produces({"application/xml", "application/json"})
public class APIScooterBean {


	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/ScooterCtrlBean!bean.business.ScooterCtrlBeanLocal")
	private ScooterCtrlBeanLocal business;
	
	
    public APIScooterBean() {
        // TODO Auto-generated constructor stub
    }
    
    
    @POST
	@Path("/ab/{operation}")
    @Consumes( {"application/json"} )
	@Produces( {"application/json"} )
	public Map<String, Object> AB(@PathParam("operation") char operation, @QueryParam("guid") String guid) {

    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		boolean a = business.AB(operation, guid);
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
	@Path("/m/{campo}")
    @Consumes( {"application/json"} )
	@Produces( {"application/json"} )
	public Map<String, Object> M(@PathParam("campo") String campo, @QueryParam("guid") String guid, @QueryParam("value") String value) {

    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		boolean a = business.M(campo, guid, value);
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
	@Path("/area/{operation}")
    @Consumes( {"application/json"} )
	@Produces( {"application/json"} )
	public Map<String, Object> abArea(@PathParam("operation") char operation, DtoGeometria geometry) {

    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		boolean a = business.abArea(operation, geometry);
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
    
    
    //-----------------------------------------  GET  ------------------------------------------------------------------------//
    
    
    @GET
    @Path("/disponibles")
    @Consumes( {"application/json"} )
	@Produces( {"application/json"} )
	public Map<String, Object> scootersDisponibles() {

    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		List<DtoScooter> a = business.scootersDisponibles();
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
    @Path("/isAlquilado")
    @Consumes( {"application/json"} )
	@Produces( {"application/json"} )
	public Map<String, Object> estaAlquilado(@QueryParam("guid") String guid) {

    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		String a = business.estaAlquilado(guid);
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
    @Path("/isAlquilado")
    @Consumes( {"application/json"} )
	@Produces( {"application/json"} )
	public Map<String, Object> obtenerArea() {

    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		DtoGeometria a = business.obtenerArea();
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
