/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author E1287240 TareaSemana03
 */
public class Usuario extends Persona {

    private int idusuario;
    private String Usuario; 
    private String contrasena;
    private int idPersona;

    public Usuario() {
    }

    public Usuario(int idusuario, String Usuario, String contrasena, int idPersona) {
        this.idusuario = idusuario;
        this.Usuario = Usuario;
        this.contrasena = contrasena;
        this.idPersona = idPersona;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsername() {
        return Usuario;
    }

    public void setUsername(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    

    
}
