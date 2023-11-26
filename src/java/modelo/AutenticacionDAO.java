package modelo;

import java.util.HashMap;
import modelo.pojo.Mensaje;
import modelo.pojo.Usuario;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class AutenticacionDAO {

    public static Mensaje iniciarSesionEscritorio(String userName,
            String contrasenia) {

        Mensaje mensaje = new Mensaje();
        mensaje.setError(true);
        
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
                HashMap<String,String> parametros = new HashMap<>();
                parametros.put("userName", userName);
                parametros.put("contrasenia", contrasenia);
                
                Usuario usuario = conexionBD.selectOne("autenticacion.loginEscritorio",parametros);
                
                if(usuario != null){
                    mensaje.setError(false);
                    mensaje.setContenido("Bienvenid(@) "+ usuario.getNombre()+" al sistema de cupones.");
                    mensaje.setUsuarioSesion(usuario);
                }else{
                    mensaje.setContenido("Usuario incorrecto y/o contraseña incorrectos, favor de verificar");
                }
            } catch (Exception e) {
                mensaje.setContenido("Error: " + e.getMessage());
            }finally{
                conexionBD.close();
            }
            
        }else{
            mensaje.setContenido("Error: Por el momento no hay conexion en la base de datos");
        }
        
        return mensaje;
    }

}
