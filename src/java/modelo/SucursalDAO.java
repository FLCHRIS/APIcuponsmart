package modelo;

import modelo.pojo.Mensaje;
import modelo.pojo.Sucursal;
import org.apache.ibatis.session.SqlSession;

public class SucursalDAO {
    
    public static Mensaje agregarSucursal(int idEmpresa, String nombre, String telefono, float latitud, float longitud){
        Mensaje msj = new Mensaje();
        msj.setError(Boolean.TRUE);
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();

        Sucursal sucursal = new Sucursal();
        sucursal.setIdEmpresa(idEmpresa);
        sucursal.setNombre(nombre);
        sucursal.setTelefono(telefono);
        sucursal.setLatitud(latitud);
        sucursal.setLongitud(longitud);
        
        if (conexionBD != null){
            try {
                int filasAfectadas = conexionBD.insert("sucursal.registrar", sucursal);
                conexionBD.commit();
                if (filasAfectadas != 0){
                    msj.setError(Boolean.FALSE);
                    msj.setContenido("La sucursal "+sucursal.getNombre()+" esta dada de alta");
                }else{
                    msj.setContenido("Error al agregar la sucursal");
                }
            } catch (Exception e) {
                    e.printStackTrace();
                    msj.setContenido("Error de compilacion: "+ e);
            }finally{
                conexionBD.close();
            }
        }
        return msj;   
    }
}
