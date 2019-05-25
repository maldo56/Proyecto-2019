package bean.reportes.api;

import java.sql.Timestamp;
import java.util.HashMap;
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

import bean.business.ReportesCtrlBeanLocal;
import bean.business.ScooterCtrlBeanLocal;
import obj.dto.DtoInfoScooters;

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
	public Map<String, Object> infoScooters() {

    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		
    		System.out.println("Llega <=============================== API");
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
	public Map<String, Object> ganancias(@QueryParam("inicio") Timestamp inicio, @QueryParam("fin") Timestamp fin) {

    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		
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
	public Map<String, Object> cantAlquileres(@QueryParam("inicio") Timestamp inicio, @QueryParam("fin") Timestamp fin) {

    	Map<String, Object> resp = new HashMap();
    	
    	try {
    		
    		int a = business.cantAlquileres(inicio, fin);
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