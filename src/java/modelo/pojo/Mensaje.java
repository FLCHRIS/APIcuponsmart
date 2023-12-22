package modelo.pojo;

import java.util.List;

public class Mensaje {

    private Boolean error;
    private String contenido;
    private List<Empresa> empresas;
    private Empresa empresa;
    private Usuario usuarioSesion;
    private List<Sucursal> sucursales;
    private Sucursal sucursal;
    private List<Usuario> usuarios;
    private Cliente cliente;
    private List<Roll> roles;
    private List<Promocion> promociones;
    private Promocion promocion;
    private List<Categoria> categorias;
    private Ubicacion ubicacion;

    public Mensaje() {
    }

    public Mensaje(Boolean error, String contenido, List<Empresa> empresas, Empresa empresa, Usuario usuarioSesion, List<Sucursal> sucursales, Sucursal sucursal, List<Usuario> usuarios, Cliente cliente, List<Roll> roles, List<Promocion> promociones, Promocion promocion, List<Categoria> categorias, Ubicacion ubicacion) {
        this.error = error;
        this.contenido = contenido;
        this.empresas = empresas;
        this.empresa = empresa;
        this.usuarioSesion = usuarioSesion;
        this.sucursales = sucursales;
        this.sucursal = sucursal;
        this.usuarios = usuarios;
        this.cliente = cliente;
        this.roles = roles;
        this.promociones = promociones;
        this.promocion = promocion;
        this.categorias = categorias;
        this.ubicacion = ubicacion;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Promocion getPromocion() {
        return promocion;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }

    public List<Promocion> getPromociones() {
        return promociones;
    }

    public void setPromociones(List<Promocion> promociones) {
        this.promociones = promociones;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Usuario getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(Usuario usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Roll> getRoles() {
        return roles;
    }

    public void setRoles(List<Roll> roles) {
        this.roles = roles;
    }
    
    

}
