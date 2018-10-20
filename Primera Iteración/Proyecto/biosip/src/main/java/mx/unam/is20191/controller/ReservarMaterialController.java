/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.controller;
/**
 *
 * @author dams_
 */
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import mx.unam.is20191.dao.CategoriaDao;
import mx.unam.is20191.dao.MaterialDao;
import mx.unam.is20191.dao.SubcategoriaDao;
import mx.unam.is20191.models.Material;
import mx.unam.is20191.models.Categoria;
import mx.unam.is20191.models.Subcategoria;

@ManagedBean
@SessionScoped
public class ReservarMaterialController {
    private List<Material> listaPrestamo = new ArrayList<Material>();
    private boolean vacia;
    
    public ReservarMaterialController(){
        listaPrestamo.add(new Material());
    }
    
    public List<Material> getListaPrestamo() {
        return listaPrestamo;
    }

    public void setListaPrestamo(List<Material> listaPrestamo) {
        this.listaPrestamo = listaPrestamo;
    }
    
    public List<Material> getMateriales() {
        MaterialDao matdao = new MaterialDao();
        return matdao.getMateriales();
    }
    
    public void agregarALista(Material material){
        listaPrestamo.add(material);
    }
    
    public boolean isEmpty(){
        return listaPrestamo.isEmpty();
    }

    public boolean isVacia() {
        listaPrestamo.add(new Material());
        return listaPrestamo.isEmpty();
    }

    public void setVacia(boolean vacia) {
        this.vacia = vacia;
    }

    
}
