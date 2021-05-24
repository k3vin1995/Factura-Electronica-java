/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author svarg
 */
public class Direccion {
    
    int iddireccion;
    String provincia, canton, distrito, resena;

    public Direccion() {
    }

    public Direccion(int iddireccion, String provincia, String canton, String distrito, String resena) {
        this.iddireccion = iddireccion;
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
        this.resena = resena;
    }

    public int getIddireccion() {
        return iddireccion;
    }

    public void setIddireccion(int iddireccion) {
        this.iddireccion = iddireccion;
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
    
    
}
