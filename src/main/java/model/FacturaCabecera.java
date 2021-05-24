
package model;

import java.util.Date;

public class FacturaCabecera  {
 private int idFacturaCabecera;
 private int idCliente;
 private String idEmisor;
 private String estado;
 private Date fechaFacturado;
 private String tipoCambio;
 private double totalFactura;
 private String idTipoPago;
 private double subTotal;
 private String nombreEmisor;
 private String NombreCliente;
 private String apellidoCliente;
 private String apellido2Cliente;
 

    public FacturaCabecera() {
    }

    public FacturaCabecera(int idFacturaCabecera, int idCliente, String idEmisor, String estado, Date fechaFacturado, String tipoCambio, double totalFactura, String idTipoPago) {
        this.idFacturaCabecera = idFacturaCabecera;
        this.idCliente = idCliente;
        this.idEmisor = idEmisor;
        this.estado = estado;
        this.fechaFacturado = fechaFacturado;
        this.tipoCambio = tipoCambio;
        this.totalFactura = totalFactura;
        this.idTipoPago = idTipoPago;
    }

    
    
    public FacturaCabecera(int idFacturaCabecera, int idCliente, String idEmisor, String estado, Date fechaFacturado, String tipoCambio, double totalFactura, String idTipoPago, double subTotal, String nombreEmisor, String NombreCliente, String apellidoCliente, String apellido2Cliente) {
        this.idFacturaCabecera = idFacturaCabecera;
        this.idCliente = idCliente;
        this.idEmisor = idEmisor;
        this.estado = estado;
        this.fechaFacturado = fechaFacturado;
        this.tipoCambio = tipoCambio;
        this.totalFactura = totalFactura;
        this.idTipoPago = idTipoPago;
        this.subTotal = subTotal;
        this.nombreEmisor = nombreEmisor;
        this.NombreCliente = NombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.apellido2Cliente = apellido2Cliente;
    }
    
    public int getIdFacturaCabecera() {
        return idFacturaCabecera;
    }

    public void setIdFacturaCabecera(int idFacturaCabecera) {
        this.idFacturaCabecera = idFacturaCabecera;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdEmisor() {
        return idEmisor;
    }

    public void setIdEmisor(String idEmisor) {
        this.idEmisor = idEmisor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaFacturado() {
        return fechaFacturado;
    }

    public void setFechaFacturado(Date fechaFacturado) {
        this.fechaFacturado = fechaFacturado;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(double totalFactura) {
        this.totalFactura = totalFactura;
    }

    public String getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(String idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public String getNombreEmisor() {
        return nombreEmisor;
    }

    public void setNombreEmisor(String nombreEmisor) {
        this.nombreEmisor = nombreEmisor;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String NombreCliente) {
        this.NombreCliente = NombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getApellido2Cliente() {
        return apellido2Cliente;
    }

    public void setApellido2Cliente(String apellido2Cliente) {
        this.apellido2Cliente = apellido2Cliente;
    }

    
    
}
