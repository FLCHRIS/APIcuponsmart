package modelo;

import java.util.HashMap;
import java.util.List;
import modelo.pojo.Categoria;
import modelo.pojo.Mensaje;
import modelo.pojo.Promocion;
import modelo.pojo.Sucursal;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class PromocionDAO {

    public static Mensaje registrarPromocion(Promocion promocion) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexión a la base de datos.");
                return mensaje;
            }
            
            Promocion codigoExiste = conexionDB.selectOne("promocion.buscarCodigo", promocion.getCodigo());
            if (codigoExiste != null) {
                mensaje.setContenido("No se puede registrar porque el código ya existe.");
                return mensaje;
            }
            
            int filasAfectadas = conexionDB.insert("promocion.registrar", promocion);
            
            for (int idSucursal : promocion.getIdSucursales()) {
                HashMap<String, Object> parametros = new HashMap<>();
                parametros.put("idPromocion", promocion.getIdPromocion());
                parametros.put("idSucursal", idSucursal);
                
                int filasAfectadasSucursal = conexionDB.insert("promocion.registrarPromocionSucursal", parametros);
                if (filasAfectadasSucursal <= 0) {
                    mensaje.setContenido("No se pudieron registrar todas las sucursales.");
                    return mensaje;
                }
            }
            conexionDB.commit();
            
            if (filasAfectadas > 0) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Promoción registrada con éxito.");
            } else {
                mensaje.setContenido("No se pudo registrar la promoción.");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }

        return mensaje;
    }

    public static Mensaje eliminarPromocion(Integer idPromocion) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexion con la base de datos.");
                return mensaje;
            }

            int promocionSucursalEliminada = conexionDB.delete("promocion.eliminarPromocionSucursal", idPromocion);
            int promocionEliminada = conexionDB.delete("promocion.eliminar", idPromocion);
            conexionDB.commit();

            if (promocionEliminada != 0 || promocionSucursalEliminada != 0) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Promocion eliminada con éxito.");
            } else {
                mensaje.setContenido("Hubo un error en la operación de eliminar la promocion.");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }

        return mensaje;
    }

    public static Mensaje editarPromocion(
            Integer idPromocion, Integer idCategoria, String nombre, String descripcion, String fechaInicio,
            String fechaFin, String restriccion, String tipoPromocion, Float porcentajeDescuento,
            Float precioRebajado, Integer noCuponesMaximo, String codigo, String estatus) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        Promocion promocion = new Promocion();
        promocion.setIdPromocion(idPromocion);
        promocion.setIdCategoria(idCategoria);
        promocion.setNombre(nombre);
        promocion.setDescripcion(descripcion);
        promocion.setFechaInicio(fechaInicio);
        promocion.setFechaFin(fechaFin);
        promocion.setRestriccion(restriccion);
        promocion.setTipoPromocion(tipoPromocion);
        promocion.setPorcentajeDescuento(porcentajeDescuento);
        promocion.setPrecioRebajado(precioRebajado);
        promocion.setNoCuponesMaximo(noCuponesMaximo);
        promocion.setCodigo(codigo);
        promocion.setEstatus(estatus);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexión a la base de datos.");
                return mensaje;
            }

            Promocion codigoExiste = conexionDB.selectOne("promocion.buscarCodigo", codigo);

            if (codigoExiste != null && !codigoExiste.getIdPromocion().equals(promocion.getIdPromocion())) {
                mensaje.setContenido("No se puede editar porque el código ya existe para otra promoción.");
                return mensaje;
            }

            int filasAfectadas = conexionDB.update("promocion.editar", promocion);
            conexionDB.commit();

            if (filasAfectadas > 0) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Promoción actualizada con éxito.");
            } else {
                mensaje.setContenido("No se pudo actualizar la promoción.");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }

        return mensaje;
    }

    public static Mensaje buscarPromociones() {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexion con la base de datos.");
                return mensaje;
            }
            List<Promocion> promociones = conexionDB.selectList("promocion.buscarPromociones");
            mensaje.setPromociones(promociones);

            if (!promociones.isEmpty()) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Respuesta exitosa");
            } else {
                mensaje.setContenido("No hay promociones registradas.");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }

        return mensaje;
    }
    
    public static Mensaje buscarPromocionesEmpresa(Integer idEmpresa) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexion con la base de datos.");
                return mensaje;
            }
            List<Promocion> promociones = conexionDB.selectList("promocion.buscarPromocionesEmpresa", idEmpresa);
            mensaje.setPromociones(promociones);

            if (!promociones.isEmpty()) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Respuesta exitosa");
            } else {
                mensaje.setContenido("No hay promociones registradas.");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }

        return mensaje;
    }

    public static Mensaje registrarfotografia(Integer idPromocion, byte[] fotografia) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                Promocion promocion = new Promocion();
                promocion.setIdPromocion(idPromocion);
                promocion.setFotografia(fotografia);

                int filasAfectadas = conexionBD.update("promocion.subirFotografia", promocion);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setContenido("Fotografía de la promoción guardada correctamente.");
                } else {
                    mensaje.setContenido("Hubo un error al intentar guardar la fotografía de la promoción.");
                }
            } catch (Exception e) {
                mensaje.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setContenido("Lo sentimos, no hay conexión para guardar la fotografía.");
        }

        return mensaje;
    }

    public static Mensaje buscarLogo(Integer idPromocion) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexion con la base de datos.");
                return mensaje;
            }

            Promocion promocion = conexionDB.selectOne("promocion.obtenerFotografia", idPromocion);
            mensaje.setPromocion(promocion);

            if (promocion != null) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Respuesta exitosa.");
            } else {
                mensaje.setContenido("La promoción no tiene una fotografía registrada.");
            }

        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }

        return mensaje;
    }

    public static Mensaje buscarCategorias() {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexion con la base de datos.");
                return mensaje;
            }

            List<Categoria> categorias = conexionDB.selectList("promocion.buscarCategorias");
            mensaje.setCategorias(categorias);

            if (!categorias.isEmpty()) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Respuesta exitosa");
            } else {
                mensaje.setContenido("No hay categorias registradas.");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }

        return mensaje;
    }
    
    public static Mensaje buscarSucursalesEmpresa(Integer idEmpresa) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);
        
        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexion con la base de datos.");
                return mensaje;
            }
            
            List<Sucursal> sucursales = conexionDB.selectList("promocion.buscarSucursalesEmpresa", idEmpresa);
            mensaje.setSucursales(sucursales);
            
            if (!sucursales.isEmpty()) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Respuesta exitosa");
            } else {
                mensaje.setContenido("No hay sucursales registradas.");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }
        
        return mensaje;
    }

}
