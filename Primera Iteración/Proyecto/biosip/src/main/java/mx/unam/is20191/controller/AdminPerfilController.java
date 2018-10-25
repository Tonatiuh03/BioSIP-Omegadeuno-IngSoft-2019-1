/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.controller;

/**
 *
 * @author Josué Rodrigo Cárdenas Vallarta
 */
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.unam.is20191.dao.PerfilDao;
import mx.unam.is20191.dao.UsuarioDao;
import mx.unam.is20191.models.Perfil;
import mx.unam.is20191.models.Usuario;

@ManagedBean
@ViewScoped
public class AdminPerfilController implements Serializable {
    
    private Usuario usuarioObjetivo;
    
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
    
    public void updateUsuarioObjetivoPerfiles() {
        this.usuarioObjetivoPerfiles = this.usuarioObjetivo == null
                ? null : this.usuarioObjetivo.getPerfilSet();
    }
    
    public void cambiarPerfiles() {
        UsuarioDao usuarioDao = new UsuarioDao();
        PerfilDao perfilDao = new PerfilDao();
        usuarioDao.getEntityManager().getTransaction().begin();
        Usuario u = usuarioDao.getByKey(usuarioObjetivo.getId());
        u.getPerfilSet().clear();
        for (Perfil p : usuarioObjetivoPerfiles) {
            System.err.println(u.getUserName());
            u.getPerfilSet().add(perfilDao.getByKey(p.getId()));
        }
        usuarioDao.save(u);
        usuarioDao.getEntityManager().getTransaction().commit();
    }
    
}
