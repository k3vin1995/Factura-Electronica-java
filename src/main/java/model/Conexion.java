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
 * @author E1287240 proyecto
 */
public class Conexion {

    private static Conexion conexion;
    private static final String DBURLKEVIN = "jdbc:mysql://localhost:3306/proyectodb?serverTimezone=UTC"; 
    private static final String DBURLSEBAS = "jdbc:mysql://localhost:3306/proyectodb?zeroDateTimeBehavior=CONVERT_TO_NULL"; 
    private static final String DBURLGABY = "jdbc:mysql://localhost:3306/proyectodb?zeroDateTimeBehavior=CONVERT_TO_NULL"; 
    private static final String DBURLDIAZ = "jdbc:mysql://localhost:3306/proyectodb?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static Connection conn = null;
    String kevin = "NVpfnuNQNH23dp";
    String sebas = "admin@123";
    String gaby = "gaby1004211096";
    String diaz = "27Agosto1991";

    private Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver")
                    .getDeclaredConstructor()
                    .newInstance();

            conn = DriverManager.getConnection(DBURLKEVIN, "root",kevin );


        } catch (ClassNotFoundException | SQLException
                | NoSuchMethodException | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException ex) {

            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*Singleton Patron de Diseño*/
    public static synchronized Connection getConexion() {
        if (conexion == null) {
            conexion = new Conexion();
        }
        return conn;
    }

}
