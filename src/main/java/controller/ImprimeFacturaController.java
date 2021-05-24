/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Conexion;
import model.FacturaCabecera;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author svarg
 */
@Named(value = "imprimeFacturaController")
@SessionScoped
public class ImprimeFacturaController implements Serializable {

    /**
     * Creates a new instance of ImprimeFacturaController
     */
    public ImprimeFacturaController() {
    }
    
    
    
     public void imprimirFacturaJasper(FacturaCabecera factura) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idfacturaCabecera", factura.getIdFacturaCabecera());
        try {            
            
            File jasper = new File(FacesContext
                    .getCurrentInstance().
                    getExternalContext()
                    .getRealPath("/factura/Factura_Electronica_v.5.jasper"));

            JasperPrint reporteJasper = JasperFillManager.
                    fillReport(jasper.getPath(), parametros, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();

            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type", "application/pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            Logger.getLogger(ImprimeFacturaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
