package mx.unam.is20191.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.unam.is20191.dao.PerfilDao;
import mx.unam.is20191.dao.UsuarioDao;
import mx.unam.is20191.models.Perfil;
import mx.unam.is20191.models.Usuario;

/**
 * Clase que controla las acciones al momento de administrar perfiles.
 *
 * @author Josué Rodrigo Cárdenas Vallarta
 */
@ManagedBean
@ViewScoped
public class AdminPerfilController implements Serializable {

    /**
     * En este atributo se almacena al usuario que vamos a afectar sus permisos.
     */
    private Usuario usuarioObjetivo;

    /**
     * En este atributo obtendremos los nuevos permisos a guardar del usuario
     * objetivo.
     */
    private Set<Perfil> usuarioObjetivoPerfiles;

    public Usuario getUsuarioObjetivo() {
        return usuarioObjetivo;
    }

    public void setUsuarioObjetivo(Usuario usuarioObjetivo) {
        this.usuarioObjetivo = usuarioObjetivo;
    }

    public Set<Perfil> getUsuarioObjetivoPerfiles() {
        return usuarioObjetivoPerfiles;
    }

    public void setUsuarioObjetivoPerfiles(Set<Perfil> usuarioObjetivoPerfiles) {
        this.usuarioObjetivoPerfiles = usuarioObjetivoPerfiles;
    }

    public List<Usuario> getUsuarios() {
        return new UsuarioDao().getAll();
    }

    public List<Perfil> getPerfiles() {
        return new PerfilDao().getAll();
    }

    /**
     * Método que actualiza los perfiles del usuario seleccionado a partir de la
     * opción seleccionada en su campo.
     */
    public void updateUsuarioObjetivoPerfiles() {
        this.usuarioObjetivoPerfiles = this.usuarioObjetivo == null
                ? null : this.usuarioObjetivo.getPerfilSet();
    }

    /**
     * Método que cambia los perfiles de los usuarios.
     */
    public void cambiarPerfiles() {
        try {
            UsuarioDao usuarioDao = new UsuarioDao();
            PerfilDao perfilDao = new PerfilDao();
            usuarioDao.getEntityManager().getTransaction().begin();
            Usuario u = usuarioDao.getByKey(usuarioObjetivo.getId());
            u.getPerfilSet().clear();
            for (Perfil p : usuarioObjetivoPerfiles) {
                u.getPerfilSet().add(perfilDao.getByKey(p.getId()));
            }
            usuarioDao.update(u);
            usuarioDao.getEntityManager().getTransaction().commit();
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Se han cambiado los privilegios del usuario " + usuarioObjetivo.getUserName() + ".",
                            "Se han cambiado los privilegios del usuario " + usuarioObjetivo.getUserName() + "."));
            usuarioObjetivo = null;
            usuarioObjetivoPerfiles = null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Ha ocurrido un problema con la autorización, inténtelo más tarde.",
                            "Ha ocurrido un problema con la autorización, inténtelo más tarde."));
        }
    }

}
