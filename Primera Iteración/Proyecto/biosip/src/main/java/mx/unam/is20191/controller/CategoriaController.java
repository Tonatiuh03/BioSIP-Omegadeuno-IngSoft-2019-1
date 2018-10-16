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

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import mx.unam.is20191.dao.CategoriaDao;
import mx.unam.is20191.models.Categoria;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class CategoriaController{
    
    private String nombre;
    private String descripcion;
    private boolean esSubCategoria;
    private final CategoriaDao CATEGORIA_DAO;
    private ArrayList subcategorias;
 
    public CategoriaController(){
        CATEGORIA_DAO = new CategoriaDao();
    }
    
    public String getCategoria(){
        return nombre;
    }
    
    public void setCategoria(String categoria){
        this.nombre = categoria;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public void setDescripcion(String des){
        this.descripcion = des;
    }
    
    public boolean getEsSubCategoria(){
        return esSubCategoria;
    }
    
    public void setEsSubCategoria(boolean esSubCategoria){
        this.esSubCategoria = esSubCategoria;
    }
    
    public void agregarCategoria()throws Exception{
        try{
            Categoria cat = new Categoria();
            cat.setNombre(nombre);
            cat.setDescripcion(descripcion);
            this.CATEGORIA_DAO.getEntityManager().getTransaction().begin();
            cat = this.CATEGORIA_DAO.update(cat);
            this.CATEGORIA_DAO.update(cat);
            //this.CATEGORIA_DAO.save(cat);
            this.CATEGORIA_DAO.getEntityManager().getTransaction().commit();
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Se ha creado la Categoria: "+cat.getNombre()+".",
                            "Se ha creado la Categoria: "+cat.getNombre()+"."));
        }catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Por el momento no podemos agregar una nueva Categoria al sistema, inténtelo más tarde.",
                            "Por el momento no podemos agregar una nueva Categoria al sistema, inténtelo más tarde."));

            
        }
    }
    
    
        /**
     * Método que verifica si la categoria ya eziste
     *
     * @param context Es el contexto del jsf.
     * @param component Es el componente que contiene el email.
     * @param value Es el valor del mail que se tiene.
     */
    public void validateCategoriaExist(FacesContext context, UIComponent component, Object value) {
        FacesMessage msg;
        
        if (CATEGORIA_DAO.categoriaExist((String) value)) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "La Categoría: "+value+" ya existe.",
                    "La Categoría: "+value+"  ya existe.");
            throw new ValidatorException(msg);
        }
    }
}
