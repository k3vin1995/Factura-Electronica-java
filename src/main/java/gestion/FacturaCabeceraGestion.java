/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.sql.CallableStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import model.Conexion;
import model.FacturaCabecera;
import java.sql.Connection; 
import java.sql.ResultSet; 
import java.sql.SQLException;  
import oracle.jdbc.OracleTypes;

public class FacturaCabeceraGestion {
      
   private static int ultimaIDFacturaCabecera;
  
    
   public static ArrayList<FacturaCabecera> getFacturas() {
        ArrayList<FacturaCabecera> lista = new ArrayList<>();
               
        try {
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_FACTURA_CABECERA_GESTION.GET_FACTURAS(?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.registerOutParameter(1,OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs;
            rs = (ResultSet) cs.getObject(1);
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
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_FACTURA_CABECERA_GESTION.GET_UNA_FACTURA(?,?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.setInt(1,idFacturaCabecera);
            cs.registerOutParameter(2,OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs;
            rs = (ResultSet) cs.getObject(2);
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
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_FACTURA_CABECERA_GESTION.UPDATE_FACTURA(?,?,?,?,?,?,?,?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.setInt(8, factura.getIdFacturaCabecera());
            cs.setInt(1, factura.getIdCliente());
            cs.setString(2, factura.getIdEmisor());
            cs.setString(3, factura.getEstado());            
            cs.setObject(4, factura.getFechaFacturado());
            cs.setString(5, factura.getTipoCambio());
            cs.setDouble(6, factura.getTotalFactura());
            cs.setString(7, factura.getIdTipoPago());            
            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FacturaCabeceraGestion.class.getName())
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
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_FACTURA_CABECERA_GESTION.GET_IMPRIME_UNA_FACTURA(?,?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.setInt(1,idfacturacabecera);
            cs.registerOutParameter(2,OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs;
            rs = (ResultSet) cs.getObject(2);
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
