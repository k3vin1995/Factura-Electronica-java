/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.DetalleFacturaGestion;
import model.Persona;
import controller.PersonaController;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.DetalleFactura;

/**
 *
 * @author Usuario
 */
@Named(value = "detalleFacturaController")
@SessionScoped
public class DetalleFacturaController extends DetalleFactura implements Serializable {

    /**
     * Creates a new instance of DetalleFacturaController
     */
    public DetalleFacturaController() {
    }
    
    public List<DetalleFactura> productosFactura() {
            return DetalleFacturaGestion.getArrayProductos();
    }
    
    public String inserListaProducto(int idProducto, int cantidad,int idfactura, String descripcion,double precio,double iva, String unidadmedida) {
        if (cantidad>0) {
            if (DetalleFacturaGestion.insertListDetalleFactura(idProducto,cantidad,idfactura) != null && DetalleFacturaGestion.insertListDetalleProducto(idProducto, descripcion, precio, cantidad, iva, unidadmedida) != null) {
                this.setCantidad(0);
            return "factura.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al insertar el Producto");
            FacesContext.getCurrentInstance().addMessage("facturaForm:AnadirProducto", msg);
            return "factura.xhtml";
        }
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al insertar el Producto");
            FacesContext.getCurrentInstance().addMessage("facturaForm:CantidadProdserv", msg);
            this.setCantidad(0);
            return "factura.xhtml";
        }        
    }
    
    public String deleteProductoFactura(int idDetalleFactura) {
        if (DetalleFacturaGestion.deleteProductoFactura(idDetalleFactura)) {
            return "factura.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al eliminar el producto");
            FacesContext.getCurrentInstance().addMessage("facturaForm:identificacion", msg);
            return "factura.xhtml";

        }
    }
    
    public String removeArrayProducto(int idDetalleFactura,int cantidad) {
        if (DetalleFacturaGestion.RemoveArrayProducto(idDetalleFactura,cantidad) != null) {
            
            return "factura.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al eliminar el producto");
            FacesContext.getCurrentInstance().addMessage("facturaForm:identificacion", msg);
            return "factura.xhtml";
        }
    }
    
    
    
    public String crearFactura (int id, int idEmisor, int facturaCabeceraLastID, String tipocambio, int idtipopago){
        if (DetalleFacturaGestion.facturavacia()) {
            
      if (DetalleFacturaGestion.insertaFacturaCabecera(id, idEmisor, facturaCabeceraLastID, tipocambio, idtipopago) && DetalleFacturaGestion.insertaDetalleProductos(this)){
          
          DetalleFacturaGestion.cleanArray();
          return "/principal.xhtml";
          
      }  else{
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                  "Ocurrio un error al generar la factura, verifique los datos y sus productos");
          FacesContext.getCurrentInstance().addMessage("facturaForm:cedula", msg);
            return "factura.xhtml";
      }
        
    }else{
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                  "Ocurrio un error al generar la factura, verifique los datos y sus productos");
          FacesContext.getCurrentInstance().addMessage("facturaForm:cedula", msg);
            return "factura.xhtml";
      }
    }    
             
    
}