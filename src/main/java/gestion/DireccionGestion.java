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
import model.Direccion;

/**
 *
 * @author svarg
 */
public class DireccionGestion {

    private static final String SQL_GETDIRECCIONES = "SELECT * FROM direccion";
    private static final String SQL_GETDIRECCION = "SELECT * FROM direccion where iddireccion=? and provincia=?";
    private static final String SQL_INSERDIRECCION = "insert into direccion(provincia,canton,distrito,resena) VALUES (?,?,?,?)";
    private static final String SQL_INSERDIRECCION2 = "insert into direccion(provincia,canton,distrito,resena) VALUES (?,?,?,?); select last_isert_id();";

    private static final String SQL_UPDATEDIRECCION = "Update direccion set provincia=?, canton=?,distrito=?,resena=? where iddireccion=?";
    private static final String SQL_DELETEDIRECCION = "DELETE FROM direccion where iddireccion=? and provincia=?";
    private static int iddireccionprueba = 0;

    public static int getIddireccionprueba() {
        return iddireccionprueba;
    }

    public static void setIddireccionprueba(int iddireccionprueba) {
        DireccionGestion.iddireccionprueba = iddireccionprueba;
    }

    public static ArrayList<Direccion> getDirecciones() {
        ArrayList<Direccion> listaDire = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETDIRECCIONES);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                listaDire.add(new Direccion(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DireccionGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return listaDire;

    }

    public static Direccion getDireccion(int iddireccion, String provincia) {
        Direccion direccion = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETDIRECCION);
            sentencia.setInt(1, iddireccion);
            sentencia.setString(2, provincia);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                direccion = new Direccion(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(DireccionGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return direccion;

    }

    public static boolean insertDireccion(Direccion direccion) {
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_INSERDIRECCION);
            sentencia.setString(1, direccion.getProvincia());
            sentencia.setString(2, direccion.getCanton());
            sentencia.setString(3, direccion.getDistrito());
            sentencia.setString(4, direccion.getResena());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DireccionGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean updateDireccion(Direccion direccion) {
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_UPDATEDIRECCION);
            sentencia.setString(1, direccion.getProvincia());
            sentencia.setString(2, direccion.getCanton());
            sentencia.setString(3, direccion.getDistrito());
            sentencia.setString(4, direccion.getResena());
            sentencia.setInt(5, direccion.getIddireccion());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DireccionGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteDireccion(Direccion direccion) {
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_DELETEDIRECCION);
            sentencia.setInt(1, direccion.getIddireccion());
            sentencia.setString(2, direccion.getProvincia());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DireccionGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
