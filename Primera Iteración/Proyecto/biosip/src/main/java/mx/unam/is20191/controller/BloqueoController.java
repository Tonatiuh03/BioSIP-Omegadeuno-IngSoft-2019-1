/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.controller;

/**
 *
 * @author NoraLuna
 */
import java.io.Serializable;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import mx.unam.is20191.dao.UsuarioDao;
import mx.unam.is20191.models.Usuario;

@ManagedBean
@ViewScoped

public class BloqueoController implements Serializable {

    //Lista que muestra a los usurarios disponibles
    List<Usuario> modificados = new ArrayList<>();
    //Varibable para bloquear usuario
    private Usuario usuarioBloquear;
    private Date fecha;

    /**
     * Método que con el que se inicializan los valores que usará la vista La
     * anotación PostConstruct realiza el proceso mientras se rendiza la vista
     * en el navegador
     */
    @PostConstruct
    public void init() {
        UsuarioDao usuarioDao = new UsuarioDao();
        usuarioDao.getEntityManager().getTransaction().begin();
        modificados = usuarioDao.getAll();
        usuarioDao.getEntityManager().getTransaction().commit();
    }

    /*
    *Funcion que dado un usuario lo restringe de algunas actividades
    *@param Usuario usuario
    *
     */
    public void bloquearUsuario() {
        UsuarioDao usuarioDao = new UsuarioDao();
        usuarioDao.getEntityManager().getTransaction().begin();
        Usuario u = usuarioDao.getByKey(usuarioBloquear.getId());
        u.setFechaDeDesbloqueo(fecha);
        usuarioDao.update(u);
        usuarioDao.getEntityManager().getTransaction().commit();
        StringBuilder mensaje = new StringBuilder("Se ha bloqueado al usuario ");
        mensaje.append(usuarioBloquear.getUserName()).append(" con éxito.");
        FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje.toString(), mensaje.toString()));
        usuarioBloquear = null;
        this.fecha = null;
    }

    public void desbloquearUsuario() {
        UsuarioDao usuarioDao = new UsuarioDao();
        usuarioDao.getEntityManager().getTransaction().begin();
        Usuario u = usuarioDao.getByKey(usuarioBloquear.getId());
        u.setFechaDeDesbloqueo(null);
        usuarioDao.update(u);
        usuarioDao.getEntityManager().getTransaction().commit();
        StringBuilder mensaje = new StringBuilder("Se ha desbloqueado al usuario ");
        mensaje.append(usuarioBloquear.getUserName()).append(" con éxito.");
        FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje.toString(), mensaje.toString()));
        usuarioBloquear = null;
        this.fecha = null;
    }

    /*
    *Funcion que nos dice si es valido el bloquear a un usuario
     */
    public String validarBloqueo() {
        ExternalContext facesContext = FacesContext.getCurrentInstance().getExternalContext();
        Usuario u = (Usuario) facesContext.getSessionMap().get("user");
        System.out.println("Usuario: " + u.getNombreCompleto());
        if (u.getFechaDeDesbloqueo() != null) {
            return ""; //con este solo te muestra la página normal 
        } else {
            StringBuilder mensaje = new StringBuilder("No tiene acceso a esta funcionalidad ");
            FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje.toString(), mensaje.toString()));
            return "";
        }
    }

    public List<Usuario> getModificados() {
        return modificados;
    }

    public void setModificados(List<Usuario> modificados) {
        this.modificados = modificados;
    }

    public Usuario getUsuarioBloquear() {
        return usuarioBloquear;
    }

    public void setUsuarioBloquear(Usuario usuarioBloquear) {
        this.usuarioBloquear = usuarioBloquear;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
