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
import java.io.Serializable;
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
public class CategoriaController implements Serializable{

    private String nombre;
    private String descripcion;
    private boolean esSubcategoria;
    private Categoria categoria;
    private String tipo; 
    private boolean exito;
    
    public CategoriaController() {
        this.exito = false;
    }

    public String getTipo() {
        if(esSubcategoria == false){
            return "Categoría";
        }else{
            return "Subcategoría";
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

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }
    
    /**
     * Méotodo que agrega una nueva Ctagoería a la base de datos. Y además
     * genera un mensaje informativo para notar al usuario que proceso se
     * realizó.
     *
     * @throws Exception
     */
    public void agregarCategoria(CategoriaDao categoriaDao) throws Exception {
        try {
            Categoria cat = new Categoria();
            cat.setNombre(nombre);
            cat.setDescripcion(descripcion);
            categoriaDao.getEntityManager().getTransaction().begin();
            //cat = this.CATEGORIA_DAO.update(cat);
            //categoriaDao.update(cat);
            categoriaDao.save(cat);
            categoriaDao.getEntityManager().getTransaction().commit();
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
    public void agregarSubcategoria(SubcategoriaDao subdao) throws Exception {
        try {

            Subcategoria subcat = new Subcategoria();
            subcat.setNombre(nombre);
            subcat.setDescripcion(descripcion);
            subcat.setCategoriaId(categoria);
            subdao.getEntityManager().getTransaction().begin();
            //cat = this.SUBCAT_DAO.update(cat);
            //subdao.update(cat);
            subdao.save(subcat);
            subdao.getEntityManager().getTransaction().commit();
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
        if (this.esSubcategoria == false) {
            CategoriaDao catdao = new CategoriaDao();
            FacesMessage msg1;
            if (catdao.categoriaExist((String) value)) {
                msg1 = new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "La Categoría: " + value + " ya existe.",
                        "La Categoría: " + value + "  ya existe.");
                throw new ValidatorException(msg1);
            }
        } else if (this.esSubcategoria == true) {
            SubcategoriaDao subcatdao = new SubcategoriaDao();
            FacesMessage msg2;
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
            CategoriaDao cd = new CategoriaDao();
            this.agregarCategoria(cd);
        } else if (this.esSubcategoria == true) {
            SubcategoriaDao sd = new SubcategoriaDao();
            this.agregarSubcategoria(sd);
        }
        this.exito = true;
    }
    
    public void clear(){
        this.nombre = "";
        this.descripcion = "";
        this.esSubcategoria = false;
        this.categoria = null;
        this.exito = false;
    }
}
