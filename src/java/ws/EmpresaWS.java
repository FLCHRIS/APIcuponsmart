package ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.EmpresaDAO;
import modelo.pojo.Mensaje;
import utils.Utilidades;

@Path("empresas")
public class EmpresaWS {

    @Context
    private UriInfo context;

    public EmpresaWS() {
    }
    
    @GET
    @Path("buscarEmpresa/{parametro}")
    @Produces(MediaType.APPLICATION_JSON)
    public String buscarEmpresaBy_Name_RFC_REPRESENTANTENAME(@PathParam("parametro") String parametro) {


        if (parametro.length() == 8) {
            //validaciones para el tipo de consulta
        }

        return parametro;
    }
    

    @POST
    @Path("registroEmpresa")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registroEmpresa(
            @FormParam("nombreComercial") String nombreComercial, @FormParam("nombreRepresentanteLegal") String nombreRepresentanteLegal, 
            @FormParam("email") String email, @FormParam("calle") String calle, @FormParam("numero") Integer numero, 
            @FormParam("codigoPostal") String codigoPostal, @FormParam("ciudad") String ciudad, @FormParam("telefono") String telefono, 
            @FormParam("paginaWeb") String paginaWeb,@FormParam("RFC") String RFC) {

        if (nombreComercial == null || nombreComercial.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        if (nombreRepresentanteLegal == null || nombreRepresentanteLegal.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        if (email == null || email.isEmpty() || !Utilidades.validarEmail(email)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        if (calle == null || calle.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        if (numero == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        if (codigoPostal == null || codigoPostal.isEmpty() || !Utilidades.validarCodigoPostal(codigoPostal)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        if (ciudad == null || ciudad.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        if (telefono == null || telefono.isEmpty() || !Utilidades.validarTelefono(telefono)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        if (paginaWeb == null || paginaWeb.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        if (RFC == null || RFC.isEmpty() || !Utilidades.validarRfcEmpresa(RFC)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        Mensaje mensaje = EmpresaDAO.registrar(
                nombreComercial, nombreRepresentanteLegal, 
                email, calle, numero, codigoPostal, 
                ciudad, telefono, paginaWeb, RFC);
        
        return mensaje;
    }

    @PUT
    @Path("edicionEmpresa/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public String edicionEmpresa(@PathParam("idEmpresa") Integer idEmpresa) {

        return "" + idEmpresa;
    }

    @DELETE
    @Path("eliminarEmpresa/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public String eliminarEmpresa(@PathParam("idEmpresa") Integer idEmpresa) {

        return "" + idEmpresa;
    }
}
