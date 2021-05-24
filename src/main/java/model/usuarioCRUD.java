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
public class usuarioCRUD {
    
    private int idusuario;
    private String usuario; 
    private String contrasena;
    private int idpersonausuario;
    private String nombrePersona;

    public usuarioCRUD() {
    }

    public usuarioCRUD(int idusuario, String usuario, String contrasena, int idpersonausuario) {
        this.idusuario = idusuario;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.idpersonausuario = idpersonausuario;
    }
    
    public usuarioCRUD(int idusuario, String usuario, String contrasena, int idpersonausuario,String nombrePersona) {
        this.idusuario = idusuario;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.idpersonausuario = idpersonausuario;
        this.nombrePersona = nombrePersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getIdpersonausuario() {
        return idpersonausuario;
    }

    public void setIdpersonausuario(int idpersonausuario) {
        this.idpersonausuario = idpersonausuario;
    }
    

    

    

    
    
}
