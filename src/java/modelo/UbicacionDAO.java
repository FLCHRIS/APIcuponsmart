package modelo;

import modelo.pojo.Empresa;
import modelo.pojo.Mensaje;
import modelo.pojo.Sucursal;
import modelo.pojo.Ubicacion;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class UbicacionDAO {

    public static Mensaje registrarUbicacionEmpresa(
            String calle, 
            Integer numero,
            String codigoPostal,
            String ciudad,
            Integer idEmpresa) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        Ubicacion ubicacionEmpresa = new Ubicacion();
        ubicacionEmpresa.setCalle(calle);
        ubicacionEmpresa.setNumero(numero);
        ubicacionEmpresa.setCodigoPostal(codigoPostal);
        ubicacionEmpresa.setCiudad(ciudad);
        

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexión a la base de datos.");
            }

            int filasRegistroUbicacion = conexionDB.insert("ubicacion.registrar", ubicacionEmpresa);
            int idUbicacion = ubicacionEmpresa.getIdUbicacion();

            Empresa empresa = new Empresa();
            empresa.setIdEmpresa(idEmpresa);
            empresa.setIdUbicacion(idUbicacion);

            int filasRegistroUbicacionEmpresa = conexionDB.update("ubicacion.registrarUbicacionAEmpresa", empresa);
            conexionDB.commit();

            if (filasRegistroUbicacion > 0 && filasRegistroUbicacionEmpresa > 0) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Ubicación registrada con éxito.");
            } else {
                mensaje.setContenido("No se pudo registrar la ubicación.");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }
        return mensaje;
    }

    public static Mensaje registrarUbicacionSucursal(
            String calle, Integer numero, String codigoPostal,
            String ciudad, Integer idSucursal) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        Ubicacion ubicacionEmpresa = new Ubicacion();
        ubicacionEmpresa.setCalle(calle);
        ubicacionEmpresa.setNumero(numero);
        ubicacionEmpresa.setCodigoPostal(codigoPostal);
        ubicacionEmpresa.setCiudad(ciudad);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexión a la base de datos.");
            }

            int filasRegistroUbicacion = conexionDB.insert("ubicacion.registrar", ubicacionEmpresa);
            int idUbicacion = ubicacionEmpresa.getIdUbicacion();

            Sucursal sucursal = new Sucursal();
            sucursal.setIdSucursal(idSucursal);
            sucursal.setIdUbicacion(idUbicacion);

            int filasRegistroUbicacionEmpresa = conexionDB.update("ubicacion.registrarUbicacionASucursal", sucursal);
            conexionDB.commit();

            if (filasRegistroUbicacion > 0 && filasRegistroUbicacionEmpresa > 0) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Ubicación registrada con éxito.");
            } else {
                mensaje.setContenido("No se pudo registrar la ubicación.");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }
        return mensaje;
    }
    
    public static Mensaje editarUbicacion(
            Integer idUbicacion, String calle, Integer numero,
            String codigoPostal, String ciudad) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setCalle(calle);
        ubicacion.setNumero(numero);
        ubicacion.setCodigoPostal(codigoPostal);
        ubicacion.setCiudad(ciudad);
        ubicacion.setIdUbicacion(idUbicacion);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexión a la base de datos.");
            }

            int filasAfectadas = conexionDB.update("ubicacion.editar", ubicacion);
            conexionDB.commit();

            if (filasAfectadas > 0) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Ubicación actualizada con éxito.");
            } else {
                mensaje.setContenido("No se pudo actualizar la ubicación.");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }
        return mensaje;
    }

    public static Mensaje eliminarUbicacion(Integer idUbicacion) {

        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexión a la base de datos.");
            }

            int filasAfectadas = conexionDB.delete("ubicacion.eliminar", idUbicacion);
            conexionDB.commit();

            if (filasAfectadas > 0) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Ubicación eliminada con éxito.");
            } else {
                mensaje.setContenido("No se pudo eliminar la ubicación.");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }

        return mensaje;
    }
    
}
