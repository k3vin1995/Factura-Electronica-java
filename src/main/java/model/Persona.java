/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Usuario
 */

public class Persona {
    private int id;
    private String nombre;
    private String apellido;
    private String apellido2;
    private String telefono;
    private String correo;
    private String cedula;
    private Date fechaNacimiento;
    private String provincia;
    private String canton;
    private String distrito;
    private String resena;
    private String tipoPersona;

    public Persona() {
    }

    public Persona(int id, String nombre, String apellido, String apellido2, String telefono, String correo, String cedula, Date fechaNacimiento, String provincia, String canton, String distrito, String resena, String tipoPersona) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.correo = correo;
        this.cedula = cedula;
        this.fechaNacimiento = fechaNacimiento;
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
        this.resena = resena;
        this.tipoPersona = tipoPersona;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getResena() {
        return resena;
    }

    public void setResena(String resena) {
        this.resena = resena;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String nombreCompleto() {
        String texto = "";
        texto += this.nombre != null ? this.nombre + " " : "";
        texto += this.apellido != null ? this.apellido + " " : "";
        texto += this.apellido2 != null ? this.apellido2 + " " : "";
        return texto;
    }
    
}
