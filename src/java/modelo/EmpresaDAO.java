package modelo;

import modelo.pojo.Empresa;
import modelo.pojo.Mensaje;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class EmpresaDAO {
    public static Mensaje registrar(
            String nombreComercial, String nombreRepresentanteLegal, String email, 
            String calle, Integer numero, String codigoPostal, String ciudad, String telefono, 
            String paginaWeb, String RFC) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);
        
        Empresa nuevaEmpresa = new Empresa();
        nuevaEmpresa.setNombreComercial(nombreComercial);
        // nuevaEmpresa.setLogo(logo); , byte[] logo
        nuevaEmpresa.setNombreRepresentanteLegal(nombreRepresentanteLegal);
        nuevaEmpresa.setEmail(email);
        nuevaEmpresa.setCalle(calle);
        nuevaEmpresa.setNumero(numero);
        nuevaEmpresa.setCodigoPostal(codigoPostal);
        nuevaEmpresa.setCiudad(ciudad);
        nuevaEmpresa.setTelefono(telefono);
        nuevaEmpresa.setPaginaWeb(paginaWeb);
        nuevaEmpresa.setRFC(RFC);
        
        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexión a la base de datos");
            }
            
            int filasAfectadas = conexionDB.insert("empresa.registrar", nuevaEmpresa);
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
