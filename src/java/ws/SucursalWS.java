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
    public Mensaje registrarSucursal(
            @FormParam("idEmpresa") Integer idEmpresa, @FormParam("nombre") String nombre,
            @FormParam("colonia") String colonia, @FormParam("telefono") String telefono,
            @FormParam("longitud") Float longitud, @FormParam("latitud") Float latitud) {

        if (idEmpresa == null || idEmpresa <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (nombre == null || nombre.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (colonia == null || colonia.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (telefono == null || telefono.isEmpty() || !Utilidades.validarCadena(telefono, Utilidades.TELEFONO_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (longitud == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (latitud == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje mensaje = SucursalDAO.registrarSucursal(
                idEmpresa, nombre, colonia,
                telefono, longitud, latitud);

        return mensaje;
    }

    @PUT
    @Path("editarSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarSucursal(
            @FormParam("idSucursal") Integer idSucursal,
            @FormParam("idEmpresa") Integer idEmpresa, @FormParam("nombre") String nombre,
            @FormParam("colonia") String colonia, @FormParam("telefono") String telefono,
            @FormParam("longitud") Float longitud, @FormParam("latitud") Float latitud) {

        if (idSucursal == null || idSucursal <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (idEmpresa == null || idEmpresa <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (nombre == null || nombre.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (colonia == null || colonia.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (telefono == null || telefono.isEmpty() || !Utilidades.validarCadena(telefono, Utilidades.TELEFONO_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (longitud == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (latitud == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje msj = SucursalDAO.editarSucursal(
                idSucursal, idEmpresa, nombre,
                colonia, telefono,
                longitud, latitud);

        return msj;
    }

    @DELETE
    @Path("eliminarSucursal/{idSucursal}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarSucursal(@PathParam("idSucursal") Integer idSucursal) {
        if (idSucursal == null || idSucursal <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje msj = SucursalDAO.eliminarSucursal(idSucursal);

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
    public Mensaje registrarUbicacion(
            @FormParam("calle") String calle,
            @FormParam("numero") Integer numero,
            @FormParam("codigoPostal") String codigoPostal,
            @FormParam("ciudad") String ciudad,
            @FormParam("idSucursal") Integer idSucursal) {

        if (idSucursal == null || idSucursal <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (calle == null || calle.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (numero == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (ciudad == null || ciudad.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (codigoPostal == null || codigoPostal.isEmpty() || !Utilidades.validarCadena(codigoPostal, Utilidades.CODIGO_POSTAL_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje mensaje = SucursalDAO.registrarUbicacion(
                calle, numero, codigoPostal, ciudad, idSucursal);

        return mensaje;
    }

    @PUT
    @Path("editarUbicacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarUbicacion(@FormParam("calle") String calle,
            @FormParam("numero") Integer numero,
            @FormParam("codigoPostal") String codigoPostal,
            @FormParam("ciudad") String ciudad,
            @FormParam("idUbicación") Integer idUbicacion) {

        if (idUbicacion == null || idUbicacion <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (calle == null || calle.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (numero == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (ciudad == null || ciudad.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (codigoPostal == null || codigoPostal.isEmpty() || !Utilidades.validarCadena(codigoPostal, Utilidades.CODIGO_POSTAL_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje mensaje = SucursalDAO.editarUbicacion(
                idUbicacion, calle, numero,
                codigoPostal, ciudad);

        return mensaje;
    }

    @DELETE
    @Path("eliminarUbicacion/{idUbicacion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarUbicacion(
            @PathParam("idUbicacion") Integer idUbicacion) {

        if (idUbicacion == null || idUbicacion <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje mensaje = SucursalDAO.eliminarUbicacion(idUbicacion);

        return mensaje;
    }

    /* 
    ###########################
    ######## ENCARGADO ########
    ###########################  
     */
    @POST
    @Path("registrarEncargado")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarEncargado(
            @FormParam("nombre") String nombre, @FormParam("apellidoPaterno") String apellidoPaterno,
            @FormParam("apellidoMaterno") String apellidoMaterno, @FormParam("idSucursal") Integer idSucursal) {

        if (idSucursal == null || idSucursal <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (nombre == null || nombre.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (apellidoPaterno == null || apellidoPaterno.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (apellidoMaterno == null || apellidoMaterno.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje mensaje = SucursalDAO.registrarEncargado(
                idSucursal, nombre, apellidoPaterno, apellidoMaterno);

        return mensaje;
    }

    @PUT
    @Path("editarEncargado")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarEncargado(
            @FormParam("idEncargadoSucursal") Integer idEncargadoSucursal, @FormParam("nombre") String nombre,
            @FormParam("apellidoPaterno") String apellidoPaterno, @FormParam("apellidoMaterno") String apellidoMaterno) {

        if (idEncargadoSucursal == null || idEncargadoSucursal <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (nombre == null || nombre.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (apellidoPaterno == null || apellidoPaterno.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (apellidoMaterno == null || apellidoMaterno.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje mensaje = SucursalDAO.editarEncargado(
                nombre, apellidoPaterno, apellidoMaterno, idEncargadoSucursal);

        return mensaje;
    }
    
    @DELETE
    @Path("eliminarEncargado/{idEncargadoSucursal}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarEncargado(
            @PathParam("idEncargadoSucursal") Integer idEncargadoSucursal) {

        if (idEncargadoSucursal == null || idEncargadoSucursal <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje mensaje = SucursalDAO.eliminarEncargado(idEncargadoSucursal);

        return mensaje;
    }
}
