package mx.unam.is20191.controller;

import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mx.unam.is20191.dao.UsuarioDao;
import mx.unam.is20191.models.Usuario;

@ManagedBean
@RequestScoped
public class LoginController {

    private Usuario usuario = new Usuario();

    private final UsuarioDao USUARIO_DAO = new UsuarioDao();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LoginController() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("es-Mx"));
    }

    public String loginUser() {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario y/o ´la contraseña son inválidos.", ""));
        return null;
    }

}
