/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.UsuarioCRUDgestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.usuarioCRUD;

/**
 *
 * @author svarg
 */
@Named(value = "usuarioCRUDcontroller")
@SessionScoped
public class UsuarioCRUDcontroller extends usuarioCRUD implements Serializable {

    /**
     * Creates a new instance of UsuarioCRUDcontroller
     */
    public UsuarioCRUDcontroller() {
    }
    
    public List<usuarioCRUD> getUsuariosCRUD(){
        return UsuarioCRUDgestion.getUsuarios();
    }
    
    public String editaUsuarioCRUD(int idusuario) {
        usuarioCRUD unUser = UsuarioCRUDgestion.getUsuario(idusuario);
        if (unUser != null){
            this.setIdusuario(unUser.getIdusuario());
            this.setUsuario(unUser.getUsuario());
            this.setContrasena(unUser.getContrasena());
            this.setIdpersonausuario(unUser.getIdpersonausuario());
            this.setNombrePersona(unUser.getNombrePersona());
            return "editaUsuario.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro del usuario no exista");
            FacesContext.getCurrentInstance().addMessage("editaUsuarioForm:id", msg);
            return "listUsuarios.xhtml";
        }

    }
   
   public String updateUsuarioCRUD() {
            if (UsuarioCRUDgestion.updateUsuario(this)) {
            return "listUsuarios.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio unrror al modificar los datos del usuario");
            FacesContext.getCurrentInstance().addMessage("editaUsuarioForm:identificacion", msg);
            return "editaUsuario.xhtml";

        }
    }
   
    public String deleteUsuarioCRUD() {
        if (UsuarioCRUDgestion.deleteUsuario(this)) {
            return "listUsuarios.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "No se puede eliminar este usuario ya que tiene facturas emitidas");
            FacesContext.getCurrentInstance().addMessage("editaUsuarioForm:id", msg);
            return "editaUsuario.xhtml";

        }
    }
    
}
