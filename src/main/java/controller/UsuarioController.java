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
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Usuario;

/**
 *
 * @author E1287240 TareaSemana03
 */
@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController extends Usuario implements Serializable {

    /**
     * Creates a new instance of UsuarioController
     */
    public UsuarioController() {
    }
    
    public List<Usuario> getUsuarios(){
        return UsuarioGestion.getUsuarios();
    }

    public String getUsuario() {
        Usuario user = UsuarioGestion.getUsuario(this.getUsername(), this.getContrasena());
        if (user != null) {
            this.setIdusuario(user.getIdusuario());
            this.setIdPersona(user.getIdPersona());
            this.setCedula(user.getCedula());
            this.setNombre(user.getNombre());
            this.setApellido(user.getApellido());
            this.setApellido2(user.getApellido2());
            this.setTelefono(user.getTelefono());
            this.setCorreo(user.getCorreo());
            this.setFechaNacimiento(user.getFechaNacimiento());
            this.setProvincia(user.getProvincia());
            this.setCanton(user.getCanton());
            this.setDistrito(user.getDistrito());
            this.setResena(user.getResena());
            this.setTipoPersona(user.getTipoPersona());
            return "principal.xhtml";
        } else {
            this.setIdPersona(0);
            this.setCedula("");
            this.setNombre("");
            this.setApellido("");
            this.setApellido2("");
            this.setTelefono("");
            this.setCorreo("");
            this.setFechaNacimiento(null);
            this.setProvincia("");
            this.setCanton("");
            this.setDistrito("");
            this.setResena("");
            this.setTipoPersona("");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Usuario o Contraseña incorrecta");
            FacesContext.getCurrentInstance().addMessage("loginForm:clave", msg);
            return "index.xhtml";
        }
    }
    
    public String insertarUsuario (){
        if (UsuarioGestion.insertaUsuario(this)) {
            this.setIdusuario(0);
            this.setUsername(null);
            this.setContrasena(null);
            this.setIdPersona(0);
            return "index.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al insertar un nuevo usuario");
            FacesContext.getCurrentInstance().addMessage("editaUsuarioForm:identificacion", msg);
            return "registroUsuario.xhtml";

        }
    }
    
    public String insertarUsuarioLista (){
        if (UsuarioGestion.insertaUsuario(this)) {
            this.setIdusuario(0);
            this.setUsername(null);
            this.setContrasena(null);
            this.setIdPersona(0);
            return "listUsuarios.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al insertar un nuevo usuario");
            FacesContext.getCurrentInstance().addMessage("editaUsuarioForm:identificacion", msg);
            return "registroUsuario.xhtml";

        }
    }
    
    public String insertarUsuarioInterno (){
            this.setIdusuario(0);
            this.setNombre(null);
            this.setUsername(null);
            this.setContrasena(null);
            this.setIdPersona(0);
            this.setCedula(null);
            this.setApellido(null);
            this.setApellido2(null);
            this.setFechaNacimiento(null);
            this.setId(0);
            this.setTelefono(null);
            this.setCorreo(null);
            this.setProvincia(null);
            this.setCanton(null);
            this.setDistrito(null);
            this.setResena(null);
            
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al insertar un nuevo usuario");
            FacesContext.getCurrentInstance().addMessage("editaUsuarioForm:identificacion", msg);
            return "registroUsuario.xhtml";

        
    }
    
   public String editaUsuario(int username) {
        Usuario unUser = UsuarioGestion.getUsuario(username);
        if (unUser != null){
            
            return "editaUsuario.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro del usuario no exista");
            FacesContext.getCurrentInstance().addMessage("editaUsuarioForm:identificacion", msg);
            return "listUsuarios.xhtml";
        }

    }
   
   public String updateUsuario() {
            if (UsuarioGestion.updateUsuario(this)) {
            return "listUsuarios.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio unrror al modificar los datos del usuario");
            FacesContext.getCurrentInstance().addMessage("editaUsuarioForm:identificacion", msg);
            return "editaUsuario.xhtml";

        }
    }
   
    public String deleteUsuario() {
        if (UsuarioGestion.deleteUsuario(this)) {
            return "listUsuarios.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al eliminar la persona");
            FacesContext.getCurrentInstance().addMessage("editaUsuarioForm:identificacion", msg);
            return "editaUsuario.xhtml";

        }
    }
   
   public void limpiaUsuario(){
            this.setUsername(null);
            this.setContrasena(null);
            this.setCedula(null);
            this.setNombre(null);
            this.setApellido(null);
            this.setApellido2(null);
            this.setTelefono(null);
            this.setCorreo(null);
            this.setFechaNacimiento(null);
            this.setProvincia(null);
            this.setCanton(null);
            this.setDistrito(null);
            this.setResena(null);
            this.setTipoPersona(null);
    }
       
    
}
