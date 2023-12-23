package ws;

import com.google.gson.Gson;
import java.util.List;
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
    public Mensaje editarPromocion(
            @FormParam("idPromocion") Integer idPromocion, @FormParam("idCategoria") Integer idCategoria,
            @FormParam("nombre") String nombre, @FormParam("descripcion") String descripcion,
            @FormParam("fechaInicio") String fechaInicio, @FormParam("fechaFin") String fechaFin,
            @FormParam("restriccion") String restriccion, @FormParam("tipoPromocion") String tipoPromocion,
            @FormParam("porcentajeDescuento") Float porcentajeDescuento, @FormParam("codigo") String codigo,
            @FormParam("noCuponesMaximo") Integer noCuponesMaximo, @FormParam("estatus") String estatus,
            @FormParam("precioRebajado") Float precioRebajado) {
        Mensaje mensaje = new Mensaje();

        if (idPromocion == null || idPromocion <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (idCategoria == null || idCategoria <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (nombre == null || nombre.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (descripcion == null || descripcion.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        if (Utilidades.validarFechas(fechaInicio, fechaFin)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        if (restriccion == null || restriccion.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (noCuponesMaximo == null || noCuponesMaximo <= 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (codigo == null || !Utilidades.validarCadena(codigo, Utilidades.CODIGO_PATTERN)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (estatus == null || estatus.isEmpty() || (!Constantes.ESTADO_ACTIVO.equals(estatus) && !Constantes.ESTADO_INACTIVO.equals(estatus))) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (tipoPromocion == null || tipoPromocion.isEmpty() || (!Constantes.PROMOCION_DESCUENTO.equals(tipoPromocion) && !Constantes.PROMOCION_REBAJADO.equals(tipoPromocion))) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (tipoPromocion.equals(Constantes.PROMOCION_DESCUENTO)) {
            if (porcentajeDescuento == null || porcentajeDescuento <= 0) {
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }

            mensaje = PromocionDAO.editarPromocion(
                    idPromocion, idCategoria, nombre, descripcion, fechaInicio, fechaFin,
                    restriccion, tipoPromocion, porcentajeDescuento, precioRebajado,
                    noCuponesMaximo, codigo, estatus);
        } else {
            if (precioRebajado == null || precioRebajado <= 0) {
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }

            mensaje = PromocionDAO.editarPromocion(
                    idPromocion, idCategoria, nombre, descripcion, fechaInicio, fechaFin,
                    restriccion, tipoPromocion, porcentajeDescuento, precioRebajado,
                    noCuponesMaximo, codigo, estatus);
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
    @Path("buscarCategorias")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje buscarCategorias() {

        return PromocionDAO.buscarCategorias();
    }

}
