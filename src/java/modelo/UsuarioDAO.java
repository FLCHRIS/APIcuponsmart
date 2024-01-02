package modelo;

import java.util.List;
import modelo.pojo.Mensaje;
import modelo.pojo.Roll;
import modelo.pojo.Usuario;
import org.apache.ibatis.session.SqlSession;

public class UsuarioDAO {

    public static Mensaje registrarUsuario(
            Integer idEmpresa, Integer idRollUsuario, String nombre, String apellidoPaterno,
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

                List<Usuario> usuariosExiste = conexionDB.selectList("usuario.buscarRepetido", usuario);

                if (!usuariosExiste.isEmpty()) {
                    msj.setContenido("Ya existe un usuario con el mismo nombre de usuario, correo o CURP.");
                    return msj;
                }

                int filasAfectadas = conexionDB.insert("usuario.registrar", usuario);
                conexionDB.commit();

                if (filasAfectadas != 0) {
                    msj.setError(Boolean.FALSE);
                    msj.setContenido("Se registró correctamente el usuario.");
                } else {
                    msj.setContenido("No se pudo registrar el usuario.");
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

    public static Mensaje editarUsuario(
            String nombre, String apellidoPaterno,
            String apellidoMaterno, String curp,
            String correo, String userName,
            String contrasenia, Integer idUsuario) {

        Mensaje msj = new Mensaje();
        msj.setError(Boolean.TRUE);
        SqlSession conexionDB = mybatis.MyBatisUtil.getSession();
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        usuario.setNombre(nombre);
        usuario.setApellidoPaterno(apellidoPaterno);
        usuario.setApellidoMaterno(apellidoMaterno);
        usuario.setCurp(curp);
        usuario.setCorreo(correo);
        usuario.setUserName(userName);
        usuario.setContrasenia(contrasenia);

        if (conexionDB != null) {
            try {

                List<Usuario> usuariosExiste = conexionDB.selectList("usuario.buscarRepetido", usuario);

                for (Usuario usuarioExistente : usuariosExiste) {
                    if (!usuarioExistente.getIdUsuario().equals(usuario.getIdUsuario())) {
                        msj.setContenido("Ya existe un usuario con el mismo nombre de usuario, correo o CURP.");
                        return msj;
                    }
                }

                int filasAfectadas = conexionDB.update("usuario.editar", usuario);
                conexionDB.commit();

                if (filasAfectadas != 0) {
                    
                    msj.setError(Boolean.FALSE);
                    msj.setContenido("Se actualizó correctamente el usuario.");
                    
                } else {
                    msj.setContenido("No se pudo actualizar el usuario.");
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

    public static Mensaje eliminarUsuario(Integer idUsuario) {
        Mensaje msj = new Mensaje();
        msj.setError(Boolean.TRUE);
        SqlSession conexioBD = mybatis.MyBatisUtil.getSession();

        if (conexioBD != null) {
            try {
                int filasAfectadas = conexioBD.delete("usuario.eliminar", idUsuario);
                conexioBD.commit();

                if (filasAfectadas != 0) {
                    msj.setError(Boolean.FALSE);
                    msj.setContenido("El usuario se eliminó correctamente.");
                } else {
                    msj.setContenido("No se pudo eliminar el usuario.");
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

    public static Mensaje buscarUsuarios(Integer idUsuario) {
        Mensaje msj = new Mensaje();
        msj.setError(Boolean.TRUE);
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                List<Usuario> consulta = conexionBD.selectList("usuario.buscarUsuarios", idUsuario);
                msj.setUsuarios(consulta);
                
                if (!consulta.isEmpty()) {
                    msj.setError(Boolean.FALSE);
                    msj.setContenido("Respuesta exitosa.");
                } else {
                    msj.setContenido("No hay usuarios registrados.");
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

    public static Mensaje buscarRolles() {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);
        SqlSession conexionDB = mybatis.MyBatisUtil.getSession();

        if (conexionDB != null) {
            try {
                List<Roll> listRoll = conexionDB.selectList("usuario.buscarRolles");
                mensaje.setRoles(listRoll);
                
                if (!listRoll.isEmpty()) {
                    mensaje.setError(Boolean.FALSE);
                    mensaje.setContenido("Respuesta exitosa.");
                } else {
                    mensaje.setContenido("No hay roles registrados.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                mensaje.setContenido("Error: " + e);
            } finally {
                conexionDB.close();
            }
        }

        return mensaje;
    }
}
