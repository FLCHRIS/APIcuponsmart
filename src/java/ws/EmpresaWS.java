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
import utils.Constantes;
import utils.Utilidades;

@Path("empresas")
public class EmpresaWS {

    @Context
    private UriInfo context;

    public EmpresaWS() {
    }

    /* 
    #########################
    ######## EMPRESA ########
    #########################    
     */
    @POST
    @Path("registrarEmpresa")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarEmpresa(
            @FormParam("nombreComercial") String nombreComercial, @FormParam("nombre") String nombre,
            @FormParam("email") String email, @FormParam("telefono") String telefono,
            @FormParam("paginaWeb") String paginaWeb, @FormParam("RFC") String RFC) {

        if (nombreComercial == null || nombreComercial.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (nombre == null || nombre.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (email == null || email.isEmpty() || !Utilidades.validarCadena(email, Utilidades.EMAIL_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (telefono == null || telefono.isEmpty() || !Utilidades.validarCadena(telefono, Utilidades.TELEFONO_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (paginaWeb == null || paginaWeb.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (RFC == null || RFC.isEmpty() || !Utilidades.validarCadena(RFC, Utilidades.RFC_EMPRESA_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje mensaje = EmpresaDAO.registrarEmpresa(
                nombre, nombreComercial, email,
                telefono, paginaWeb, RFC);

        return mensaje;
    }

    @PUT
    @Path("editarEmpresa")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarEmpresa(
            @FormParam("nombre") String nombre, @FormParam("nombreComercial") String nombreComercial,
            @FormParam("email") String email, @FormParam("telefono") String telefono,
            @FormParam("paginaWeb") String paginaWeb,
            @FormParam("estatus") String estatus, @FormParam("idEmpresa") Integer idEmpresa) {

        if (idEmpresa == null || idEmpresa <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (nombreComercial == null || nombreComercial.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (nombre == null || nombre.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (email == null || email.isEmpty() || !Utilidades.validarCadena(email, Utilidades.EMAIL_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (telefono == null || telefono.isEmpty() || !Utilidades.validarCadena(telefono, Utilidades.TELEFONO_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (paginaWeb == null || paginaWeb.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (estatus == null || estatus.isEmpty() || (!Constantes.ESTADO_ACTIVO.equals(estatus) && !Constantes.ESTADO_INACTIVO.equals(estatus))) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje mensaje = EmpresaDAO.editarEmpresa(
                nombre, nombreComercial, email,
                telefono, paginaWeb, estatus, idEmpresa);

        return mensaje;
    }

    @DELETE
    @Path("eliminarEmpresa/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarEmpresa(
            @PathParam("idEmpresa") Integer idEmpresa) {

        if (idEmpresa == null || idEmpresa <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje msj = EmpresaDAO.eliminarEmpresa(idEmpresa);
        return msj;
    }

    @GET
    @Path("buscarEmpresaPorNombre/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarEmpresaPorNombre(
            @PathParam("nombre") String nombre) {

        if (nombre == null || nombre.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje mensaje = EmpresaDAO.buscarEmpresaPorNombre(nombre);

        return mensaje;
    }

    @GET
    @Path("buscarEmpresaPorRepresentante/{nombre}/{apellidoPaterno}/{apellidoMaterno}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarEmpresaPorRepresentante(
            @PathParam("nombre") String nombre,
            @PathParam("apellidoPaterno") String apellidoPaterno,
            @PathParam("apellidoMaterno") String apellidoMaterno) {

        if (nombre == null || nombre.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (apellidoPaterno == null || apellidoPaterno.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (apellidoMaterno == null || apellidoMaterno.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje mensaje = EmpresaDAO.buscarEmpresaPorRepresentante(nombre, apellidoPaterno, apellidoMaterno);

        return mensaje;
    }

    @GET
    @Path("buscarEmpresaPorRFC/{RFC}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarEmpresaPorRFC(
            @PathParam("RFC") String RFC) {

        if (RFC == null || RFC.isEmpty() || !Utilidades.validarCadena(RFC, Utilidades.RFC_EMPRESA_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje mensaje = EmpresaDAO.buscarEmpresaPorRFC(RFC);

        return mensaje;
    }

    @PUT
    @Path("registrarLogo/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarLogo(
            @PathParam("idEmpresa") Integer idEmpresa, byte[] logo) {

        if (idEmpresa == null || idEmpresa <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (logo == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje mensaje = EmpresaDAO.registrarLogo(idEmpresa, logo);
        return mensaje;
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
            @FormParam("calle") String calle, @FormParam("numero") Integer numero,
            @FormParam("codigoPostal") String codigoPostal, @FormParam("ciudad") String ciudad,
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

        Mensaje mensaje = EmpresaDAO.registrarUbicacion(
                calle, numero, codigoPostal, ciudad, idEmpresa);

        return mensaje;
    }

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

        Mensaje mensaje = EmpresaDAO.editarUbicacion(calle, numero, codigoPostal, ciudad, idUbicacion);

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

        Mensaje mensaje = EmpresaDAO.eliminarUbicacion(idUbicacion);

        return mensaje;
    }

    /* 
    #####################################
    ######## REPRESENTANTE LEGAL ########
    #####################################
     */
    @POST
    @Path("registrarRepresentante")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarRepresentante(
            @FormParam("nombre") String nombre, @FormParam("apellidoPaterno") String apellidoPaterno,
            @FormParam("apellidoMaterno") String apellidoMaterno, @FormParam("idEmpresa") Integer idEmpresa) {

        if (idEmpresa == null || idEmpresa <= 0) {
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

        Mensaje mensaje = EmpresaDAO.registrarRepresentante(nombre, apellidoPaterno, apellidoMaterno, idEmpresa);

        return mensaje;
    }

    @PUT
    @Path("editarRepresentante")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarRepresentante(
            @FormParam("idRepresentanteLegal") Integer idRepresentanteLegal, @FormParam("nombre") String nombre,
            @FormParam("apellidoPaterno") String apellidoPaterno, @FormParam("apellidoMaterno") String apellidoMaterno) {

        if (idRepresentanteLegal == null || idRepresentanteLegal <= 0) {
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

        Mensaje mensaje = EmpresaDAO.editarRepresentante(
                nombre, apellidoPaterno, apellidoMaterno, idRepresentanteLegal);

        return mensaje;
    }

    @DELETE
    @Path("eliminarRepresentante/{idRepresentanteLegal}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarRepresentante(
            @PathParam("idRepresentanteLegal") Integer idRepresentanteLegal) {

        if (idRepresentanteLegal == null || idRepresentanteLegal <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje mensaje = EmpresaDAO.eliminarRepresentante(idRepresentanteLegal);

        return mensaje;
    }

}
