package ws;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
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
    
    /* 
    ##########################
    ######## SUCURSAL ########
    ##########################  
     */

    @POST
    @Path("registrarSucursal")
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

        msj = SucursalDAO.registrarSucursal(idEmpresa, nombre, telefono, latitud, longitud);

        return msj;
    }
    
    @PUT
    @Path("editarSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarSucursal(
            @FormParam("idSucursal") Integer idSucursal,
            @FormParam("nombre") String nombre,
            @FormParam("telefono") String telefono,
            @FormParam("latitud") float latitud,
            @FormParam("longitud") float longitud) {
        Mensaje msj = new Mensaje();

        if (idSucursal == null || idSucursal <= 0) {
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

        msj = SucursalDAO.editarSucursal(idSucursal, nombre, telefono, latitud, longitud);

        return msj;
    }
    
    @DELETE
    @Path("eliminarSucursal/{idSucursal}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarSucursal(@PathParam("idSucursal") Integer idSucursal) {
        Mensaje msj = new Mensaje();
        if (idSucursal <= 0 || idSucursal == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        msj = SucursalDAO.eliminarSucursal(idSucursal);

        return msj;
    }

    @GET
    @Path("buscarPorNombre/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarPorNombre(@PathParam("nombre") String nombre) {
        Mensaje msj = new Mensaje();

        if (nombre == null || nombre.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        msj = SucursalDAO.buscarPorNombre(nombre);
        return msj;
    }

    @GET
    @Path("buscarPorDireccion/{cuidad}/{calle}/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarPorDireccion(@PathParam("cuidad") String cuidad,
            @PathParam("calle") String calle,
            @PathParam("numero") Integer numero) {
        Mensaje msj = new Mensaje();

        if (cuidad == null || cuidad.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);

        }

        if (calle == null || calle.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);

        }

        if (numero <= 0 || numero == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);

        }

        msj = SucursalDAO.buscarPorDireccion(cuidad, calle, numero);
        return msj;
    }
    
    /* 
    ###########################
    ######## UBICACIÓN ########
    ###########################  
     */
    
    @POST
    @Path("registrarUbicacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarUbicacion(@FormParam("calle") String calle,
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

        msj = SucursalDAO.registrarUbicacion(calle, numero, codigoPostal, ciudad, idSucursal);

        return msj;
    }
    
    @PUT
    @Path("editarUbicacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarUbicacion(@FormParam("calle") String calle,
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

        msj = SucursalDAO.editarUbicacion(idUbicacion, calle, numero, codigoPostal, ciudad);

        return msj;
    }
    
    // ELIMINAR UBICACION
    
    /* 
    ###########################
    ######## ENCARGADO ########
    ###########################  
     */
    
    // ELIMINAR, AGREGAR Y EDITAR
}
