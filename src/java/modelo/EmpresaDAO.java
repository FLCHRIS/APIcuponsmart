package modelo;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import modelo.pojo.Empresa;
import modelo.pojo.Mensaje;
import modelo.pojo.Sucursal;
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
                mensaje.setContenido("No hay conexión a la base de datos.");
            }

            int filasAfectadas = conexionDB.insert("empresa.registrar", nuevaEmpresa);
            conexionDB.commit();

            if (filasAfectadas > 0) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Empresa registrada con éxito.");
            } else {
                mensaje.setContenido("No se pudo registrar la empresa.");
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
                mensaje.setContenido("No hay conexión a la base de datos.");
            }

            int filasAfectadas = conexionDB.update("empresa.editarEmpresa", editarEmpresa);
            conexionDB.commit();

            if (filasAfectadas > 0) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Empresa actualizada con éxito.");
            } else {
                mensaje.setContenido("No se pudo actualizar la empresa.");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }
        return mensaje;
    }

    public static Mensaje eliminarEmpresa(Integer idEmpresa, Integer idUbicacion) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexion con la base de datos.");
                return mensaje;
            }

            List<Sucursal> sucursales = conexionDB.selectList("sucursal.buscarSucursales");

            // OBTIENE EN sucursalesAsociadas TODAS LAS SUCURSALES ASOCIADAS A LA EMPRESA HACIENDO UN FILTRADO
            List<Sucursal> sucursalesAsociadas = sucursales.stream()
                    .filter(sucursal -> sucursal.getIdEmpresa().equals(idEmpresa))
                    .collect(Collectors.toList());

            if (!sucursalesAsociadas.isEmpty()) {
                mensaje.setContenido("No se puede eliminar la empresa porque tiene sucursales asociadas.");
                return mensaje;
            }

            int usuariosEliminados = conexionDB.delete("usuario.eliminarUsuariosPorEmpresa", idEmpresa);
            int numeroAfectadas = conexionDB.delete("empresa.eliminar", idEmpresa);
            int ubicacionEliminada = conexionDB.delete("ubicacion.eliminar", idUbicacion);
            conexionDB.commit();

            if (numeroAfectadas != 0 || usuariosEliminados != 0 || ubicacionEliminada != 0) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Empresa eliminada con éxito.");
            } else {
                mensaje.setContenido("Hubo un error en la operación de eliminar la empresa.");
            }
        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }

        return mensaje;
    }

    public static Mensaje buscarEmpresas() {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexion con la base de datos.");
                return mensaje;
            }

            List<Empresa> empresas = conexionDB.selectList("empresa.obtenerEmpresas");
            mensaje.setEmpresas(empresas);

            if (!empresas.isEmpty()) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Respuesta exitosa");
            } else {
                mensaje.setContenido("No hay empresas registradas.");
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
                    mensaje.setContenido("Logo de la empresa guardado correctamente.");
                } else {
                    mensaje.setContenido("Hubo un error al intentar guardar el logo de la empresa.");
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

    public static Mensaje buscarLogo(Integer idEmpresa) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexion con la base de datos.");
                return mensaje;
            }

            Empresa empresa = conexionDB.selectOne("empresa.obtenerLogo", idEmpresa);
            mensaje.setEmpresa(empresa);

            if (empresa != null) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Respuesta exitosa.");
            } else {
                mensaje.setContenido("La empresa no tiene un logo registrado.");
            }

        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }

        return mensaje;
    }

    public static Mensaje buscarEmpresa(Integer idEmpresa) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError(Boolean.TRUE);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB == null) {
                mensaje.setContenido("No hay conexion con la base de datos.");
                return mensaje;
            }

            Empresa empresa = conexionDB.selectOne("empresa.obtenerEmpresa", idEmpresa);
            mensaje.setEmpresa(empresa);

            if (empresa != null) {
                mensaje.setError(Boolean.FALSE);
                mensaje.setContenido("Respuesta exitosa.");
            } else {
                mensaje.setContenido("No hay empresa con el ID proporcionado.");
            }

        } catch (Exception e) {
            mensaje.setContenido("Error: " + e.getMessage());
        }

        return mensaje;
    }
}
