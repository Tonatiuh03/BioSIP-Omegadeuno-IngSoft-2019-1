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
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.unam.is20191.dao.UsuarioDao;
import mx.unam.is20191.models.Perfil;
import mx.unam.is20191.models.Usuario;
import org.primefaces.model.StreamedContent;

@ManagedBean
@ViewScoped
public class AdminPerfilController {

    private Usuario usuarioObjetivo;

    private List<Perfil> perfiles;

    public Usuario getUsuarioObjetivo() {
        return usuarioObjetivo;
    }

    public void setUsuarioObjetivo(Usuario usuarioObjetivo) {
        this.usuarioObjetivo = usuarioObjetivo;
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    public List<Usuario> getUsuarios() {
        return new UsuarioDao().getAll();
    }

    public StreamedContent getListUserImage() {
        FacesContext context = FacesContext.getCurrentInstance();
        String id = context.getExternalContext().getRequestParameterMap().get("id");
        System.out.println(id);
        return new UsuarioDao().getByKey(Long.valueOf(id)).getImage();
    }

}
