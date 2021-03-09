/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Conexion {

    private static Conexion conexion;
    private static final String DBURL = "jdbc:derby://localhost:1527/ugeneral";
    private static Connection conn;

    private Conexion() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(DBURL, "root", "root");
        } catch (ClassNotFoundException | SQLException | InstantiationException
                | IllegalAccessException | NoSuchMethodException | SecurityException
                | IllegalArgumentException
                | InvocationTargetException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static synchronized Connection getConexion() {
        if (conn == null) {
            conexion = new Conexion();
        }
        return conn;
    }

}
