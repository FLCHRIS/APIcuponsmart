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
import modelo.ClienteDAO;
import modelo.pojo.Mensaje;
import utils.Utilidades;

@Path("clientes")
public class ClienteWS {

    @Context
    private UriInfo context;

    public ClienteWS() {
    }

    @POST
    @Path("registrarCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarCliente(
            @FormParam("nombre") String nombre, @FormParam("apellidoPaterno") String apellidoPaterno,
            @FormParam("apellidoMaterno") String apellidoMaterno, @FormParam("telefono") String telefono,
            @FormParam("correo") String correo, @FormParam("calle") String calle,
            @FormParam("numero") Integer numero, @FormParam("contrasenia") String contrasenia,
            @FormParam("fechaNacimiento") String fechaNacimiento) {

        if (nombre == null || nombre.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (apellidoPaterno == null || apellidoPaterno.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (apellidoMaterno == null || apellidoMaterno.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (correo == null || correo.isEmpty() || !Utilidades.validarCadena(correo, Utilidades.EMAIL_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (telefono == null || telefono.isEmpty() || !Utilidades.validarCadena(telefono, Utilidades.TELEFONO_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (calle == null || calle.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (numero == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (contrasenia == null || contrasenia.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (fechaNacimiento == null || fechaNacimiento.isEmpty() || !Utilidades.validarCadena(fechaNacimiento, Utilidades.FECHA_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje mensaje = ClienteDAO.registrarCliente(
                nombre, apellidoPaterno, apellidoMaterno,
                telefono, correo, calle, numero,
                contrasenia, fechaNacimiento);

        return mensaje;
    }

    @PUT
    @Path("editarCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarCliente(
            @FormParam("idCliente") Integer idCliente, @FormParam("nombre") String nombre,
            @FormParam("apellidoPaterno") String apellidoPaterno, @FormParam("calle") String calle,
            @FormParam("apellidoMaterno") String apellidoMaterno, @FormParam("telefono") String telefono,
            @FormParam("numero") Integer numero, @FormParam("contrasenia") String contrasenia,
            @FormParam("fechaNacimiento") String fechaNacimiento) {

        if (idCliente == null || idCliente <= 0) {
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

        if (telefono == null || telefono.isEmpty() || !Utilidades.validarCadena(telefono, Utilidades.TELEFONO_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (calle == null || calle.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (numero == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (contrasenia == null || contrasenia.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (fechaNacimiento == null || fechaNacimiento.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje mensaje = ClienteDAO.editarCliente(
                nombre, apellidoPaterno, apellidoMaterno,
                telefono, calle, numero, contrasenia, fechaNacimiento, idCliente);

        return mensaje;
    }

}
