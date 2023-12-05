package modelo;

import java.util.List;
import modelo.pojo.Mensaje;
import modelo.pojo.Sucursal;
import modelo.pojo.Ubicacion;
import org.apache.ibatis.session.SqlSession;

public class SucursalDAO {

    public static Mensaje registrarSucursal(
            Integer idEmpresa, String nombre,
            String colonia, String telefono,
            Float longitud, Float latitud, String nombreEncargado) {
        Mensaje msj = new Mensaje();
        msj.setError(Boolean.TRUE);
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();

        Sucursal sucursal = new Sucursal();
        sucursal.setIdEmpresa(idEmpresa);
        sucursal.setNombre(nombre);
        sucursal.setColonia(colonia);
        sucursal.setTelefono(telefono);
        sucursal.setLongitud(longitud);
        sucursal.setLatitud(latitud);
        sucursal.setNombreEncargado(nombreEncargado);

        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.insert("sucursal.registrar", sucursal);
                conexionBD.commit();
                if (filasAfectadas != 0) {
                    msj.setError(Boolean.FALSE);
                    msj.setContenido("Sucursal registrada correctamente.");
                } else {
                    msj.setContenido("No se pudo registrar la sucursal.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                msj.setContenido("Error de compilacion: " + e);
            } finally {
                conexionBD.close();
            }
        }
        return msj;
    }

    public static Mensaje editarSucursal(
            Integer idSucursal, Integer idEmpresa, String nombre,
            String colonia, String telefono,
            Float longitud, Float latitud, String nombreEncargado) {
        Mensaje msj = new Mensaje();
        msj.setError(Boolean.TRUE);
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        Sucursal sucursal = new Sucursal();
        sucursal.setIdSucursal(idSucursal);
        sucursal.setIdEmpresa(idEmpresa);
        sucursal.setNombre(nombre);
        sucursal.setColonia(colonia);
        sucursal.setTelefono(telefono);
        sucursal.setLongitud(longitud);
        sucursal.setLatitud(latitud);
        sucursal.setNombreEncargado(nombreEncargado);

        if (conexionBD != null) {
            try {

                int filasAfectadas = conexionBD.update("sucursal.actualizar", sucursal);
                conexionBD.commit();

                if (filasAfectadas != 0) {
                    msj.setError(Boolean.FALSE);
                    msj.setContenido("Sucursal actualizada correctamente.");
                } else {
                    msj.setContenido("No se pudo actualizar la sucursal.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                msj.setContenido("Error:" + e);
            } finally {
                conexionBD.close();
            }

        }

        return msj;
    }

    public static Mensaje eliminarSucursal(Integer idSucursal) {
        Mensaje msj = new Mensaje();
        msj.setError(Boolean.TRUE);
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {

                int filasAfectadas = conexionBD.delete("sucursal.eliminar", idSucursal);
                conexionBD.commit();

                if (filasAfectadas != 0) {
                    msj.setError(Boolean.FALSE);
                    msj.setContenido("Sucursal eliminada correctamente.");
                } else {
                    msj.setContenido("No se pudo eliminar la sucursal.");
                }

            } catch (Exception e) {
                e.printStackTrace();
                msj.setContenido("Error de servidor: " + e);
            } finally {
                conexionBD.close();

            }

        }

        return msj;
    }

    public static Mensaje buscarPorNombre(String nombre) {
        Mensaje msj = new Mensaje();
        msj.setError(Boolean.TRUE);

        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                List<Sucursal> consulta = conexionBD.selectList("sucursal.buscarPorNombre", nombre);

                if (!consulta.isEmpty()) {
                    msj.setSucursales(consulta);
                    msj.setContenido("Respuesta exitosa.");
                    msj.setError(Boolean.FALSE);
                } else {
                    msj.setContenido("No hay sucursales con el nombre proporcionado.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                msj.setContenido("Error:" + e);
            } finally {
                conexionBD.close();
            }
        }
        return msj;
    }

    public static Mensaje buscarPorDireccion(String calle, Integer numero) {
        Mensaje msj = new Mensaje();
        msj.setError(Boolean.TRUE);

        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setCalle(calle);
        ubicacion.setNumero(numero);

        if (conexionBD != null) {
            try {

                List<Sucursal> consultasql = conexionBD.selectList("sucursal.buscarPorDireccion", ubicacion);

                if (!consultasql.isEmpty()) {
                    msj.setSucursales(consultasql);
                    msj.setContenido("Respuesta exitosa.");
                    msj.setError(Boolean.FALSE);
                } else {
                    msj.setContenido("No hay sucursales con la dirección proporcionada.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                msj.setContenido("Error:" + e);
            } finally {
                conexionBD.close();
            }
        }
        return msj;
    }

    public static Mensaje buscarSucursales() {
        Mensaje msj = new Mensaje();
        msj.setError(Boolean.TRUE);

        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {

                List<Sucursal> sucursales = conexionBD.selectList("sucursal.buscarSucursales");

                if (!sucursales.isEmpty()) {
                    msj.setSucursales(sucursales);
                    msj.setContenido("Respuesta exitosa.");
                    msj.setError(Boolean.FALSE);
                } else {
                    msj.setContenido("No hay sucursales registradas.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                msj.setContenido("Error:" + e);
            } finally {
                conexionBD.close();
            }
        }
        return msj;
    }
    
}
