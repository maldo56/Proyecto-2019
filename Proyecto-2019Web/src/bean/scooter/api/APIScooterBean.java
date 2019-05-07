package bean.scooter.api;

import java.util.List;

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
	public Boolean AB(@PathParam("operation") char operation, @QueryParam("guid") String guid) {

    	return business.AB(operation, guid);
    }

    @POST
	@Path("/m/{campo}")
    @Consumes( {"application/json"} )
	@Produces( {"application/json"} )
	public Boolean M(@PathParam("campo") String campo, @QueryParam("guid") String guid, @QueryParam("value") String value) {

    	return business.M(campo, guid, value);
    }
    
    
    //-----------------------------------------  GET  ------------------------------------------------------------------------//
    
    
    @GET
    @Path("/disponibles")
    @Consumes( {"application/json"} )
	@Produces( {"application/json"} )
	public List<DtoScooter> scootersDisponibles() {

    	return business.scootersDisponibles();
    }
    
    
    @GET
    @Path("/isAlquilado")
    @Consumes( {"application/json"} )
	@Produces( {"application/json"} )
	public String estaAlquilado(@QueryParam("guid") String guid) {

    	return business.estaAlquilado(guid);
    }
    
}
