/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.controller;

/**
 * Clase para bloquear usuarios
 *
 * @author NoraLuna
 */
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import mx.unam.is20191.dao.PerfilDao;
import mx.unam.is20191.dao.UsuarioDao;
import mx.unam.is20191.dao.PrestamoMaterialDao;
import mx.unam.is20191.models.Usuario;

@ManagedBean
@ViewScoped

public class BloquearController implements Serializable {
	//Lista que muestra a los usurarios disponibles
    ArrayList<Usuario> modificados = new ArrayList<>();
    //Varibable para bloquear usuario
    private Usuario usuarioBloquear;

    /*
    *Funcion que dado un usuario lo restringe de algunas actividades
    *@param Usuario usuario
    *
    */
    public void bloquearUsuario(Usuario usuario) {
        UsuarioDao usuarioDao = new UsuarioDao();
        usuarioDao.getEntityManager().getTransaction().begin();
        Usuario u = usuarioDao.getByKey(usuarioBloquear.getId());
        u.setBloqueado(1);//los valores en cuno están bloqueados
        usuarioDao.update(u);
        usuarioDao.getEntityManager().getTransaction().commit();
        StringBuilder mensaje = new StringBuilder("Se han cambiado los privilegios del usuario ");
        mensaje.append(usuarioBloquear.getUserName()).append(".");
        FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje.toString(), mensaje.toString()));
        usuarioBloquear = null;
    }

    /*
    *Funcion que nos dice si es valido el bloquear a un usuario
    */
    public String validarBloqueo() {
        ExternalContext facesContext = FacesContext.getCurrentInstance().getExternalContext();
        Usuario u = (Usuario) facesContext.getSessionMap().get("user");
        System.out.println("Usuario: " + u.getNombreCompleto());
        if (u.getBloqueado() == 0) {
            return ""; //con este solo te muestra la página normal 
        } else {
            StringBuilder mensaje = new StringBuilder("No tiene acceso a esta funcionalidad ");
            FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje.toString(), mensaje.toString()));
            return ""; 

        }
    }

//    public ArrayList<Usuario> getUsuarios() {
//        return usuarios;
//    }
//
    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    public ArrayList<Usuario> getModificados() {
        return modificados;
    }

    public void setModificados(ArrayList<Usuario> modificados) {
        this.modificados = modificados;
    }

    public Usuario getUsuarioBloquear() {
        return usuarioBloquear;
    }

    public void setUsuarioBloquear(Usuario usuarioBloquear) {
        this.usuarioBloquear = usuarioBloquear;
    }

}
