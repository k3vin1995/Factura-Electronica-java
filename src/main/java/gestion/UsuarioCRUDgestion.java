/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.usuarioCRUD;

/**
 *
 * @author svarg
 */
public class UsuarioCRUDgestion {
    
       private static final String SQL_GETALLUSERS = "Select usuario.*, persona.nombre as 'nombrepersona' FROM usuario INNER JOIN persona on usuario.idpersonausuario = persona.idpersona;";
       private static final String SQL_GetUnSoloUser = "Select * from usuario where idusuario=?";
       private static final String SQL_UpdateUser = "UPDATE proyectodb.usuario SET usuario= ?, contrasena=?, idpersonausuario=?  WHERE idusuario = ?";
       private static final String SQL_ELIMINARPERSONA = "DELETE FROM usuario where idusuario=?";

       public static ArrayList<usuarioCRUD> getUsuarios() {
        ArrayList<usuarioCRUD> lista = new ArrayList<>();
                
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETALLUSERS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                lista.add(new usuarioCRUD(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioCRUDgestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(lista);
        return lista;
    }
       
       public static usuarioCRUD getUsuario (int id){
        usuarioCRUD user = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GetUnSoloUser);
            sentencia.setInt(1, id);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                user = new usuarioCRUD(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioCRUDgestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return user;

}
       
       public static boolean updateUsuario(usuarioCRUD user){
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_UpdateUser);            
            sentencia.setString(1, user.getUsuario());
            sentencia.setString(2, user.getContrasena());
            sentencia.setInt(3, user.getIdpersonausuario());
            sentencia.setInt(4, user.getIdusuario()); 
            
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioCRUDgestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }      
        return false;
    }
    
    public static boolean deleteUsuario(usuarioCRUD user) {
        try {
            
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_ELIMINARPERSONA);
            sentencia.setInt(1, user.getIdusuario());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioCRUDgestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

}



