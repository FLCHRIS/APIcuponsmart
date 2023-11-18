package modelo;

import java.util.HashMap;
import java.util.List;
import modelo.pojo.Empresa;
import modelo.pojo.Mensaje;
import modelo.pojo.RepresentanteLegal;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class EmpresaDAO {

    public static Mensaje obtenerEmpresaPorRFC(String RFC) {
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

    public static Mensaje registrar(
            String nombre, String nombreComercial, String email, String telefono,
            String paginaWeb, String RFC) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        Empresa nuevaEmpresa = new Empresa();
        nuevaEmpresa.setNombreComercial(nombreComercial);
        // nuevaEmpresa.setLogo(logo); , byte[] logo
        nuevaEmpresa.setNombre(nombre);
        nuevaEmpresa.setEmail(email);
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

    public static Mensaje registrarRepresentante(
            String nombre, String apellidoPaterno, String apellidoMaterno, Integer idEmpresa) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        RepresentanteLegal representanteLegal = new RepresentanteLegal();
        representanteLegal.setNombre(nombre);
        representanteLegal.setApellidoPaterno(apellidoPaterno);
        representanteLegal.setApellidoMaterno(apellidoMaterno);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexión a la base de datos");
            }

            int filasRegistroRepresentanteAfectadas = conexionDB.insert("empresa.registrarRepresentanteLegal", representanteLegal);
            int idRepresentanteLegal = representanteLegal.getIdRepresentanteLegal();

            Empresa empresa = new Empresa();
            empresa.setIdEmpresa(idEmpresa);
            empresa.setIdRepresentanteLegal(idRepresentanteLegal);

            int filasRegistroRepresentanteEmpresa = conexionDB.update("empresa.registrarRepresentanteAEmpresa", empresa);
            conexionDB.commit();

            if (filasRegistroRepresentanteAfectadas > 0 && filasRegistroRepresentanteEmpresa > 0) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Representante registrado con éxito");
            } else {
                mensaje.setContenido("No se pudo registrar el representante");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }
        return mensaje;
    }

    public static Mensaje editarRepresentante(
            String nombre, String apellidoPaterno, String apellidoMaterno, Integer idRepresentanteLegal) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        RepresentanteLegal representanteLegal = new RepresentanteLegal();
        representanteLegal.setNombre(nombre);
        representanteLegal.setApellidoPaterno(apellidoPaterno);
        representanteLegal.setApellidoMaterno(apellidoMaterno);
        representanteLegal.setIdRepresentanteLegal(idRepresentanteLegal);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexión a la base de datos");
            }

            int filasAfectadas = conexionDB.update("empresa.editarRepresentanteLegal", representanteLegal);
            conexionDB.commit();

            if (filasAfectadas > 0) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Representante actualizado con éxito");
            } else {
                mensaje.setContenido("No se pudo actualizar el representante");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }
        return mensaje;
    }

    public static Mensaje editarEmpresa(
            String nombre, String nombreComercial, String email, String telefono,
            String paginaWeb, String estatus, Integer idEmpresa) {
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

    public static Mensaje subirLogo(Integer idEmpresa, byte[] logo) {
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

    public static Mensaje buscarEmpresaPorNombre(String nombre) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                List<Empresa> empresas
                        = conexionBD.selectList("empresa.obtenerEmpresaPorNombre", nombre);

                if (!empresas.isEmpty()) {
                    mensaje.setError(false);
                    mensaje.setContenido("Respuesta exitosa");
                    mensaje.setEmpresas(empresas);
                } else {
                    mensaje.setContenido("No se encontro ninguna empresa con el nombre seleccionado");
                }
            } catch (Exception e) {
                mensaje.setContenido("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            mensaje.setContenido("Error: Por el momento no hay conexion en la base de datos");
        }
        return mensaje;
    }
}
