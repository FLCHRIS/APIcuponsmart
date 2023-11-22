package modelo;

import java.util.HashMap;
import java.util.List;
import modelo.pojo.Mensaje;
import modelo.pojo.RepresentanteLegal;
import modelo.pojo.Sucursal;
import modelo.pojo.Ubicacion;
import org.apache.ibatis.session.SqlSession;

public class SucursalDAO {

    public static Mensaje buscarPorNombre(String nombre){
        Mensaje msj = new Mensaje();
        msj.setError(Boolean.TRUE);
        
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        
        if (conexionBD != null){
            try {
                List<Sucursal> consulta = conexionBD.selectList("sucursal.buscarPorNombre", nombre);
                
                if (!consulta.isEmpty()){
                msj.setSucursales(consulta);
                msj.setContenido("La sucursal que buscas si existe");
                msj.setError(Boolean.FALSE);
                }else{
                msj.setContenido("Error en la busqueda de la sucursal");
                }
            } catch (Exception e) {
                e.printStackTrace();
                msj.setContenido("Error:"+e);
            }finally{
            conexionBD.close();
            }   
        }   
        return msj;
    }
    
    public static Mensaje buscarPorDireccion(String ciudad, String calle, Integer numero){
        Mensaje msj = new Mensaje();
        msj.setError(Boolean.TRUE);
        
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
       
        
        if (conexionBD != null){
            try {
                HashMap<String, Object> consulta = new HashMap<>();
                consulta.put("cuidad", ciudad);
                consulta.put("calle", calle);
                consulta.put("numero", numero);
                
                List<Sucursal> consultasql = conexionBD.selectList("sucursal.buscarPorDireccion",consulta);
                
                if (!consultasql.isEmpty()){
                msj.setSucursales(consultasql);
                msj.setContenido("Busqueda de sucursal Exitosa");
                msj.setError(Boolean.FALSE);
                }else{
                msj.setContenido("Error en la busqueda de la sucursal por direccion");
                }
            } catch (Exception e) {
                e.printStackTrace();
                msj.setContenido("Error:"+e);
            }finally{
            conexionBD.close();
            }   
        }   
        return msj;
    }
    
    public static Mensaje agregarSucursal(int idEmpresa, String nombre, String telefono, float latitud, float longitud) {
        Mensaje msj = new Mensaje();
        msj.setError(Boolean.TRUE);
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();

        Sucursal sucursal = new Sucursal();
        sucursal.setIdEmpresa(idEmpresa);
        sucursal.setNombre(nombre);
        sucursal.setTelefono(telefono);
        sucursal.setLatitud(latitud);
        sucursal.setLongitud(longitud);

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

    public static Mensaje agregarUbicacion(String calle, int numero, String codigoPostal, String ciudad, int idSucursal) {

        Mensaje msj = new Mensaje();
        msj.setError(Boolean.TRUE);
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setCalle(calle);
        ubicacion.setNumero(numero);
        ubicacion.setCodigoPostal(codigoPostal);
        ubicacion.setCiudad(ciudad);

        if (conexionBD != null) {
            try {

                int filasAfectadas = conexionBD.insert("ubicacion.registrar", ubicacion);

                int idUbicacion = ubicacion.getIdUbicacion();

                Sucursal sucursal = new Sucursal();
                sucursal.setIdSucursal(idSucursal);
                sucursal.setIdUbicacion(idUbicacion);

                int actualizacionSucursal = conexionBD.update("sucursal.actualizarUbicacion", sucursal);
                conexionBD.commit();

                if (filasAfectadas != 0 && actualizacionSucursal != 0) {
                    msj.setError(Boolean.FALSE);
                    msj.setContenido("Se registro la ubicación de la sucursal correctamente");

                } else {
                    msj.setContenido("Hubo un error al querer agregar la ubicación ala sucursal");
                }
            } catch (Exception e) {
                e.printStackTrace();
                msj.setContenido("Error:" + e + "esta es");
            } finally {
                conexionBD.close();
            }
        }

        return msj;
    }

    public static Mensaje actualizarSucursal(int idSucursal, String nombre, String telefono, float latitud, float longitud) {
        Mensaje msj = new Mensaje();
        msj.setError(Boolean.TRUE);
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        Sucursal sucursal = new Sucursal();
        sucursal.setIdSucursal(idSucursal);
        sucursal.setNombre(nombre);
        sucursal.setTelefono(telefono);
        sucursal.setLatitud(latitud);
        sucursal.setLongitud(longitud);

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

    public static Mensaje actualizarUbicacionSucursal(Integer idUbicacion,String calle, Integer numero, String codigoPostal,
            String ciudad, Integer idSucursal) {

        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setCalle(calle);
        ubicacion.setNumero(numero);
        ubicacion.setCodigoPostal(codigoPostal);
        ubicacion.setCiudad(ciudad);
        ubicacion.setIdUbicacion(idUbicacion);

        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();

        if (conexionBD != null) {
            mensaje.setContenido("No hay conexión a la base de datos");

            try {
                int filasAfectadas = conexionBD.update("ubicacion.editar", ubicacion);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    mensaje.setError(Boolean.FALSE);
                    mensaje.setContenido("Ubicación actualizada con éxito");
                } else {
                    mensaje.setContenido("No se pudo actualizar la ubicación");
                }
            } catch (Exception e) {
                mensaje.setContenido("Error: " + e.getMessage());
            }
        }

        return mensaje;
    }

    public static Mensaje eliminarSucursal(Integer idSucursal) {
        Mensaje msj = new Mensaje();
        msj.setError(Boolean.TRUE);
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        
        if (conexionBD != null){
            try {
                
                int filasAfectadas = conexionBD.delete("sucursal.eliminar", idSucursal);
                conexionBD.commit();
                
                if (filasAfectadas != 0){
                    msj.setError(Boolean.FALSE);
                    msj.setContenido("La sucursal seleccionada se elimino correctamente del sistema");
                }else{
                    msj.setContenido("Ocurrio un error en el proceso de eliminación de esta sucursal");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                msj.setContenido("Error de servidor: "+e);
            }finally{
                conexionBD.close();
            
            }
    
        }
        
        return msj;
    }

}
