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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Producto;

/**
 *
 * @author svarg
 */
@Named(value = "productoController")
@SessionScoped
public class ProductoController extends Producto implements Serializable {

    /**
     * Creates a new instance of ProductoController
     */
    public ProductoController() {
    }
    
    public List<Producto> getProductos() {
        return ProductoGestion.getProductos();
    }
    
    public String editaProducto(int idproducto) {
        Producto elProducto = ProductoGestion.getProducto(idproducto);
        if (elProducto != null) {
            this.setIdproducto(elProducto.getIdproducto());
            this.setDescripcion(elProducto.getDescripcion());
            this.setPrecio(elProducto.getPrecio());
            this.setIdtipoproducto(elProducto.getIdtipoproducto());
            this.setIva(elProducto.getIva());
            this.setUnidadmedida(elProducto.getUnidadmedida());
            return "editaProducto.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el registro del producto no exista");
            FacesContext.getCurrentInstance().addMessage("editaProductoForm:identificacion", msg);
            return "listaProducto.xhtml";
        }

    }

    public String insertProducto() {
        if (ProductoGestion.insertProducto(this)) {
            this.setIdproducto(0);
            this.setDescripcion(null);
            this.setPrecio(0);
            this.setIdtipoproducto(0);
            this.setIva(0);
            this.setUnidadmedida(null);
            return "listaProducto.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al insertar un nuevo producto");
            FacesContext.getCurrentInstance().addMessage("editaProductoFormRegistro:identificacion", msg);
            return "registroProducto.xhtml";

        }
    }

    public String updateProducto() {
            if (ProductoGestion.updateProducto(this)) {
            return "listaProducto.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Ocurrio un error al modificar el producto");
            FacesContext.getCurrentInstance().addMessage("editaProductoForm:identificacion", msg);
            return "editaProducto.xhtml";

        }
    }

    public String deleteProducto() {
        if (ProductoGestion.deleteProducto(this)) {
            return "listaProducto.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "No se puede eliminar, este producto ya se encuentra en una factura.");
            FacesContext.getCurrentInstance().addMessage("editaProductoForm:ID", msg);
            return "editaProducto.xhtml";

        }
    }
    
}
