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
            @FormParam("paginaWeb") String paginaWeb, @FormParam("RFC") String RFC,
            @FormParam("nombreRepresentante") String nombreRepresentante) {

        if (nombreComercial == null || nombreComercial.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (nombre == null || nombre.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (nombreRepresentante == null || nombreRepresentante.isEmpty()) {
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
                telefono, paginaWeb, RFC, nombreRepresentante);

        return mensaje;
    }

    @PUT
    @Path("editarEmpresa")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarEmpresa(
            @FormParam("nombre") String nombre, @FormParam("nombreComercial") String nombreComercial,
            @FormParam("email") String email, @FormParam("telefono") String telefono,
            @FormParam("paginaWeb") String paginaWeb, @FormParam("nombreRepresentante") String nombreRepresentante,
            @FormParam("estatus") String estatus, @FormParam("idEmpresa") Integer idEmpresa) {

        if (idEmpresa == null || idEmpresa <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (nombreComercial == null || nombreComercial.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (nombreRepresentante == null || nombreRepresentante.isEmpty()) {
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
                telefono, paginaWeb, estatus, idEmpresa, nombreRepresentante);

        return mensaje;
    }

    @DELETE
    @Path("eliminarEmpresa")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarEmpresa(
            @FormParam("idEmpresa") Integer idEmpresa) {

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
    @Path("buscarEmpresaPorRepresentante/{nombreRepresentante}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarEmpresaPorRepresentante(
            @PathParam("nombreRepresentante") String nombreRepresentante) {

        if (nombreRepresentante == null || nombreRepresentante.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje mensaje = EmpresaDAO.buscarEmpresaPorRepresentante(nombreRepresentante);

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

    @GET
    @Path("buscarEmpresas")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarEmpresas() {

        return EmpresaDAO.buscarEmpresas();
    }
    
    @GET
    @Path("buscarEmpresa/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarEmpresa(
            @PathParam("idEmpresa") Integer idEmpresa) {

        if (idEmpresa == null || idEmpresa <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        return EmpresaDAO.buscarEmpresa(idEmpresa);
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

    @GET
    @Path("buscarLogoEmpresa/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarLogoEmpresa(
            @PathParam("idEmpresa") Integer idEmpresa) {

        if (idEmpresa == null || idEmpresa <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje mensaje = EmpresaDAO.buscarLogo(idEmpresa);

        return mensaje;
    }

}
