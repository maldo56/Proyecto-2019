package bean.alquiler.api;

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
import javax.ws.rs.core.MediaType;

import bean.business.AlquilerCtrlBeanLocal;
import obj.dto.DtoAlquiler;


@Stateless
@LocalBean
@Path("alquileres")
@Consumes({"application/xml", "application/json"})
@Produces({"application/xml", "application/json"})
public class APIAlquilerBean {

	@EJB(mappedName="java:global/Proyecto-2019/Proyecto-2019EJB/AlquilerCtrlBean!bean.business.AlquilerCtrlBeanLocal")
	private AlquilerCtrlBeanLocal buissnes;
	
	
    public APIAlquilerBean() {
        // TODO Auto-generated constructor stub
    }

    
    @POST
    @Path("/alquiler/{operation}")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Boolean alquiler(@PathParam("operation") char operation, DtoAlquiler alquiler) {
    	
    	return buissnes.alquiler(operation, alquiler);
    }
    
    
    //--------------------------------------  GET  ---------------------------------------------------------//

    
    @GET
    @Path("/porcliente")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public List<DtoAlquiler> obtenerAlquileres(@QueryParam("username") String username) {
    	
    	
    	return buissnes.obtenerAlquileres(username);
    }
}
