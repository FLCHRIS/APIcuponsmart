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
import modelo.UsuarioDAO;
import modelo.pojo.Mensaje;
import utils.Constantes;
import utils.Utilidades;

@Path("usuarios")
public class UsuarioWS {

    @Context
    private UriInfo context;

    @POST
    @Path("registrarUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarUsuario(
            @FormParam("nombre") String nombre, @FormParam("apellidoPaterno") String apellidoPaterno,
            @FormParam("apellidoMaterno") String apellidoMaterno, @FormParam("curp") String curp,
            @FormParam("correo") String correo, @FormParam("userName") String userName,
            @FormParam("contrasenia") String contrasenia, @FormParam("idRollUsuario") Integer idRollUsuario,
            @FormParam("idEmpresa") Integer idEmpresa) {
        Mensaje mensaje = null;

        if (nombre == null || nombre.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (apellidoPaterno == null || apellidoPaterno.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (apellidoMaterno == null || apellidoMaterno.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (curp == null || curp.isEmpty() || !Utilidades.validarCadena(curp, Utilidades.CURP_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (correo == null || correo.isEmpty() || !Utilidades.validarCadena(correo, Utilidades.EMAIL_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (userName == null || userName.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (contrasenia == null || contrasenia.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (idRollUsuario == null || (idRollUsuario != Constantes.ID_ROL_COMERCIAL && idRollUsuario != Constantes.ID_ROL_GENERAL)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (idRollUsuario == Constantes.ID_ROL_GENERAL) {
            mensaje = UsuarioDAO.registrarUsuario(
                    idEmpresa, idRollUsuario, nombre, apellidoPaterno,
                    apellidoMaterno, curp, correo, userName, contrasenia);
        } else {
            if (idEmpresa == null || idEmpresa <= 0) {
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }

            mensaje = UsuarioDAO.registrarUsuario(
                    idEmpresa, idRollUsuario, nombre, apellidoPaterno,
                    apellidoMaterno, curp, correo, userName, contrasenia);
        }

        return mensaje;
    }

    @PUT
    @Path("editarUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarUsuario(
            @FormParam("nombre") String nombre, @FormParam("apellidoPaterno") String apellidoPaterno,
            @FormParam("apellidoMaterno") String apellidoMaterno, @FormParam("curp") String curp,
            @FormParam("correo") String correo, @FormParam("userName") String userName,
            @FormParam("contrasenia") String contrasenia, @FormParam("idUsuario") Integer idUsuario) {

        if (idUsuario == null || idUsuario <= 0) {
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

        if (curp == null || curp.isEmpty() || !Utilidades.validarCadena(curp, Utilidades.CURP_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (correo == null || correo.isEmpty() || !Utilidades.validarCadena(correo, Utilidades.EMAIL_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (userName == null || userName.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (contrasenia == null || contrasenia.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje msj = UsuarioDAO.editarUsuario(
                nombre, apellidoPaterno, apellidoMaterno, curp,
                correo, userName, contrasenia, idUsuario);

        return msj;
    }

    @DELETE
    @Path("eliminarUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarUsuario(@FormParam("idUsuario") Integer idUsuario) {
        if (idUsuario == null || idUsuario <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje mensaje = UsuarioDAO.eliminarUsuario(idUsuario);

        return mensaje;
    }
    
    @GET
    @Path("buscarUsuarios/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarUsuarios(
            @PathParam("idUsuario") Integer idUsuario) {
        
        if (idUsuario == null || idUsuario <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        return UsuarioDAO.buscarUsuarios(idUsuario);
    }

    @GET
    @Path("buscarRolles")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarRolles() {
        return UsuarioDAO.buscarRolles();
    }
}
