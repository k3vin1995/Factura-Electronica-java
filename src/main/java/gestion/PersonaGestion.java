/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Persona;
import oracle.jdbc.OracleType;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Usuario
 */
public class PersonaGestion {

    private static int ultimoID;
    

    public static ArrayList<Persona> getPersonas() {
        ArrayList<Persona> lista = new ArrayList<>();

         
        try {
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_PERSONAS.LISTAR_PERSONAS(?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.registerOutParameter(1,OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs;
            rs = (ResultSet) cs.getObject(1);
            while (rs != null && rs.next()) {
                lista.add(new Persona(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getDate(8),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(10)
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(lista);
        return lista;
    }

    public static Persona getPersona(String cedula) {
        Persona persona = null;
        try {
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_PERSONAS.GET_PERSONA(?,?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.setString(1,cedula);
            cs.registerOutParameter(2,OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs;
            rs = (ResultSet) cs.getObject(2);
            while (rs != null && rs.next()) {
                persona = new Persona(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getDate(8),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(10)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(PersonaGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return persona;

    }

    public static boolean insertaPersona(Persona persona) {
        try {
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_PERSONAS.INSERT_PERSONA(?,?,?,?,?,?,?,?,?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.setString(1, persona.getNombre());
            cs.setString(2, persona.getApellido());
            cs.setString(3, persona.getApellido2());
            cs.setString(4, persona.getTelefono());
            cs.setString(5, persona.getCorreo());
            cs.setString(6, persona.getCedula());
            cs.setObject(7, persona.getFechaNacimiento());
            cs.setInt(8, getLastInsert());
            cs.setString(9, "Cliente");
            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DireccionGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean insertDireccion(Persona persona) {
        try {
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_PERSONAS.INSERTDIRECCION(?,?,?,?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.setString(1, persona.getProvincia());
            cs.setString(2, persona.getCanton());
            cs.setString(3, persona.getDistrito());
            cs.setString(4, persona.getResena());
            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DireccionGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static int getLastInsert() {
        try {
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_FACTURA_CABECERA_GESTION.GET_LAST(?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //PARA usar cursores
            cs.registerOutParameter(1,OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs;
            rs = (ResultSet) cs.getObject(1);
            while (rs != null && rs.next()) {
                ultimoID = (rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DireccionGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return ultimoID;
    }

    public static boolean updatePersona(Persona persona) {
        try {
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_PERSONAS.UPDATEPERSONA(?,?,?,?,?,?,?,?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.setString(1, persona.getNombre());
            cs.setString(2, persona.getApellido());
            cs.setString(3, persona.getApellido2());
            cs.setString(4, persona.getTelefono());
            cs.setString(5, persona.getCorreo());
            cs.setString(6, persona.getCedula());
            cs.setObject(7, persona.getFechaNacimiento());
            cs.setString(8, persona.getTipoPersona());
            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DireccionGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deletePersona(Persona persona) {
        try {
            if (persona.getTipoPersona().equals("Usuario")) {
                deleteUsuario(persona.getId());
            } else {
                deleteCliente(persona.getId());
            }
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_PERSONAS.DELETE_person(?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.setInt(1,persona.getId());
            return cs.executeUpdate()> 0;
        } catch (SQLException ex) {
            Logger.getLogger(PersonaGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteCliente(int idPersonaCliente) {
        try {

            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_PERSONAS.DELETE_CLIENTE(?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.setInt(1, idPersonaCliente);
            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PersonaGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteUsuario(int idPersonaUsuario) {
        try {

            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_PERSONAS.DELETE_USUARIO(?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.setInt(1, idPersonaUsuario);
            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PersonaGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean insertaPersonaUsuario(Persona persona) {
        try {
            insertDireccion(persona);
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_PERSONAS.INSERT_PERSONA(?,?,?,?,?,?,?,?,?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.setString(1, persona.getNombre());
            cs.setString(2, persona.getApellido());
            cs.setString(3, persona.getApellido2());
            cs.setString(4, persona.getTelefono());
            cs.setString(5, persona.getCorreo());
            cs.setString(6, persona.getCedula());
            cs.setObject(7, persona.getFechaNacimiento());
            cs.setInt(8, getLastInsert());
            cs.setString(9, "Usuario");
            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DireccionGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
