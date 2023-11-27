package modelo;

import java.util.List;
import modelo.pojo.Encargado;
import modelo.pojo.Mensaje;
import modelo.pojo.Sucursal;
import modelo.pojo.Ubicacion;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class SucursalDAO {

    /* 
    ##########################
    ######## SUCURSAL ########
    ##########################  
     */
    public static Mensaje registrarSucursal(
            Integer idEmpresa, String nombre,
            String colonia, String telefono,
            Float longitud, Float latitud) {
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

        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.insert("sucursal.registrar", sucursal);
                conexionBD.commit();
                if (filasAfectadas != 0) {
                    msj.setError(Boolean.FALSE);
                    msj.setContenido("La sucursal " + sucursal.getNombre() + " esta dada de alta");
                } else {
                    msj.setContenido("Error al agregar la sucursal");
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
            Float longitud, Float latitud) {
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

        if (conexionBD != null) {
            try {

                int filasAfectadas = conexionBD.update("sucursal.actualizar", sucursal);
                conexionBD.commit();

                if (filasAfectadas != 0) {
                    msj.setError(Boolean.FALSE);
                    msj.setContenido("Los datos de la empresa se actualizarón correctamente");
                } else {
                    msj.setContenido("No se pudo realizar la actualización" + filasAfectadas);
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
                    msj.setContenido("La sucursal seleccionada se elimino correctamente del sistema");
                } else {
                    msj.setContenido("Ocurrio un error en el proceso de eliminación de esta sucursal");
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
                    msj.setContenido("Respuesta exitosa");
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

    public static Mensaje buscarPorDireccion(String ciudad, String calle, Integer numero) {
        Mensaje msj = new Mensaje();
        msj.setError(Boolean.TRUE);

        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setCiudad(ciudad);
        ubicacion.setCalle(calle);
        ubicacion.setNumero(numero);

        if (conexionBD != null) {
            try {

                List<Sucursal> consultasql = conexionBD.selectList("sucursal.buscarPorDireccion", ubicacion);

                if (!consultasql.isEmpty()) {
                    msj.setSucursales(consultasql);
                    msj.setContenido("Respuesta exitosa");
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

    /* 
    ###########################
    ######## UBICACIÓN ########
    ###########################  
     */
    public static Mensaje registrarUbicacion(
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
                mensaje.setContenido("No hay conexión a la base de datos");
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
                mensaje.setContenido("Ubicación registrada con éxito");
            } else {
                mensaje.setContenido("No se pudo registrar la ubicación");
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
                mensaje.setContenido("No hay conexión a la base de datos");
            }

            int filasAfectadas = conexionDB.update("ubicacion.editar", ubicacion);
            conexionDB.commit();

            if (filasAfectadas > 0) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Ubicación actualizada con éxito");
            } else {
                mensaje.setContenido("No se pudo actualizar la ubicación");
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
                mensaje.setContenido("No hay conexión a la base de datos");
            }

            int filasAfectadas = conexionDB.delete("ubicacion.eliminar", idUbicacion);
            conexionDB.commit();

            if (filasAfectadas > 0) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Ubicación eliminada con éxito");
            } else {
                mensaje.setContenido("No se pudo eliminar la ubicación");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }

        return mensaje;
    }

    /* 
    ###########################
    ######## ENCARGADO ########
    ###########################
     */
    
    public static Mensaje registrarEncargado(
            Integer idSucursal, String nombre, 
            String apellidoPaterno, String apellidoMaterno) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);
        
        Encargado encargado = new Encargado();
        encargado.setNombre(nombre);
        encargado.setApellidoPaterno(apellidoPaterno);
        encargado.setApellidoMaterno(apellidoMaterno);
        
        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexión a la base de datos");
            }

            int filasRegistroEncargadoAfectadas = conexionDB.insert("sucursal.registrarEncargadoSucursal", encargado);
            int IdEncargadoSucursal = encargado.getIdEncargadoSucursal();

            Sucursal sucursal = new Sucursal();
            sucursal.setIdEncargadoSucursal(IdEncargadoSucursal);
            sucursal.setIdSucursal(idSucursal);

            int filasRegistroEncargadoSucursal = conexionDB.update("sucursal.registrarEncargadoASucursal", sucursal);
            conexionDB.commit();

            if (filasRegistroEncargadoAfectadas > 0 && filasRegistroEncargadoSucursal > 0) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Encargado registrado con éxito");
            } else {
                mensaje.setContenido("No se pudo registrar el encargado");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }
        return mensaje;
    }

    public static Mensaje editarEncargado(
            String nombre, String apellidoPaterno, 
            String apellidoMaterno, Integer idEncargadoSucursal) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        Encargado encargado = new Encargado();
        encargado.setNombre(nombre);
        encargado.setApellidoPaterno(apellidoPaterno);
        encargado.setApellidoMaterno(apellidoMaterno);
        encargado.setIdEncargadoSucursal(idEncargadoSucursal);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexión a la base de datos");
            }

            int filasAfectadas = conexionDB.update("sucursal.editarEncargadoSucursal", encargado);
            conexionDB.commit();

            if (filasAfectadas > 0) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Encargado actualizado con éxito");
            } else {
                mensaje.setContenido("No se pudo actualizar el encargado");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }
        return mensaje;
    }

    public static Mensaje eliminarEncargado(Integer idEncargadoSucursal) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexión a la base de datos");
            }

            int filasAfectadas = conexionDB.delete("sucursal.eliminarEncargadoSucursal", idEncargadoSucursal);
            conexionDB.commit();

            if (filasAfectadas > 0) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Encargado eliminado con éxito");
            } else {
                mensaje.setContenido("No se pudo eliminar el encargado");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }

        return mensaje;
    }
}
