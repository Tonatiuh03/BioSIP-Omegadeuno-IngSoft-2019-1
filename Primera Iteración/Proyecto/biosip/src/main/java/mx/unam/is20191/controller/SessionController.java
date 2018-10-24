/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import mx.unam.is20191.dao.UsuarioDao;
import mx.unam.is20191.models.Usuario;

/**
 * Clase que implementa operaciones útiles con la sesión.
 *
 * @author Josué Cárdenas
 */
@ManagedBean
@SessionScoped
public class SessionController implements Serializable{

    /**
     * Objeto en donde tendremos presente la información del usuario.
     */
    private Usuario usuario;


    /**
     * Método que obtiene al usuario que está loggeado.
     *
     * @return El usuario que está logueado.
     */
    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        }
        return usuario;
    }

    /**
     * Método para facilitar la validación de que si el usuario es
     * administrador.
     *
     * @return True si es administrador, falso en otro caso.
     */
    public boolean isAdmin() {
        return UsuarioDao.isAdmin(getUsuario());
    }

    /**
     * Método que facilita la validación de si el usuario es profesor.
     *
     * @return True si es profesor, falso en otro caso.
     */
    public boolean isProfesor() {
        return UsuarioDao.isProfesor(getUsuario());
    }
}
