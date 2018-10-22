/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.controller;

/**
 *
 * @author fjtfj
 */
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import mx.unam.is20191.dao.KitDao;
import mx.unam.is20191.models.KitMaterial;
import mx.unam.is20191.models.Kit;


@ManagedBean
@ViewScoped
public class KitController {

    private String nombre;
    private String descripcion;
    private String tipo; 
    
    public KitController() {
    }

    public String getTipo() {
        return tipo;
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

    public List<Kit> getKits() {
        KitDao kitdao = new KitDao();
        return kitdao.getKits();
    }

    /**
     * Método que agrega un nuevo Kit a la base de datos. Y además
     * genera un mensaje informativo para notar al usuario que proceso se
     * realizó.
     *
     * @throws Exception
     */
    public void agregarKit(KitDao kitDao) throws Exception {
        try {
            KitMaterial kit = new KitMaterial();
            kit.setNombre(nombre);
            kit.setDescripcion(descripcion);
            kitDao.getEntityManager().getTransaction().begin();
            kitDao.save(kit);
            kitDao.getEntityManager().getTransaction().commit();
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Se ha creado el Kit: " + kit.getNombre() + ".",
                            "Se ha creado el Kit: " + kit.getNombre() + "."));

        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Por el momento no podemos agregar un nuevo Kit al sistema, inténtelo más tarde.",
                            "Por el momento no podemos agregar un nuevo Kit al sistema, inténtelo más tarde."));

        }
    }
}