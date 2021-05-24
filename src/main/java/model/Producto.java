/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author svarg
 */
public class Producto {
    
    int idproducto;
    String descripcion;
    double precio;
    int idtipoproducto;
    double iva;
    String unidadmedida;
    String idProductoDescripcion;

    public Producto() {
    }

    public Producto(int idproducto, String descripcion, double precio, int idtipoproducto, double iva, String unidadmedida,String idProductoDescripcion) {
        this.idProductoDescripcion = idProductoDescripcion;
        this.idproducto = idproducto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.idtipoproducto = idtipoproducto;
        this.iva = iva;
        this.unidadmedida = unidadmedida;
    }

    public String getIdProductoDescripcion() {
        return idProductoDescripcion;
    }

    public void setIdProductoDescripcion(String idProductoDescripcion) {
        this.idProductoDescripcion = idProductoDescripcion;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdtipoproducto() {
        return idtipoproducto;
    }

    public void setIdtipoproducto(int idtipoproducto) {
        this.idtipoproducto = idtipoproducto;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public String getUnidadmedida() {
        return unidadmedida;
    }

    public void setUnidadmedida(String unidadmedida) {
        this.unidadmedida = unidadmedida;
    }

   
    
    
}
