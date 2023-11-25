package modelo;

import java.util.List;
import modelo.pojo.Mensaje;
import modelo.pojo.Usuario;
import org.apache.ibatis.session.SqlSession;

public class UsuarioDAO {
    
    public static Mensaje registrar(Integer idEmpresa, Integer idRollUsuario, String nombre, String apellidoPaterno,
            String apellidoMaterno, String curp, String correo, String userName, String contrasenia) {
        Mensaje msj = new Mensaje();
        msj.setError(Boolean.TRUE);
        SqlSession conexionDB = mybatis.MyBatisUtil.getSession();
        Usuario usuario = new Usuario();
        usuario.setIdEmpresa(idEmpresa);
        usuario.setIdRollUsuario(idRollUsuario);
        usuario.setNombre(nombre);
        usuario.setApellidoPaterno(apellidoPaterno);
        usuario.setApellidoMaterno(apellidoMaterno);
        usuario.setCurp(curp);
        usuario.setCorreo(correo);
        usuario.setUserName(userName);
        usuario.setContrasenia(contrasenia);        
        if (conexionDB != null) {
            try {
                int filasAfectadas = conexionDB.insert("usuario.registrar", usuario);
                conexionDB.commit();
                
                if (filasAfectadas != 0) {
                    msj.setError(Boolean.FALSE);
                    msj.setContenido("El usuario: " + nombre + " se agrego al sistema satisfactoriamente");
                    
                } else {
                    msj.setContenido("El usuario: " + nombre + " No se pudo dar de alta verifique los campos");
                }
            } catch (Exception e) {
                e.printStackTrace();
                msj.setContenido("Error de tipo:" + e);
            } finally {
                conexionDB.close();
            }
            
        }
        
        return msj;
        
    }
    
    public static Mensaje editar(Integer idEmpresa, Integer idUsuario, String nombre, String apellidoPaterno,
            String apellidoMaterno, String curp, String correo, String userName, String contrasenia) {
        Mensaje msj = new Mensaje();
        msj.setError(Boolean.TRUE);
        SqlSession conexionDB = mybatis.MyBatisUtil.getSession();
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        usuario.setIdEmpresa(idEmpresa);
        usuario.setNombre(nombre);
        usuario.setApellidoPaterno(apellidoPaterno);
        usuario.setApellidoMaterno(apellidoMaterno);
        usuario.setCurp(curp);
        usuario.setCorreo(correo);
        usuario.setUserName(userName);
        usuario.setContrasenia(contrasenia);
        
        if (conexionDB != null) {
            try {
                int filasAfectadas = conexionDB.update("usuario.editar", usuario);
                conexionDB.commit();
                
                if (filasAfectadas != 0) {
                    msj.setError(Boolean.FALSE);
                    msj.setContenido("La actualización del usuario: " + nombre + " se hizo correctamente");
                } else {
                    msj.setContenido("Error en la actualización del usuario");
                }
            } catch (Exception e) {
                e.printStackTrace();
                msj.setContenido("Error: " + e);
            } finally {
                conexionDB.close();
            }
            
        }
        
        return msj;
    }
    
    public static Mensaje eliminar(Integer idUsuario) {
        Mensaje msj = new Mensaje();
        msj.setError(Boolean.TRUE);
        SqlSession conexioBD = mybatis.MyBatisUtil.getSession();
        
        if (conexioBD != null) {
            try {
                int filasAfectadas = conexioBD.delete("usuario.eliminar", idUsuario);
                conexioBD.commit();
                
                if (filasAfectadas != 0) {
                    msj.setError(Boolean.FALSE);
                    msj.setContenido("La eliminación del usuario fue satisfactoría");
                } else {
                    msj.setContenido("No se pudo eliminar el usuario seleccionado");
                }
            } catch (Exception e) {
                e.printStackTrace();
                msj.setContenido("Error: " + e);
            } finally {
                conexioBD.close();
            }
            
        }
        
        return msj;
    }
    
    public static Mensaje busquedaPorNombre(String nombre) {
        Mensaje msj = new Mensaje();
        msj.setError(Boolean.TRUE);
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        
        if (conexionBD != null) {
            try {
                List<Usuario> consulta = conexionBD.selectList("usuario.busquedaPorNombre", nombre);
                conexionBD.commit();
                
                if (!consulta.isEmpty()) {
                    msj.setError(Boolean.FALSE);
                    msj.setContenido("Consulta por Nombre existosa");
                    msj.setUsuarios(consulta);
                }else{
                    msj.setContenido("No existen usuarios con ese nombre");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                msj.setContenido("Error: " + e);
            } finally {
                conexionBD.close();
            }
        }
        
        return msj;
    }

    public static Mensaje busquedaPorUserName(String username) {
        Mensaje msj = new Mensaje();
        msj.setError(Boolean.TRUE);
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        
        if (conexionBD != null) {
            try {
                List<Usuario> consulta = conexionBD.selectList("usuario.busquedaPorUserName", username);
                conexionBD.commit();
                
                if (!consulta.isEmpty()) {
                    msj.setError(Boolean.FALSE);
                    msj.setContenido("Consulta por UserName existosa");
                    msj.setUsuarios(consulta);
                }else{
                    msj.setContenido("No existen usuarios con ese nombre de usuario");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                msj.setContenido("Error: " + e);
            } finally {
                conexionBD.close();
            }
        }
        
        return msj;
    }

    public static Mensaje busquedaPorUserName(Integer idRollUsuario) {
        Mensaje msj = new Mensaje();
        msj.setError(Boolean.TRUE);
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        
        if (conexionBD != null) {
            try {
                List<Usuario> consulta = conexionBD.selectList("usuario.busquedaRol", idRollUsuario);
                conexionBD.commit();
                
                if (!consulta.isEmpty()) {
                    msj.setError(Boolean.FALSE);
                    msj.setContenido("Consulta por roll existosa");
                    msj.setUsuarios(consulta);
                }else{
                    msj.setContenido("No existen usuarios con ese Rol dentro del sistema");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                msj.setContenido("Error: " + e);
            } finally {
                conexionBD.close();
            }
        }
        
        return msj;

    }
    
}
