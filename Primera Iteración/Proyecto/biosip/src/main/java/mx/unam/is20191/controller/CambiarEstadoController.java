/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.primefaces.model.UploadedFile;
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
import mx.unam.is20191.models.Material;
import mx.unam.is20191.utils.Config;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author NoraLuna
 */
public class CambiarEstadoController implements Serializable{
    
    private List<Material> listaMateriales;
    private boolean estado;

     /**
     * Atributo que contiene el nombre del material a registrar
     */
    private String nombre;
    /**
     * Atributo que contiene el numero de materiales
     */
    private int disponibles;

    /**
     * Atributo que contiene la descripcion del material a registrar
     */
    private String descripcion;
    
    /**
     * Es el atributo que contiene al archivo que se subira
     */
    private UploadedFile file;
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public UploadedFile getFile() {
        return file;
    }
    public List<Material> getListaMAteriales() {
        return listaMateriales;
    }

    public void setListaMateriales(List<Material> listaMateriales) {
        this.listaMateriales = listaMateriales;
    }
    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    /**
     * Método que elimina el archivo subido cuando se ingresa en la página de
     * regitro.
     */
    public void clear() {
        this.file = null;
        this.nombre = null;
        this.disponibles = 0;
        this.descripcion = null;
    }
    
    public void uploadImg(FileUploadEvent e) {
        this.file = e.getFile();
    }
    /**
     * Método para obtener la imagen del material, muestra la
     * default si no sube alguna o se muestra la que el usuario pase como
     * parámetro.
     *
     * @return La imagen que se despliega
     */
    public StreamedContent getImagestream() {
        try {
            if (file != null) {
                return new DefaultStreamedContent(file.getInputstream(), file.getContentType());
            } else {
                return new DefaultStreamedContent(new FileInputStream(new File(Config.IMG_MATERIAL_REPO_DEFAULT)), "png");
            }
        } catch (IOException ex) {
            return null;
        }
    }
    /**
     * Método que registra el material con el formulario ya validado.
     *
     */
    public void registerMaterial() {
        try {
            Material nuevoMaterial = new Material();
            nuevoMaterial.setNombre(nombre);
            nuevoMaterial.setDisponibles(disponibles);
            nuevoMaterial.setDescripcion(descripcion);
            if (this.file == null) {
                nuevoMaterial.setRutaImagen(Config.IMG_MATERIAL_REPO_DEFAULT_FILE_NAME);
            } else {
                file.write(Config.IMG_MATERIAL_REPO + nuevoMaterial.getRutaImagen());
            }
            MaterialDao materialDao = new MaterialDao();
            materialDao.getEntityManager().getTransaction().begin();
            nuevoMaterial = materialDao.update(nuevoMaterial);

            materialDao.save(nuevoMaterial);
            materialDao.getEntityManager().getTransaction().commit();
            
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Se ha registrado el material con éxito",
                            "Se ha registrado el material con éxito"));
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            ExternalContext eContext = context.getExternalContext();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Por el momento no podemos agregar su registro al sistema, inténtelo más tarde.",
                            "Por el momento no podemos agregar su registro al sistema, inténtelo más tarde."));
        }
    }
}
