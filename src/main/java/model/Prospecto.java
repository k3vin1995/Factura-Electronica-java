/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author svarg
 */
public class Prospecto {
    
    private int id;
    private String idProspecto;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Date fechaNacimiento;
    private Date fechaGraduacionColegio;
    private Date fechaPosibleIngreso;
    private char correo;
    private String celular;
    
    public Prospecto(){
    
}

    public Prospecto(int id, String idProspecto, String nombre, String apellido1, String apellido2, Date fechaNacimiento, Date fechaGraduacionColegio, Date fechaPosibleIngreso, char correo, String celular) {
        this.id = id;
        this.idProspecto = idProspecto;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaGraduacionColegio = fechaGraduacionColegio;
        this.fechaPosibleIngreso = fechaPosibleIngreso;
        this.correo = correo;
        this.celular = celular;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdProspecto() {
        return idProspecto;
    }

    public void setIdProspecto(String idProspecto) {
        this.idProspecto = idProspecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaGraduacionColegio() {
        return fechaGraduacionColegio;
    }

    public void setFechaGraduacionColegio(Date fechaGraduacionColegio) {
        this.fechaGraduacionColegio = fechaGraduacionColegio;
    }

    public Date getFechaPosibleIngreso() {
        return fechaPosibleIngreso;
    }

    public void setFechaPosibleIngreso(Date fechaPosibleIngreso) {
        this.fechaPosibleIngreso = fechaPosibleIngreso;
    }

    public char getCorreo() {
        return correo;
    }

    public void setCorreo(char correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    
    
    
    
    
}
