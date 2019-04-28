package bean.user.api;

import java.sql.Timestamp;
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

import bean.business.UserCtrlBeanLocal;
import obj.dto.DtoClient;
import obj.dto.DtoMovimiento;
import obj.dto.DtoParm;
import obj.dto.DtoUsuario;
import obj.dto.DtoAdmin;


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
    public DtoUsuario login(@QueryParam("username") String username, @QueryParam("password") String password) {
    	return buissnes.login(username, password);
    }
    
    
    @POST
	@Path("/client/abm/{operation}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Boolean createClient(@PathParam("operation") char operation, @QueryParam("client") DtoClient client) {
    	
    	return buissnes.ABMClient(operation, client);
    }
    
    @POST
	@Path("/admin/abm/{operation}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Boolean createAdmin(@PathParam("operation") char operation, @QueryParam("admin") DtoAdmin admin) {
    	
    	return buissnes.ABMAdmin(operation, admin);
    }

    @POST
	@Path("/movimiento")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Boolean createMovimiento(@QueryParam("movimiento") DtoMovimiento movimiento) {
    	    	
    	
    	return buissnes.createMovimiento(movimiento);
    }
    
    @POST
    @Path("/parametro/abm/{operation}")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Boolean ABMParametro(@PathParam("operation") char operation, @QueryParam("parm") DtoParm parm) {
    	
    	return buissnes.ABMParametro(operation, parm);
    }
    
    @GET
    @Path("/movimientos")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public List<DtoMovimiento> obtenerMovimientos(@QueryParam("client") String client) {
    	
    	if ( client.equals("") || client == null ) {
    		System.out.println("Todos los movimientos");
    	} else {
    		System.out.println("Movimientos de " + client);
    	}
    	
    	return buissnes.obtenerMovimientos(client);
    }
    
    
}
