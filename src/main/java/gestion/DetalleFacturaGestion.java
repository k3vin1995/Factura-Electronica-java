/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.DetalleFactura;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Usuario
 */
public class DetalleFacturaGestion {

    private static String SQL_INSERTAPRODUCTO = "INSERT INTO detallefactura (idproducto,cantidad,idFacturaFK) values(?,?,?);";
    private static String SQL_DELETEPRODUCTOFACTURA = "DELETE  FROM proyectodb.detallefactura WHERE iddetallefactura =?;";
    private static String SQL_ULTIMOIDFACTURA = "select max(detallefactura.idFactura) from detallefactura;";
    private static String SQL_GETPRODUCTOSFactura = "SELECT detallefactura.*, producto.idproducto as 'idProducto', producto.descripcion as 'Descripcion',"
            + " producto.precio * detallefactura.cantidad as 'Precio Subtotal', producto.iva as 'IVA',producto.unidadmedida as 'Medida' FROM detallefactura"
            + " INNER JOIN producto on detallefactura.idproducto = producto.idproducto and detallefactura.idFactura = ?;";
    
    private static final String SQL_InsertFacturaCabecera= "insert into facturacabecera(idcliente,idemisor,estado,fechafacturado,tipocambio,totalfactura,"
            + "idtipopago) values(?,?,?,?,?,?,?);";
    
    private static int ultimoID;
    static ArrayList<DetalleFactura> listaDetalleFactura = new ArrayList<>();
    static ArrayList<DetalleFactura> listaDetllaProductos = new ArrayList<>();
    
    
    private static String objDate (){
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");  

            String formattedDate = myDateObj.format(myFormatObj);
            
        return formattedDate;
    }
    //String strDateFormat = "hh: mm: ss a dd-MMM-aaaa";
    
    private static double subtotal; 


    
    
    
    public static boolean insertProducto(int idProducto, double cantidad) {
        try {
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_DETALLE_FACTURA.INSERTA_DETALLE_FACTURA(?,?,?)}";
            CallableStatement cs = cn.prepareCall(sql);
            cs.setInt(1, idProducto);
            cs.setDouble(2, cantidad);
            cs.setInt(3, getLastInsert());
            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public static int getLastInsert() {
        try {
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_DETALLE_FACTURA.ULTIMO_INSERT(?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //PARA usar cursores
            cs.registerOutParameter(1,OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs;
            rs = (ResultSet) cs.getObject(1);
            while (rs != null && rs.next()) {
                ultimoID = (rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DireccionGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return ultimoID;
    }
    public static ArrayList<DetalleFactura> getProductos() {
        ArrayList<DetalleFactura> listaProdu = new ArrayList<>();
        try {
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_DETALLE_FACTURA.GET_PRODUCTOS_FACTURA(?,?)}";
            CallableStatement cs = cn.prepareCall(sql);
            //PARA usar cursores
            cs.setInt(1, getLastInsert());
            cs.registerOutParameter(2,OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs;
            rs = (ResultSet) cs.getObject(2);
            while (rs != null && rs.next()) {
                listaProdu.add(new DetalleFactura(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getInt(3),
                        rs.getDouble(8),
                        rs.getString(9)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return listaProdu;
    }
    
     public static ArrayList<DetalleFactura> getArrayProductos() {      
        return listaDetllaProductos;
    }
     
    public static boolean deleteProductoFactura(int idDetalleFactura) {
        try {
            
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_DETALLE_FACTURA.DELETEPRODUCTOFACTURA(?)}";
            CallableStatement cs = cn.prepareCall(sql);
            cs.setInt(1, idDetalleFactura);
            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public static  ArrayList<DetalleFactura> insertListDetalleFactura(int idproducto, int cantidad, int idfactura){
        DetalleFactura detalle = new DetalleFactura();
        detalle.setIdProducto(idproducto);
        detalle.setCantidad(cantidad);
        detalle.setIdFactura(idfactura);
        listaDetalleFactura.add(detalle);        
        return listaDetalleFactura;
    }
    
    
    public static  ArrayList<DetalleFactura> insertListDetalleProducto(int idproducto, String descripcion, double precio, int cantidad, double iva, String unidadmedida){
        DetalleFactura detalleProducto = new DetalleFactura(); 
        detalleProducto.setIdDetalleFactura(listaDetllaProductos.size()+1);
        detalleProducto.setIdproducto(idproducto);
        detalleProducto.setDescripcion(descripcion);
        detalleProducto.setPrecio(precio * cantidad);
        detalleProducto.setCantidad(cantidad);
        detalleProducto.setIva(iva);
        detalleProducto.setUnidadmedida(unidadmedida);
        listaDetllaProductos.add(detalleProducto);
        subtotal = subtotal+((precio*cantidad)*1.13);
        return listaDetllaProductos;
     }
    
    public static ArrayList<DetalleFactura> RemoveArrayProducto(int idDetalleFactura,int cantidad) {        
        int tamano = listaDetllaProductos.size();
        DetalleFactura objeto = new DetalleFactura();
        for (int i = 0; i < tamano; i++) {
            objeto = listaDetllaProductos.get(i);
            if (objeto.getIdproducto() == idDetalleFactura && objeto.getCantidad() == cantidad) {
              listaDetllaProductos.remove(i);
              listaDetalleFactura.remove(i);
              break;
            }   
        }
        return listaDetllaProductos;
  }
    
    public static boolean insertaDetalleProductos(DetalleFactura detalleFactura) {
        try {
//            PreparedStatement cs = Conexion.getConexion()
//                    .prepareStatement(SQL_INSERTAPRODUCTO);
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_DETALLE_FACTURA.INSERTA_DETALLE_FACTURA(?,?,?)}";
            CallableStatement cs = cn.prepareCall(sql);
            
            int tamano = listaDetalleFactura.size();
            DetalleFactura objeto = new DetalleFactura();
            for (int i = 0; i < tamano; i++) {
            objeto = listaDetalleFactura.get(i);
            
            cs.setInt(1, objeto.getIdProducto());
            cs.setDouble(2, objeto.getCantidad());
            cs.setInt(3, objeto.getIdFactura());
            cs.executeUpdate();
        }
            return true;       
        } catch (SQLException ex) {
            Logger.getLogger(DetalleFacturaGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    // Metodos Factura Cabecera
    
        public static boolean insertaFacturaCabecera(int id, int idEmisor, int facturaCabeceraLastID, String tipocambio, int idtipopago) {
        try {
            Connection cn = Conexion.getConexion();
            String sql = "{call PKG_DETALLE_FACTURA.INSERTAFACTURACABECERA(?,?,?,?,?,?,?)}";
            CallableStatement cs = cn.prepareCall(sql);
            cs.setInt(1, id);
            cs.setInt(2, idEmisor);
            cs.setString(3, "Facturado");
            cs.setObject(4, objDate());  //objDate
            cs.setInt(5, 1);
            cs.setDouble(6, subtotal);
            cs.setInt(7, idtipopago);
            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FacturaCabeceraGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }
        public static void cleanArray(){
            listaDetllaProductos.clear();
            listaDetalleFactura.clear();
            
        }
        
        public static boolean facturavacia(){
            if (!listaDetalleFactura.isEmpty() && !listaDetllaProductos.isEmpty()) {
                return true;
            }
        return false;
        }
    
    
}