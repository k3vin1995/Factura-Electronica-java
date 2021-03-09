/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.UsuarioGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Usuario;

/**
 *
 * @author User
 */
@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController extends Usuario implements Serializable {

    public UsuarioController() {
    }

    public String valida() {
        Usuario usuario = UsuarioGestion.valida(this.getIdUsuario(), this.getPwUsuario());
        if (usuario != null) {
            this.setNombreUsuario(usuario.getNombreUsuario());
            this.setIdRol(usuario.getIdRol());
            return "principal";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Usuario y/o contraseña invalidas");
            FacesContext.getCurrentInstance().addMessage("loginForm:clave", msg);
            FacesContext.getCurrentInstance().addMessage("loginForm:usuario", msg);
            return "index.xhtml";
        }
    }

}
