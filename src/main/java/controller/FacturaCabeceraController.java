/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.FacturaCabeceraGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.FacturaCabecera;

/**
 *
 * @author jhons
 */
@Named(value = "facturaCabeceraController")
@SessionScoped
public class FacturaCabeceraController extends FacturaCabecera implements Serializable {

    /**
     * Creates a new instance of FacturaCabeceraController
     */
    public FacturaCabeceraController() {
    }
    
    public List<FacturaCabecera> getFacturas() {
        return FacturaCabeceraGestion.getFacturas();
    }
    
    public String editaFactura (int idFacturaCabecera) {
        FacturaCabecera laFactura = FacturaCabeceraGestion.getUnaFactura(idFacturaCabecera);
        if (laFactura != null) {
            this.setIdFacturaCabecera(laFactura.getIdFacturaCabecera());
            this.setIdCliente(laFactura.getIdCliente());
            this.setIdEmisor(laFactura.getIdEmisor());
            this.setEstado(laFactura.getEstado());
            this.setFechaFacturado(laFactura.getFechaFacturado());
            this.setTipoCambio(laFactura.getTipoCambio());
            this.setTotalFactura(laFactura.getTotalFactura());
            this.setIdTipoPago(laFactura.getIdTipoPago());
            return "editaFacturaEstado.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro de la factura no exista");
            FacesContext.getCurrentInstance().addMessage("editaFacturaForm:identificacion", msg);
            return "listaFacturas.xhtml";
        }

    }
    
    public String updateFactura() {
            if (FacturaCabeceraGestion.updateFactura(this)) {
            return "listaFacturas.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al modificar la factura");
            FacesContext.getCurrentInstance().addMessage("editaFacturaForm:identificacion", msg);
            return "editaFacturaEstado.xhtml";

        }
    }
    
    public static int facturaCabeceraLastID(){
      int lastIdFactura= FacturaCabeceraGestion.getLastInsert();
        
      if(lastIdFactura != 0) {
          return lastIdFactura; 
       }else{
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el valor del ID no exista");
            FacesContext.getCurrentInstance().addMessage("facturaForm:identificacion", msg);
            
       }
       return lastIdFactura;
    }
    
    private boolean noImprimir = true;

    public boolean isNoImprimir() {
        return noImprimir;
    }

    public void setNoImprimir(boolean noImprimir) {
        this.noImprimir = noImprimir;
    }
    
    public String buscarFactura(int idfacturacabecera) {
        FacturaCabecera laFactura = FacturaCabeceraGestion.getImprimeFactura(idfacturacabecera);
        if (laFactura != null) {
            this.setIdFacturaCabecera(laFactura.getIdFacturaCabecera());
            this.setIdCliente(laFactura.getIdCliente());
            this.setIdEmisor(laFactura.getIdEmisor());
            this.setEstado(laFactura.getEstado());
            this.setFechaFacturado(laFactura.getFechaFacturado());
            this.setTipoCambio(laFactura.getTipoCambio());
            this.setTotalFactura(laFactura.getTotalFactura());
            this.setIdTipoPago(laFactura.getIdTipoPago());
            noImprimir = false;
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("pdfFacturaForm:idFacturaCabecera", msg);
            noImprimir = true;
        }
        return "imprimeFactura.xhtml";
    }
    

    
    
}
