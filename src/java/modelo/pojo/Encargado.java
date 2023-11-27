package modelo.pojo;

public class Encargado {

    private Integer idEncargadoSucursal;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;

    public Encargado() {
    }

    public Encargado(Integer idEncargadoSucursal, String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.idEncargadoSucursal = idEncargadoSucursal;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public Integer getIdEncargadoSucursal() {
        return idEncargadoSucursal;
    }

    public void setIdEncargadoSucursal(Integer idEncargadoSucursal) {
        this.idEncargadoSucursal = idEncargadoSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    
}
