package modelo;

import java.util.HashMap;
import java.util.List;
import modelo.pojo.Empresa;
import modelo.pojo.Mensaje;
import modelo.pojo.Ubicacion;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class EmpresaDAO {

    public static Mensaje registrarEmpresa(
            String nombre, String nombreComercial, String email, String telefono,
            String paginaWeb, String RFC, String nombreRepresentante) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        Empresa nuevaEmpresa = new Empresa();
        nuevaEmpresa.setNombreComercial(nombreComercial);
        nuevaEmpresa.setNombre(nombre);
        nuevaEmpresa.setEmail(email);
        nuevaEmpresa.setTelefono(telefono);
        nuevaEmpresa.setPaginaWeb(paginaWeb);
        nuevaEmpresa.setRFC(RFC);
        nuevaEmpresa.setNombreRepresentante(nombreRepresentante);

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

    public static Mensaje editarEmpresa(
            String nombre, String nombreComercial, String email, String telefono,
            String paginaWeb, String estatus, Integer idEmpresa, String nombreRepresentante) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        HashMap<String, Object> editarEmpresa = new HashMap<>();
        editarEmpresa.put("idEmpresa", idEmpresa);
        editarEmpresa.put("nombre", nombre);
        editarEmpresa.put("nombreComercial", nombreComercial);
        editarEmpresa.put("email", email);
        editarEmpresa.put("telefono", telefono);
        editarEmpresa.put("paginaWeb", paginaWeb);
        editarEmpresa.put("estatus", estatus);
        editarEmpresa.put("nombreRepresentante", nombreRepresentante);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexión a la base de datos");
            }

            int filasAfectadas = conexionDB.update("empresa.editarEmpresa", editarEmpresa);
            conexionDB.commit();

            if (filasAfectadas > 0) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Empresa actualizada con éxito");
            } else {
                mensaje.setContenido("No se pudo actualizar la empresa");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }
        return mensaje;
    }

    public static Mensaje eliminarEmpresa(Integer idEmpresa) {
        Mensaje msj = new Mensaje();
        msj.setError(Boolean.TRUE);
        SqlSession conexinBD = mybatis.MyBatisUtil.getSession();

        if (conexinBD != null) {
            try {
                int numeroAfectadas = conexinBD.delete("empresa.eliminar", idEmpresa);
                conexinBD.commit();
                if (numeroAfectadas != 0) {
                    msj.setError(Boolean.FALSE);
                    msj.setContenido("Empresa eliminada con éxito");

                } else {
                    msj.setContenido("Hubo un error en la operación de eliminar la empresa");
                }
            } catch (Exception e) {
                e.printStackTrace();
                msj.setContenido("Erroe de tipo: " + e);
            } finally {
                conexinBD.close();
            }
        }
        return msj;
    }

    public static Mensaje buscarEmpresaPorNombre(String nombre) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexion con la base de datos");
                return mensaje;
            }

            List<Empresa> empresas = conexionDB.selectList("empresa.obtenerEmpresaPorNombre", nombre);
            mensaje.setEmpresas(empresas);

            if (!empresas.isEmpty()) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Respuesta exitosa");
            } else {
                mensaje.setContenido("No hay empresas con el nombre proporcionado.");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }

        return mensaje;
    }

    public static Mensaje buscarEmpresaPorRepresentante(
            String nombreRepresentante) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexion con la base de datos");
                return mensaje;
            }

            List<Empresa> empresas = conexionDB.selectList("empresa.obtenerEmpresaPorRepresentante", nombreRepresentante);
            mensaje.setEmpresas(empresas);

            if (!empresas.isEmpty()) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Respuesta exitosa");
            } else {
                mensaje.setContenido("No hay empresas con el representante proporcionado.");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }

        return mensaje;
    }

    public static Mensaje buscarEmpresaPorRFC(String RFC) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexion con la base de datos");
                return mensaje;
            }

            List<Empresa> empresas = conexionDB.selectList("empresa.obtenerEmpresaPorRFC", RFC);
            mensaje.setEmpresas(empresas);

            if (!empresas.isEmpty()) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Respuesta exitosa");
            } else {
                mensaje.setContenido("No hay empresas con el RFC proporcionado.");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }

        return mensaje;
    }

    public static Mensaje registrarLogo(Integer idEmpresa, byte[] logo) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                Empresa empresa = new Empresa();
                empresa.setIdEmpresa(idEmpresa);
                empresa.setLogo(logo);

                int filasAfectadas = conexionBD.update("empresa.subirLogo", empresa);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    mensaje.setError(false);
                    mensaje.setContenido("Logo de la empresa guardado correctamente");
                } else {
                    mensaje.setContenido("Hubo un error al intentar guardar el logo de la empresa");
                }
            } catch (Exception e) {
                mensaje.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setContenido("Lo sentimos, no hay conexión para guardar el logo.");
        }

        return mensaje;
    }

}
