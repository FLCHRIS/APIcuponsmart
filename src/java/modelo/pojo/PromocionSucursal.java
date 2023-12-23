/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojo;

/**
 *
 * @author Admin
 */
public class PromocionSucursal {
    
     private Integer idPromocion;
    private Integer idSucursal;

    public PromocionSucursal() {
    }
    
    public PromocionSucursal(Integer idPromocion, Integer idSucursal) {
        this.idPromocion = idPromocion;
        this.idSucursal = idSucursal;
    }

    public Integer getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(Integer idPromocion) {
        this.idPromocion = idPromocion;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }
    
}
