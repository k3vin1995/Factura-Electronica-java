/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Prospecto;

/**
 *
 * @author User
 */
public class ProspectoGestion {

    public ProspectoGestion() {
    }
    private static final String SQL_INSERT_PROSPECTO = "insert into prospecto values (?,?,?,?,?,?,?,?,?)";
    private static final String SQL_SELECT_PROSPECTO = "Select * from prospecto WHERE CEDULA=?";

    public static boolean insertar(Prospecto prospecto) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERT_PROSPECTO);
            sentencia.setString(1, prospecto.getCedula());
            sentencia.setString(2, prospecto.getNombre());
            sentencia.setString(3, prospecto.getApellido1());
            sentencia.setString(4, prospecto.getApellido2());
            sentencia.setObject(5, prospecto.getFechaNacimiento());
            sentencia.setObject(6, prospecto.getFechaGraduacionColegio());
            sentencia.setObject(7, prospecto.getFechaPosibleIngreso());
            sentencia.setString(8, prospecto.getCorreo());
            sentencia.setString(9, prospecto.getCelular());
            int fila = sentencia.executeUpdate();
            return fila > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProspectoGestion.class.getName()).log(Level.SEVERE, null, ex);

        }

        return false;
    }

    public static Prospecto getProspecto(String id) {
        Prospecto prospecto = null;
        try {
            PreparedStatement consulta = Conexion.
                    getConexion().prepareStatement(SQL_SELECT_PROSPECTO);
            consulta.setString(1, id);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                prospecto = new Prospecto(
                        datos.getString(1),
                        datos.getString(2),
                        datos.getString(3),
                        datos.getString(4),
                        datos.getDate(5),
                        datos.getDate(6),
                        datos.getDate(7),
                        datos.getString(8),
                        datos.getString(9));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProspectoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prospecto;
    }

}
