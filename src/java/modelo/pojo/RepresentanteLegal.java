package modelo.pojo;

public class RepresentanteLegal {

    private Integer idRepresentanteLegal;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;

    public RepresentanteLegal() {
    }

    public RepresentanteLegal(Integer idRepresentanteLegal, String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.idRepresentanteLegal = idRepresentanteLegal;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
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
