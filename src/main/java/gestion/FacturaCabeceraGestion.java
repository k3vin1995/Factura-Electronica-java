/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import com.mysql.cj.jdbc.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import model.Conexion;
import model.FacturaCabecera;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.SQLException;  
import oracle.jdbc.OracleTypes;

public class FacturaCabeceraGestion {
  
    private static final String SQL_GetFacturaCabecera = "SELECT facturacabecera.*, persona.nombre, persona.apellido, persona.apellido2 , usuario.usuario, tipopago.descripcion FROM facturacabecera " +
                        "INNER JOIN persona ON facturacabecera.idcliente = persona.idpersona " +
                        "INNER JOIN usuario ON facturacabecera.idemisor = usuario.idusuario " +
                        "INNER JOIN tipopago ON facturacabecera.idtipopago = tipopago.idtipopago;";
    
    //private static final String SQL_InsertFacturaCabecera= "insert into facturacabecera(idcliente,idemisor,estado,fechafacturado,tipocambio,totalfactura,iddetallefactura,idtipopago) values(?,?,?,?,?,?,?,?);";
    
    private static final String SQL_GetLastfacturaCabecera = "select max(facturacabecera.idfacturacabecera) from facturacabecera;";
    
    private static final String SQL_UNAFACTURA = "SELECT facturacabecera.*, persona.nombre, persona.apellido, persona.apellido2 , usuario.usuario, tipopago.descripcion FROM facturacabecera " +
                        "INNER JOIN persona ON facturacabecera.idcliente = persona.idpersona " +
                        "INNER JOIN usuario ON facturacabecera.idemisor = usuario.idusuario " +
                        "INNER JOIN tipopago ON facturacabecera.idtipopago = tipopago.idtipopago WHERE facturacabecera.idfacturacabecera=?";
    private static final String SQL_UPDATEFACTURA = "UPDATE facturacabecera SET  idCliente=?, idEmisor=?, estado=?, fechaFacturado=?, tipoCambio=?, totalFactura=?, idTipoPago=? WHERE idFacturaCabecera=?";
   private static final String SQL_IMPRIMEFACTURA = "SELECT * from facturacabecera WHERE idFacturaCabecera=?";
    
    
   private static int ultimaIDFacturaCabecera;
    
    
    
   public static ArrayList<FacturaCabecera> getFacturas() {
        ArrayList<FacturaCabecera> lista = new ArrayList<>();
                
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GetFacturaCabecera);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                lista.add(new FacturaCabecera(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getString(13),
                        rs.getDouble(7),
                        rs.getString(12),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11)
                ));
            }


        } catch (SQLException ex) {
            Logger.getLogger(FacturaCabeceraGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
   
   public static FacturaCabecera getUnaFactura (int idFacturaCabecera){
        FacturaCabecera factura = null;
        try {
            //PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_UNAFACTURA);
            
            CallableStatement callableStatement = (CallableStatement) Conexion.getConexion().prepareCall("begin GET_UNA_FACTURA(?); end;");

            callableStatement.setInt(1, idFacturaCabecera);
            ResultSet rs = callableStatement.executeQuery();
            while (rs != null && rs.next()) {
                factura = new FacturaCabecera(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getString(8),
                        rs.getDouble(7),
                        rs.getString(12),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11)
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FacturaCabeceraGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return factura;
        
    }
   
   public static boolean updateFactura(FacturaCabecera factura){
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_UPDATEFACTURA);
            sentencia.setInt(8, factura.getIdFacturaCabecera());
            sentencia.setInt(1, factura.getIdCliente());
            sentencia.setString(2, factura.getIdEmisor());
            sentencia.setString(3, factura.getEstado());            
            sentencia.setObject(4, factura.getFechaFacturado());
            sentencia.setString(5, factura.getTipoCambio());
            sentencia.setDouble(6, factura.getTotalFactura());
            sentencia.setString(7, factura.getIdTipoPago());            
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FacturaCabeceraGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }      
        return false;
    }
   
   
   
   
    public static int getLastInsert() {
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GetLastfacturaCabecera);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                ultimaIDFacturaCabecera = (
                        rs.getInt(1)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturaCabeceraGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return ultimaIDFacturaCabecera+1;
    }
    
    public static FacturaCabecera getImprimeFactura(int idfacturacabecera) {
        FacturaCabecera factura = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_IMPRIMEFACTURA);
            sentencia.setInt(1, idfacturacabecera);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                factura = new FacturaCabecera(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getString(8)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(FacturaCabeceraGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return factura;

    }
    
    
    
    
}
