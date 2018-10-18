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
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import mx.unam.is20191.dao.CategoriaDao;
import mx.unam.is20191.dao.SubcategoriaDao;
import mx.unam.is20191.models.Categoria;
import mx.unam.is20191.models.Subcategoria;

@ManagedBean
@ViewScoped
public class CategoriaController {

    private String nombre;
    private String descripcion;
    private boolean esSubcategoria;
    private Categoria categoria;

    public CategoriaController() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String des) {
        this.descripcion = des;
    }

    public boolean isEsSubcategoria() {
        return esSubcategoria;
    }

    public void setEsSubcategoria(boolean esSubcategoria) {
        this.esSubcategoria = esSubcategoria;
    }

    public List<Categoria> getCategorias() {
        CategoriaDao catdao = new CategoriaDao();
        return catdao.getCategorias();
    }

    /**
     * Méotodo que agrega una nueva Ctagoería a la base de datos. Y además
     * genera un mensaje informativo para notar al usuario que proceso se
     * realizó.
     *
     * @throws Exception
     */
    public void agregarCategoria() throws Exception {
        try {
            Categoria cat = new Categoria();
            cat.setNombre(nombre);
            cat.setDescripcion(descripcion);
            //this.CATEGORIA_DAO.getEntityManager().getTransaction().begin();
            //cat = this.CATEGORIA_DAO.update(cat);
            //this.CATEGORIA_DAO.update(cat);
            //this.CATEGORIA_DAO.save(cat);
            //this.CATEGORIA_DAO.getEntityManager().getTransaction().commit();
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Se ha creado la Categoria: " + cat.getNombre() + ".",
                            "Se ha creado la Categoria: " + cat.getNombre() + "."));

        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Por el momento no podemos agregar una nueva Categoria al sistema, inténtelo más tarde.",
                            "Por el momento no podemos agregar una nueva Categoria al sistema, inténtelo más tarde."));

        }
    }

    /**
     * Método que agrega una nueva Subcategoria a la base de datos. Y además
     * genera un mensaje informativo para notar al usuario que proceso se
     * realizó.
     *
     * @throws Exception
     */
    public void agregarSubcategoria() throws Exception {
        try {

            Subcategoria subcat = new Subcategoria();
            subcat.setNombre(nombre);
            subcat.setDescripcion(descripcion);
            subcat.setCategoriaId(new Categoria());
            //this.SUBCAT_DAO.getEntityManager().getTransaction().begin();
            //cat = this.SUBCAT_DAO.update(cat);
            //this.SUBCAT_DAO.update(cat);
            //this.SUBCAT_DAO.save(cat);
            //this.SUBCAT_DAO.getEntityManager().getTransaction().commit();
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Se ha creado la Subcategoria: " + subcat.getNombre() + ".",
                            "Se ha creado la Subcategoria: " + subcat.getNombre() + "."));
        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Por el momento no podemos agregar una nueva Subcategoria al sistema, inténtelo más tarde.",
                            "Por el momento no podemos agregar una nueva Subcategoria al sistema, inténtelo más tarde."));

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
        CategoriaDao catdao = new CategoriaDao();
        SubcategoriaDao subcatdao = new SubcategoriaDao();
        FacesMessage msg1;
        FacesMessage msg2;
        if (this.esSubcategoria == false) {
            if (catdao.categoriaExist((String) value)) {
                msg1 = new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "La Categoría: " + value + " ya existe.",
                        "La Categoría: " + value + "  ya existe.");
                throw new ValidatorException(msg1);
            }
        } else if (this.esSubcategoria == true) {
            if (subcatdao.categoriaExist((String) value)) {
                msg2 = new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "La Subcategoria: " + value + " ya existe.",
                        "La Subcategoria: " + value + "  ya existe.");
                throw new ValidatorException(msg2);
            }
        }

    }



    /*
    * Método que agrega una Categoria o una Subcategoria,
    * dependiendo de la bandera "esSubcategoria".
     */
    public void crear() throws Exception {
        if (this.esSubcategoria == false) {
            this.agregarCategoria();
        } else if (this.esSubcategoria == true) {
            this.agregarSubcategoria();
        }
    }
}
