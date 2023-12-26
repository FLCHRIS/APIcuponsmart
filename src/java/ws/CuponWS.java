package ws;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import modelo.CuponDAO;
import modelo.pojo.Mensaje;


@Path("cupones")
public class CuponWS {
    
    @Context 
    private UriInfo context;
    
    public CuponWS(){}
    
    @GET
    @Path("buscarTodosLosCupones")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarTodosLosCupones(){
        
        Mensaje mensaje = CuponDAO.buscarTodosLosCupones();
    
        return mensaje;
    }
    
    @GET
    @Path("buscarCuponesComercial/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarCuponesComercial(@PathParam("idEmpresa") Integer idEmpresa){
        
        Mensaje mensaje = CuponDAO.buscarCuponesComercial(idEmpresa);
    
        return mensaje;
    }
    
    @PUT
    @Path("canjearCupon")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje canjearCupon(@FormParam("codigo") String codigo){
        
        if (codigo.length() != 8){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        Mensaje mensaje = CuponDAO.canjearCupon(codigo);
        return mensaje;
    }
}
