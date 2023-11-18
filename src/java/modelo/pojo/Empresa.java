/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojo;

/**
 *
 * @author Oscar
 */
public class Empresa {
    
    private Integer idEmpresa;
    private Integer idRepresentanteLegal;
    private String nombre;
    private String nombreComercial;
    private byte [] logo;
    private String email;
    private String telefono;
    private String paginaWeb;
    private String RFC;
    private String estatus;
    private String logoBase64;

    public Empresa() {
    }

    public Empresa(Integer idEmpresa, Integer idRepresentanteLegal, String nombre, String nombreComercial, byte[] logo, String email, String telefono, String paginaWeb, String RFC, String estatus, String logoBase64) {
        this.idEmpresa = idEmpresa;
        this.idRepresentanteLegal = idRepresentanteLegal;
        this.nombre = nombre;
        this.nombreComercial = nombreComercial;
        this.logo = logo;
        this.email = email;
        this.telefono = telefono;
        this.paginaWeb = paginaWeb;
        this.RFC = RFC;
        this.estatus = estatus;
        this.logoBase64 = logoBase64;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdRepresentanteLegal() {
        return idRepresentanteLegal;
    }

    public void setIdRepresentanteLegal(Integer idRepresentanteLegal) {
        this.idRepresentanteLegal = idRepresentanteLegal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getLogoBase64() {
        return logoBase64;
    }

    public void setLogoBase64(String logoBase64) {
        this.logoBase64 = logoBase64;
    }
    
}
