package modelo;

import modelo.pojo.Cliente;
import modelo.pojo.Mensaje;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class ClienteDAO {

    public static Mensaje registrarCliente(
            String nombre, String apellidoPaterno, String apellidoMaterno, 
            String telefono, String correo, String calle, Integer numero, 
            String contrasenia, String fechaNacimiento) {
        
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);
        
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellidoPaterno(apellidoPaterno);
        cliente.setApellidoMaterno(apellidoMaterno);
        cliente.setTelefono(telefono);
        cliente.setCorreo(correo);
        cliente.setCalle(calle);
        cliente.setNumero(numero);
        cliente.setContrasenia(contrasenia);
        cliente.setFechaNacimiento(fechaNacimiento);
        
        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexión a la base de datos");
            }

            int filasAfectadas = conexionDB.insert("cliente.registrar", cliente);
            conexionDB.commit();

            if (filasAfectadas > 0) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Cliente registrado con éxito");
            } else {
                mensaje.setContenido("No se pudo registrar el cliente");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }
        
        return mensaje;
    }
    
    public static Mensaje actualizarCliente(
            String nombre, String apellidoPaterno, String apellidoMaterno, 
            String telefono, String calle, Integer numero, 
            String contrasenia, String fechaNacimiento, Integer idCliente) {
        
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);
        
        Cliente cliente = new Cliente();
        cliente.setIdCliente(idCliente);
        cliente.setNombre(nombre);
        cliente.setApellidoPaterno(apellidoPaterno);
        cliente.setApellidoMaterno(apellidoMaterno);
        cliente.setTelefono(telefono);
        cliente.setCalle(calle);
        cliente.setNumero(numero);
        cliente.setContrasenia(contrasenia);
        cliente.setFechaNacimiento(fechaNacimiento);
        
        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexión a la base de datos");
            }

            int filasAfectadas = conexionDB.insert("cliente.actualizar", cliente);
            conexionDB.commit();

            if (filasAfectadas > 0) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Cliente actualizado con éxito");
            } else {
                mensaje.setContenido("No se pudo actualizar el cliente");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }
        
        return mensaje;
    }
}
