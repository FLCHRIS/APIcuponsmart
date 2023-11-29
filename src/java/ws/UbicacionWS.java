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
import modelo.UbicacionDAO;
import modelo.pojo.Mensaje;
import utils.Utilidades;

@Path("ubicacion")
public class UbicacionWS {

    @Context
    private UriInfo context;

    public UbicacionWS() {
    }

    /* 
    ######################################################
    ######## registrar ubicación Empresa/sucursal ########
    ######################################################
     */
    @POST
    @Path("registrarUbicacionEmpresa")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarUbicacionEmpresa(
            @FormParam("calle") String calle, 
            @FormParam("numero") Integer numero,
            @FormParam("codigoPostal") String codigoPostal,
            @FormParam("ciudad") String ciudad,
            @FormParam("idEmpresa") Integer idEmpresa) {

        if (idEmpresa == null || idEmpresa <= 0) {
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

        Mensaje mensaje = UbicacionDAO.registrarUbicacionEmpresa(
                calle, numero, codigoPostal, ciudad, idEmpresa);

        return mensaje;
    }

    @POST
    @Path("registrarUbicacionSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarUbicacionSucursal(
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

        Mensaje mensaje = UbicacionDAO.registrarUbicacionSucursal(
                calle, numero, codigoPostal, ciudad, idSucursal);

        return mensaje;
    }

    /* 
    ######################################################
    ######## editar/eliminar ubicación  ##################
    ######################################################
     */
    @PUT
    @Path("editarUbicacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarUbicacion(
            @FormParam("calle") String calle, @FormParam("numero") Integer numero,
            @FormParam("codigoPostal") String codigoPostal, @FormParam("ciudad") String ciudad,
            @FormParam("idUbicacion") Integer idUbicacion) {

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

        Mensaje mensaje = UbicacionDAO.editarUbicacion(idUbicacion, calle, numero, codigoPostal, ciudad);

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

        Mensaje mensaje = UbicacionDAO.eliminarUbicacion(idUbicacion);

        return mensaje;
    }

}
