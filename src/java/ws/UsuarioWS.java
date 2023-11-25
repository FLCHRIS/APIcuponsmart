package ws;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import modelo.UsuarioDAO;
import modelo.pojo.Mensaje;
import utils.Utilidades;

@Path("usuarios")
public class UsuarioWS {

    @Context
    private UriInfo context;

    @POST
    @Path("registrar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrar(@FormParam("idEmpresa") Integer idEmpresa,
            @FormParam("idRollUsuario") Integer idRollUsuario,
            @FormParam("nombre") String nombre,
            @FormParam("apellidoPaterno") String apellidoPaterno,
            @FormParam("apellidoMaterno") String apellidoMaterno,
            @FormParam("curp") String curp,
            @FormParam("correo") String correo,
            @FormParam("userName") String userName,
            @FormParam("contrasenia") String contrasenia) {
        Mensaje msj = new Mensaje();

        if (idEmpresa == null || idEmpresa <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (idRollUsuario == null || idRollUsuario <= 0) {
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

        if (curp == null || curp.isEmpty() || curp.length() != 18) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);

        }
        
        if (correo == null || correo.isEmpty() || !Utilidades.validarCadena(correo, Utilidades.EMAIL_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        if (userName == null || userName.isEmpty()){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        if (contrasenia == null || contrasenia.isEmpty()){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        msj = UsuarioDAO.registrar(idEmpresa, idRollUsuario, nombre, apellidoPaterno, apellidoMaterno, curp, correo, userName, contrasenia);

        return msj;
    }
    
    @PUT
    @Path("editar")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editar(@FormParam("idEmpresa") Integer idEmpresa,
            @FormParam("idUsuario") Integer idUsuario,
            @FormParam("nombre") String nombre,
            @FormParam("apellidoPaterno") String apellidoPaterno,
            @FormParam("apellidoMaterno") String apellidoMaterno,
            @FormParam("curp") String curp,
            @FormParam("correo") String correo,
            @FormParam("userName") String userName,
            @FormParam("contrasenia") String contrasenia){
        Mensaje msj = new Mensaje();
        
        if (idEmpresa == null || idEmpresa <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

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

        if (curp == null || curp.isEmpty() || curp.length() != 18) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);

        }
        
        if (correo == null || correo.isEmpty() || !Utilidades.validarCadena(correo, Utilidades.EMAIL_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        if (userName == null || userName.isEmpty()){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        if (contrasenia == null || contrasenia.isEmpty()){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        msj = UsuarioDAO.editar(idEmpresa,idUsuario,nombre, apellidoPaterno, apellidoMaterno, curp, correo, userName, contrasenia);
        
        return msj;
    }

}
