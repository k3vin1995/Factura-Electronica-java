    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Direccion;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author svarg
 */
public class DireccionGestion {

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
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_DIRECCION_GESTION.GET_ALL_DIRECCION(?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //PARA usar cursores
            cs.registerOutParameter(1,OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs;
            rs = (ResultSet) cs.getObject(1);
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
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_DIRECCION_GESTION.GET_SPECIFIC_DIRECCION(?,?,?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //PARA usar cursores
            cs.setInt(1, iddireccion);
            cs.setString(2, provincia);
            cs.registerOutParameter(3,OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs;
            rs = (ResultSet) cs.getObject(3);
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
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_DIRECCION_GESTION.INSERT_DIRECCION(?,?,?,?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //PARA usar cursores
            cs.setString(1, direccion.getProvincia());
            cs.setString(2, direccion.getCanton());
            cs.setString(3, direccion.getDistrito());
            cs.setString(4, direccion.getResena());
            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DireccionGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean updateDireccion(Direccion direccion) {
        try {
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_DIRECCION_GESTION.UPDATE_DIRECCION(?,?,?,?,?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //PARA usar cursores
            cs.setString(1, direccion.getProvincia());
            cs.setString(2, direccion.getCanton());
            cs.setString(3, direccion.getDistrito());
            cs.setString(4, direccion.getResena());
            cs.setInt(5, direccion.getIddireccion());
            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DireccionGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteDireccion(Direccion direccion) {
        try {
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_DIRECCION_GESTION.DELETE_DIRECCION(?,?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //PARA usar cursores
            cs.setInt(1, direccion.getIddireccion());
            cs.setString(2, direccion.getProvincia());
            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DireccionGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
