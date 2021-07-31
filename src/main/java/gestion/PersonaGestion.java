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

    private static final String SQL_GetPersonas = "SELECT PERSONA.*, DIRECCION.PROVINCIA , DIRECCION.CANTON,DIRECCION.DISTRITO , DIRECCION.RESENA FROM PERSONA INNER JOIN DIRECCION ON PERSONA.DIRECCIONFK = DIRECCION.IDDIRECCION where PERSONA.DIRECCIONFK= PERSONA.DIRECCIONFK";

    private static final String SQL_InsertPersona = "SELECT persona.*, direccion.provincia 'provincia', direccion.canton 'canton',direccion.distrito 'distrito', direccion.resena 'resena' FROM persona INNER JOIN direccion ON persona.iddireccion = direccion.iddireccion where persona.cedula=?;";

    private static final String SQL_InsertDireccion = "insert into persona(nombre,apellido,apellido2,telefono,correoElectronico,cedula,fechanacimiento,iddireccion,tipopersona) values(?,?,?,?,?,?,?,?,?);";
    private static final String SQL_GetLastDireccion = "select max(direccion.iddireccion) from direccion;";

    private static final String SQL_GetPersona = "SELECT persona.*, direccion.provincia 'provincia', direccion.canton 'canton',direccion.distrito 'distrito', direccion.resena 'resena' FROM persona INNER JOIN direccion ON persona.iddireccion = direccion.iddireccion where persona.cedula=?;";
    private static final String SQL_INSERDIRECCION2 = "insert into direccion(provincia,canton,distrito,resena) VALUES (?,?,?,?);";
    private static final String SQL_UPDATEPERSONA = "UPDATE proyectodb.persona SET nombre= ?, apellido=?, apellido2=?,telefono= ?, correoElectronico= ?, cedula=?, fechaNacimiento=? ,tipopersona= ? WHERE idpersona = ?";
    private static final String SQL_ELIMINARPERSONA = "DELETE FROM persona where idpersona=?";
    private static final String SQL_ELIMINARPERSONACLIENTE = "DELETE FROM cliente where idpersonacliente=?;";
    private static final String SQL_ELIMINARPERSONAUSUARIO = "DELETE FROM usuario where idpersonausuario=?;";
    private static int ultimoID;
    

    public static ArrayList<Persona> getPersonas() {
        ArrayList<Persona> lista = new ArrayList<>();
         
         Connection cn = null;
         CallableStatement cs = null;
         
        try {
            cn = Conexion.getConexion();
            String sql = "{call PKG_PERSONAS.LISTAR_PERSONAS(?)}";
            cs = cn.prepareCall(sql);
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
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GetPersona);
            sentencia.setString(1, cedula);
            ResultSet rs = sentencia.executeQuery();
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
            insertDireccion(persona);
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_InsertDireccion);
            sentencia.setString(1, persona.getNombre());
            sentencia.setString(2, persona.getApellido());
            sentencia.setString(3, persona.getApellido2());
            sentencia.setString(4, persona.getTelefono());
            sentencia.setString(5, persona.getCorreo());
            sentencia.setString(6, persona.getCedula());
            sentencia.setObject(7, persona.getFechaNacimiento());
            sentencia.setInt(8, getLastInsert());
            sentencia.setString(9, "Cliente");
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DireccionGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean insertDireccion(Persona persona) {
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_INSERDIRECCION2);
            sentencia.setString(1, persona.getProvincia());
            sentencia.setString(2, persona.getCanton());
            sentencia.setString(3, persona.getDistrito());
            sentencia.setString(4, persona.getResena());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DireccionGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static int getLastInsert() {
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GetLastDireccion);
            ResultSet rs = sentencia.executeQuery();
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
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_UPDATEPERSONA);
            sentencia.setInt(9, persona.getId());
            sentencia.setString(1, persona.getNombre());
            sentencia.setString(2, persona.getApellido());
            sentencia.setString(3, persona.getApellido2());
            sentencia.setString(4, persona.getTelefono());
            sentencia.setString(5, persona.getCorreo());
            sentencia.setString(6, persona.getCedula());
            sentencia.setObject(7, persona.getFechaNacimiento());
            sentencia.setString(8, persona.getTipoPersona());
            return sentencia.executeUpdate() > 0;
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
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_ELIMINARPERSONA);
            sentencia.setInt(1, persona.getId());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PersonaGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteCliente(int idPersonaCliente) {
        try {

            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_ELIMINARPERSONACLIENTE);
            sentencia.setInt(1, idPersonaCliente);
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PersonaGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteUsuario(int idPersonaUsuario) {
        try {

            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_ELIMINARPERSONAUSUARIO);
            sentencia.setInt(1, idPersonaUsuario);
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PersonaGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean insertaPersonaUsuario(Persona persona) {
        try {
            insertDireccion(persona);
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_InsertDireccion);
            sentencia.setString(1, persona.getNombre());
            sentencia.setString(2, persona.getApellido());
            sentencia.setString(3, persona.getApellido2());
            sentencia.setString(4, persona.getTelefono());
            sentencia.setString(5, persona.getCorreo());
            sentencia.setString(6, persona.getCedula());
            sentencia.setObject(7, persona.getFechaNacimiento());
            sentencia.setInt(8, getLastInsert());
            sentencia.setString(9, "Usuario");
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DireccionGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
