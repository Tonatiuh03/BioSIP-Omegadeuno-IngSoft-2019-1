/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.controller;

/**
 *
 * @author Francisco Fuentes
 */

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import mx.unam.is20191.dao.MaterialDao;
import mx.unam.is20191.models.Kit;
import mx.unam.is20191.models.KitMaterial;
import mx.unam.is20191.models.KitMaterialPK;
import mx.unam.is20191.models.Material;

public class KitController implements Serializable {
    
    private String nombre;
    private String descripcion;
    private String tipo; 
    private boolean exito;
    private boolean confirmarCompartir;
    private String nombreBtnAccion;
    private boolean esLab;
    
    public KitController() {
        this.exito = false;
    }
    
    public String getTipo() {
        if(esLab == false){
            return "Laboratorio";
        }else{
            return "Campo";
        }
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String des) {
        this.descripcion = des;
    }
    
    public boolean isconfirmarCompartir() {
        return confirmarCompartir;
    }

    public void setconfirmarCompartir(boolean confirmarCompartir) {
        this.confirmarCompartir = confirmarCompartir;
    }
    
}      
    
   