package ws;

import com.google.gson.Gson;
import javax.ws.rs.Consumes;
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
import modelo.PromocionDAO;
import modelo.pojo.Mensaje;
import modelo.pojo.Promocion;
import utils.Constantes;
import utils.Utilidades;

@Path("promociones")
public class PromocionWS {

    @Context
    private UriInfo context;

    public PromocionWS() {
    }

    @POST
    @Path("registrarPromocion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje registrarPromocion(String json) {
        Gson gson = new Gson();
        Promocion promocion = gson.fromJson(json, Promocion.class);

        Mensaje mensaje = null;

        if (promocion == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (promocion.getIdCategoria() == null || promocion.getIdCategoria() <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (promocion.getNombre() == null || promocion.getNombre().isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (promocion.getDescripcion() == null || promocion.getDescripcion().isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        if (Utilidades.validarFechas(promocion.getFechaInicio(), promocion.getFechaFin())) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        if (promocion.getRestriccion() == null || promocion.getRestriccion().isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (promocion.getNoCuponesMaximo() == null || promocion.getNoCuponesMaximo() <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (promocion.getCodigo() == null || !Utilidades.validarCadena(promocion.getCodigo(), Utilidades.CODIGO_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (promocion.getTipoPromocion() == null
                || promocion.getTipoPromocion().isEmpty()
                || (!Constantes.PROMOCION_DESCUENTO.equals(promocion.getTipoPromocion())
                && !Constantes.PROMOCION_REBAJADO.equals(promocion.getTipoPromocion()))) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (promocion.getIdSucursales() == null || promocion.getIdSucursales().isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (promocion.getTipoPromocion().equals(Constantes.PROMOCION_DESCUENTO)) {
            if (promocion.getPorcentajeDescuento() == null || promocion.getPorcentajeDescuento() <= 0) {
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }

            mensaje = PromocionDAO.registrarPromocion(promocion);
        } else {
            if (promocion.getPrecioRebajado() == null || promocion.getPrecioRebajado() <= 0) {
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }

            mensaje = PromocionDAO.registrarPromocion(promocion);
        }

        return mensaje;
    }

    @PUT
    @Path("editarPromocion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarPromocion(String json) {
        Gson gson = new Gson();
        Promocion promocion = gson.fromJson(json, Promocion.class);

        Mensaje mensaje = null;

        if (promocion == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (promocion.getIdPromocion() == null || promocion.getIdPromocion() <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (promocion.getIdCategoria() == null || promocion.getIdCategoria() <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (promocion.getNombre() == null || promocion.getNombre().isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (promocion.getDescripcion() == null || promocion.getDescripcion().isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (Utilidades.validarFechas(promocion.getFechaInicio(), promocion.getFechaFin())) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (promocion.getRestriccion() == null || promocion.getRestriccion().isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (promocion.getNoCuponesMaximo() == null || promocion.getNoCuponesMaximo() <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (promocion.getCodigo() == null || !Utilidades.validarCadena(promocion.getCodigo(), Utilidades.CODIGO_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (promocion.getEstatus() == null || promocion.getEstatus().isEmpty() || (!Constantes.ESTADO_ACTIVO.equals(promocion.getEstatus()) && !Constantes.ESTADO_INACTIVO.equals(promocion.getEstatus()))) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (promocion.getTipoPromocion() == null
                || promocion.getTipoPromocion().isEmpty()
                || (!Constantes.PROMOCION_DESCUENTO.equals(promocion.getTipoPromocion())
                && !Constantes.PROMOCION_REBAJADO.equals(promocion.getTipoPromocion()))) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (promocion.getIdSucursales() == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (promocion.getTipoPromocion().equals(Constantes.PROMOCION_DESCUENTO)) {
            if (promocion.getPorcentajeDescuento() == null || promocion.getPorcentajeDescuento() <= 0) {
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }

            mensaje = PromocionDAO.editarPromocion(promocion);
        } else {
            if (promocion.getPrecioRebajado() == null || promocion.getPrecioRebajado() <= 0) {
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }

            mensaje = PromocionDAO.editarPromocion(promocion);
        }

        return mensaje;
    }

    @DELETE
    @Path("eliminarPromocion")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarPromocion(
            @FormParam("idPromocion") Integer idPromocion) {

        if (idPromocion == null || idPromocion <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        return PromocionDAO.eliminarPromocion(idPromocion);
    }

    @PUT
    @Path("registrarFotografia/{idPromocion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarFotografia(
            @PathParam("idPromocion") Integer idPromocion, byte[] fotografia) {

        if (idPromocion == null || idPromocion <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (fotografia == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        return PromocionDAO.registrarfotografia(idPromocion, fotografia);
    }

    @GET
    @Path("buscarFotografia/{idPromocion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarLogoEmpresa(
            @PathParam("idPromocion") Integer idPromocion) {

        if (idPromocion == null || idPromocion <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        return PromocionDAO.buscarLogo(idPromocion);
    }

    @GET
    @Path("buscarPromociones")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarPromociones() {

        return PromocionDAO.buscarPromociones();
    }

    @GET
    @Path("buscarPromocionesEmpresa/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarPromocionesEmpresa(
            @PathParam("idEmpresa") Integer idEmpresa) {

        if (idEmpresa == null || idEmpresa <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        return PromocionDAO.buscarPromocionesEmpresa(idEmpresa);
    }

    @GET
    @Path("buscarSucursalesEmpresa/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarSucursalesEmpresa(
            @PathParam("idEmpresa") Integer idEmpresa) {

        if (idEmpresa == null || idEmpresa <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        return PromocionDAO.buscarSucursalesEmpresa(idEmpresa);
    }

    @GET
    @Path("buscarCategorias")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarCategorias() {

        return PromocionDAO.buscarCategorias();
    }

    @GET
    @Path("buscarSucursalesValidas/{idPromocion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarSucursalesValidas(
            @PathParam("idPromocion") Integer idPromocion) {

        if (idPromocion == null || idPromocion <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        return PromocionDAO.buscarSucursalesValidas(idPromocion);
    }

    @GET
    @Path("buscarPromocionesPorCategoria/{idCategoria}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarPromocionesPorCategoria(
            @PathParam("idCategoria") Integer idCategoria) {

        if (idCategoria == null || idCategoria < 1) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        return PromocionDAO.buscarPromocionesPorCategoria(idCategoria);
    }

    @GET
    @Path("buscarPromocion/{idPromocion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarPromocion(
            @PathParam("idPromocion") Integer idPromocion) {

        if (idPromocion == null || idPromocion <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        return PromocionDAO.buscarPromocion(idPromocion);
    }
    
    @GET
    @Path("buscarPromocionesPorIdEmpresa/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarPromocionesPorIdEmpresa(
            @PathParam("idEmpresa") Integer idEmpresa) {

        if (idEmpresa == null || idEmpresa <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        return PromocionDAO.buscarPromocionesPorIdEmpresa(idEmpresa);
    }
    
    @GET
    @Path("buscarPromocionesPorFechaFin/{fechaFin}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarPromocionesPorFechaFin(
            @PathParam("fechaFin") String fechaFin) {

        if (fechaFin == null || fechaFin.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        return PromocionDAO.buscarPromocionesPorFechaFin(fechaFin);
    }
    
}
