/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import mx.unam.is20191.dao.UsuarioDao;
import mx.unam.is20191.models.Usuario;
import static mx.unam.is20191.utils.Config.IMG_PROFILE_REPO;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * Clase que implementa operaciones útiles con la sesión.
 *
 * @author Josué Cárdenas
 */
@ManagedBean
@SessionScoped
public class SessionController {

    /**
     * Objeto en donde tendremos presente la información del usuario.
     */
    private Usuario usuario;

    /**
     * Método que obtiene la imagen de perfil del usuario.
     *
     * @return La imagen de perfil del usuario .
     */
    public StreamedContent getImage() {
        try {
            String profilePicturePath = IMG_PROFILE_REPO + getUsuario().getRutaImagen();
            return new DefaultStreamedContent(new FileInputStream(new File(profilePicturePath)), "png");
        } catch (IOException ex) {
            return null;
        }
    }

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
