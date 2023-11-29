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

    @POST
    @Path("registrarSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarSucursal(
            @FormParam("idEmpresa") Integer idEmpresa, @FormParam("nombre") String nombre,
            @FormParam("colonia") String colonia, @FormParam("telefono") String telefono,
            @FormParam("longitud") Float longitud, @FormParam("latitud") Float latitud, 
            @FormParam("nombreEncargado") String nombreEncargado) {

        if (idEmpresa == null || idEmpresa <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (nombre == null || nombre.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        if (nombreEncargado == null || nombreEncargado.isEmpty()) {
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
                telefono, longitud, latitud, nombreEncargado);

        return mensaje;
    }

    @PUT
    @Path("editarSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarSucursal(
            @FormParam("idSucursal") Integer idSucursal,
            @FormParam("idEmpresa") Integer idEmpresa, @FormParam("nombre") String nombre,
            @FormParam("colonia") String colonia, @FormParam("telefono") String telefono,
            @FormParam("longitud") Float longitud, @FormParam("latitud") Float latitud,
            @FormParam("nombreEncargado") String nombreEncargado) {

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
        
        if (nombreEncargado == null || nombreEncargado.isEmpty()) {
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
                longitud, latitud, nombreEncargado);

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
        if (nombre == null || nombre.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje msj = SucursalDAO.buscarPorNombre(nombre);
        return msj;
    }

    @GET
    @Path("buscarPorDireccion/{calle}/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarPorDireccion(
            @PathParam("calle") String calle,
            @PathParam("numero") Integer numero) {

        if (calle == null || calle.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);

        }

        if (numero <= 0 || numero == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);

        }

        Mensaje msj = SucursalDAO.buscarPorDireccion(calle, numero);
        return msj;
    }


}
