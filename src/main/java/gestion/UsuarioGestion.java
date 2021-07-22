/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.sql.PreparedStatement;
import java .sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Persona;
import model.Usuario;

/**
 *
 * @author E1287240 TareaSemana03
 */
public class UsuarioGestion {

    private static final String SQL_GetUsuario = "Select * from usuario where usuario=? and contrasena=?";
    
    private static final String SQL_GetUsuarioAlt = "SELECT usuario.*, persona.* FROM usuario " +
"INNER JOIN  persona ON usuario.idpersonausuario = persona.idpersona WHERE usuario.usuario=? AND usuario.contrasena=?;";
    
    private static final String SQL_GETALLUSERS = "Select * from usuario";
    private static final String SQL_GetUnSoloUser = "Select * from usuario where idusuario=?";
    private static final String SQL_GetLastPersona = "select max(persona.idpersona) from persona;";       
    private static final String SQL_INSERTUSER =  "INSERT INTO USUARIO (usuario,contrasena,idpersonausuario) VALUES (?,?,?);";
    private static final String SQL_UpdateUser = "UPDATE proyectodb.usuario SET idusuario= ?, usuario= ?, contrasena=?, idpersonausuario=?  WHERE idusuario = ?";
    private static final String SQL_ELIMINARPERSONA = "DELETE FROM persona where idusuario=?";
    
    private static int ultimoIDPersona;
    
    public static Usuario getUsuario(String usuario, String password) {
        Usuario user = null;
        try {
            
             PreparedStatement stmt = Conexion.getConexion().prepareStatement("SELECT usuario.*, persona.* FROM usuario INNER JOIN  persona ON usuario.idpersonausuario = persona.idpersona WHERE usuario.usuario=? AND usuario.contrasena=?");
            //step4 execute query
             //ResultSet rs = stmt.executeQuery("SELECT usuario.*, persona.* FROM usuario INNER JOIN  persona ON usuario.idpersonausuario = persona.idpersona WHERE usuario.usuario='kevin' AND usuario.contrasena=123");
            
            //PreparedStatement sentencia = Conexion.getConexion().prepareStatement("SELECT usuario.*, persona.* FROM usuario INNER JOIN  persona ON usuario.idpersonausuario = persona.idpersona WHERE usuario.usuario=? AND usuario.contrasena=?;");
            stmt.setString(1, usuario);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new Usuario();
                user.setIdusuario(rs.getInt(1));
                user.setUsername(usuario);
                user.setContrasena(password);                 
                user.setIdPersona(rs.getInt(4));
                user.setId(rs.getInt(5));
                user.setNombre(rs.getString(6));
                user.setApellido(rs.getString(7));
                user.setApellido2(rs.getString(8));
                user.setTelefono(rs.getString(9));                
                user.setCorreo(rs.getString(10));
                user.setCedula(rs.getString(11));
                user.setFechaNacimiento(rs.getDate(12));
                user.setTipoPersona(rs.getString(14));
                //llenado datos
                user.setProvincia(rs.getString(6));
                user.setCanton(rs.getString(6));
                user.setDistrito(rs.getString(6));
                user.setResena(rs.getString(6));
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public static ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();
                
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETALLUSERS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                lista.add(new Usuario(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(lista);
        return lista;
    }
    
    public static Usuario getUsuario (int username1){
        Usuario user = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GetUnSoloUser);
            sentencia.setInt(1, username1);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                user = new Usuario(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return user;

}
    
    public static boolean insertaUsuario(Usuario user) {
        try {
            PersonaGestion.insertaPersonaUsuario(user);
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERTUSER);
           
            sentencia.setString(1, user.getUsername());
            sentencia.setString(2, user.getContrasena());
            sentencia.setInt(3, getLastInsert());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean updateUsuario(Usuario user){
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_UpdateUser);
            sentencia.setInt(1, user.getIdusuario());
            sentencia.setString(1, user.getContrasena());
            sentencia.setString(2, user.getUsername());
            sentencia.setInt(3, user.getIdPersona());           
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }      
        return false;
    }
    
    public static boolean deleteUsuario(Usuario user) {
        try {
            
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_ELIMINARPERSONA);
            sentencia.setInt(1, user.getIdusuario());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
     public static int getLastInsert() {
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GetLastPersona);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                ultimoIDPersona = (
                        rs.getInt(1)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DireccionGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return ultimoIDPersona;
    }
    
}
