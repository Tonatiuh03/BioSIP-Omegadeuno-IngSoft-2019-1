package mx.unam.is20191.controller;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mx.unam.is20191.dao.UsuarioDao;
import mx.unam.is20191.models.Usuario;
import mx.unam.is20191.utils.Password;

@ManagedBean
@ViewScoped
public class LoginController {

    private String userName, password;

    private final UsuarioDao USUARIO_DAO;

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
        USUARIO_DAO = new UsuarioDao();
    }

    public String loginUser() {
        Usuario u = this.USUARIO_DAO.searchByUserNameOrEmail(userName);
        try {
            if (u == null || !u.getPassword().equals(Password.encryptPassword(password))) {
                System.err.println(u + "\n" + password + "\n" + Password.encryptPassword(password) + "\n" + u.getValidado());
                FacesContext.getCurrentInstance().addMessage("messages",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario y/o la contraseña son inválidos.", ""));
            } else {
                if (u.getValidado()) {
                    if (u.getFechaDeDesbloqueo() == null) {
                        return "/faces/navegar.xhtml";
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
        } catch (NoSuchAlgorithmException ex) {
            FacesContext.getCurrentInstance().addMessage("messages",
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Por el momento no se puede iniciar sesiòn en el sistema, intèntelo màs tarde.", ""));
        }
        return null;
    }

}
