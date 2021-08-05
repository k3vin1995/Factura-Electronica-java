/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.usuarioCRUD;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author svarg
 */
public class UsuarioCRUDgestion {
    
       private static final String SQL_GETALLUSERS = "Select usuario.*, persona.nombre as 'nombrepersona' FROM usuario INNER JOIN persona on usuario.idpersonausuario = persona.idpersona;";
       private static final String SQL_GetUnSoloUser = "Select * from usuario where idusuario=?";
       private static final String SQL_UpdateUser = "UPDATE proyectodb.usuario SET contrasena=?, idpersonausuario=?  WHERE idusuario = ?";
       private static final String SQL_ELIMINARPERSONA = "DELETE FROM usuario where idusuario=?";

       public static ArrayList<usuarioCRUD> getUsuarios() {
        ArrayList<usuarioCRUD> lista = new ArrayList<>();
                
        try {
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_USUARIOS.GET_ALL_USUARIOS(?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.registerOutParameter(1,OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs;
            rs = (ResultSet) cs.getObject(1);
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
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_USUARIOS.GET_UN_USUARIO(?,?)}";
            CallableStatement cs = cn.prepareCall(sql);
            cs.setInt(1, id);
            cs.registerOutParameter(2,OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs;
            rs = (ResultSet) cs.getObject(2);
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
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_USUARIOS.UPDATEUSER(?,?,?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //Por si quieres usar cursores         
            cs.setString(1, user.getUsuario());
            cs.setString(2, user.getContrasena());
            cs.setInt(3, user.getIdusuario());
            
            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioCRUDgestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }      
        return false;
    }
    
    public static boolean deleteUsuario(usuarioCRUD user) {
        try {
            
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_USUARIOS.DELETE_USUARIO(?)}";
            CallableStatement cs = cn.prepareCall(sql);
            cs.setInt(1, user.getIdusuario());
            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioCRUDgestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

}



