/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import model.Conexion;
import model.Estudiante;
import model.YearGender;
import java.io.StringWriter;

/**
 *
 * @author User
 */
public class EstudianteGestion {

    private static final String SQL_SELECT_ESTUDIANTES = "Select * from Estudiante";
    private static final String SQL_INSERT_ESTUDIANTES = "INSERT INTO ESTUDIANTE VALUES(?,?,?,?,?,?,?)";
    private static final String SQL_SELECT_ESTUDIANTE = "Select * from Estudiante WHERE id=?";
    private static final String SQL_UPDATE_ESTUDIANTE = "UPDATE ESTUDIANTE SET nombre=?,apellido1=?,apellido2=?,fechaNaci=?,fechaIngr=?,genero=? where id=?";
    private static final String SQL_DELETE_ESTUDIANTE = "DELETE from Estudiante WHERE id=?";
    private static final String SQL_INGRESO_YEAR_GENDER = "SELECT YEAR(fechaIngr) AS  Fecha,genero,count(*) AS suma FROM ESTUDIANTE group by YEAR(fechaIngr),genero";

    public static ArrayList<Estudiante> getEstudiantes() {
        ArrayList<Estudiante> lista = new ArrayList<>();
        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_SELECT_ESTUDIANTES);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                lista.add(new Estudiante(
                        datos.getString(1),
                        datos.getString(2),
                        datos.getString(3),
                        datos.getString(4),
                        datos.getDate(5),
                        datos.getDate(6),
                        datos.getString(7).charAt(0)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;

    }

    public static boolean insertar(Estudiante estudiante) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERT_ESTUDIANTES);
            sentencia.setString(1, estudiante.getId());
            sentencia.setString(2, estudiante.getNombre());
            sentencia.setString(3, estudiante.getApellido1());
            sentencia.setString(4, estudiante.getApellido2());
            sentencia.setObject(5, estudiante.getFechaNaci());
            sentencia.setObject(6, estudiante.getFechaIngr());
            sentencia.setString(7, "" + estudiante.getGenero());
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(EstudianteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean modificar(Estudiante estudiante) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_UPDATE_ESTUDIANTE);
            sentencia.setString(1, estudiante.getNombre());
            sentencia.setString(2, estudiante.getApellido1());
            sentencia.setString(3, estudiante.getApellido2());
            sentencia.setObject(4, estudiante.getFechaNaci());
            sentencia.setObject(5, estudiante.getFechaIngr());
            sentencia.setString(6, "" + estudiante.getGenero());
            sentencia.setString(7, estudiante.getId());
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(EstudianteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean eliminar(Estudiante estudiante) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_DELETE_ESTUDIANTE);
            sentencia.setString(1, estudiante.getId());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static Estudiante getEstudiante(String id) {
        Estudiante estudiante = null;
        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_SELECT_ESTUDIANTE);
            consulta.setString(1, id);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                estudiante = new Estudiante(
                        datos.getString(1),
                        datos.getString(2),
                        datos.getString(3),
                        datos.getString(4),
                        datos.getDate(5),
                        datos.getDate(6),
                        datos.getString(7).charAt(0));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return estudiante;
    }

    //Fill ChartAreaView
    public static ArrayList<YearGender> getIngresosYearGender() {
        ArrayList<YearGender> lista = new ArrayList<>();
        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_INGRESO_YEAR_GENDER);
            ResultSet datos = consulta.executeQuery();
            while (datos.next()) {
                lista.add(new YearGender(
                        datos.getInt(1),
                        datos.getString(2),
                        datos.getInt(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public static String generarJson() {
        Estudiante estudiante = null;
        String tiraJson = "";
        String fechaNaci;
        String fechaIngr;
        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_SELECT_ESTUDIANTES);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                estudiante = new Estudiante(
                        datos.getString(1),
                        datos.getString(2),
                        datos.getString(3),
                        datos.getString(4),
                        datos.getDate(5),
                        datos.getDate(6),
                        datos.getString(7).charAt(0));

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                fechaNaci = format.format(estudiante.getFechaNaci());
                fechaIngr = format.format(estudiante.getFechaIngr());

                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objetoJson = creadorJson.add("id", estudiante.getId())
                        .add("nombre", estudiante.getNombre())
                        .add("apellido1", estudiante.getApellido1())
                        .add("apellido2", estudiante.getApellido2())
                        .add("fechaNacimiento", fechaNaci)
                        .add("fechaIngreso", fechaIngr).build();

                StringWriter tira = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(tira);
                jsonWriter.writeObject(objetoJson);
                if (tiraJson == null) {
                    tiraJson = tira.toString() + "\n";
                } else {
                    tiraJson = tiraJson + tira.toString() + "\n";
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(EstudianteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }

}
