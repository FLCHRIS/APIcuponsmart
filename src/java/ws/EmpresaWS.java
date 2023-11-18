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
import utils.Utilidades;

@Path("empresas")
public class EmpresaWS {

    @Context
    private UriInfo context;

    public EmpresaWS() {
    }

    @GET
    @Path("buscarEmpresaPorNombre/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
<<<<<<< HEAD
    public Mensaje registroEmpresa(@PathParam("nombre") String nombre) {
        Mensaje mensaje = null;
        if (nombre == null || nombre.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
=======
    public String buscarEmpresaBy_Name_RFC_REPRESENTANTENAME(@PathParam("parametro") String parametro) {

        if (parametro.length() == 8) {
            //validaciones para el tipo de consulta
>>>>>>> 4538332b8b034100f2bf8285d402c088038c3dd7
        }
        mensaje = EmpresaDAO.buscarEmpresaPorNombre(nombre);
        return mensaje;
    }
<<<<<<< HEAD
 
=======

>>>>>>> 4538332b8b034100f2bf8285d402c088038c3dd7
    @GET
    @Path("buscarEmpresaPorRFC/{RFC}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarEmpresaPorRFC(
            @PathParam("RFC") String RFC) {

        if (RFC == null || RFC.isEmpty() || !Utilidades.validarCadena(RFC, Utilidades.RFC_EMPRESA_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje mensaje = EmpresaDAO.obtenerEmpresaPorRFC(RFC);

<<<<<<< HEAD
=======
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

        Mensaje mensaje = EmpresaDAO.obtenerEmpresaPorRepresentante(nombre, apellidoPaterno, apellidoMaterno);

>>>>>>> 4538332b8b034100f2bf8285d402c088038c3dd7
        return mensaje;
    }

    @POST
    @Path("registroEmpresa")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registroEmpresa(
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

        Mensaje mensaje = EmpresaDAO.registrar(
                nombre, nombreComercial, email,
                telefono, paginaWeb, RFC);

        return mensaje;
    }

    @POST
    @Path("registroRepresentante")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registroEmpresa(
            @FormParam("nombre") String nombre, @FormParam("apellidoPaterno") String apellidoPaterno,
            @FormParam("apellidoMaterno") String apellidoMaterno, @FormParam("idEmpresa") Integer idEmpresa) {

        if (idEmpresa == null || idEmpresa <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (nombre == null || nombre.isEmpty()) {
            System.out.println("1");
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (apellidoPaterno == null || apellidoPaterno.isEmpty()) {
            System.out.println("2");
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (apellidoMaterno == null || apellidoMaterno.isEmpty()) {
            System.out.println("3");
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Mensaje mensaje = EmpresaDAO.registrarRepresentante(nombre, apellidoPaterno, apellidoMaterno, idEmpresa);

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

        Mensaje mensaje = EmpresaDAO.subirLogo(idEmpresa, logo);
        return mensaje;
    }

    @PUT
    @Path("edicionRepresentante")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarRepresentanteLegal(
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

    @PUT
    @Path("edicionEmpresa")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje edicionEmpresa(
            @FormParam("nombre") String nombre, @FormParam("nombreComercial") String nombreComercial,
            @FormParam("email") String email, @FormParam("telefono") String telefono,
            @FormParam("paginaWeb") String paginaWeb,
            @FormParam("estatus") String estatus, @FormParam("idEmpresa") Integer idEmpresa) {

        if (idEmpresa == null || idEmpresa <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (nombreComercial == null || nombreComercial.isEmpty()) {
            System.out.println("2");
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (nombre == null || nombre.isEmpty()) {
            System.out.println("3");
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (email == null || email.isEmpty() || !Utilidades.validarCadena(email, Utilidades.EMAIL_PATTERN)) {
            System.out.println("4");
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (telefono == null || telefono.isEmpty() || !Utilidades.validarCadena(telefono, Utilidades.TELEFONO_PATTERN)) {
            System.out.println("5");
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (paginaWeb == null || paginaWeb.isEmpty()) {
            System.out.println("6");
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (estatus == null || estatus.isEmpty() || (!estatus.equals("activo") && !estatus.equals("inactivo"))) {
            System.out.println("8");
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
    public Mensaje eliminarEmpresa(@PathParam("idEmpresa") Integer idEmpresa) {
        Mensaje msj = new Mensaje();
        
        if(idEmpresa == null || idEmpresa<0){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);    
        }else{
            msj = EmpresaDAO.eliminarEmpresa(idEmpresa);
        }
        return msj;
    }

}
