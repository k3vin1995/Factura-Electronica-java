/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java .sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Usuario;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author E1287240 TareaSemana03
 */
public class UsuarioGestion {
   
    private static int ultimoIDPersona;
    
    public static Usuario getUsuario(String usuario, String password) {
        Usuario user = null;
        try {
            
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_USUARIOS.GET_USUARIO_PW(?,?,?)}";
            CallableStatement cs =cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.setString(1,usuario);
            cs.setString(2,password);
            cs.registerOutParameter(3,OracleTypes.CURSOR);
            cs.executeUpdate();
            ResultSet rs = (ResultSet) cs.getObject(3);
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
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_USUARIOS.GET_ALL_USUARIO(?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.registerOutParameter(1,OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs;
            rs = (ResultSet) cs.getObject(1);
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
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_USUARIOS.GET_UN_USUARIO(?,?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.setInt(1,username1);
            cs.registerOutParameter(2,OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs;
            rs = (ResultSet) cs.getObject(2);
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
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_USUARIOS.INSERT_USUARIO(?,?,?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.setString(1, user.getUsername());
            cs.setString(2, user.getContrasena());
            cs.setInt(3, getLastInsert());
            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean updateUsuario(Usuario user){
        try {
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_USUARIOS.UPDATEUSER(?,?,?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.setString(1, user.getUsername());
            cs.setString(2, user.getContrasena());
            cs.setInt(3, user.getIdPersona());           
            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }      
        return false;
    }
    
    public static boolean deleteUsuario(Usuario user) {
        try {
            
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_PERSONAS.DELETE_USUARIO(?)}";
            CallableStatement cs = cn.prepareCall(sql);
            cs.setInt(1, user.getIdusuario());
            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
     public static int getLastInsert() {
        try {
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_USUARIOS.GET_LAST(?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //PARA usar cursores
            cs.registerOutParameter(1,OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs;
            rs = (ResultSet) cs.getObject(1);
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
