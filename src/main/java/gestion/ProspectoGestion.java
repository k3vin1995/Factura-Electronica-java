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
import model.Prospecto;

/**
 *
 * @author User
 */
public class ProspectoGestion {

    private static final String SQL_GETPROSPECTOS = "SELECT * FROM prospecto";
    private static final String SQL_GETPROSPECTO = "SELECT * FROM prospecto where id=? and idProspecto=?";
    private static final String SQL_INSERTPROSPECTO= "insert into prospecto(idProspecto,nombre,apellido1,apellido2,fechaNacimiento,fechaGraduacionColegio,fechaPosibleIngreso,correo,celular) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATEPROSPECTO = "Update prospecto set idProspecto=?,nombre=?,apellido1=?,apellido2=?,fechaNacimiento=?,fechaGraduacionColegio=?,fechaPosibleIngreso=?,correo=?,celular=? where id=? ";
    private static final String SQL_DELETEPROSPECTO = "DELETE FROM prospecto where id=? and idProspecto=?";

    //Metodo encargado de traer todos los prospectos
    public static ArrayList<Prospecto> getProspectos() {
        ArrayList<Prospecto> lista = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETPROSPECTOS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                lista.add(new Prospecto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        rs.getDate(8),
                        rs.getString(9).charAt(0),
                        rs.getString(10)
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProspectoGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return lista;

    }

    //Metodo encargado de traer un estudiante
    public static Prospecto getProspecto(int id, String idProspecto) {
        Prospecto prospecto = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETPROSPECTO);
            sentencia.setInt(1, id);
            sentencia.setString(2, idProspecto);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                prospecto = new Prospecto(
                         rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        rs.getDate(8),
                        rs.getString(9).charAt(0),
                        rs.getString(10)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProspectoGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return prospecto;

    }

    public static boolean insertProspecto(Prospecto prospecto) {
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_INSERTPROSPECTO);
            sentencia.setString(1, prospecto.getIdProspecto());
            sentencia.setString(2, prospecto.getNombre());
            sentencia.setString(3, prospecto.getApellido1());
            sentencia.setString(4, prospecto.getApellido2());
            sentencia.setObject(5, prospecto.getFechaNacimiento());
            sentencia.setObject(6, prospecto.getFechaGraduacionColegio());
            sentencia.setObject(7, prospecto.getFechaPosibleIngreso());
            sentencia.setString(8, "" + prospecto.getCorreo());
            sentencia.setString(9, prospecto.getCelular());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProspectoGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean updateProspecto(Prospecto prospecto) {
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_UPDATEPROSPECTO);
            sentencia.setString(1, prospecto.getIdProspecto());
            sentencia.setString(2, prospecto.getNombre());
            sentencia.setString(3, prospecto.getApellido1());
            sentencia.setString(4, prospecto.getApellido2());
            sentencia.setObject(5, prospecto.getFechaNacimiento());
            sentencia.setObject(6, prospecto.getFechaGraduacionColegio());
            sentencia.setObject(7, prospecto.getFechaPosibleIngreso());
            sentencia.setString(8, "" + prospecto.getCorreo());
            sentencia.setString(9, prospecto.getCelular());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProspectoGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteProspecto(Prospecto prospecto) {
        try {
            
//            DELETE FROM prospecto where id=? and idEstudiante=?
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_DELETEPROSPECTO);
            sentencia.setInt(1, prospecto.getId());
            sentencia.setString(2, prospecto.getIdProspecto());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProspectoGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
