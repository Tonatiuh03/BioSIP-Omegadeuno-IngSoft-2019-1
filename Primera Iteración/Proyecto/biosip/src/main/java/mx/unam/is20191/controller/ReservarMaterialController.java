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

    private List<Material> listaPrestamo;
    private List<Material> listaPrestamoUnica;
    private boolean confirmarPrestamo;

    public ReservarMaterialController() {
        this.listaPrestamo = new ArrayList<Material>();
        this.listaPrestamoUnica = new ArrayList<Material>();
        this.confirmarPrestamo = false;
    }

    public List<Material> getListaPrestamo() {
        return listaPrestamo;
    }

    public void setListaPrestamo(List<Material> listaPrestamo) {
        this.listaPrestamo = listaPrestamo;
    }

    public List<Material> getListaPrestamoUnica() {
        return listaPrestamoUnica;
    }

    public void setListaPrestamoUnica(List<Material> listaPrestamoUnica) {
        this.listaPrestamoUnica = listaPrestamoUnica;
    }

    public List<Material> getMateriales() {
        MaterialDao matdao = new MaterialDao();
        return matdao.getMateriales();
    }

    public boolean isConfirmarPrestamo() {
        return confirmarPrestamo;
    }

    public void setConfirmarPrestamo(boolean confirmarPrestamo) {
        this.confirmarPrestamo = confirmarPrestamo;
    }

    
    
    public void generarPrestamo() throws Exception{
        System.out.println("Se cambiará a "+ !this.confirmarPrestamo);
        this.confirmarPrestamo = !this.confirmarPrestamo;
    }
    
    public void agregar(Material m) {
        this.listaPrestamo.add(m);
        if (!this.listaPrestamoUnica.contains(m)) {
            this.listaPrestamoUnica.add(m);
        }
    }

    public void eliminar(Material m) throws Exception{
        this.listaPrestamo.remove(m);
        if (!this.listaPrestamo.contains(m)) {
            this.listaPrestamoUnica.remove(m);
        }
    }

    public int contarMateriales(Material m) throws Exception {
        int cont = 0;
        for (Material material : listaPrestamo) {
            if (material.equals(m)) {
                cont++;
            }
        }
        return cont;
    }
    
    public boolean hayDispobibles(Material m) throws Exception{
        if(m.getDisponibles() > this.contarMateriales(m)){
            return true;
        }else{
            return false;
        }
    }
}
