/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Usuario
 */
public class DetalleFactura extends Producto{
    
    private int idDetalleFactura;
    private int idProducto;
    private int cantidad;
    private int idFactura;

    public DetalleFactura() {
    }

    public DetalleFactura(int idDetalleFactura, int idProducto, int cantidad, int idFactura) {
        this.idDetalleFactura = idDetalleFactura;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.idFactura = idFactura;
    }
    
    public DetalleFactura(int idDetalleFactura,int idproducto, String descripcion, double precio, int cantidad, double iva, String unidadmedida) {
        this.idDetalleFactura = idDetalleFactura;
        this.idproducto = idproducto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.iva = iva;
        this.unidadmedida = unidadmedida;
    }

    @Override
    public int getIdproducto() {
        return idproducto;
    }

    @Override
    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public double getPrecio() {
        return precio;
    }

    @Override
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public int getIdtipoproducto() {
        return idtipoproducto;
    }

    @Override
    public void setIdtipoproducto(int idtipoproducto) {
        this.idtipoproducto = idtipoproducto;
    }
    
    @Override
    public double getIva() {
        return iva;
    }

    @Override
    public void setIva(double iva) {
        this.iva = iva;
    }

    @Override
    public String getUnidadmedida() {
        return unidadmedida;
    }

    @Override
    public void setUnidadmedida(String unidadmedida) {
        this.unidadmedida = unidadmedida;
    }


    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    

    public int getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(int idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}

