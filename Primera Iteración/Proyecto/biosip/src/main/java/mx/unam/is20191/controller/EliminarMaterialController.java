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
import mx.unam.is20191.dao.PrestamoDao;
import mx.unam.is20191.dao.PrestamoMaterialDao;
import mx.unam.is20191.dao.UsuarioDao;
import mx.unam.is20191.models.Material;
import mx.unam.is20191.models.Prestamo;
import mx.unam.is20191.models.PrestamoMaterial;


@ManagedBean
@SessionScoped
public class EliminarMaterialController implements Serializable{

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

    public void eliminaMaterial(ActionEvent event) throws Exception {
        
        MaterialDao materialDao = new MaterialDao();
        Material material = (Material) event.getComponent().getAttributes().get("material");
        materialDao.getEntityManager().getTransaction().begin();
        material = materialDao.getByKey(material.getId());
        materialDao.delete(material);
        materialDao.getEntityManager().getTransaction().commit();

        List<Prestamo> prestamos = new ArrayList<Prestamo>();
        List<PrestamoMaterial> prestamosMateriales = new ArrayList<PrestamoMaterial>();

        PrestamoDao prestamoDao = new PrestamoDao();
        PrestamoMaterialDao prestamoMaterialDao = new PrestamoMaterialDao();
        prestamosMateriales = prestamoMaterialDao.getRegistros();
        prestamos = prestamoDao.getPrestamos();

        for (PrestamoMaterial pm : prestamosMateriales) {
            prestamoMaterialDao.getEntityManager().getTransaction().begin();
            if (pm.getMaterial().getId().equals(material.getId())) {
                prestamoMaterialDao.delete(pm);
                System.err.println("Se elimino un registro de los prestamos-materiales");
            }
            prestamoMaterialDao.getEntityManager().getTransaction().commit();
        }

        FacesContext.getCurrentInstance().addMessage("mensaje-agregar-material",
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Se ha eliminado el material: \"" + material.getNombre() + "\"",
                        "Material eliminado."));

    }


}
