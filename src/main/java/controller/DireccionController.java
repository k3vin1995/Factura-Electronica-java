/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.DireccionGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Direccion;

/**
 *
 * @author svarg
 */
@Named(value = "direccionController")
@SessionScoped
public class DireccionController extends Direccion implements Serializable {

    /**
     * Creates a new instance of DireccionController
     */
    public DireccionController() {
    }
    
    public List<Direccion> getDirecciones() {
        return DireccionGestion.getDirecciones();
    }

    public String editaDireccion(int iddireccion, String provincia) {
        Direccion laDireccion = DireccionGestion.getDireccion(iddireccion, provincia);
        if (laDireccion != null) {
            this.setIddireccion(laDireccion.getIddireccion());
            this.setProvincia(laDireccion.getProvincia());
            this.setCanton(laDireccion.getCanton());
            this.setDistrito(laDireccion.getDistrito());
            this.setResena(laDireccion.getResena());
            return "editaDireccion.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro de la direccion no exista");
            FacesContext.getCurrentInstance().addMessage("editaDireccionForm:identificacion", msg);
            return "listaDireccion.xhtml";
        }

    }

    public String insertDireccion() {
        if (DireccionGestion.insertDireccion(this)) {
            return "listaDireccion.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al insertar una nueva direccion");
            FacesContext.getCurrentInstance().addMessage("editaLaptopForm:identificacion", msg);
            return "editaDireccion.xhtml";

        }
    }

    public String updateDireccion() {
            if (DireccionGestion.updateDireccion(this)) {
            return "listaDireccion.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio unrror al modificar la direccion");
            FacesContext.getCurrentInstance().addMessage("editaLaptopForm:identificacion", msg);
            return "editaDireccion.xhtml";

        }
    }

    public String deleteLaptop() {
        if (DireccionGestion.deleteDireccion(this)) {
            return "listaDireccion.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "No se puede eliminar, esta direccion ya se encuentra asignada.");
            FacesContext.getCurrentInstance().addMessage("editaDireccionForm:iddireccion", msg);
            return "editaDireccion.xhtml";

        }
    }
    
}
