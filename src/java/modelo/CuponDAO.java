package modelo;

import java.util.List;
import modelo.pojo.Mensaje;
import modelo.pojo.Promocion;
import org.apache.ibatis.session.SqlSession;

public class CuponDAO {

    public static Mensaje buscarTodosLosCupones() {
        Mensaje respuesta = new Mensaje();
        respuesta.setError(false);
        SqlSession conexionDB = mybatis.MyBatisUtil.getSession();

        if (conexionDB != null) {
            try {
                List<Promocion> promociones = conexionDB.selectList("cupon.buscarTodosLosCupones");
                if (promociones.size() > 0) {
                    respuesta.setContenido("Exito el la busqueda de los cupones");
                    respuesta.setPromociones(promociones);
                } else {
                    respuesta.setError(true);
                    respuesta.setContenido("Error al obtener los cupones");
                }
            } catch (Exception e) {
                e.printStackTrace();
                respuesta.setError(true);
                respuesta.setContenido("Error:" + e);
            } finally {
                conexionDB.close();
            }
        }
        return respuesta;
    }

    public static Mensaje buscarCuponesComercial(Integer idEmpresa) {
        Mensaje respuesta = new Mensaje();
        respuesta.setError(false);
        SqlSession conexionDB = mybatis.MyBatisUtil.getSession();

        if (conexionDB != null) {
            try {
                List<Promocion> promociones = conexionDB.selectList("cupon.buscarCuponesComercial", idEmpresa);
                if (promociones.size() > 0) {
                    respuesta.setContenido("Exito el la busqueda de los cupones");
                    respuesta.setPromociones(promociones);
                } else {
                    respuesta.setError(true);
                    respuesta.setContenido("Error al obtener los cupones");
                }
            } catch (Exception e) {
                e.printStackTrace();
                respuesta.setError(true);
                respuesta.setContenido("Error:" + e);
            } finally {
                conexionDB.close();
            }
        }
        return respuesta;
    }

    public static Mensaje canjearCupon(String codigo) {
        Mensaje respuesta = new Mensaje();
        respuesta.setError(false);
        SqlSession conexionDB = mybatis.MyBatisUtil.getSession();
        
        if (conexionDB != null){
            try {
                int filasAfectadas = conexionDB.update("cupon.canjearCupon", codigo);
                
                if (filasAfectadas != 0){
                    respuesta.setContenido("Cupon canjeado con exito");
                    conexionDB.commit();
                }else{
                    respuesta.setError(true);
                    respuesta.setContenido("Canje del cupón invalido, verifique el código");
                }
            } catch (Exception e) {
                e.printStackTrace();
                respuesta.setError(true);
                respuesta.setContenido("Error;"+e);
                }finally {
                conexionDB.close();
            }
        }
        
        return respuesta;
    }

}
