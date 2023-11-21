package ws;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import modelo.SucursalDAO;
import modelo.pojo.Mensaje;
import modelo.pojo.Sucursal;
import utils.Utilidades;

@Path("sucursales")
public class SucursalWS {

    @Context
    private UriInfo context;

    public SucursalWS() {
    }

    @POST
    @Path("registrar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarSucursal(@FormParam("idEmpresa") Integer idEmpresa,
            @FormParam("nombre") String nombre,
            @FormParam("telefono") String telefono,
            @FormParam("latitud") float latitud,
            @FormParam("longitud") float longitud) {
        Mensaje msj = new Mensaje();
        Sucursal sucursal = new Sucursal();

        if (idEmpresa <= 0 || idEmpresa == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (nombre == null || nombre.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (telefono == null || telefono.isEmpty() || !Utilidades.validarCadena(telefono, Utilidades.TELEFONO_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (latitud < 0 || Utilidades.esDecimal(Float.toString(latitud)) != true) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);

        }

        if (longitud < 0 || Utilidades.esDecimal(Float.toString(longitud)) != true) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        msj = SucursalDAO.agregarSucursal(idEmpresa, nombre, telefono, latitud, longitud);

        return msj;
    }

    @POST
    @Path("registrarUbicacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarUbicacionSucursal(@FormParam("calle") String calle,
            @FormParam("numero") int numero,
            @FormParam("codigoPostal") String codigoPostal,
            @FormParam("ciudad") String ciudad,
            @FormParam("idSucursal") int idSucursal) {
        Mensaje msj = new Mensaje();

        if (calle == null || calle.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (numero <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);

        }

        if (codigoPostal.length() < 5 || codigoPostal.isEmpty() || codigoPostal == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (ciudad == null || ciudad.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        msj = SucursalDAO.agregarUbicacion(calle, numero, codigoPostal, ciudad, idSucursal);

        return msj;
    }

    @PUT
    @Path("actualizarSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje actualizarEmpresa(@FormParam("idSucursal") Integer idSucursal,
            @FormParam("nombre") String nombre,
            @FormParam("telefono") String telefono,
            @FormParam("latitud") float latitud,
            @FormParam("longitud") float longitud) {
        Mensaje msj = new Mensaje();

        if (idSucursal <= 0 || idSucursal == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (nombre == null || nombre.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (telefono == null || telefono.isEmpty() || !Utilidades.validarCadena(telefono, Utilidades.TELEFONO_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (latitud < 0 || Utilidades.esDecimal(Float.toString(latitud)) != true) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);

        }

        if (longitud < 0 || Utilidades.esDecimal(Float.toString(longitud)) != true) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        
        msj = SucursalDAO.actualizarSucursal(idSucursal, nombre, telefono, latitud, longitud);
        
        return msj;
    }
    
    
    @PUT
    @Path("editarUbicacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarUbicacionSucursal(@FormParam("calle") String calle,
            @FormParam("numero") int numero,
            @FormParam("codigoPostal") String codigoPostal,
            @FormParam("ciudad") String ciudad,
            @FormParam("idUicación") int idUbicacion) {
        Mensaje msj = new Mensaje();

        if (calle == null || calle.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (numero <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);

        }

        if (codigoPostal.length() < 5 || codigoPostal.isEmpty() || codigoPostal == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (ciudad == null || ciudad.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        msj = SucursalDAO.agregarUbicacion(calle, numero, codigoPostal, ciudad, idUbicacion);

        return msj;
    }
    
    @DELETE
    @Path("eliminar/{idSucursal}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarSucursal(@PathParam("idSucursal") Integer idSucursal){
        Mensaje msj = new Mensaje();
        if (idSucursal <= 0 || idSucursal == null){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        msj = SucursalDAO.eliminarSucursal(idSucursal);
        
        return msj;
    }
    

}
