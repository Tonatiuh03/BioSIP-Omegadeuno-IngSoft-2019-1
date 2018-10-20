package mx.unam.is20191.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import mx.unam.is20191.dao.UsuarioDao;
import mx.unam.is20191.models.Usuario;
import mx.unam.is20191.utils.Config;
import mx.unam.is20191.utils.Password;

@ManagedBean
@ViewScoped
public class LoginController {

    private String userName, password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginController() {
    }

    /**
     * Método que inicia sesión a un usuario.
     *
     */
    public void loginUser() {
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario u = usuarioDao.searchByUserNameOrEmail(userName);
        try {
            if (u == null || !u.getPassword().equals(Password.encryptPassword(password))) {
                FacesContext.getCurrentInstance().addMessage("messages",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario y/o la contraseña son inválidos.", ""));
            } else {
                if (u.getValidado()) {
                    if (u.getFechaDeDesbloqueo() == null) {
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.getExternalContext().getSessionMap().put("user", u);
                        ExternalContext eContext = context.getExternalContext();
                        String redirect = (UsuarioDao.isAdmin(u) ? Config.ADM_PRINCIPAL_PAGE
                                : (UsuarioDao.isProfesor(u) ? Config.PROF_PRINCIPAL_PAGE : Config.USR_PRINCIPAL_PAGE));
                        eContext.redirect(eContext.getRequestContextPath() + redirect);
                    }
                    FacesContext.getCurrentInstance().addMessage("messages",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Lo sentimos, tiene un bloqueo hasta el "
                                    + u.getFechaDeDesbloqueo()
                                    + ", inténtelo después de la fecha mencionada.", ""));
                }
                FacesContext.getCurrentInstance().addMessage("messages",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario no está validado, ingrese en su correo y de click en el enlace de confirmaciòn.", ""));
            }
        } catch (NoSuchAlgorithmException | IOException ex) {
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Por el momento no se puede iniciar sesiòn en el sistema, intèntelo màs tarde.", ""));
        }
    }

    /**
     * Método que cierra la sesión de un usuario.
     */
    public void logoutUser() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Se ha cerrado la sesión con éxito, vuelva pronto.",
                            "Se ha cerrado la sesión con éxito, vuelva pronto."));
            context.getExternalContext().getFlash().setKeepMessages(true);
            ExternalContext eContext = context.getExternalContext();
            eContext.redirect(eContext.getRequestContextPath() + Config.LOGIN_PAGE);
        } catch (IOException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Ha ocurrido un problema con el cierre de sesión, inténtelo más tarde.",
                            "Ha ocurrido un problema con el cierre de sesión, inténtelo más tarde."));
        }
    }

}
