/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojo;

import java.util.List;

/**
 *
 * @author Admin
 */
public class Mensaje {

    private Boolean error;
    private String contenido;
    private List<Empresa> empresas;
    private Empresa empresa;
    private Usuario usuarioSesion;
    private List<Sucursal> sucursales;
    private List<Usuario> usuarios;
    private Cliente cliente;
    private List<Roll> roles;

    public Mensaje() {
    }

    public Mensaje(Boolean error, String contenido, List<Empresa> empresas, Empresa empresa, Usuario usuarioSesion, List<Sucursal> sucursales, List<Usuario> usuarios, Cliente cliente, List<Roll> roles) {
        this.error = error;
        this.contenido = contenido;
        this.empresas = empresas;
        this.empresa = empresa;
        this.usuarioSesion = usuarioSesion;
        this.sucursales = sucursales;
        this.usuarios = usuarios;
        this.cliente = cliente;
        this.roles = roles;
    }

    public List<Roll> getRoles() {
        return roles;
    }

    public void setRoles(List<Roll> roles) {
        this.roles = roles;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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
    
}
