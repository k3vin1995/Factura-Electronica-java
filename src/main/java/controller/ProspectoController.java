/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.ProspectoGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import model.Prospecto;

/**
 *
 * @author User
 */
@Named(value = "prospectoController")
@SessionScoped
public class ProspectoController extends Prospecto implements Serializable {

    private String tiraJson;

    public String getTiraJson() {
        return tiraJson;
    }

    public void setTiraJson(String tiraJson) {
        this.tiraJson = tiraJson;
    }

    /**
     * Creates a new instance of ProspectoController
     */
    public ProspectoController() {
    }

    public String inserta() {
        if (ProspectoGestion.insertar(this)) {
            return "confirmacion.xhtml";
        } else {
            FacesMessage mensaje
                    = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Posible Cedula Duplicada");
            FacesContext.getCurrentInstance().addMessage("registroProspectoForm:cedula", mensaje);
            return "registro.xhtml";
        }
    }

    public void recupera(String id) {
        Prospecto prospecto = ProspectoGestion.getProspecto(id);
        if (prospecto != null) {
            this.setCedula(prospecto.getCedula());
            this.setNombre(prospecto.getNombre());
            this.setApellido1(prospecto.getApellido1());
            this.setApellido2(prospecto.getApellido2());
            this.setFechaNacimiento(prospecto.getFechaNacimiento());
            this.setFechaGraduacionColegio(prospecto.getFechaGraduacionColegio());
            this.setFechaPosibleIngreso(prospecto.getFechaPosibleIngreso());
            this.setCorreo(prospecto.getCorreo());
            this.setCelular(prospecto.getCelular());
        } else {
            this.setNombre("");
            this.setApellido1("");
            this.setApellido2("");
            this.setFechaNacimiento(null);
            this.setFechaGraduacionColegio(null);
            this.setFechaPosibleIngreso(null);
            this.setCorreo("");
            this.setCelular("");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Prospecto no encontrado");
            FacesContext.getCurrentInstance().
                    addMessage("prospectoJsonForm:identificacion", msg);
        }
    }

    public void creaJson() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fecha1 = format.format(this.getFechaNacimiento());
        JsonObjectBuilder creadorJson = Json.createObjectBuilder();
        JsonObject objetoJson = creadorJson.add("cedula", this.getCedula())
                .add("nombre", this.getNombre())
                .add("apellido1", this.getApellido1())
                .add("apellido2", this.getApellido2())
                .add("fechaNacimiento", fecha1)
                .add("correo", this.getCorreo())
                .add("celular", this.getCelular()).build();
        StringWriter tira = new StringWriter();
        JsonWriter jsonWriter = Json.createWriter(tira);
        jsonWriter.writeObject(objetoJson);
        setTiraJson(tira.toString());
    }

    public void crearObjetoProspecto() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            JsonReader lectorJson = Json.createReader(new StringReader(tiraJson));
            JsonObject objetoJson = lectorJson.readObject();
            this.setCedula(objetoJson.getString("cedula"));
            this.setNombre(objetoJson.getString("nombre"));
            this.setApellido1(objetoJson.getString("apellido1"));
            this.setApellido2(objetoJson.getString("apellido2"));
            this.setFechaNacimiento(sdf.parse(objetoJson.getString("fechaNacimiento")));
            this.setCorreo(objetoJson.getString("correo"));
            this.setCelular(objetoJson.getString("celular"));
        } catch (ParseException ex) {
            Logger.getLogger(ProspectoController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

    }

}
