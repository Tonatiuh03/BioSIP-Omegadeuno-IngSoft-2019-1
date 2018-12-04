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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import mx.unam.is20191.dao.MaterialDao;
import mx.unam.is20191.dao.UsuarioDao;
import mx.unam.is20191.models.Material;

public class EliminarMaterialController {
    private boolean estado;
    private String nombreBtnAccion;
    private boolean exito;
    private String nombreMaterial;
    
    public String getNombreMaterial() {
        return nombreMaterial;
    }

    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }
   
    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public String getNombreBtnAccion() {
        if (this.estado) {
            this.nombreBtnAccion = "ContinÃºa Agregando";
        } else {
            this.nombreBtnAccion = "Confirma PrÃ©stamo";
        }
        return nombreBtnAccion;
    }

    public void setNombreBtnAccion(String nombreBtnAccion) {
        this.nombreBtnAccion = nombreBtnAccion;
    }

    public boolean hayMaterial(Material m) throws Exception {
        if (m.getDisponibles() != 0) {
            return true;
        } else {
            return false;
        }
    }
  
    public void eliminaMaterial(Material material){
            material.setNombre(null);
            material.setDisponibles(0);
            material.setDescripcion(null);
            material.setRutaImagen(null);
    }
    
}
