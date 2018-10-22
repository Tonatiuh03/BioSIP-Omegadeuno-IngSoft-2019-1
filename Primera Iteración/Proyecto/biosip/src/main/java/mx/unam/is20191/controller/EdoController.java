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
import mx.unam.is20191.dao.EdoDao;
import mx.unam.is20191.dao.SubcategoriaDao;
import mx.unam.is20191.models.Material;
import mx.unam.is20191.models.Categoria;
import mx.unam.is20191.models.Subcategoria;

@ManagedBean
@SessionScoped
public class ReservarMaterialController {

    private List<Material> listaPrestamo;
    private boolean confirmarPrestamo;

    public ReservarMaterialController() {
        this.listaPrestamo = new ArrayList<Material>();
        this.confirmarPrestamo = false;
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

    public boolean isConfirmarPrestamo() {
        return confirmarPrestamo;
    }

    public void setConfirmarPrestamo(boolean confirmarPrestamo) throws Exception {
        this.confirmarPrestamo = confirmarPrestamo;
    }
    
    public int contarMateriales(Material m) throws Exception {
        int cont = 0;
        for(Material material: listaPrestamo){
            if(material==m){
                cont++;
            }
        }
        return cont;
    }
}
