/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.EstudianteGestion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Estudiante;

/**
 *
 * @author User
 */
@Named(value = "estudianteController")
@SessionScoped
public class EstudianteController extends Estudiante implements Serializable {

    public EstudianteController() {
    }

    public List<Estudiante> getEstudiantes() {
        return EstudianteGestion.getEstudiantes();
    }

    public String inserta() {
        if (EstudianteGestion.insertar(this)) {
            return "list.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente identificacion duplicada");
            FacesContext.getCurrentInstance().addMessage("editaEstudianteForm:identificacion", msg);
            return "edita.xhtml";
        }
    }

    public String modifica() {
        if (EstudianteGestion.modificar(this)) {
            return "list.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente identificacion duplicada");
            FacesContext.getCurrentInstance().addMessage("editaEstudianteForm:identificacion", msg);
            return "edita.xhtml";
        }
    }

    public String elimina() {
        if (EstudianteGestion.eliminar(this)) {
            return "list.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente identificacion ya no exista");
            FacesContext.getCurrentInstance().addMessage("editaEstudianteForm:identificacion", msg);
            return "edita.xhtml";
        }
    }

    public String edita(String id) {
        Estudiante elEstudiante = EstudianteGestion.getEstudiante(id);
        if (elEstudiante != null) {
            this.setId(elEstudiante.getId());
            this.setNombre(elEstudiante.getNombre());
            this.setApellido1(elEstudiante.getApellido1());
            this.setApellido2(elEstudiante.getApellido2());
            this.setFechaNaci(elEstudiante.getFechaNaci());
            this.setFechaIngr(elEstudiante.getFechaIngr());
            this.setGenero(elEstudiante.getGenero());
            return "edita.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente identificacion ya no exista");
            FacesContext.getCurrentInstance().addMessage("editaEstudianteForm:identificacion", msg);
            return "list.xhtml";
        }
    }

    //Creacion de nueva Variable 
    private boolean noImprimir = true;

    public boolean isNoImprimir() {
        return noImprimir;
    }

    public void setNoImprimir(boolean noImprimir) {
        this.noImprimir = noImprimir;
    }

    public void buscaEstudiante(String id) {
        if ("".equalsIgnoreCase(id)) {
            noImprimir = true;
        } else {
            Estudiante elEstudiante = EstudianteGestion.getEstudiante(id);
            if (elEstudiante != null) {
                this.setId(elEstudiante.getId());
                this.setNombre(elEstudiante.getNombre());
                this.setApellido1(elEstudiante.getApellido1());
                this.setApellido2(elEstudiante.getApellido2());
                this.setFechaNaci(elEstudiante.getFechaNaci());
                this.setFechaIngr(elEstudiante.getFechaIngr());
                noImprimir = false;
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                        "Posiblemente identificacion ya no exista");
                FacesContext.getCurrentInstance()
                        .addMessage("certificacionEstudianteForm:identificacion", msg);
                noImprimir = true;

            }

        }

    }

    public void respaldo() {
        ZipOutputStream out = null;
        try {
            String json = EstudianteGestion.generarJson();
            File f = new File(FacesContext.getCurrentInstance()
                    .getExternalContext().
                    getRealPath("/respaldo") + "estudiantes.zip");
            out = new ZipOutputStream(new FileOutputStream(f));
            ZipEntry e = new ZipEntry("estudiantes.json");
            out.putNextEntry(e);
            byte[] data = json.getBytes();
            out.write(data, 0, data.length);
            out.closeEntry();
            out.close();
            File zipPath = new File(FacesContext.getCurrentInstance()
                    .getExternalContext().
                    getRealPath("/respaldo") + "estudiantes.zip");
            byte[] zip = Files.readAllBytes(zipPath.toPath());

            HttpServletResponse respuesta
                    = (HttpServletResponse) FacesContext.getCurrentInstance()
                            .getExternalContext().getResponse();
            ServletOutputStream sos = respuesta.getOutputStream();
            respuesta.setContentType("application/pdf");
            respuesta.setHeader("Content-disposition",
                    "attachment; filename=estudiantes.zip");
            sos.write(zip);
            sos.flush();
            FacesContext.getCurrentInstance().getResponseComplete();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EstudianteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EstudianteController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(EstudianteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
