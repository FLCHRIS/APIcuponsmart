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

    public Mensaje(Boolean error, String contenido, List<Empresa> empresas) {
        this.error = error;
        this.contenido = contenido;
        this.empresas = empresas;
    }

    public Mensaje() {
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

}
