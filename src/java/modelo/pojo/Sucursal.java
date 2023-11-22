package modelo.pojo;

public class Sucursal {

    private Integer idSucursal;
    private Integer idEmpresa;
    private Integer idUbicacion;
    private String nombre;
    private String telefono;
    private float latitud;
    private float longitud;
    private String direccion;
    private String codigoPostal;

    public Sucursal() {
    }

    public Sucursal(Integer idSucursal, Integer idEmpresa, Integer idUbicacion, String nombre, String telefono, float latitud, float longitud, String direccion, String codigoPostal) {
        this.idSucursal = idSucursal;
        this.idEmpresa = idEmpresa;
        this.idUbicacion = idUbicacion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
    }

    

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    
    
}
