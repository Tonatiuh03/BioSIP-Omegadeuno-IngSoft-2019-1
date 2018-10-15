package mx.unam.is20191.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import mx.unam.is20191.dao.KitDao;
import mx.unam.is20191.models.Kit;
import mx.unam.is20191.utils.Config;
import mx.unam.is20191.utils.Password;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class KitController {

    private String nombre, categoria, descripcion, material;

    private final KitDao KIT_DAO;

    private UploadedFile file;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = dmaterial;
    }

    
    public KitController() {
        KIT_DAO = new KitDao();
    }

    /**
     * Método que registra al Kit con el formulario ya validado.
     *
     * @return La página a la que vamos a redireccionar.
     */
    public String registerKit() throws Exception {

        try {
            Kit nuevoKit = new Kit();
            nuevoKit.setNombre(nombre);
            nuevoKit.setCategoria(categoria);
            nuevoKit.setDescripcion(descripcion);
            nuevoKit.setMaterial(material);
            
            this.KIT_DAO.getEntityManager().getTransaction().begin();
            this.KIT_DAO.save(nuevoKit);
            this.KIT_DAO.getEntityManager().getTransaction().commit();
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Se ha registrado el Kit con éxito, favor de revisar su correo para activarlo.",
                            "Se ha registrado el Kit con éxito, favor de revisar su correo para activarlo."));
        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Por el momento no podemos agregar su kit al sistema, inténtelo más tarde.",
                            "Por el momento no podemos agregar su kit al sistema, inténtelo más tarde."));

            return null;
        }
        return "/index.xhtml";
    }

    
    /**
     * Método que valida si un Kit ya está registrado en la base de datos,
     * manda error si se escribe un Kit que ya esté registrado.
     *
     * @param context Es el contexto del jsf.
     * @param component Es el componente que contiene el user name.
     * @param value Es el valor obtenido del componente que llama al validador,
     * es decir, el user name.
     */
    public void validateUniqueUserName(FacesContext context, UIComponent component, Object value) {
        if (Kit_DAO.userExist(value.toString())) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "El Kit ya existe.",
                    "El Kit ya existe.");
            throw new ValidatorException(msg);
        }
    }

    

    /**
     * Método que elimina el archivo subido cuando se ingresa en la página de
     * regitro.
     */
    public void clear() {
        this.nombre = null;
        this.categoria = null;
        this.descripcion = null;
        this.material = null;
    }

}
