package ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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
}
