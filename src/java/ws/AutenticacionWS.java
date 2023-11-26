package ws;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import modelo.AutenticacionDAO;
import modelo.pojo.Mensaje;

@Path("autenticacion")
public class AutenticacionWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AutenticacionWS
     */
    public AutenticacionWS() {

    }

    @POST
    @Path("iniciarSesionEscritorio")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje iniciarSesionEscritorio(
            @FormParam("userName") String userName,
            @FormParam("contrasenia") String contrasenia) {

        Mensaje mensaje = null;
        if (!userName.isEmpty() && !contrasenia.isEmpty()) {
            mensaje = AutenticacionDAO.iniciarSesionEscritorio(userName, contrasenia);
        } else {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        return mensaje;
    }

}
