/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.PersonaGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Persona;

/**
 *
 * @author Usuario
 */
@Named(value = "personaController")
@SessionScoped
public class PersonaController extends Persona implements Serializable {
    


    /**
     * Creates a new instance of PersonaController
     */
    public PersonaController() {
    }
    
    public List<Persona> getPersona(){
        return PersonaGestion.getPersonas();
    }
    
    public void unaPersona(String cedula){
        Persona unaPersona = PersonaGestion.getPersona(cedula);
        if (unaPersona != null){
            this.setId(unaPersona.getId());
            this.setCedula(unaPersona.getCedula());
            this.setNombre(unaPersona.getNombre());
            this.setApellido(unaPersona.getApellido());
            this.setApellido2(unaPersona.getApellido2());
            this.setTelefono(unaPersona.getTelefono());
            this.setCorreo(unaPersona.getCorreo());
            this.setFechaNacimiento(unaPersona.getFechaNacimiento());
            this.setProvincia(unaPersona.getProvincia());
            this.setCanton(unaPersona.getCanton());
            this.setDistrito(unaPersona.getDistrito());
            this.setResena(unaPersona.getResena());
            this.setTipoPersona(unaPersona.getTipoPersona());
            
        }else {
            this.setId(0);
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
                    "Posiblemente el registro no exista");
            FacesContext.getCurrentInstance().addMessage("facturaForm:identificacion", msg);
            
        }
        
    }
    
    public String editaPersona(String cedula) {
        Persona unaPersona = PersonaGestion.getPersona(cedula);
        if (unaPersona != null){
            this.setId(unaPersona.getId());
            this.setCedula(unaPersona.getCedula());
            this.setNombre(unaPersona.getNombre());
            this.setApellido(unaPersona.getApellido());
            this.setApellido2(unaPersona.getApellido2());
            this.setTelefono(unaPersona.getTelefono());
            this.setCorreo(unaPersona.getCorreo());
            this.setFechaNacimiento(unaPersona.getFechaNacimiento());
            this.setProvincia(unaPersona.getProvincia());
            this.setCanton(unaPersona.getCanton());
            this.setDistrito(unaPersona.getDistrito());
            this.setResena(unaPersona.getResena());
            this.setTipoPersona(unaPersona.getTipoPersona());
            return "editaPersona.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro de la persona no exista");
            FacesContext.getCurrentInstance().addMessage("editaPersonaForm:identificacion", msg);
            return "listPersonas.xhtml";
        }

    }
    
    public String insertarPersona (){
        if (PersonaGestion.insertaPersona(this)) {
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
            return "listPersonas.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al insertar una nueva persona");
            FacesContext.getCurrentInstance().addMessage("editaPersonaForm:identificacion", msg);
            return "registro.xhtml";

        }
    }
    
    
     public String updatePersona() {
            if (PersonaGestion.updatePersona(this)) {
            return "listPersonas.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio unrror al modificar los datos del usuario");
            FacesContext.getCurrentInstance().addMessage("editaPersonaForm:identificacion", msg);
            return "editaPersona.xhtml";

        }
    }
     
         public String deletePersona() {
        if (PersonaGestion.deletePersona(this)) {
            return "listPersonas.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "No se puede eliminar la persona, esta ya tiene asignaciones");
            FacesContext.getCurrentInstance().addMessage("editaPersonaForm:idpersona", msg);
            return "editaPersona.xhtml";

        }
    }
         
    public void limpiaCliente(){
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
