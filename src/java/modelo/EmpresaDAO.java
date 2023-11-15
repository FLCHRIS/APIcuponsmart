/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import modelo.pojo.Mensaje;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Oscar
 */
public class EmpresaDAO {
    public static Mensaje registrar() {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);
        
        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexión a la base de datos");
            }
            
            int filasAfectadas = conexionDB.insert("empresa.registrar", "XXX");
            conexionDB.commit();
            
            if (filasAfectadas > 0) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Empresa registrada con éxito");
            } else {
                mensaje.setContenido("No se pudo registrar la empresa");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }
        return mensaje;
    }
}
