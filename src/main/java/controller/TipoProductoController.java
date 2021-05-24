/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.ProductoGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import model.TipoProducto;

/**
 *
 * @author svarg
 */
@Named(value = "tipoProductoController")
@SessionScoped
public class TipoProductoController extends TipoProducto implements Serializable {

    /**
     * Creates a new instance of TipoProductoController
     */
    public TipoProductoController() {
    }
    
    public List<TipoProducto> getTipoProductos() {
        return ProductoGestion.getTipoProductos();
    }  
    
}
