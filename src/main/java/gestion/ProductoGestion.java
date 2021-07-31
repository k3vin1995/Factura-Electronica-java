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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Producto;
import model.TipoProducto;
import oracle.jdbc.OracleTypes;


/**
 *
 * @author svarg
 */
public class ProductoGestion {
    
    private static final String SQL_GETPRODUCTOS = "SELECT producto.*, tipoproducto.descripcion as 'Tipo producto' FROM producto " +
                                                "INNER JOIN tipoproducto ON producto.idtipoproducto = tipoproducto.idtipoProducto where tipoproducto.idtipoProducto = producto.idtipoProducto;";
    private static final String SQL_GETPRODUCTO = "SELECT producto.*, tipoproducto.descripcion 'Tipo producto' FROM producto " +
                                                  "INNER JOIN tipoproducto ON producto.idproducto = tipoproducto.idtipoProducto where idproducto=?;";
    
    
    private static final String SQL_GETPRODUCTOSTEST = "SELECT * FROM producto";
    private static final String SQL_TESTGETPRODUCTO = "SELECT * FROM producto where idproducto=?; ";
    
    private static final String SQL_GETTIPOSDEPRODUCTO = "SELECT * FROM tipoproducto";
    
    private static final String SQL_INSERTPRODUCTO = "INSERT INTO producto (descripcion,precio,idtipoproducto,iva,unidadmedida) VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATEPRODUCTO = "UPDATE producto set descripcion=?,precio=?,idtipoproducto=?,iva=?,unidadmedida=? where idproducto=?";
    private static final String SQL_DELETEPRODUCTO = "DELETE FROM producto where idproducto=?";

    public static ArrayList<Producto> getProductos() {
        ArrayList<Producto> listaProdu = new ArrayList<>();
         Connection cn = null;
         CallableStatement cs = null;
         
        try {
            cn = Conexion.getConexion();
            String sql = "{call PKG_PRODUCTO_GESTION.GET_PRODUCTO(?)}";
            cs = cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.registerOutParameter(1,OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs;
            rs = (ResultSet) cs.getObject(1);
            while (rs != null && rs.next()) {
                listaProdu.add(new Producto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getString(7)
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return listaProdu;

    }
    
        public static ArrayList<TipoProducto> getTipoProductos() {
        ArrayList<TipoProducto> listaTipoProdu = new ArrayList<>();
        Connection cn = null;
         CallableStatement cs = null;
         
        try {
            cn = Conexion.getConexion();
            String sql = "{call PKG_PRODUCTO_GESTION.GET_TIPO_PRODUCTO(?)}";
            cs = cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.registerOutParameter(1,OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs;
            rs = (ResultSet) cs.getObject(1);
            while (rs != null && rs.next()) {
                listaTipoProdu.add(new TipoProducto(
                        rs.getInt(1),
                        rs.getString(2)
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return listaTipoProdu;

    }
    
    public static Producto getProducto(int idproducto) {
        Producto producto = null;
        Connection cn = null;
         CallableStatement cs = null;
         
        try {
            cn = Conexion.getConexion();
            String sql = "{call PKG_PRODUCTO_GESTION.GET_SPECIFIC_PRODUCTO2(?,?)}";
            cs = cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.setInt(1,idproducto);
            cs.registerOutParameter(2,OracleTypes.CURSOR);
            cs.executeUpdate();
            ResultSet rs = (ResultSet) cs.getObject(2);
            while (rs != null && rs.next()) {
                producto = new Producto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getString(4)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return producto;

    }
    
    public static boolean insertProducto(Producto producto) {
        Connection cn = null;
        CallableStatement cs = null;
         
        try {
            cn = Conexion.getConexion();
            String sql = "{call PKG_PRODUCTO_GESTION.INSERT_PRODUCTO(?,?,?,?,?)}";
            cs = cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.setString(1, producto.getDescripcion());
            cs.setDouble(2, producto.getPrecio());
            cs.setInt(3, producto.getIdtipoproducto());
            cs.setDouble(4, producto.getIva());
            cs.setString(5, producto.getUnidadmedida());
            //cs.execute();
            return cs.executeUpdate()> 0;
//        try {
//            PreparedStatement sentencia = Conexion.getConexion()
//                    .prepareStatement(INSERT_PRODUCTO);
//            sentencia.setString(1, producto.getDescripcion());
//            sentencia.setDouble(2, producto.getPrecio());
//            sentencia.setInt(3, producto.getIdtipoproducto());
//            sentencia.setDouble(4, producto.getIva());
//            sentencia.setString(5, producto.getUnidadmedida());
//            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean updateProducto(Producto producto) {
        Connection cn = null;
        CallableStatement cs = null;
         
        try {
            cn = Conexion.getConexion();
            String sql = "{call PKG_PRODUCTO_GESTION.UPDATE_DIRECCION(?,?,?,?,?,?)}";
            cs = cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.setString(1, producto.getDescripcion());
            cs.setDouble(2, producto.getPrecio());
            cs.setInt(3, producto.getIdtipoproducto());
            cs.setDouble(4, producto.getIva());
            cs.setString(5, producto.getUnidadmedida());
            cs.setInt(6, producto.getIdproducto());
            return cs.executeUpdate()> 0;
//        try {
//            PreparedStatement sentencia = Conexion.getConexion()
//                    .prepareStatement(SQL_UPDATEPRODUCTO);
//            sentencia.setString(1, producto.getDescripcion());
//            sentencia.setDouble(2, producto.getPrecio());
//            sentencia.setInt(3, producto.getIdtipoproducto());
//            sentencia.setDouble(4, producto.getIva());
//            sentencia.setString(5, producto.getUnidadmedida());
//            sentencia.setInt(6, producto.getIdproducto());
//            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }

        public static boolean deleteProducto(Producto producto) {
         Connection cn = null;
         CallableStatement cs = null;
         
        try {
            cn = Conexion.getConexion();
            String sql = "{call PKG_PRODUCTO_GESTION.DELETE_PRODUCTO(?)}";
            cs = cn.prepareCall(sql);
            //Por si quieres usar cursores
            cs.setInt(1,producto.getIdproducto());
            return cs.executeUpdate()> 0;
//        try {
//            
//            PreparedStatement sentencia = Conexion.getConexion()
//                    .prepareStatement(SQL_DELETEPRODUCTO);
//            sentencia.setInt(1, producto.getIdproducto());
//            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoGestion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }
        
        
}

